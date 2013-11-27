package starBallz;
import javafx.scene.paint.Paint;
import javafx.scene.shape.*;

public class Ballz extends Circle implements Bouncy , Explosive
{

	double xVel;
	double yVel;

	public Ballz(double xPos, double yPos, double size, double xVel, double yVel, Paint color)
	{
		super(xPos, yPos, size, color);
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

	public double getxVel() {
		return xVel;
	}

	public void setxVel(double xVel) {
		this.xVel = xVel;
	}

	public double getyVel() {
		return yVel;
	}

	public void setyVel(double yVel) {
		this.yVel = yVel;
	}


}
