import javax.swing.*;
import java.awt.*;

public class Grid extends Shapes{
    static final Image grid_image = (new ImageIcon("src/Grid.png").getImage());
    static int count = 1;
    static final MyButton button = new MyButton("Grid", 20, 750, 20, 20, grid_image, grid_image);

    static Grid gridInstance = null;

    Board board;

    private Grid(Board board) {
        super(Color.black, Color.BLACK);
        this.board = board;
    }

    public static Grid getInstance(Board board){
        if(gridInstance == null) gridInstance = new Grid(board);
        return gridInstance;
    }

    public void clicks(int x, int y){
        if(button.IsClicked(x,y)){
            if(count<64) count*=2;
            else count = 1;
            button.SetPressed(false);
        }
    }

    public void draw(Graphics g){
        g.setColor(stroke_color);

        int height = board.getHeight(); int width = board.getWidth();
        int space_x = (height+90)/count; int space_y = (width+70)/count;
        for (int i = 0; i < count; i++) {

            g.drawLine(i*space_x, 90, i*space_x, board.getHeight());
        }
        for (int i = 0; i < count; i++) {
            g.drawLine(70, i*space_y, board.getWidth(), i*space_y);
        }
    }

    public void buttonDraw(Graphics g){
        button.draw(g);
        int x = button.x+5; int y = button.y+18;
        g.setColor(Color.red);
        g.setFont(new Font("abc", Font.BOLD, 15));
        if(count == 1) g.drawString("OFF", x,y);
        else g.drawString(String.valueOf(count), x,y);
    }

    public void hovered(int x, int y, Graphics g){
        button.hovered(x, y, g);
    }
}
