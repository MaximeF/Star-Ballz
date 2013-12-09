package particle.engine;

import ball.Ball;
import javafx.scene.paint.Paint;


public class Particle extends Ball {


	// properties for animation
	// and colouring
	private double endTime;

	public Particle(double xPos, double yPos, double xVel, double yVel, Paint color, int size, int lifeTime) 
	{
		super(xPos, yPos, size, xVel, yVel, color);
		this.endTime = System.currentTimeMillis() + lifeTime;

	}

	public boolean isDead()
	{
		return (System.currentTimeMillis() > this.endTime);
	}





}
