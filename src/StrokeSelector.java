import javax.swing.*;
import java.awt.*;

public class StrokeSelector {

    Board board;
    Image increase = (new ImageIcon("src/Stroke_Increase.png")).getImage();
    Image decrease = (new ImageIcon("src/Stroke_Decrease.png")).getImage();

    StrokeButton increase_button;
    StrokeButton decrease_button;
    int stroke_Size = 4;
    int x, y;

    public StrokeSelector(int x, int y, Board board) {
        this.x = x;
        this.y = y;
        this.board = board;
        increase_button = new StrokeButton("Stroke++", x + 15, y - 20, 10, 10, increase, increase);
        decrease_button = new StrokeButton("Stroke--", x + 15, y - 8, 10, 10, decrease, decrease);
    }

    public int getStroke_Size() {
        return stroke_Size;
    }

    public void paint(Graphics g){
        increase_button.draw(g);
        decrease_button.draw(g);

        g.setColor(Color.black);
        g.setFont(new Font("asd", Font.BOLD, 15));
        g.drawString("Stroke", x-10, y-30);
        g.setFont(new Font("asd", Font.BOLD, 20));
        g.drawString(String.valueOf(stroke_Size), x-5, y);
    }

    public void IsClicked(int x, int y){
        if(x >= this.x+15 && x < this.x+25){
            if(increase_button.IsClicked(x,y)){
                if(stroke_Size<20) {
                    stroke_Size += 2;
                    System.out.println("Stroke Increased");
                }
            }
            else if(decrease_button.IsClicked(x,y)){
                if(stroke_Size>0){
                    stroke_Size-=2;
                    System.out.println("Stroke Decreased");
                }
            }
        }
    }
    private static class StrokeButton extends MyButton{

        public StrokeButton(String name, int x, int y, int width, int height, Image i_depressed, Image i_pressed) {
            super(name, x, y, width, height, i_depressed, i_pressed);
        }

        @Override
        public boolean IsClicked(int x, int y) {
            if(x >= this.x && x <= this.x + width && y >= this.y && y <= this.y + height) return true;
            return false;
        }
    }

    public void hovered(int x, int y, Graphics g){
        increase_button.hovered(x,y,g);
        decrease_button.hovered(x,y,g);
    }
}
