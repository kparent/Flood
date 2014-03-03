import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Game extends JPanel implements ActionListener {
	
	int width, height;
    int rows, columns;
    int tileSize;

    Board board;
    UI    ui;
	
    Game(int r, int c, int s) {
        rows     = r;
        columns  = c;
        tileSize = s;

        width  = (columns + 4) * tileSize;
        height = (rows + 3) * tileSize;

        board = new Board(rows, columns, tileSize);
        ui    = new UI(columns, tileSize);

		addMouseListener(new MouseMonitor());
	}

	public void actionPerformed(ActionEvent e) {
		repaint();
	}

	public void paint(Graphics g) {
		super.paint(g);
		Graphics2D g2d = (Graphics2D) g;
		
        board.draw(g2d);
        ui.draw(g2d);
	}

	class MouseMonitor extends MouseAdapter {
		public void mousePressed(MouseEvent e) {}
		public void mouseReleased(MouseEvent e) {
            int newCol = ui.getNewColor(e.getX(), e.getY());
            if(newCol > 0) {
                board.changeFloodColor(newCol);
            }
            repaint();
        }

		public void mouseClicked(MouseEvent e) {}
	}
}
