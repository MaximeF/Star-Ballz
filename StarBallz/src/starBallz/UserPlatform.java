package starBallz;

import javafx.scene.effect.DropShadow;
import javafx.scene.effect.Reflection;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

public class UserPlatform extends Rectangle
{
	private int MOVE_SPEED = 1;
	private DropShadow ds = null;
	public UserPlatform(double xPos,double yPos,int width,double height)
	{
		
		super();
		this.setX(xPos);
		this.setY(yPos);
		this.setWidth(width);
		this.setHeight(height);
		Image platformImage = new Image("redPlatform.jpg");
		this.setFill(new ImagePattern(platformImage));
		this.ds = new DropShadow();
		this.ds.setColor(Color.RED);
		this.ds.setSpread(0.4);
		Reflection reflection = new Reflection();
		ds.setInput(reflection);    
		this.setEffect(ds);
	}
	
	public void setDropShadowColor(Color color)
	{
		this.ds.setColor(color);
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
