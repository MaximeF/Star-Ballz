package starBallz.backend.explosion;

import java.util.ArrayList;
import java.util.List;

import starBallz.backend.Explosive;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;


public class Engine {


	private List<Explosion> explosions;


	public Engine()
	{

		this.explosions = new ArrayList<Explosion>();

	}


	public void setExplosion(int x, int y, int maxParticle,Color color)
	{
		this.explosions.add(new Explosion(x, y, maxParticle, color));
	}

	public void refresh()
	{
		for(int i = 0; i < this.explosions.size(); i++)
		{

			if(this.explosions.get(i).isDead())
			{
				this.explosions.get(i).getParticles().clear();
				this.explosions.remove(i);
			}
			else
			{
				this.explosions.get(i).refresh();

			}
		}
	}

	public void draw(GraphicsContext graphics)
	{
		for(int i = 0; i < this.explosions.size(); i++)
		{
			List<Particle> particles = this.explosions.get(i).getParticles();
			for(int j = 0; j < particles.size(); j++)
			{
				particles.get(j).draw(graphics);

			}
		}

	}


}
