package starBallz.backend;
import java.util.Random;

import starBallz.backend.explosion.Engine;

import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;


public class Ballz extends Ball implements Bouncy, Explosive
{

	private Paint colors[] = {javafx.scene.paint.Color.RED,javafx.scene.paint.Color.DARKTURQUOISE,javafx.scene.paint.Color.LIMEGREEN,javafx.scene.paint.Color.YELLOW,javafx.scene.paint.Color.DEEPPINK,javafx.scene.paint.Color.DARKVIOLET};
	private boolean rebounded = false;
	public Ballz(double xPos, double yPos, int size, double xVel, double yVel)
	{
		super(xPos, yPos, size, xVel, yVel);
		Random random = new Random();
		int colorIndex = random.nextInt(6);
		super.setColor((Color)colors[colorIndex]);
	}

	@Override
	public void sideRebound()
	{
		super.setxVel(-super.getxVel());
	}

	@Override
	public boolean bottomRebound()
	{
		if(!this.rebounded)
		{
			super.setyVel(-super.getyVel());
			this.rebounded = true;
			return true;
		}
		else
		{
			return false;
		}

	}



	@Override
	public void setExplosion(Engine engine, int x, int y, int maxParticle,Color color) 
	{
		engine.setExplosion(x, y, maxParticle, color);
	}


}
