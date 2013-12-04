package particle.engine;



import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Paint;


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
	@SuppressWarnings("unused")
	private double alpha;
	private double fade;
	

	public Particle(double xPos, double yPos, double xVel, double yVel, Paint color, int size, int lifeTime) 
	{
		
		//super.setBlendMode(BlendMode.DIFFERENCE);
		this.xPos = xPos;
		this.yPos = yPos;
		this.xVel = xVel;
		this.yVel = yVel;
		this.color = color;
		this.size = size;
		this.alpha = 1;
		this.fade = Math.random() * 0.1;
		this.endTime = System.currentTimeMillis() + lifeTime;
		
		
	}

	public void move() {

		this.alpha -= this.fade;
		this.xPos += this.xVel;
		this.yPos += this.yVel;

	}

	public boolean isDead()
	{
		return (System.currentTimeMillis() > this.endTime);
	}

	public void draw(GraphicsContext graphics) 
	{
		double x = Math.round(this.xPos);
		double y = Math.round(this.yPos);
		//graphics.setGlobalAlpha(Math.random() * this.alpha);
		graphics.setFill(this.color);
		graphics.fillOval(x-this.size, y-this.size, this.size + this.size, this.size + this.size);
	
	}


}
