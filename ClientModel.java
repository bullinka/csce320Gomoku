
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;

/**
 * Team One
 * Gomoku
 * CSCE 320 - Spring 2015
 * 3/16/2015
 * Java - JVM
 * Sources:
 * 
 * Revisions:
 * 3/14/2015 - Class created by Karen Bullinger.
 * 4/5/2015 - Added createFrame() method.
 * 4/11/2015 - Updated newServerConnection to return boolean
 */
public class ClientModel {
    private LeaderboardController leaderboardController;
    private LobbyController lobbyController = new LobbyController(); //why isn't set LobbyController working?
    private LoginController loginController;
    public Socket socket;
    static frame frame;
   
    /**
     * Associates ClientModel and LoginController.  Opens new socket to
     * specified host and port.
     * @param cont
     * @param host
     * @param port 
     */
  public void createFrame(){
      frame  = new frame();
  } 
   /**
    * Creates new socket connection between client and server. Returns true if
    * socket is created.
    * @param cont
    * @param host
    * @param port 
    */
  public boolean newServerConnection(LoginController cont, String host, int port) {
        this.loginController = cont;
        try {
            socket = new Socket(host, port);
            
        } catch (UnknownHostException ex) {
            return false;
        } catch (IOException ex) {
            Logger.getLogger(ClientModel.class.getName()).log(Level.SEVERE, null, ex);
          return false;

        }
        return true;
       
    }
  /**
   * Associates model with lobbyController.
   * @param cont 
   */
  public void setLobbyController(LobbyController cont){
      this.lobbyController =  cont;// **see line 26
  }
  /**
   * Associates model with loginController.
   * @param cont 
   */
   public void setLoginController(LoginController cont){
      this.loginController =  cont;
 
  }
 
  public void loginLobbyTrans(){

      loginController.closeView("lobby");
      
  }

    void updateLobbyPlayers(String[] online) {
        lobbyController.updateOnlinePlayers(online);
    }
  


  
}
