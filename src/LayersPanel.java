import java.awt.*;

public class LayersPanel {
    LayersLogic ll;
    Board board;
    public LayersPanel(LayersLogic ll, Board board){
        this.ll = ll;
        this.board = board;
        //ll.addLayer(new LinkedList<>());
    }
    public void draw(Graphics g){
        ll.draw(g);
        ll.drawLayer(g);
    }
    public void layersProcessing(int x, int y, Graphics g){
        if(ll.add.IsClicked(x, y)){
            System.out.println("add clicked");
            ll.drawLayer(g);
            ll.addLayer();
            ll.add.setCurrent_image(ll.add.getImage_pressed());
            ll.remove.setCurrent_image(ll.remove.getImage_depressed());
            ll.up.setCurrent_image(ll.up.getImage_depressed());
            ll.down.setCurrent_image(ll.down.getImage_depressed());
        }
        else if(ll.down.IsClicked(x, y)){
            System.out.println("down clicked");
            ll.drawLayer(g);
            board.shapes = ll.getNext();
            ll.add.setCurrent_image(ll.add.getImage_depressed());
            ll.remove.setCurrent_image(ll.remove.getImage_depressed());
            ll.up.setCurrent_image(ll.up.getImage_depressed());
            ll.down.setCurrent_image(ll.down.getImage_pressed());
            board.undoStack = ll.getUndoStack();
            g.clearRect(67, 90, board.getWidth(), board.getHeight()); board.repaint();
        }
        else if(ll.remove.IsClicked(x, y)){
            System.out.println("remove clicked");
            ll.drawLayer(g);
            ll.removeLayer();
            ll.add.setCurrent_image(ll.add.getImage_depressed());
            ll.remove.setCurrent_image(ll.remove.getImage_pressed());
            ll.up.setCurrent_image(ll.up.getImage_depressed());
            ll.down.setCurrent_image(ll.down.getImage_depressed());
            board.shapes = ll.getBoard();
            board.undoStack = ll.getUndoStack();
            board.getGraphics().clearRect(67, 90, board.getWidth(), board.getHeight());board.repaint();
        }
        else if (ll.up.IsClicked(x, y)){
            System.out.println("up clicked");
            ll.drawLayer(g);
            board.shapes = ll.getPrevious();
            ll.add.setCurrent_image(ll.add.getImage_depressed());
            ll.remove.setCurrent_image(ll.remove.getImage_depressed());
            ll.up.setCurrent_image(ll.up.getImage_pressed());
            ll.down.setCurrent_image(ll.down.getImage_depressed());
            board.undoStack = ll.getUndoStack();
            g.clearRect(67, 90, board.getWidth(), board.getHeight());board.repaint();
        }
    }
    public void unClick(){
        ll.add.setCurrent_image(ll.add.getImage_depressed());
        ll.add.SetPressed(false);
        ll.remove.setCurrent_image(ll.remove.getImage_depressed());
        ll.remove.SetPressed(false);
        ll.down.setCurrent_image(ll.down.getImage_depressed());
        ll.down.SetPressed(false);
        ll.up.setCurrent_image(ll.up.getImage_depressed());
        ll.up.SetPressed(false);
    }
}
