import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Random;

public class MySaveBar extends MyButton{

    Image save2, open, NewSave;
    Board board;

    FileButton[] fileButtons;

    Boolean OpenPressed = false;
    File[] files;
    String name0;

    public MySaveBar(int x, int y, int width, int height, Image i_depressed, Image i_pressed, Image save2, Image open, Image NewSave, Board board) {
        super("Save Bar", x, y, width, height, i_depressed, i_pressed);
        this.save2 = save2;
        this.open = open;
        this.NewSave = NewSave;
        this.board = board;
        files = OpenDirectory();
        fileButtons = new FileButton[files.length];

        /*(new Thread(new Runnable() {
            @Override
            public void run() {
                name0 = (new SimpleDateFormat("dd/MM/yyyy HH:mm:ss")).format(new Date());
            }
        })).start();*/
    }


    public File[] OpenDirectory() {
        File file = new File("./Paintings");
        File[] files = file.listFiles();
        return files;
    }

    public void drawSaveBar(Graphics g){
        g.drawImage(GetImage(), x, y, this);
        if(getCurrent_image().equals(getImage_pressed())){
            g.drawImage(open, x, y+60, this);
            g.drawImage(save2, x, y+120, this);
            g.drawImage(NewSave, x, y+180, this);
        }
    }

    @Override
    public boolean IsClicked(int x, int y){
        boolean a = super.IsClicked(x, y);
        if(IsPressed() && !a){
            if((x > 0 && x < this.width) && (y < (this.y + this.height + open.getHeight(board)/2) && y > this.y+this.getHeight()/2)){
                /*Frame SecondFrame = new JFrame();
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setDialogTitle("Specify a file to save");
                fileChooser.setCurrentDirectory(new File("./Paintings"));
                int userSelection = fileChooser.showOpenDialog( SecondFrame);
                if(userSelection == JFileChooser.APPROVE_OPTION){
                    File file = fileChooser.getSelectedFile();
                    try {
                        SaveBarLogic.openingLogic(file);
                        System.out.println("File Opened");
                        board.lp.ll = new LayersLogic(board, new Stack<>());
                    } catch (IOException e) {
                        System.out.println("Failed tp load file");
                    } catch (ClassNotFoundException e) {
                        throw new RuntimeException(e);
                    }
                }*/
                OpenPressed = true;

            } else if(x > 0  && x < save2.getWidth(board) && y > this.y + 120 && y < this.y + 170){

                try {
                    String name = "./Paintings/save_";
                    SaveBarLogic.savingLogic(new File(name + (files.length+1) + ".ser"), board);
                    System.out.println("Saved!");
                } catch (IOException e) {
                    System.err.println("Couldn't Save");
                    e.printStackTrace();
                }

            }
            else if(x > this.x  && x < NewSave.getWidth(board) && y > 170 && y < 240){

                try {
                    board.lp.ll.data_layers = new LinkedList<>();
                    board.lp.ll.undoStack_layers = new LinkedList<>();
                    System.out.println("New Board Created");
                    SwingTimerEx.ex.setSize(SwingTimerEx.ex.getWidth()+(new Random()).nextInt(-1,1),
                            SwingTimerEx.ex.getHeight()+(new Random()).nextInt(-1,1));
                } catch (Exception e) {
                    System.err.println("Failed to create a new board");
                }
            }
            SetPressed(!IsPressed());
        }
        return a;
    }

    public void drawOpenMenu(Graphics g){
        if(OpenPressed){

            g.setColor(Color.red);
            g.fillRect(550, 250 + 30*files.length + 50, -20, 20);

            g.setColor(Color.lightGray);
            g.fillRect(250, 250, 300, 30*files.length + 50);

            for (int i = 0; i < files.length; i++) {
                fileButtons[i] = new FileButton(250, 260+i*30, 300, 30, files[i], board);
            }
            for (FileButton nIbuttons : fileButtons) {
                nIbuttons.draw(g);
            }
        }
    }

    public void OpenClicked(int x, int y){
        for (FileButton noimbutt :
                fileButtons) {
            try{
                if(noimbutt.IsClicked(x,y)){
                    try {
                        SaveBarLogic.openingLogic(noimbutt.file, board);
                        System.out.println("File Opened");
                    } catch (IOException e) {
                        System.err.println("Couldnt open file: IO Exception");
                    } catch (ClassNotFoundException e) {
                        System.err.println("Couldnt open file: ClassNotFound Exception");
                    }
                    OpenPressed = false;
                }
            }catch (NullPointerException ignored){}
        }
    }

    @Override
    public void hovered(int x, int y, Graphics g) {
        if(!IsPressed()){
            String name = this.name;
            if(x >= this.x && x <= this.x + width && y >= this.y && y <= this.y + height) {
                g.setFont(new Font("123", Font.BOLD, 20));
                g.drawRect(this.x, this.y, 10, 6);
                g.drawString(name, this.x+4, this.y+3);
                g.setFont(new Font("123", Font.BOLD, 15));
                g.drawString("Open--> CTRL O", 10, 100);
                g.drawString("Save--> CTRL S", 10, 130);
            }
        }
    }
}
