package starBallz.backend;

/**
 * La classe UserPlatform représente la plateforme contrôlée par 
 * l'utilisateur.
 * @author  Patrick Arsenault, Maxime Forgues, Francis Chandonnet
 */

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class UserPlatform 
{
	private static int MOVE_SPEED = 1;
	private double xPos;
	private double yPos;
	private double width;
	private double height;
	private Image image;

	/**
	 * Constructeur de la classe UserPlatform. Initialise l'image de l'objet
	 * platform ainsi que sa position.
	 * @param xPos La position en X.
	 * @param yPos La position en Y.
	 */
	public UserPlatform(double xPos, double yPos)
	{
		this.xPos = xPos;
		this.yPos = yPos;
		this.image = new Image("redPlatform.jpg");
		this.width = this.image.getWidth();
		this.height = this.image.getHeight();
	}
	
	/**
	 * Sert à modifier l'image de l'objet platform.
	 * @param imageURL Chemain d'accès à l'image de l'objet platform.
	 */
    public void setImage(String imageURL)
    {
    	this.image = new Image(imageURL);
    }

    /**
	 * Déplacer l'objet platform vers la droite.
	 */
	public void moveRight()
	{
		this.xPos += UserPlatform.MOVE_SPEED;
	}

	/**
	 * Déplacer l'objet platform vers la gauche.
	 */
	public void moveLeft()
	{
		this.xPos -=  UserPlatform.MOVE_SPEED;
	}
	
	/**
	 * Dessine l'image de l'objet platform sur l'objet canvas.
	 * @param gc Utilisé pour dessiner sur l'objet canvas.
	 */
	public void draw(GraphicsContext gc)
	{
		gc.drawImage(this.image, this.xPos, this.yPos, this.width, this.height);
	}

	/**
	 * @return La position en X de l'objet platform.
	 */
	public double getxPos() {
		return this.xPos;
	}

	/**
	 * @return La position en Y de l'objet platform.
	 */
	public double getyPos() {
		return this.yPos;
	}

	/**
	 * @return La largeur de l'objet platform.
	 */
	public double getWidth() {
		return this.width;
	}

	/**
	 * @return La hauteur de l'objet platform.
	 */
	public double getHeight() {
		return this.height;
	}

	/**
	 * Sert à modifier la position en X l'objet platform.
	 * @param La position en X l'objet platform.
	 */
	public void setxPos(double xPos) {
		this.xPos = xPos;
	}
	
	/**
	 * Sert à modifier la position en Y l'objet platform.
	 * @param La position en Y l'objet platform.
	 */
	public void setyPos(double yPos) {
		this.yPos = yPos;
	}
	
	
}