import java.awt.*;
import java.util.LinkedList;

public class Free_Drawing extends Shapes{
    LinkedList<Integer> x_list = new LinkedList<>();
    LinkedList<Integer> y_list = new LinkedList<>();

    int stroke_size;
    public Free_Drawing(Color col, int x, int y, int stroke_size, Color stroke_Color) {
        super(col, stroke_Color);
        this.center = new Point(x,y);
        this.x_list.add(x);
        this.y_list.add(y);
        this.stroke_size = stroke_size;
    }

    public void updateBrush(int x, int y){
        x_list.add(x); y_list.add(y);
    }

    @Override
    public void draw(Graphics g) {
        for (int i = 0; i < x_list.size(); i++) {
            g.setColor(stroke_color);
            g.fillOval(x_list.get(i), y_list.get(i), stroke_size, stroke_size);
        }
    }
}
