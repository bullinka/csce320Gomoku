
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 * Team One Gomoku CSCE 320 - Spring 2015 3/16/2015 Java - JVM Sources:
 *
 * Revisions: 3/14/2015 - Class created by Karen Bullinger.
 */
public class LobbyController implements Runnable {

    private LobbyView lobby;
    private ClientModel model;
    private OutputStream outStream;
    private InputStream inStream;
    private DataInputStream dataIn;
    private DataOutputStream dataOut;
    private Thread worker;
    private byte[] msg = new byte[1024];
    private String receivedMsg;
    //Message processing constants
    final private String ONLINE = "online";
    final private String CHALLENGE = "challenge";
    final private String STATS = "stats";
    final private String ACCEPT = "accept";
    final private String REJECT = "reject";

    /**
     *
     * @param m
     */
    public void setModel(ClientModel m) {
        this.model = m;
    }

    /**
     *
     * @param v
     */
    public void setLobbyView(LobbyView v) {

        this.lobby = v;

    }

    /**
     *
     */
    private void playRandom() {
    }

    /**
     *
     * @param online
     */
    public void updateOnlinePlayers(String[] online) {
        System.out.println(lobby instanceof LobbyView);
        lobby.updateOnlinePlayerList(online);
    }

    /**
     *
     */
    public void setupIOStreams() {
        try {
            inStream = model.socket.getInputStream();
            dataIn = new DataInputStream(inStream);
            outStream = model.socket.getOutputStream();
            dataOut = new DataOutputStream(outStream);
            worker = new Thread(this);
            worker.start();


        } catch (IOException ex) {
            Logger.getLogger(LobbyController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    // will listen for messages from server.
    @Override
    public void run() {
        while (true) {
            try {
                int len = dataIn.read(msg);

                if (len > 0) {
                    receivedMsg = new String(msg, 0, len);
                    String[] msgArray;
                    System.out.println("Messages from server: " + receivedMsg);
                    msgArray = receivedMsg.split("[ ]+");

                    processMessage(msgArray);
                }
            } catch (IOException ex) {
                Logger.getLogger(LobbyController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private void processMessage(String[] msg) {
        String temp = msg[0];
        String name = msg[1];
        switch (temp) {
            case CHALLENGE:
                challengeReceived(name);
                break;
            case ACCEPT:
                System.out.println("Challenge accepted.");
                challengeAccepted(msg[2]);
                break;
            case REJECT:
                rejectChallenge(name);
                break;
            case ONLINE:
                lobby.updateOnlinePlayerList(msg);
                break;
            default:
                break;
        }
    }

    private void challengeReceived(String s) {
        lobby.updateReceivedList(s);
    }

    private void challengeAccepted(String m) {
        model.connectToOpponent(m);
    }

    private void rejectChallenge(String rej) {

        lobby.removeFromSentChallenges(rej);
    }

    public void sendChallenge(String challengee) {
        try {
            String send = CHALLENGE + " " + challengee + " " + model.username;
            dataOut.write(send.getBytes());
            dataOut.flush();
        } catch (IOException ex) {
            Logger.getLogger(LobbyController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void sendAcceptResponse(String challengee) {
        try {
            String accept = ACCEPT + " " + challengee + " " + model.username;
            dataOut.write(accept.getBytes());
            dataOut.flush();
            System.out.println("before lobby transition");
            model.lobbyGameTrans();
            System.out.println("about to make new challenge game");
            newChallengeGame();
        } catch (IOException ex) {
            Logger.getLogger(LobbyController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void sendRejectResponse(String challengee) {
        try {
            String reject = REJECT + " " + challengee + " " + model.username;
            dataOut.write(reject.getBytes());
            dataOut.flush();

        } catch (IOException ex) {
            Logger.getLogger(LobbyController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void newChallengeGame() {
        model.openGameConnection();
    }
}
