package starBallz;

import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;

public class UserPlatform extends Rectangle
{
	public int MOVE_SPEED = 1;
	
	public UserPlatform(double xPos,double yPos,int width,double height,Paint color)
	{
		super();
		this.setX(xPos);
		this.setY(yPos);
		this.setWidth(width);
		this.setHeight(height);
		this.setFill(color);
	}
	
	public void moveRight()
	{
		this.setX(this.getX()+MOVE_SPEED);
	}
	
	public void moveLeft()
	{
		this.setX(this.getX()-MOVE_SPEED);
	}
}
