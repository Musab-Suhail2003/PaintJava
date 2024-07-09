import java.io.*;
import java.util.LinkedList;
import java.util.Stack;

public class SaveBarLogic {
    public static void savingLogic(File file, Board board) throws IOException {

        MyList list = board.mylist;
        FileOutputStream fOUT = new FileOutputStream(file);
        ObjectOutputStream objOUT = new ObjectOutputStream(fOUT);
        objOUT.writeObject(list);
        fOUT.close();
        objOUT.close();
    }

    public static void openingLogic(File file, Board board) throws IOException, ClassNotFoundException {
        MyList list;

        FileInputStream fIN = new FileInputStream(file);
        ObjectInputStream objIN = new ObjectInputStream(fIN);
        list = (MyList) objIN.readObject();
        board.mylist.setData_layers(list.getData_layers());
        board.mylist.setUndoStack_layers(list.getUndoStack_layers());
        board.lp.ll = new LayersLogic(board);
        objIN.close();
        fIN.close();
    }
}
