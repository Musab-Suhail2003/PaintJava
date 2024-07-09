import java.awt.*;

public class Rectangle extends Shapes{
    int x, y, width, height;
    int stroke_size;
    public Rectangle(Color col, Point center, int width, int height, Color stroke_color, int stroke_size){
        super(col, stroke_color);
        this.center = center;
        this.height = height;
        this.width = width;
        this.stroke_size = stroke_size;
    }
    public void setDimen(int x, int y, int w, int h){
        this.x = x; this.y = y;
        width = w;
        height = h;
    }
    @Override
    public void draw(Graphics g) {
        g.setColor(stroke_color);
        g.fillRect(center.x, center.y, width, height);
        g.setColor(col);
        g.fillRect(center.x+stroke_size, center.y+stroke_size, width-stroke_size*2, height-stroke_size*2);
    }

    public String toString(){
        return "Rectangle "+ "\n" + col.getRed() + " " + col.getGreen() + " " + col.getBlue() + " "+ x + " "+ y + " " + width + " " + height;
    }
}
