import java.awt.*;

public class MyButton extends Component {
	public int x;
	public int y;
	public int width;
	public int height;
	private Image image_depressed;
	private Image image_pressed;
	private Image current_image;
	private boolean pressed;

	String name;

	public Image getImage_depressed() {
		return image_depressed;
	}

	public void setImage_depressed(Image image_depressed) {
		this.image_depressed = image_depressed;
	}

	public Image getImage_pressed() {
		return image_pressed;
	}

	public void setImage_pressed(Image image_pressed) {
		this.image_pressed = image_pressed;
	}

	public Image getCurrent_image() {
		return current_image;
	}

	public void setCurrent_image(Image current_image) {
		this.current_image = current_image;
	}

	public MyButton(String name, int x, int y, int width, int height, Image i_depressed, Image i_pressed)
	{	this.name = name;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		image_depressed = i_depressed;
		image_pressed = i_pressed;
		current_image = i_depressed;
	}	
		
	public Image GetImage() 
	{
		return current_image;
	}
	
	public Boolean IsPressed()
	{
		return pressed;
	}
	
	public void SetPressed(boolean pressed)
	{
		this.pressed = pressed;
	}
	
	public boolean IsClicked(int x, int y)
	{
		if(x >= this.x && x <= this.x + width && y >= this.y && y <= this.y + height)
		{
			if(pressed){
				pressed = false;
				current_image = image_depressed;
				return false;
			}
			else {
				pressed = true;
				current_image = image_pressed;
				return true;
			}
		}
		return false;
	}

	public void hovered(int x, int y, Graphics g){
		String name = this.name;
		if(x >= this.x && x <= this.x + width && y >= this.y && y <= this.y + height) {
			g.setFont(new Font("123", Font.BOLD, 20));
			g.drawRect(this.x, this.y, 10, 6);
			g.drawString(name, this.x+4, this.y+3);
		}
	}
	public void draw(Graphics g){
		g.drawImage(this.GetImage(), this.x, this.y, this.width, this.height, SwingTimerEx.ex.board);
	}
}
