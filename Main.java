/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author Karen
 */
 import javax.swing.JFrame;

public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

             ClientModel model = new ClientModel();
             
             
             LobbyView lobbyView = new LobbyView();
             LoginView loginView = new LoginView();
             //GameView gameView = new GameView(model.gameHeight, model.gameWidth);
             model.createFrame(loginView, lobbyView);
             
             
             
             LoginController loginController = new LoginController();
             
             loginController.setModel(model);
             loginController.setView(loginView);
             loginView.setController(loginController);
             
             LobbyController lobbyController = new LobbyController();      
             lobbyController.setModel(model);
             lobbyView.setController(lobbyController);
             lobbyController.setLobbyView(lobbyView);

             model.setLoginController(loginController);
             model.setLobbyController(lobbyController);

             loginController.initLoginView();
              
    	    
    }
}
