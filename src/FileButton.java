import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.Stack;

public class FileButton extends JComponent {
    public int x;
    public int y;
    public int width;
    public int height;
    File file;
    Board board;

    public FileButton(int x, int y, int width, int height, File file, Board board) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.file = file;
        this.board = board;
    }
    private boolean pressed;


    public Boolean IsPressed()
    {
        return pressed;
    }

    public void SetPressed(boolean pressed)
    {
        this.pressed = pressed;
    }

    public boolean IsClicked(int x, int y)
    {
        if(x >= this.x && x <= this.x + width && y >= this.y && y <= this.y + height)
        {
            if(pressed){
                pressed = false;
                return false;
            }
            else {
                pressed = true;

                return true;
            }
        }
        return false;
    }


    public void draw(Graphics g){
        g.setColor(Color.black);
        g.setFont(new Font("abd", Font.BOLD, 15));
        g.drawRect(x,y, width, height);
        g.drawString(file.getName(), x+20, y+20);
    }
}
