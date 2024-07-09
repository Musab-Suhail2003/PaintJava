import javax.swing.*;
import java.awt.*;
import java.util.LinkedList;
import java.util.Stack;

public class LayersLogic {
    Board board;
    LinkedList<Stack<Shapes>> data_layers, undoStack_layers;
    int current = 0;


    LayerButton add = new LayerButton("add layer",0, 240, 30, 30, (new ImageIcon("src/LayerTools/AddLayer.png")).getImage(),
            (new ImageIcon("src/LayerTools/AddLayer_press.png")).getImage());
    LayerButton down = new LayerButton("layer down",35, 240, 30, 30, (new ImageIcon("src/LayerTools/moveDownLayer.png")).getImage(),
            (new ImageIcon("src/LayerTools/moveDownLayer_press.png")).getImage());
    LayerButton up = new LayerButton("layer up",0, 275, 30, 30, (new ImageIcon("src/LayerTools/moveUpLayer.png")).getImage(),
            (new ImageIcon("src/LayerTools/moveUpLayer_press.png")).getImage());
    LayerButton remove = new LayerButton("remove layer",35, 275, 30, 30, (new ImageIcon("src/LayerTools/RemoveLayer.png")).getImage(),
            (new ImageIcon("src/LayerTools/RemoveLayer_press.png")).getImage());


    public LayersLogic(Board board){
        this.board = board;
        this.data_layers = board.mylist.getData_layers();
        this.undoStack_layers = board.mylist.getUndoStack_layers();
        if(data_layers.size() == 0) {
            data_layers.add(new Stack<>());
            undoStack_layers.add(new Stack<>());
        }
    }

    public Stack<Shapes> getUndoStack(){
        try{return undoStack_layers.get(current);}catch (IndexOutOfBoundsException ignored){undoStack_layers.add(new Stack<>());}
        return undoStack_layers.get(current);
    }

    public void resetUndoStack(){
        undoStack_layers.set(current, new Stack<>());
    }

    public void addLayer(){

        if(data_layers.size() < 12){
            data_layers.add(new Stack<>());
            undoStack_layers.add(new Stack<>());
            current++;
            drawLayer(board.getGraphics());
        }else System.err.println("Reached Limit for layers. Only 12 layers allowed");
    }

    public void removeLayer(){
        data_layers.remove(current);
        undoStack_layers.remove(current);
        if(data_layers.size() == 0 || undoStack_layers.size() == 0) {
            data_layers.add(new Stack<>());
            undoStack_layers.add(new Stack<>());
            System.err.println("Requires At least one layer hence layer 1 replaced");
        }
        drawLayer(board.getGraphics());
    }
    public Stack<Shapes> getBoard(){

        try{return data_layers.get(current);}
        catch (IndexOutOfBoundsException ex){ current = 0;}
        try{return data_layers.get(current);}catch (IndexOutOfBoundsException ex){data_layers.add(new Stack<>()); undoStack_layers.add(new Stack<>());}
        return data_layers.get(current);
    }
    public Stack<Shapes> getPrevious(){
        if(current - 1 < 0) current = data_layers.size()-1;
        else current = current - 1;

        return data_layers.get(current);
    }
    public Stack<Shapes> getNext(){
        if(current + 1 == data_layers.size()) current = 0;
        else current = current + 1;
        return data_layers.get(current);
    }
    public void drawAdd(){
        add.IsClicked(board.getX(), board.getY());
        addLayer();
    }

    public void draw(Graphics g){
        add.draw(g);
        down.draw(g);
        up.draw(g);
        remove.draw(g);
    }

    public void drawLayer(Graphics g){
        int i = 0;
        g.setFont(new Font("abc", Font.PLAIN, 12));
        for (Stack<Shapes> layer : data_layers) {
            if (i == current) {
                g.setColor(Color.BLUE);
                g.fillRect(2, 320+i*30, 60, 20);
                g.setColor(Color.white);
                g.fillRect(5, 322+i*30, 53, 16);
                g.setColor(Color.BLACK);
                g.drawString("Layer " +  (i+1), 12, 335+i*30);
            }
            else {
                g.setColor(Color.BLACK);
                g.drawRect(2, 320+i*30, 60, 20);
                g.drawRect(5, 322+i*30, 53, 16);
                g.drawString("Layer " +  (i+1), 12, 335+i*30);
            }
            i++;
            for (Shapes shape : layer) {
                shape.draw(g);
            }
        }
    }
}
