package starBallz.backend.explosion;

import starBallz.backend.Ball;
import javafx.scene.paint.Paint;
/**
 * Classe servant � cr�er une particle
 * @author Maxime Forgues, Patrick Arsenault, Fran�is Chandonnet
 *
 */

public class Particle extends Ball {

	private double endTime;

	/**
	 * 
	 * @param xPos position en x
	 * @param yPos position en y
	 * @param xVel v�locit� en x
	 * @param yVel v�locit� en y
	 * @param color couleur de la particle
	 * @param size rayon de la particle
	 * @param lifeTime temps de vie de particle
	 */
	public Particle(double xPos, double yPos, double xVel, double yVel, Paint color, int size, int lifeTime) 
	{
		super(xPos, yPos, size, xVel, yVel, color);
		this.endTime = System.currentTimeMillis() + lifeTime;

	}

	/**
	 * 
	 * @return vrai si le temps de vie de la particle est �coul�
	 */
	public boolean isDead()
	{
		return (System.currentTimeMillis() > this.endTime);
	}

	





}
