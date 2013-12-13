package starBallz.backend;




import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;


public class UserPlatform 
{
	private static int MOVE_SPEED = 1;
	private double xPos;
	private double yPos;
	private double width;
	private double height;
	private Image image;

	public UserPlatform(double xPos, double yPos)
	{
		this.xPos = xPos;
		this.yPos = yPos;
		this.image = new Image("redPlatform.jpg");
		this.width = this.image.getWidth();
		this.height = this.image.getHeight();

	}
    public void setImage(String imageURL)
    {
    	this.image = new Image(imageURL);
    }

	public void moveRight()
	{
		this.xPos += UserPlatform.MOVE_SPEED;
	}

	public void moveLeft()
	{
		this.xPos -=  UserPlatform.MOVE_SPEED;
	}
	
	public void draw(GraphicsContext gc)
	{
		gc.drawImage(this.image, this.xPos, this.yPos, this.width, this.height);
	}

	public double getxPos() {
		return this.xPos;
	}

	public double getyPos() {
		return this.yPos;
	}

	public double getWidth() {
		return this.width;
	}

	public double getHeight() {
		return this.height;
	}

	public void setxPos(double xPos) {
		this.xPos = xPos;
	}
	
	
}
