package starBallz.backend;

/**
 * La classe abstraite Ball repr�sente des caract�ristiques communes 
 * de l'objet ballz et de l'objet particle.
 * @see Ballz
 * @see Particle
 * @author  Patrick Arsenault, Maxime Forgues, Francis Chandonnet
 */

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Paint;

public abstract class Ball {

	private double xPos;
	private double yPos;
	private double xVel;
	private double yVel;
	private double size;
	private Paint color; 

	/**
	 * Constructeur de la classe abstraite Ball, servant pour l'objet
	 * particle.
	 * @param xPos La position en X.
	 * @param yPos La position en Y.
	 * @param size Le rayon de l'objet.
	 * @param xVel La v�locit� en X.
	 * @param yVel La v�locit� en Y.
	 * @param color Couleur de l'objet.
	 */
	public Ball(double xPos, double yPos, int size, double xVel, double yVel, Paint color)
	{
		this.xPos = xPos;
		this.yPos = yPos;
		this.xVel = xVel;
		this.yVel = yVel;
		this.size = size;
		this.color = color;
	}

	/**
	 * Constructeur de la classe abstraite Ball, servant pour l'objet ballz.
	 * @param xPos La position en X.
	 * @param yPos La position en Y.
	 * @param size Le rayon de l'objet.
	 * @param xVel La v�locit� en X.
	 * @param yVel La v�locit� en Y.
	 */
	public Ball(double xPos, double yPos, int size, double xVel, double yVel)
	{
		this.xPos = xPos;
		this.yPos = yPos;
		this.xVel = xVel;
		this.yVel = yVel;
		this.size = size;
		this.color = null;
	}

	/**
	 * D�termine le mouvement de l'objet (ballz ou particle).
	 */
	public void move() 
	{
		this.xPos += this.xVel;
		this.yPos += this.yVel;
	}

	/**
	 * Sert � dessiner l'objet (ballz ou particle) sur l'objet canvas.
	 * @param gc Utilis� pour dessiner sur l'objet canvas.
	 */
	public void draw(GraphicsContext gc) 
	{
		gc.setFill(this.color);
		gc.fillOval(this.xPos-this.size, this.yPos-this.size, this.size + this.size, this.size + this.size);
	}

	/**
	 * @return La position en X de l'objet (ballz ou particle).
	 */
	public double getxPos() {
		return xPos;
	}

	/**
	 * @return La position en Y de l'objet (ballz ou particle).
	 */
	public double getyPos() {
		return yPos;
	}

	/**
	 * @return La v�locit� en X de l'objet (ballz ou particle).
	 */
	public double getxVel() {
		return xVel;
	}
	
	/**
	 * @return La v�locit� en Y de l'objet (ballz ou particle).
	 */
	public double getyVel() {
		return yVel;
	}
	
	/**
	 * Sert � modifier la v�locit� en X de l'objet (ballz ou particle).
	 * @param xVel La v�locit� en X.
	 */
	public void setxVel(double xVel) {
		this.xVel = xVel;
	}
	
	/**
	 * Sert � modifier la v�locit� en Y de l'objet (ballz ou particle).
	 * @param yVel La v�locit� en Y.
	 */
	public void setyVel(double yVel) {
		this.yVel = yVel;
	}
	
	/**
	 * @return size Le rayon de l'objet (ballz ou particle).
	 */
	public double getSize() {
		return size;
	}

	/**
	 * Sert � modifier la couleur de l'objet (ballz ou particle).
	 * @param color La couleur de l'objet (ballz ou particle).
	 */
	public void setColor(Paint color) {
		this.color = color;
	}

	/**
	 * @return La couleur de l'objet (ballz ou particle).
	 */
	public Paint getColor() {
		return color;
	}

}
