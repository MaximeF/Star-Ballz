package particle.engine;
import java.util.ArrayList;
import java.util.List;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import particle.engine.Particle;

public class Explosion {

	private int posX;
	private int posY;
	private int maxParticle;
	private List<Particle> particles;
	private Color color = null;
	private double lifeTime;
	private Canvas canvas;

	public Explosion(int posX, int posY, int maxParticle, Canvas canvas,Color color)
	{
		this.posX = posX;
		this.posY = posY;
		this.maxParticle = maxParticle;
		this.particles = new ArrayList<Particle>();
		this.color = color;
		this.lifeTime = System.currentTimeMillis() +250;
		this.canvas = canvas;
		this.init();
	}

	
	public void init()
	{
		double angle = (Math.PI * 2) / this.maxParticle;
		for(int i = 0; i < this.maxParticle; i++)
		{
			double randomVelocity = 1 + Math.random();
			double particleAngle = i * angle;

			Particle newParticle = new Particle(this.posX, this.posY, Math.cos(particleAngle) * randomVelocity,  
					Math.sin(particleAngle) * randomVelocity, this.color, 1, (int) (800 * Math.random()%1000));
			this.particles.add(newParticle);

		}

	}

	public void refresh()
	{
		GraphicsContext graphics = this.canvas.getGraphicsContext2D();

		for(int i = 0; i < this.particles.size(); i++)
		{


			if(this.particles.get(i).isDead())
			{
				this.particles.remove(i);

				if(System.currentTimeMillis() < this.lifeTime)
				{
					double randomVelocity = 1 + Math.random();
					double angle = 0.1f * (double)(Math.random() * 2 - 1);

					Particle newParticle = new Particle(this.posX, this.posY, Math.cos(angle * i) *  randomVelocity,  
							Math.sin(angle * i) *  randomVelocity, this.color, 1, (int) (800 * Math.random()%1000));
					this.particles.add(newParticle);					
				}


			}
			else
			{
				this.particles.get(i).move();
				this.particles.get(i).draw(graphics);
			}
		}
	}

	public boolean isDead()
	{
		return (System.currentTimeMillis() > this.lifeTime && this.particles.isEmpty());
	}

}
