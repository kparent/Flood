import javax.swing.JFrame;

public class Flood extends JFrame {

	public Flood() {
        Game game = new Game(14, 14, 32);
        add(game);
        setTitle("Space Adventure");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(game.width, game.height);
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(false);
    }
	
    public static void main(String[] args) {
        new Flood();
    }
}        
