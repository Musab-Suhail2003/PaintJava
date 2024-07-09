import java.awt.*;

public class Hexagon extends Shapes{
    int[] X = new int[6];
    int[] Y = new int[6];

    int[] X1 = new int[6], Y1 = new int[6];
    int x,y;
    int R;
    int stroke_size;

    public Hexagon(int x, int y, int R, Color color, Color strokeColor, int stroke_size) {
        super(color, strokeColor);
        this.x = x;
        this.y = y;
        this.R = R;
        this.stroke_size = stroke_size;
    }
    public void updateR(int x2, int y2){
        R = (int) Math.sqrt(Math.pow(x2-x, 2)+Math.pow(y2-y, 2));
    }


    public void draw(Graphics graph){
        int r1 = (int) (R * Math.sin(Math.PI / 6));

        for (int i = 0; i < 6; i += 1) {
            X[i] = (int) (x + r1 * Math.cos(Math.toRadians(60 + i * 60)));
            Y[i] = (int) (y + r1 * Math.sin(Math.toRadians(60 + i * 60)));
            X1[i] = (int) (x + (r1 - stroke_size) * Math.cos(Math.toRadians(60 + i * 60))) ;
            Y1[i] = (int) (y + (r1 - stroke_size) * Math.sin(Math.toRadians(60 + i * 60))) ;
        }

        graph.setColor(stroke_color);
        graph.fillPolygon(X, Y, 6);
        graph.setColor(col);
        graph.fillPolygon(X1, Y1,6);
    }
}
