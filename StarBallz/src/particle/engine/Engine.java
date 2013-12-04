package particle.engine;

import java.util.ArrayList;
import java.util.List;
import javafx.animation.AnimationTimer;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;


public class Engine extends Pane {

	private AnimationTimer timer;
	private List<Explosion> explosions;
	private Canvas canvas;

	public Engine()
	{
		
		this.explosions = new ArrayList<Explosion>();
		this.canvas = new Canvas(600, 1000);
		super.getChildren().add(canvas);

		this.timer = new AnimationTimer() {
			@Override public void handle(long now) {
				GraphicsContext gc = canvas.getGraphicsContext2D();
				// clear area with transparent black
				gc.setFill(Color.rgb(0, 0, 0, 1));
				gc.fillRect(0, 0, 1024, 1000);
				Engine.this.refresh();
			}
		};

		this.start();

	}


	public void setExplosion(int x, int y, int maxParticle,Color color)
	{
		this.explosions.add(new Explosion(x, y, maxParticle, canvas,color));
	}

	public void refresh()
	{
		for(int i = 0; i < this.explosions.size(); i++)
		{

			if(this.explosions.get(i).isDead())
			{
				this.explosions.remove(i);
			}
			else
			{
				this.explosions.get(i).refresh();

			}
		}
	}

	public void start() 
	{ 
		timer.start(); 
	}

	public void stop() 
	{ 
		timer.stop(); 
	}

}
