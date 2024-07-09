import java.awt.*;
import java.io.IOException;
import javax.security.auth.RefreshFailedException;
import javax.swing.*;

public class SwingTimerEx extends JFrame implements javax.security.auth.Refreshable{
    static LayersLogic ll;
    static SwingTimerEx ex = new SwingTimerEx();
    Board board = null;
    public SwingTimerEx() {
        try {initUI();}
        catch (IOException ignores) {}

        setResizable(false);
        pack();

        setTitle("Java Painter");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        /*Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    SaveBarLogic.savingLogic();
                    System.out.println("Saved!");
                } catch (IOException e) {
                    System.err.println("Failed to Save");
                }
            }
        }));*/
    }

    private void initUI() throws IOException {

        /*try {
            SaveBarLogic.openingLogic();
            System.out.println("Loaded File SuccessFully");
        } catch (IOException e) {
            System.err.println("io exception:" +
                    " Could not load file");
        } catch (ClassNotFoundException e) {
            System.err.println("class not found");
        }*/

        add(new Board());
    }

    public static void main(String[] args) {


        EventQueue.invokeLater(() -> {

            ex.setVisible(true);

        });

    }

    @Override
    public boolean isCurrent() {
        return false;
    }

    @Override
    public void refresh() throws RefreshFailedException {
        ex.update(getGraphics());
    }
}