import java.awt.*;
import java.util.Random;

///
/// A two-dimensional array of colors
///
public class Board {

    public class Node {
        int color;
        boolean mutable;

        public Node(int c)
        {
            color   = c;
            mutable = true;
        }
    }

    int rows, columns, size;
    Node[][] nodes;

    //
    // Board Constructor
    //
    public Board(int w, int h, int s)
    {
        columns = w;
        rows    = h;
        size    = s;

        nodes = new Node[w][h];
        // Loop through and initialize each row
        for(int i = 0; i < w; i++)
            for(int j = 0; j < h; j++)
                nodes[i][j] = new Node(1 + (int)(Math.random() * 5));
    }

    //
    // Change every square in the flooded list to the specified color
    //
    public void changeFloodColor(int col)
    {
        for (int i = 0; i < columns; i++) {
            for (int j = 0; j < rows; j++) {
                nodes[i][j].mutable = true;
            }
        }
        changeIndexColor(0, 0, col);
    }

    public void changeIndexColor(int x, int y, int col)
    {
        nodes[x][y].mutable = false;
        int oldCol = nodes[x][y].color;
        if(canChange(x-1, y, oldCol)) changeIndexColor(x - 1, y, col);
        if(canChange(x+1, y, oldCol)) changeIndexColor(x + 1, y, col);
        if(canChange(x, y-1, oldCol)) changeIndexColor(x, y - 1, col);
        if(canChange(x, y+1, oldCol)) changeIndexColor(x, y + 1, col);
        nodes[x][y].color = col;
    }

    public boolean canChange(int x, int y, int col)
    {
        if(!(x >= 0 && x < columns)) return false;
        if(!(y >= 0 && y < rows))    return false;
        if(!nodes[x][y].mutable)     return false;
        if(nodes[x][y].color != col) return false;

        return true;
    }

    //
    // Print out the 2D array to look like a square board
    //
    public void draw(Graphics2D g2d)
    {
        g2d.setPaint(lookUpColor(0));
        g2d.drawRect(size - 1, size - 1, (columns * size) + 1, (rows * size) + 1);
        for (int i = 0; i < columns; i++) {
            for (int j = 0; j < rows; j++) {
				g2d.setPaint(lookUpColor(nodes[i][j].color));
				g2d.fillRect((i + 1) * size, (j + 1) * size, size, size);
            }
        }
    }

    public Color lookUpColor(int c)
    {
        switch(c) {
            case 0:
                return new Color(0, 0, 0);
            case 1:
                return new Color(255, 0, 0);
            case 2:
                return new Color(0, 255, 0);
            case 3:
                return new Color(0, 0, 255);
            case 4:
                return new Color(255, 255, 0);
            case 5:
                return new Color(0, 255, 255);
            default:
                return new Color(255, 0, 255);
        }
    }
}
