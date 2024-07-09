import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.stream.IntStream;

public class MyColors {

    int x, y;
    int height = 40, width = 40;
    ColorButton[] list = new ColorButton[9];
    Board board;
    public MyColors(int x, int y, Board board) {
        this.x = x;
        this.y = y;
        this.board = board;
        list[0] = new ColorButton(x + 40,y,20, Color.BLACK);
        list[1] = new ColorButton(x + 70, y, 20, Color.WHITE);
        list[2] = new ColorButton(x + 100,y, 20, Color.RED);
        list[3] = new ColorButton(x + 40,y+30, 20, Color.BLUE);
        list[4] = new ColorButton(x+70,y+30, 20, Color.GREEN);
        list[5] = new ColorButton(x + 100,y+30, 20, Color.PINK);
        list[6] = new ColorButton(x + 40, y + 60, 20);
        list[7] = new ColorButton(x + 70, y + 60, 20);
        list[8] = new ColorButton(x + 100, y + 60, 20);
    }
    ColorButton stroke = new ColorButton(300,y+10, 30, Color.black);
    ColorButton fill = new ColorButton(300,(y+50), 30, Color.red);
    MyButton grad = new MyButton("Gradient", x+120, y, 60, 60, (new ImageIcon("src\\Gradient")).getImage(),
            (new ImageIcon("src\\Gradient_pressed")).getImage());

    public void drawGrid(Graphics g){
        g.drawImage(grad.getCurrent_image(), grad.x, grad.y, board);
        stroke.DrawButton(g);
        fill.DrawButton(g);

        for (int i = 0; i < 9; i+=3) {
            if(list[i].color == null) {g.setColor(Color.white); g.drawRect(x + 40, y+10*i, 20, 20);}
            else {list[i].DrawButton(g);}

            if(list[i+1].color == null){g.setColor(Color.white); g.drawRect(x + 70, y+10*i, 20, 20);}
            else {list[i+1].DrawButton(g);}

            if(list[i+2].color == null){g.setColor(Color.white); g.drawRect(x + 100, y+10*i, 20, 20);
            }else{list[i+2].DrawButton(g);}

        }
    }
    public void setStroke_FillColor(int x, int y) {
        Color color1;
        if(stroke.isPressed){
            System.out.println("clicked");
            stroke.setPressed(false);
            for (ColorButton colorButton : list) {
                if (colorButton.color != null && colorButton.isPressed) {
                    color1 = colorButton.color;
                    colorButton.setPressed(false);
                    stroke.color = color1;
                }
            }
        }
        else if(fill.isPressed){
            System.out.println("clicked2");
            fill.setPressed(false);
            for (ColorButton colorButton : list) {
                if (colorButton.color != null && colorButton.isPressed) {
                    color1 = colorButton.color;
                    colorButton.setPressed(false);
                    fill.color = color1;
                }
            }
        }
        else if(x >= this.x && x < (this.x+width) && y >= this.y && y < (this.y+height)){
            stroke.clicked();
        }
        else if(x >= this.x && x < (this.x+width) && y >= this.y+40 && y < (this.y+30+height)){
            fill.clicked();
        }
    }
    public void updateStroke_Fill(MouseEvent e) {
        //Checks if color block has a color and then updates the stroke/fill with that color
        //ignores if empty color block picked
        IntStream.range(0, list.length).filter(i -> list[i] != null).filter(i -> e.getX() >= list[i].x
                && e.getX() < (list[i].x + list[i].width) && e.getY() >= list[i].y &&
                e.getY() < (list[i].y + list[i].height)).forEach(i -> list[i].clicked());
        setStroke_FillColor(e.getX(), e.getY());
    }
}
