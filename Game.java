import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.PrintWriter;
import java.io.IOException;
//import java.System;

public class Game extends JPanel implements ActionListener {
	
	int width, height;
    int rows, columns;
    int tileSize;

    int draws, updates;
    long drawNanoSeconds, updateNanoSeconds;

    Board board;
    UI    ui;
	
    Game(int r, int c, int s) {
        rows     = r;
        columns  = c;
        tileSize = s;

        width  = (columns + 4) * tileSize;
        height = (rows + 3) * tileSize;

        draws   = 0;
        updates = 0;
        drawNanoSeconds   = 0;
        updateNanoSeconds = 0;

        board = new Board(rows, columns, tileSize);
        ui    = new UI(columns, tileSize);

		addMouseListener(new MouseMonitor());
	}

	public void actionPerformed(ActionEvent e) {
		repaint();
	}

	public void paint(Graphics g) {
        long startTime = System.nanoTime();

		super.paint(g);
		Graphics2D g2d = (Graphics2D) g;
		
        board.draw(g2d);
        ui.draw(g2d);

        long dt = System.nanoTime() - startTime;
        drawNanoSeconds += dt;
        draws++;
	}

	class MouseMonitor extends MouseAdapter {
		public void mousePressed(MouseEvent e) {}
		public void mouseReleased(MouseEvent e) {
            int newCol = ui.getNewColor(e.getX(), e.getY());
            if(newCol > 0) {
                long startTime = System.nanoTime();

                board.changeFloodColor(newCol);

                long dt = System.nanoTime() - startTime;
                updateNanoSeconds += dt;
                updates++;
            }
            repaint();
        }

		public void mouseClicked(MouseEvent e) {}
	}

    public void printMetrics()
    {
        PrintWriter w = null;
        try {
            w = new PrintWriter("metrics.txt", "UTF-8");
            double avgDrawTime = ((double)drawNanoSeconds / Math.pow(10, 9)) / draws;
            double avgUpdateTime = ((double)updateNanoSeconds / Math.pow(10, 9)) / updates;
            w.println("Avg Draw Time: " + Double.toString(avgDrawTime));
            w.println("Avg Update Time: " + Double.toString(avgUpdateTime));
        } catch (IOException ex) {} finally {
            try { w.close(); } catch (Exception ex) {}
        }
    }
}
