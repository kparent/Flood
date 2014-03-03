import java.awt.*;

///
/// A two-dimensional array of colors
///
public class UI {

    public class Button {
        int color;
        int x, y;
        int size;

        public Button(int x, int y, int s, int c)
        {
            this.x = x;
            this.y = y;
            size   = s;
            color  = c;
        }

        public boolean intersects(int i, int j)
        {
            return ((i >= x && i <= x + size) && (j >= y && j <= y + size));
        }
    }

    Button[] buttons;

    //
    // Board Constructor
    //
    public UI(int x, int size)
    {
        buttons = new Button[6];
        for(int i = 1; i <= 6; i++)
            buttons[i - 1] = new Button((x + 2) * size, i * 2 * size, size, i);
    }

    public int getNewColor(int mx, int my)
    {
        for(int i = 0; i < 6; i++)
            if(buttons[i].intersects(mx, my)) return buttons[i].color;

        return -1;
    }

    public void draw(Graphics2D g2d)
    {
        for(int i = 0; i < 6; i++) {
            Button b = buttons[i];
			g2d.setPaint(lookUpColor(0));
            g2d.drawRect(b.x - 1, b.y - 1, b.size + 1, b.size + 1);
			g2d.setPaint(lookUpColor(b.color));
            g2d.fillRect(b.x, b.y, b.size, b.size);
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
