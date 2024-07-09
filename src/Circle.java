import java.awt.*;
import java.io.Serializable;

public class Circle extends Shapes {
        private int diameter;
        int stroke_size;

        int stroke_Diameter = 0;

        Circle(int diameter, Point location, Color C, Color strokeColor, int stroke_size)
        {   super(C, strokeColor);
            setDiameter(diameter);
            setLocation(location);
            setColor(C);

            this.stroke_size = stroke_size;
            stroke_Diameter = getDiameter() - stroke_size/2;
        }

        void setDiameter(int diameter) {
            this.diameter = Math.max(diameter, 1);
        }

        void setLocation(Point center) {
            super.center = center;
        }

        void setColor(Color color) {
            this.col = color;
        }
        /**
         *
         * @return returns the size of the circle
         */
        public int getDiameter()
        {
            return diameter;
        }

        Point getCenter()
        {
            return super.center;
        }


        public String toString(){
            return "Circle " + "\n" + diameter + " "+ center.x + " " + center.y + " " + col.getRed() + " " + col.getGreen() + " " + col.getBlue();
        }

        public void draw(Graphics g)
        {
            g.setColor(col);
            g.fillOval(getCenter().x - getDiameter()/2, getCenter().y - getDiameter()/2, getDiameter(), getDiameter());

            g.setColor(stroke_color);
            g.fillOval(getCenter().x - stroke_Diameter/2, getCenter().y - stroke_Diameter/2, stroke_Diameter, stroke_Diameter);
        }
    }
