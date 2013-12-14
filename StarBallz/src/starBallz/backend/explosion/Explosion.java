package starBallz.backend.explosion;
import java.util.ArrayList;
import java.util.List;


import javafx.scene.paint.Color;
import starBallz.backend.explosion.Particle;
/**
 * Classe servant à créer une explosion
 * @author Maxime Forgues, Patrick Arsenault, Françis Chandonnet
 *@see Particle
 */
public class Explosion {

	private int posX;
	private int posY;
	private int maxParticle;
	private List<Particle> particles;
	private Color color = null;
	private double lifeTime;

	/**
	 * Crée une explosion en fonction de ses paramètres
	 * @param posX position en x	
	 * @param posY position en y
	 * @param maxParticle nombre maximum de particle
	 * @param color couleur de l'explosion
	 */
	public Explosion(int posX, int posY, int maxParticle,Color color)
	{
		this.posX = posX;
		this.posY = posY;
		this.maxParticle = maxParticle;
		this.particles = new ArrayList<Particle>();
		this.color = color;
		this.lifeTime = System.currentTimeMillis() +250;
		this.init();
	}


	/**
	 * Remplie l'array de particles une première fois jusqu'au nombre maximum
	 */
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

	/**
	 * Déplace les particles et remplace celles qui sont mortes tant que le temps n'est pas écoulé
	 * 
	 */
	public void refresh()
	{
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
			}
		}
	}

	/**
	 * Return true si le temps est écoulé et la list de particle est vide, sinon false
	 * @return true si le temps est écoulé et la list de particle est vide, sinon false
	 */
	public boolean isDead()
	{
		return (System.currentTimeMillis() > this.lifeTime && this.particles.isEmpty());
	}


	/**
	 * Retourne la liste de particles
	 * @return particles la liste de particles
	 */
	public List<Particle> getParticles() 
	{
		return this.particles;
	}


	/**
	 * Retourne la position du centre de l'explosion en x
	 * @return posX la position du centre de l'explosion en x
	 */
	public int getPosX() {
		return posX;
	}


	/**
	 * Retourne la position du centre de l'explosion en y
	 * @return posY la position du centre de l'explosion en y
	 */
	public int getPosY() {
		return posY;
	}

	/**
	 * Retourne le nombre maximum de particle
	 * @return maxParticle le nombre maximum de particle
	 */
	public int getMaxParticle() {
		return maxParticle;
	}

	/**
	 * Retourne color la couleur de l'explosion
	 * @return color la couleur de l'explosion
	 */
	public Color getColor() {
		return color;
	}

	/**
	 * Retourne la durée de vie de l'explosion
	 * @return lifeTime la durée de vie de l'explosion
	 */

	public double getLifeTime() {
		return lifeTime;
	}


}
