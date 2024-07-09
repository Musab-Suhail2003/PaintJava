import java.awt.*;

public class Pentagram extends Shapes {
    int[] X = new int[10];
    int[] Y = new int[10];
    int x,y;
    Color button_color;
    int stroke;

    int R;

    public Pentagram(int x, int y, int R, Color col, Color strokeColor, int stroke){
        super(col, strokeColor);
        this.x = x; this.y = y;
        this.R = R;
        this.stroke = stroke;
    }

    public void updateR(int x2, int y2){
        this.R = (int) Math.sqrt(Math.pow(x2-x, 2)+Math.pow(y2-y, 2));
    }

    public void draw(Graphics graph){
        int r1 = (int)(R/2*Math.sin(Math.PI/5));
        int r2=(int)(r1*1.602*1.602);

        int[] X1 = new int[5], Y1 = new int[5];

        for (int i = 0; i < 10; i+=2) {
            X[i] = (int)(x+r1*Math.cos(Math.toRadians(90+i*72)));
            Y[i] = (int)(y+r1*Math.sin(Math.toRadians(90+i*72)));
            X[i+1] = (int)(x+r2*Math.cos(Math.toRadians(126+i*72)));
            Y[i+1] = (int)(y+r2*Math.sin(Math.toRadians(126+i*72)));
            X1[i/2] = (int)(x+r1*Math.cos(Math.toRadians(90+(i/2)*72)));
            Y1[i/2] = (int)(y+r1*Math.sin(Math.toRadians(90+(i/2)*72)));
        }


        graph.setColor(stroke_color);
        graph.fillPolygon(X,Y,10);
        graph.fillPolygon(X1,Y1,5);

        r1 = (int)((R-stroke*2.5)/2*Math.sin(Math.PI/5));
        r2=(int)(r1*1.602*1.602);
        for (int i = 0; i < 10; i+=2) {
            X[i] = (int)(x+r1*Math.cos(Math.toRadians(90+i*72)));
            Y[i] = (int)(y+r1*Math.sin(Math.toRadians(90+i*72)));
            X[i+1] = (int)(x+r2*Math.cos(Math.toRadians(126+i*72)));
            Y[i+1] = (int)(y+r2*Math.sin(Math.toRadians(126+i*72)));
            X1[i/2] = (int)(x+r1*Math.cos(Math.toRadians(90+(i/2)*72)));
            Y1[i/2] = (int)(y+r1*Math.sin(Math.toRadians(90+(i/2)*72)));
        }
        graph.setColor(col);
        graph.fillPolygon(X,Y,10);
        graph.fillPolygon(X1,Y1,5);

    }
}
