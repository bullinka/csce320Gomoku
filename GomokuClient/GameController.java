
/**
 * Team One Gomoku CSCE 320 - Spring 2015 3/16/2015 Java - JVM Sources:
 *
 * Revisions: 3/14/2015 - Class created by Joseph Bowley.
 */
public class GameController {

    private GameBoard board;
    private GameAI ai;
    private ClientModel model;
    private GameView view;

    public void setModel(ClientModel m) {
        this.model = m;
    }

    public void setView(GameView v) {
        this.view = v;
    }

    void newGame() {
        System.out.println("NEW GAME METHOD");
        System.out.println(view);
        System.out.println( model.gameHeight +" "+ model.gameWidth);
        view = new GameView(model.gameHeight, model.gameWidth);
        view.setVisible(true);
    }
}
