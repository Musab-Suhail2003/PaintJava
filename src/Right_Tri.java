import java.awt.*;

public class Right_Tri extends Triangle{
    public Right_Tri(int x, int y, int height, int width, Color col, Color stroke_color, int stroke_size) {
        super(x, y, height, width, col, stroke_color, stroke_size);
    }

    @Override
    public void draw(Graphics g) {
        x2 = x1-width; x3 = x1; y2 = y1; y3 = y1-height;

        Line pen1 = new Line(stroke_color, x1, y1, x2, y2, stroke_color, stroke_size);
        Line pen2 = new Line(stroke_color, x2, y2, x3, y3, stroke_color, stroke_size);
        Line pen3 = new Line(stroke_color, x3, y3, x1, y1, stroke_color, stroke_size);
        pen1.draw(g);
        pen2.draw(g);
        pen3.draw(g);
        g.setColor(col);
        g.fillPolygon(new int[]{x1,x2,x3}, new int[]{y1,y2,y3}, 3);
    }
}
