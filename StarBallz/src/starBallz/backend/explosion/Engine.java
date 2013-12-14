package starBallz.backend.explosion;

import java.util.ArrayList;
import java.util.List;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
/**
 * Classe servant à gérer les Explosions
 * @author Maxime Forgues, Patrick Arsenault, Françis Chandonnet
 *@see Explosion
 */

public class Engine {


	private List<Explosion> explosions;


	/**
	 * Constructor d'Explosion, initialise l'array d'explosion
	 */
	public Engine()
	{

		this.explosions = new ArrayList<Explosion>();

	}


	/**
	 * Ajouter une nouvelle explosion dans la liste l'explosion
	 * @param x positon en x 
	 * @param y positin
	 * @param maxParticle nombre maximum de particle
	 * @param color couleur de l'explosion
	 */
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

	/**
	 * Dessine les particles de chaque explosion
	 * @param graphics le GraphicsContext du canvas
	 */
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


	/**
	 * 
	 * @return l'array d'explosions
	 */
	public List<Explosion> getExplosions() {
		return explosions;
	}




}
