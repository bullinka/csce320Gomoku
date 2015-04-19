
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
 */
public class LobbyController {
    private LobbyView lobby;
    private ClientModel model;

    public void setModel( ClientModel m){
        this.model = m;
    }
 
    private void playRandom(){
        }

    void updateOnlinePlayers(String[] online) {
        System.out.println( online instanceof String[]);
        lobby.updatePlayerList(online);
    }
    
    
}
