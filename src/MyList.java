import java.io.Serializable;
import java.util.LinkedList;
import java.util.Stack;

public class MyList extends LinkedList<Stack<Shapes>> implements Serializable {
    LinkedList<Stack<Shapes>> data_layers = null;
    LinkedList<Stack<Shapes>> undoStack_layers = null;


    public void setData_layers(LinkedList<Stack<Shapes>> data_layers) {
        this.data_layers = data_layers;
    }

    public void setUndoStack_layers(LinkedList<Stack<Shapes>> undoStack_layers) {
        this.undoStack_layers = undoStack_layers;
    }

    public MyList getData_layers() {
        if(data_layers == null) data_layers = new MyList();
        return (MyList) data_layers;
    }

    public LinkedList<Stack<Shapes>> getUndoStack_layers() {
        if(undoStack_layers == null) undoStack_layers = new MyList();
        return undoStack_layers;
    }
}
