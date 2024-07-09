import java.awt.*;

public class Triangle extends Shapes{
    int x1 ;
    int y1;
    int stroke_size;
    int width, height;

    int x2, x3, y2, y3;
    public Triangle(int x, int y, int height, int width,  Color col, Color stroke_color, int stroke_size){
        super(col, stroke_color);
        this.x1 = x;
        this.y1 = y;
        this.width = width;
        this.height = height;
        this.stroke_size = stroke_size;


    }

    public void updateWIDTH_HEIGHT(int x, int y){
        this.width = x1 - x;
        this.height = y1 -y;
    }

    @Override
    public void draw(Graphics g) {
        x2 = x1-width; x3 = x1+width; y2 = y1-height; y3 = y1-height;

        Line pen1 = new Line(stroke_color, x1, y1, x2, y2, stroke_color, stroke_size);
        Line pen2 = new Line(stroke_color, x2, y2, x3, y3, stroke_color, stroke_size);
        Line pen3 = new Line(stroke_color, x3, y3, x1, y1, stroke_color, stroke_size);
        pen1.draw(g);
        pen2.draw(g);
        pen3.draw(g);
        g.setColor(col);
        g.fillPolygon(new int[]{x1,x2,x3}, new int[]{y1,y2,y3}, 3);
    }
    public String toString(){
        return  "Triangle "+ "\n" + col.getRed() + " " + col.getGreen() + " " + col.getBlue() +" "+ x1 +" " + x2 + " " + x3 +" "+ y1 +" " + y2 + " " + y3;
    }
}
