package starBallz;

import javafx.scene.effect.DropShadow;
import javafx.scene.effect.Reflection;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
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
		Image platformImage = new Image("platform.jpg");
		this.setFill(new ImagePattern(platformImage));

		DropShadow ds = new DropShadow();
		ds.setColor(Color.RED);


		Reflection reflection = new Reflection();

		ds.setInput(reflection);    
		this.setEffect(ds);
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
