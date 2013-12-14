package starBallz.backend;

/**
 * La classe Background représente l'arrière-plan lors d'une partie.
 * @author  Patrick Arsenault, Maxime Forgues, Francis Chandonnet
 */

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Background {

	private Image image;
	private double speed;
	private int xPosition;
	private double yPositions[];

	/**
	 * Constructeur de la classe Background.
	 * @param image Image de l'arrière-plan.
	 * @param speed Vitesse du défilement de l'arrière-plan.
	 * @param xPosition La position en X de l'image.
	 */
	public Background (Image image, double speed, int xPosition)
	{
		this.image = image;
		this.speed = speed;
		this.xPosition = xPosition;
		int numberOfPositions = 2; 
		this.yPositions = new double[numberOfPositions];
		for (int i = 0; i < yPositions.length; i++)
		{
			yPositions[i] = i * image.getHeight();
		}
	}

	/**
	 * Met à jour la position des images, créant l'effet de défilement.
	 */
	public void update()
	{
		for (int i = 0; i < this.yPositions.length; i++)
		{
			this.yPositions[i] += this.speed;
			if (this.yPositions[i] >= this.image.getHeight())
			{
				this.yPositions[i] = -this.image.getHeight();
			}
		}
	}

	/**
	 * Dessine les images d'arrière-plan sur l'objet canvas.
	 * @param gc Utilisé pour dessiner sur l'objet canvas.
	 */
	public void draw(GraphicsContext gc)
	{
		for (int i = 0; i < this.yPositions.length; i++)
		{
			gc.setGlobalAlpha(0.3);
			gc.drawImage(this.image, this.xPosition, (int)this.yPositions[i]);
		}
	}

	public double getSpeed() {
		return speed;
	}

	public int getxPosition() {
		return xPosition;
	}

	public double[] getyPositions() {
		return yPositions;
	}

	
}