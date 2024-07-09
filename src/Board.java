import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.io.Serializable;
import java.util.*;

import javax.swing.*;
import javax.swing.Timer;
import javax.swing.event.MouseInputListener;

public class Board extends JPanel
        implements ActionListener , MouseInputListener, Serializable {

    private final int B_WIDTH = 860;
    private final int B_HEIGHT = 800;
    private final int DELAY = 25;

    private Timer timer;
    private int key = 0;
    private boolean keyPressed = false;
    private boolean mousePressed = false;
    
    private boolean start_drawing = false;
    
    private int x_init;
    private int y_init;
    private int x_final;
    private int y_final;

    int currentShape;
    static int t1x=0, t1y=0, t2x=0, t2y=0, count = 0; // info for triangle

    ArrayList<MyButton> shapes_buttonList;
    MySaveBar msb;
    Edit editMenu;
    MyColors colors;
    LayersPanel lp;
    /*MyColorChooser mcc;*/
    Stack<Shapes> shapes;

    Stack<Shapes> undoStack;
    ImageIcon gradientImage = new ImageIcon("src\\Gradient_pressed.jpg");

    Grid mygrid;
    Circle circ;
    Rectangle rect;
    Triangle tri;
    Free_Drawing brush;
    Line line;
    Right_Tri right_tri;
    Hexagon hex;
    Pentagram pentagram;
    StrokeSelector strokeSelector;

    MyList mylist = new MyList();

    private class TAdapter implements KeyListener {

        @Override
        public void keyReleased(KeyEvent e) {
            keyPressed = false;

            if (key == KeyEvent.VK_SPACE) {
                
            }

        }
        @Override
        public void keyTyped(KeyEvent e) {}

        @Override
        public void keyPressed(KeyEvent e) {

        	keyPressed = true;
            key = e.getKeyCode();

            if(e.isControlDown()){
                if(key == KeyEvent.VK_Z) editMenu.undo();
                else if (key == KeyEvent.VK_Y) editMenu.redo();
                else if (key == KeyEvent.VK_SPACE) lp.ll.addLayer();
                else if (key == KeyEvent.VK_PAGE_UP) lp.ll.getPrevious();
                else if (key == KeyEvent.VK_PAGE_DOWN) lp.ll.getNext();
                else if (key == KeyEvent.VK_O){ msb.OpenPressed = true; msb.OpenClicked(getX(), getY());}
                else if (key == KeyEvent.VK_S){ msb.SetPressed(true); msb.IsClicked(10, 150);}
            }
        }
    }

    public Board() throws IOException {
        initBoard();
    }

    private void InitializeAssets() {
        editMenu = new Edit(B_WIDTH-140, 10, 50, 50, (new ImageIcon("src\\EditMenu\\Edit.png")).getImage(),
                (new ImageIcon("src\\EditMenu\\Edit_pressed.png")).getImage(), this);

        shapes_buttonList = new ArrayList<>();
        shapes_buttonList.add(new MyButton("free drawing", 100, 10, 30, 30, (new ImageIcon("src\\shapes\\brush.png").getImage()),
                (new ImageIcon("src\\shapes\\brush_press.png").getImage())));
        shapes_buttonList.add(new MyButton("circle",140, 10, 30, 30, (new ImageIcon("src\\shapes\\circle.png").getImage()),
                (new ImageIcon("src\\shapes\\circle_press.png").getImage())));
        shapes_buttonList.add(new MyButton("hexagon",180, 10, 30, 30, (new ImageIcon("src\\shapes\\hexagon.png").getImage()),
                (new ImageIcon("src\\shapes\\hexagon_press.png").getImage())));
        shapes_buttonList.add(new MyButton("Line",220, 10, 30, 30, (new ImageIcon("src\\shapes\\pencil.png").getImage()),
                (new ImageIcon("src\\shapes\\pencil_press.png").getImage())));
        shapes_buttonList.add(new MyButton("right-triangle",100, 50, 30, 30, (new ImageIcon("src\\shapes\\right-triangle.png").getImage()),
                (new ImageIcon("src\\shapes\\right-triangle_press.png").getImage())));
        shapes_buttonList.add(new MyButton("rectangle",140, 50, 30, 30, (new ImageIcon("src\\shapes\\rectangle.png").getImage()),
                (new ImageIcon("src\\shapes\\rectangle_press.png").getImage())));
        shapes_buttonList.add(new MyButton("triangle", 180, 50, 30, 30, (new ImageIcon("src\\shapes\\triangle.png").getImage()),
                (new ImageIcon("src\\shapes\\triangle_press.png").getImage())));
        shapes_buttonList.add(new MyButton("Pentagram", 215, 45, 38, 38, (new ImageIcon("src\\shapes\\pentagram.png").getImage()),
                (new ImageIcon("src\\shapes\\pentagram_press.png").getImage())));

        msb = new MySaveBar(10, 10, 80, 80, new ImageIcon("src\\Save_Toolbar\\save.png").getImage(),
                new ImageIcon("src\\Save_Toolbar\\save_press.png").getImage(), new ImageIcon("src\\Save_Toolbar\\save2.png").getImage(),
                new ImageIcon("src\\Save_Toolbar\\open.png").getImage(), new ImageIcon("src\\Save_Toolbar\\NewSave.png").getImage(),
                this);
        colors = new MyColors(300, 10, this);
        lp = new LayersPanel(new LayersLogic(this), this);
        shapes = lp.ll.getBoard();
        undoStack = lp.ll.getUndoStack();
        strokeSelector = new StrokeSelector(27, 730, this);
        mygrid = Grid.getInstance(this);

    }

    private void initBoard() {

    	addMouseListener( this );
    	addMouseMotionListener( this );
    	addKeyListener(new TAdapter());
        setBackground(Color.WHITE);
        setPreferredSize(new Dimension(B_WIDTH, B_HEIGHT));
        setFocusable(true);

        InitializeAssets();

        timer = new Timer(DELAY, this);
        timer.start();
    }
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        shapes = lp.ll.getBoard();
        undoStack = lp.ll.getUndoStack();

        if(keyPressed) drawNotification("key ", g);

        if(mousePressed) drawNotification("mouse ", g);

        for (Shapes shape : shapes){
            shape.draw(g);
        }
        mygrid.draw(g);

        drawMenuButtons(g);
    }

    private void drawMenuButtons(Graphics g) {
        msb.drawOpenMenu(g);

        /*Drawing Border code start*/
        g.setColor(Color.BLACK);
        g.drawRect(0, 0, getWidth(), 100);
        g.drawRect(0, 90, 70, getHeight());
        g.setColor(Color.gray);
        g.fillRect(0, 0, getWidth(), 100);
        g.fillRect(0, 90, 70, getHeight());
        /*Drawing border code end*/

        drawShapeButton(g);
        editMenu.drawEditMenu(g);
        lp.draw(g);
        colors.drawGrid(g);
        msb.drawSaveBar(g);
        mygrid.buttonDraw(g);
        /*Drawing Gradient code start*/
        g.drawImage(gradientImage.getImage(), 430, 0, 210, 100,this);
        g.setColor(Color.BLACK);
        g.setFont(new Font("asd", Font.BOLD, 20));
        g.drawString("Click on the 3rd Row", 435, 40);
        g.drawString("to select Colors", 465, 60);
        g.setFont(new Font("asd", Font.TYPE1_FONT, 10));
        /*Drawing Gradient code end*/
        strokeSelector.paint(g);
    }

    private void drawNotification(String text, Graphics g)
    {
    	g.setColor(Color.RED);
    	g.drawString(text + key + " pressed", getWidth()-100, getHeight()-10);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Toolkit.getDefaultToolkit().sync();
        repaint();
    }

    public void IsClicked(int x, int y) throws NullPointerException{

        strokeSelector.IsClicked(x, y);
        editMenu.IsClicked(x,y);
        mygrid.clicks(x,y);



        msb.IsClicked(x, y);
        shapesClicked(x, y);
        if(msb.OpenPressed){
            msb.OpenClicked(x,y);
        }
    }

    @Override
	public void mouseClicked(MouseEvent e) {

        try{IsClicked(e.getX(), e.getY());}
        catch (NullPointerException ignored){}

        colors.updateStroke_Fill(e);

        //getting colors for the empty color column
        for (int i = 6; i < colors.list.length; i++) {
            if(colors.list[i].isPressed){
                var jcc = ColorChooser.getInstance(this);
                colors.list[i].color = jcc.getCurrentColor();
            }
        }


    }

    @Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
        int x = e.getX(), y = e.getY();
		if(x > 67 ) x_init = x;
		if(y > 80) y_init = y;

        start_drawing = x > 67 && y > 90;
		mousePressed = true;
        lp.layersProcessing(x, y, getGraphics());
        if(SwingUtilities.isLeftMouseButton(e) && start_drawing)
        {
            CreateShape();
        }
        if(SwingUtilities.isRightMouseButton(e)){
            editMenu.undo();
        }
        if(SwingUtilities.isMiddleMouseButton(e)){
            editMenu.redo();
        }
    }

    @Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
        //UN-Clicking all the buttons
       lp.unClick();

        unclick_All(e.getX(), e.getY());

		mousePressed = false;
		start_drawing = false;
        repaint();
	}

    @Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

    @Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

    @Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
        int x = e.getX(), y = e.getY();


        x_final = x - x_init;
        y_final = y - y_init;
        if (SwingUtilities.isLeftMouseButton(e) && start_drawing) {

            growShape(e);
        }
	}

    @Override
    public void mouseMoved(MouseEvent e) {
        // TODO Auto-generated method stub
        int x = e.getX(), y = e.getY();
        Graphics g = getGraphics();
        mygrid.hovered(x,y,g);
        strokeSelector.hovered(x,y,g);
        for (MyButton shape : shapes_buttonList) {
            shape.hovered(x,y, g);
        }msb.hovered(x, y, g);
        for (ColorButton cb : colors.list) {
            try{cb.hovered(x, y, g);}
            catch (NullPointerException ignored){}
        }
        colors.fill.hovered(x,y,g);colors.stroke.hovered(x,y,g);
        editMenu.hovered(x,y,g);
        editMenu.hovered(x, y, g);
        lp.ll.add.hovered(x,y,g);
        lp.ll.remove.hovered(x,y,g);
        lp.ll.up.hovered(x,y,g);
        lp.ll.down.hovered(x,y,g);
    }


    private void unclick_All(int x, int y) {


        msb.setCurrent_image(msb.getImage_depressed());
        try{msb.OpenClicked(x, y);}catch (NullPointerException ignored){}
        msb.OpenPressed = false;
        
        for (MyButton shape : shapes_buttonList) {
            shape.setCurrent_image(shape.getImage_depressed());
        }
        for (ColorButton cb: colors.list) {
            if(cb!=null)cb.setPressed(false);
        }
    }

    private void shapesClicked(int x, int y) {

        for (MyButton shape : shapes_buttonList) {
            if(shape.IsClicked(x,y)){
                if(shape.name.equalsIgnoreCase("Circle")){
                    System.out.println("Circle Selected");
                    currentShape = 1;
                } else if(shape.name.equalsIgnoreCase("Rectangle")){
                    System.out.println("Rectangle Selected");
                    currentShape = 2;
                } else if(shape.name.equalsIgnoreCase("Triangle")){
                    System.out.println("Triangle Selected");
                    currentShape = 3;
                } else if(shape.name.equalsIgnoreCase("Line")) {
                    System.out.println("Line Selected");
                    currentShape = 4;
                } else if(shape.name.equalsIgnoreCase("Free Drawing")) {
                    System.out.println("Free Drawing Selected");
                    currentShape = 5;
                } else if(shape.name.equalsIgnoreCase("right-triangle")) {
                    System.out.println("Right Angle Triangle Selected");
                    currentShape = 6;
                } else if(shape.name.equalsIgnoreCase("Hexagon")) {
                    System.out.println("Hexagon Selected");
                    currentShape = 7;
                } else if(shape.name.equalsIgnoreCase("Pentagram")) {
                    System.out.println("Pentagram Selected");
                    currentShape = 8;
                }

            }
        }
    }


    private void CreateShape() {
        int stroke = strokeSelector.getStroke_Size();
        if (currentShape == 1) {
            circ = new Circle(2, new Point(x_init, y_init), colors.fill.color, colors.stroke.color, stroke);
            shapes.push(circ);
            lp.ll.resetUndoStack();
            repaint();
        }
        else if(currentShape == 2){
            rect = new Rectangle(colors.fill.color, new Point(x_init,y_init), 2, 2, colors.stroke.color, stroke);
            shapes.push(rect);
            lp.ll.resetUndoStack();
            repaint();
        }
        else if (currentShape == 3 && (x_init > 67) && (y_init > 90)){

            tri = new Triangle(x_init, y_init, 1, 1,colors.fill.color, colors.stroke.color, stroke);
            shapes.push(tri);

        }
        else if(currentShape == 4){
            line = new Line(colors.fill.color, x_init, y_init, x_init , y_init, colors.stroke.color, stroke);
            lp.ll.resetUndoStack();
            shapes.push(line);
        }
        else if(currentShape == 5){
            brush = new Free_Drawing(colors.fill.color, x_init, y_init, stroke, colors.stroke.color);
            lp.ll.resetUndoStack();
            shapes.push(brush);
        }
        else if (currentShape == 6){
            right_tri = new Right_Tri(x_init, y_init, 1, 1,colors.fill.color, colors.stroke.color, stroke);
            lp.ll.resetUndoStack();
            shapes.push(right_tri);
        } else if (currentShape == 7) {
            hex = new Hexagon(x_init, y_init, 1, colors.fill.color, colors.stroke.color, stroke);
            lp.ll.resetUndoStack();
            shapes.push(hex);
        }else if (currentShape == 8) {
            pentagram = new Pentagram(x_init, y_init, 1, colors.fill.color, colors.stroke.color, stroke);
            lp.ll.resetUndoStack();
            shapes.push(pentagram);
        }


    }

    private void drawShapeButton(Graphics g) {

        for (MyButton shapes_button : shapes_buttonList) {
           shapes_button.draw(g);
        }
    }

    private void growShape(MouseEvent e) {
        if (currentShape == 1) {
            if (circ != null) {
                int newX = e.getX() - circ.getCenter().x;
                int newY = e.getY() - circ.getCenter().y;
                circ.setDiameter((int) Math.sqrt(Math.pow(newX,2)+ Math.pow(newY,2)));
                circ.draw(getGraphics());
            }
        }else if (currentShape == 2) {
                rect.setDimen(x_init, y_init, x_final, y_final);
                rect.draw(getGraphics());
        }else if (currentShape == 3) {
            tri.updateWIDTH_HEIGHT(e.getX(), e.getY());
            tri.draw(getGraphics());
        } else if (currentShape == 4){
            int newX = e.getX() - line.center.x;
            int newY = e.getY() - line.center.y;
            line.upDate(newX+x_init, newY+y_init);
            line.draw(getGraphics());
        }else if (currentShape == 5){
            brush.updateBrush(e.getX(), e.getY());
            brush.draw(getGraphics());
        } else if (currentShape == 6) {
            right_tri.updateWIDTH_HEIGHT(e.getX(), e.getY());
            right_tri.draw(getGraphics());
        } else if (currentShape == 7) {
            hex.updateR(e.getX(), e.getY());
            hex.draw(getGraphics());
        }else if (currentShape == 8) {
            pentagram.updateR(e.getX(), e.getY());
            pentagram.draw(getGraphics());
        }

        repaint();
    }
}