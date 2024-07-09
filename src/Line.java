import java.awt.*;

public class Line extends Shapes{
    int x1, y1;
    int stroke_size;
    public Line(Color col, int x, int y, int x1, int y1, Color stroke_color, int stroke_size) {
        super(col, stroke_color);
        center = new Point(x, y);
        this.x1 = x1; this.y1 = y1;
        this.stroke_size = stroke_size;
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(this.stroke_color);

        for (float i = 0; i < stroke_size; i+=0.5) {
            g.drawLine((int) (center.x-2 + i), (int) (center.y-2 + i), (int) (x1-2 + i), (int) (y1-2 + i));
        }
    }

    public void upDate(int x, int y){
        this.x1 = x; this.y1 = y;
    }
}
