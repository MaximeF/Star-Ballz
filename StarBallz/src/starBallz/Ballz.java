package starBallz;
import java.util.Random;

import javafx.scene.paint.Paint;
import javafx.scene.shape.*;

public class Ballz extends Circle implements Bouncy , Explosive
{

	private double xVel;
	private double yVel;
	private Paint colors[] = {javafx.scene.paint.Color.RED,javafx.scene.paint.Color.DARKTURQUOISE,javafx.scene.paint.Color.LIMEGREEN,javafx.scene.paint.Color.YELLOW,javafx.scene.paint.Color.DEEPPINK,javafx.scene.paint.Color.DARKVIOLET};

	public Ballz(double xPos, double yPos, double size, double xVel, double yVel)
	{
		super(xPos, yPos, size);
		Random random = new Random();
		int colorIndex = random.nextInt(6);
		this.setFill(this.colors[colorIndex]);
		this.xVel = xVel;
		this.yVel = yVel;
	}
	
	public void move()
	{
		this.setCenterX(this.getCenterX()+this.xVel);
		this.setCenterY(this.getCenterY()+this.yVel);
	}

	@Override
	public void sideRebound()
	{
		this.xVel = -(this.xVel);
	}
	
	@Override
	public void bottomRebound()
	{
		this.yVel = -(this.yVel);
	}

	public void setxVel(double xVel) {
		this.xVel = xVel;
	}

	public void setyVel(double yVel) {
		this.yVel = yVel;
	}


}
