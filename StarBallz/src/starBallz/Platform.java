package starBallz;

import javafx.scene.shape.Rectangle;

public class Platform extends Rectangle
{
	static final int MOVE_SPEED = 10;
	public Platform()
	{
		super();
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
