
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;

/**
 * Team One Gomoku CSCE 320 - Spring 2015 3/16/2015 Java - JVM Sources:
 *
 * Revisions: 3/14/2015 - Class created by Karen Bullinger.
 */
public class LoginController {

    private LoginView view;
    private ClientModel model = new ClientModel(); //why do this and not just set model?
    private OutputStream outStream;
    private InputStream inStream;
    private DataInputStream dataIn;
    private DataOutputStream dataOut;
    private byte[] msg = new byte[1024];
    private String returnedMsg;
  

    /**
     * Gets username and password from view. Sends output stream to server
     * containing user and password for authentication.
     *
     * @param user
     * @param password
     * @return true if login is success, false if fail
     */
    public void setModel(ClientModel m) {
        this.model = m;
    }

    public void setView(LoginView v) {
        this.view = v;
    }

    /**
     * Sets login view to visible in frame.
     */
    public void initLoginView() {
        model.frame.setVisible(true); //makes frame visible
    }

    public void closeView(String u) {
        model.frame.updateView(u);
    }

    /**
     * Process login. Sends username and hashed password to server, waits for
     * success or fail message. If login successful, GUI transitions to lobby.
     * If fail, pop up appears alerting user.
     *
     * @param user
     * @param password
     * @return
     */
    public boolean login(String user, int password) {

        boolean waiting = true;
        user = user.toLowerCase();
        String info = "login " + user + " " + password;

        try {
            dataOut.write(info.getBytes());
            dataOut.flush();
            System.out.println("String sent: " + info);
        } catch (IOException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            while (waiting) {
                int len = dataIn.read(msg);

                if (len > 0) {
                    returnedMsg = new String(msg, 0, len);

                    if (returnedMsg.contains("success")) {
                        //  System.out.println("success");
                        waiting = false;
                        model.loginLobbyTrans(); /*if login successful, transition
                         //  to lobby view.*/
                        return true;
                    } else if (returnedMsg.contains("fail")) {
                        // System.out.println("Login failed.");
                        waiting = false;
                        view.displayErrorMessage("Username/password incorrect.");
                        return false;
                    } else {
                        System.out.println("i am here");
                    }

                }
            }
        } catch (IOException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    /**
     * Process registration. Sends username and hashed password to server, waits
     * for success or fail message. If login successful, GUI transitions to
     * lobby. If fail, pop up appears alerting user.
     *
     * @param user
     * @param password
     * @return
     */
    public boolean register(String user, int password) {

        boolean waiting = true;

       
        user = user.toLowerCase();
        String info = "register " + user + " " + password; //string to send to server


        try {
            dataOut.write(info.getBytes()); //send registration to server
            dataOut.flush();
        } catch (IOException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }

        while (waiting) {
            try {
                int len = dataIn.read(msg);

                if (len > 0) {
                    returnedMsg = new String(msg, 0, len);
                    String[] online = returnedMsg.split(" ");
                    if (online[0].contains("success")) {
                        waiting = false;
                        //System.out.println("Login success");
                        model.loginLobbyTrans();
                        
                        
                       // model.updateLobbyPlayers(online);
                        return true;
                    } else if (returnedMsg.contains("fail")) {
                        
                        view.displayErrorMessage("Registration failed.  Username taken.");

                        return false;
                    }

                }
                if (len < 0) { //no longer connected to Server -- how do we want to handle this?
                    return false;

                }
            } catch (IOException ex) {
                Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return false;
    }

    /**
     * Calls method in ClientModel to create new connection to server. Initiates
     * input and output streams.
     */
    public void newConnection() {

        try {
            //  System.out.println("New Connection.");
            model.newServerConnection(this, "127.0.0.1", 54321);
            //  System.out.println("new server connection");
            inStream = model.socket.getInputStream();
            dataIn = new DataInputStream(inStream);
            outStream = model.socket.getOutputStream();
            dataOut = new DataOutputStream(outStream);
        } catch (IOException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
