package particle.engine;

import java.util.ArrayList;
import java.util.List;

import javafx.animation.AnimationTimer;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

public class Engine extends Pane {

	private int posX;
	private int posY;
	private int maxParticle;
	private List<Particle> particles;
	private AnimationTimer timer;
	private Color colors[]= {Color.BLUE, Color.RED, Color.LIMEGREEN};
	private int color;
	private int count;
	private double lifeTime;
	private Canvas canvas;

	public Engine(int posX, int posY, int maxParticle)
	{
		this.posX = posX;
		this.posY = posY;
		this.maxParticle = maxParticle;
		this.particles = new ArrayList<Particle>();
		this.count = 0;
		this.color = 0;
		this.lifeTime = System.currentTimeMillis() +150;
		this.init();
		this.canvas = new Canvas();
		super.getChildren().add(canvas);
		this.timer = new AnimationTimer() {
			@Override public void handle(long now) {
				GraphicsContext graphics = canvas.getGraphicsContext2D();
				graphics.setFill(Color.rgb(0, 0, 0, 0.2));
				graphics.setFill(Color.rgb(0, 0, 0, 0.2));
				graphics.fillRect(0, 0, 1024, 708);
				if(Engine.this.particles.isEmpty() && System.currentTimeMillis() > Engine.this.lifeTime)
				{
					this.stop();
					clear();
				}
				else
				{
					refresh(graphics);
				}

			}
		};

		this.start();
	}

	public void start() 
	{ 
		timer.start(); 
	}

	public void stop() 
	{ 
		timer.stop(); 
	}

	public void init()
	{
		double angle = (Math.PI * 2) / this.maxParticle;
		for(int i = 0; i < this.maxParticle; i++)
		{
			double randomVelocity = 1 + Math.random();
			double particleAngle = i * angle;

			Particle newParticle = new Particle(this.posX, this.posY, Math.cos(particleAngle) * randomVelocity,  
					Math.sin(particleAngle) * randomVelocity, this.colors[color], 1, (int) (800 * Math.random()%1000));
			this.particles.add(newParticle);

		}

	}


	public void refresh(GraphicsContext graphics)
	{


		for(int i = 0; i < this.particles.size(); i++)
		{


			if(this.particles.get(i).isDead())
			{

				super.getChildren().remove(this.particles.get(i));
				this.particles.remove(i);

				if(System.currentTimeMillis() < this.lifeTime)
				{
					double randomVelocity = 1 + Math.random();
					double angle = 0.1f * (double)(Math.random() * 2 - 1);

					Particle newParticle = new Particle(this.posX, this.posY, Math.cos(angle * i) *  randomVelocity,  
							Math.sin(angle * i) *  randomVelocity, this.colors[color], 1, (int) (800 * Math.random()%1000));
					this.particles.add(newParticle);					
				}

				this.count ++;
				if(count > this.maxParticle)
				{
					this.count = 0;
					this.color++;

					if(this.color > 2)
					{
						this.color = 0;
					}
				}


			}
			else
			{
				this.particles.get(i).move();
				this.particles.get(i).draw(graphics);
			}
		}
	}

	public void clear()
	{
		this.particles.clear();
		super.getChildren().clear();
	}







}
