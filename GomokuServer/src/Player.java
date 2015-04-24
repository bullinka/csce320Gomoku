//package gomokuserver;



import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.SocketException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author PLUCSCE
 */
public class Player implements Runnable {
    //fields
    private final String REGISTER = "register";
    private final String LOGIN = "login";
    private final String CHALLENGE = "challenge";
    private final String ACCEPT = "accept";
    private final String REJECT = "reject";
    private final String SUCCESS = "success";
    private final String FAIL = "fail";
    private final String ONLINE = "online";

    private String username;
    private final Socket socket;
    //private final OutputStream out;
    //private final InputStream in;
    private final DataInputStream in;
    private final DataOutputStream out;
    private final byte[] b;
    private Thread worker;
    private final ServerController controller;
    private InetAddress IP;
    private String msg;

    /**
     * Constructs the player class
     *
     * @param s
     * @param o
     * @param i
     * @param cont
     */
    public Player(Socket s, DataOutputStream o, DataInputStream i, ServerController cont) {
        //IP = ipaddress;
        socket = s;
        out = o;
        in = i;
        b = new byte[1024];
        controller = cont;
        //getMessages();
    }

    public void addToMatchMaking(String username, Player p){
        controller.addToMatchMaking(username, p);
    }
    /**
     * Sets username
     *
     * @param user
     */
    public void setUsername(String user) {
        username = user;
    }

    /**
     * Returns the username of the player as a string
     *
     * @return
     */
    public String getUsername() {
        return username;
    }

    /**
     * Sends a message back to the client.
     *
     * @param message
     */
    public void sendMessage(String message) {
        byte[] msg = message.getBytes();


        try {
            out.write(msg);
            //System.out.println(new String(msg));
        } catch (IOException ex) {
            Logger.getLogger(Player.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Receives messages using a new thread through use of the run method from
     * Runnable
     */
    @Override
    @SuppressWarnings("UnusedAssignment")
    public void run() {
        boolean connected = true;
        try {
            while (connected) {
                int len = in.read(b);

                //len will be -1 if disconnected from the server
                if (len > 0) {
                    msg = new String(b, 0, len);
                    //System.out.println(msg);
                    sendMessage(processMessage(msg));
                } else {

                    //closes the socket
                    connected = false;

                }
            }
        } catch (SocketException e) {
            connected = false;
        } catch (NullPointerException | IOException e) {
            //Logger.getLogger(Player.class.getName()).log(Level.SEVERE, null, e);
            connected = false;
        }
        controller.removePlayer(this);
        // }
        //controller.removeConnection(this);
    }

    /**
     * starts a new thread and executes the run() method
     */
    public void getMessages() {
        worker = new Thread(this);
        worker.start();
    }

    /**
     * Takes in the messages sent from the client, parses them, then registers or logs in the player
     * @param msg
     * @return
     */
    public String processMessage(String msg) {
    	    System.out.println(msg);
        //String msg = removeFormattingCharacters(mssg); 
        String[] message = msg.split(" ");
        if (message[0].equals(REGISTER)) {
            
            if (controller.registerPlayer(message[1], message[2])) {
                controller.addPlayer(username = message[1]);
                addToMatchMaking(message[1], this);
                //System.out.println(username);
                return SUCCESS + " " + controller.getAllUserNames();
            } else {
                return FAIL;
            }
        } else if (message[0].equals(LOGIN)) {
            controller.sendMessageToAll(ONLINE + " " + controller.getAllUserNames() + " " + message[1]);
            if (controller.loginPlayer(message[1], message[2])) {
                controller.addPlayer(username = message[1]);
                addToMatchMaking(message[1], this);
                return SUCCESS + " " + controller.getAllUserNames() ;
            } else {
                return FAIL;
            }
        } else if (message[0].equals(CHALLENGE)) {
           controller.sendChallenge(message[1], message[0]+" "+message[2]);
           return "";
        } else if (message[0].equals(ACCEPT)) {
           controller.sendResponse(message[1], message[0]+ " "+message[2] + " " + controller.getUsersIPAddress(message[2]));
           return "";
        } else if (message[0].equals(REJECT)) {
            controller.sendResponse(message[1], message[0]+ " "+message[2]);
            return "";
        }
        //MYCODE
        
        return FAIL;
    }
    
    public String getRemoteIPAddress()
    {
        return socket.getRemoteSocketAddress().toString();
    }
    /*
    public String removeFormattingCharacters(final String toBeEscaped) {
        StringBuilder escapedBuffer = new StringBuilder();
        for (int i = 0; i < toBeEscaped.length(); i++) {
            if ((toBeEscaped.charAt(i) != '\n') && (toBeEscaped.charAt(i) != '\r') && (toBeEscaped.charAt(i) != '\t')) {
                escapedBuffer.append(toBeEscaped.charAt(i));
            }
        }
        String s = escapedBuffer.toString();
        return s;//
        // Strings.replaceSubString(s, "\"", "")
    }
    */
}
