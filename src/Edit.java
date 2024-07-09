import javax.swing.*;
import java.awt.*;
import java.util.EmptyStackException;

public class Edit extends MyButton{
    ImageIcon forward = new ImageIcon("src/EditMenu/forward.png");
    ImageIcon backward = new ImageIcon("src/EditMenu/backward.png");
    Board board;
    public Edit(int x, int y, int width, int height, Image i_depressed, Image i_pressed, Board board) {
        super("Edit" ,x, y, width, height, i_depressed, i_pressed);
        this.board = board;
    }

    @Override
    public boolean IsClicked(int x, int y) {
        boolean bool = super.IsClicked(x, y);
        if(IsPressed()){
            if((x > this.x-this.width - 20 && x < this.x ) && (y < 80)){
                redo();
            }else if ((x > this.x + this.width*2 - 30 && x < this.x + this.width*3) && (y < 80)){
                undo();
            }
        }
        return bool;
    }
    public void undo() {
        System.out.println("UNDO PRESSED");
        try{board.undoStack.push(board.shapes.pop());} catch (EmptyStackException ignored){ System.err.println("Nothing to Undo"); }
    }

    public void redo() {
        System.out.println("REDO PRESSED");
        try{board.shapes.push(board.undoStack.pop()); repaint();}catch (EmptyStackException ignored){ System.err.println("undo Stack is empty"); }
    }
    public void drawEditMenu(Graphics g){
        g.drawImage(this.GetImage(), this.x, this.y, this);
        if(this.IsPressed()){
            g.drawImage(this.forward.getImage(), this.x-this.width - 20, this.y, board);
            g.drawImage(this.backward.getImage(), this.x + this.width*2 - 30, this.y, board);

        }
    }

    @Override
    public void hovered(int x, int y, Graphics g) {
        if(!IsPressed()){
            String info = "REDO-->CTRL Z";
            String info2 = "UNDO-->CTRL Y";
            if(x >= this.x && x <= this.x + width && y >= this.y && y <= this.y + height) {
                g.setFont(new Font("123", Font.BOLD, 15));
                g.drawRect(this.x, this.y+height, 10, 6);
                g.drawString(info, this.x+4, this.y + 20 + height);
                g.drawString(info2, this.x+4, this.y + 35 + height);
            }
        }
    }
}
