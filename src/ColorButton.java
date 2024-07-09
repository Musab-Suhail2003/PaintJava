import java.awt.*;

public class ColorButton {
    Color color = null;
    int x, y;
    int width = 30;
    int height = 30;
    boolean isPressed = false;
    public ColorButton(int x, int y, int l){
        this.x = x;
        this.y = y;
        this.height = l;
        this.width = l;
    }
    public ColorButton(int x, int y, int l, Color color){
        this.x = x;
        this.y = y;
        this.width = l;
        this.height = l;
        this.color = color;
    }
    public void DrawButton(Graphics g){
        if(!isPressed){
            g.setColor(color);
            g.fillRect(x, y, width, height);
        }
        else{
            g.setColor(color);
            g.fillRect(x, y, width, height);
            g.setColor(Color.green);
            g.drawRect(x-2, y-2, width+2, height+2);
        }
    }

    public void setColor(Color color){
        this.color = color;
    }
    public Color getColor(){
        return this.color;
    }
    public void setPressed(boolean bool){
        this.isPressed = bool;
    }
    public void clicked(){
        setPressed(!isPressed);
    }

    public void hovered(int x, int y, Graphics g){
        String name = color.getRed() + ", " + color.getGreen() + ", " + color.getBlue();
        if(x >= this.x && x <= this.x + width && y >= this.y && y <= this.y + height) {
            g.setColor(Color.blue);
            g.setFont(new Font("123", Font.BOLD, 15));
            g.drawRect(this.x, this.y, 20, 6);
            g.drawString(name, this.x+4, this.y+3);
        }
    }

}
