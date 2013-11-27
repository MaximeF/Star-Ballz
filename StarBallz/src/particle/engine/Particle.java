package particle.engine;



import javafx.scene.canvas.GraphicsContext;
import javafx.scene.effect.BlendMode;

import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;

public class Particle {


	// properties for animation
	// and colouring
	private double xVel;
	private double yVel;
	private double endTime;
	private final Paint color;
	private final int size;
	private double xPos;
	private double yPos;
	

	public Particle(double xPos, double yPos, double xVel, double yVel, Paint color, int size, int lifeTime) 
	{
		
		//super.setBlendMode(BlendMode.DIFFERENCE);
		this.xVel = xVel;
		this.yVel = yVel;
		this.color = color;
		this.size = size;
		this.endTime = System.currentTimeMillis() + lifeTime;
		
		
	}

	public void move() {

		this.xPos += this.xVel;
		this.yPos += this.yVel;

	}

	public boolean isDead()
	{
		return (System.currentTimeMillis() > this.endTime);
	}

	public void draw(GraphicsContext graphics) 
	{
		graphics.setFill(this.color);
		graphics.fillOval(this.xPos - this.size, this.yPos - this.size, this.size, this.size);
		
	}




}
