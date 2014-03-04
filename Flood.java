import javax.swing.JFrame;
import java.awt.event.WindowEvent;

public class Flood extends JFrame {

    Game  game;

	public Flood()
    {
        game = new Game(14, 14, 32);
        add(game);
        setTitle("Space Adventure");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(game.width, game.height);
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(false);
    }

    public void processWindowEvent(WindowEvent e)
    {
        if(e.getID() == WindowEvent.WINDOW_CLOSING) {
            game.printMetrics();
            System.exit(0);
        }
    }
	
    public static void main(String[] args)
    {
        new Flood();
    }
}        
