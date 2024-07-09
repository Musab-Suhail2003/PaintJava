import javax.swing.*;
import java.awt.*;

public class ColorChooser extends JColorChooser {
    static ColorChooser jcc = null;
    static Color currentColor = null;


    public Color getCurrentColor() {
        return currentColor;
    }

    private ColorChooser(){
        super();
    }
    public static ColorChooser getInstance(Board board){
        if(jcc == null) jcc = new ColorChooser();
        currentColor = jcc.showDialog(board, "pick", Color.black);
        return jcc;
    }

}
