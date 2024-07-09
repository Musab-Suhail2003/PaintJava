import java.awt.*;
import java.io.Serializable;

public abstract class Shapes implements Serializable {
    protected Point center;
    Color col;
    Color stroke_color;

    public Color getCol() {
        return col;
    }
    public void setCol(Color col) {
        this.col = col;
    }

    public Shapes(Color col, Color stroke_color){
        this.col = col;
        this.stroke_color = stroke_color;
    }
    public abstract void draw(Graphics g);
}

