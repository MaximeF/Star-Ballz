package starBallz.backend;

/**
 * La classe Ballz représente une balle dans le jeu.
 * @see Ball
 * @see Bouncy
 * @see Explosive
 * @author  Patrick Arsenault, Maxime Forgues, Francis Chandonnet
 */

import java.util.Random;

import starBallz.backend.explosion.Engine;

import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;


public class Ballz extends Ball implements Bouncy, Explosive
{

	private Paint colors[] = {javafx.scene.paint.Color.RED,javafx.scene.paint.Color.DARKTURQUOISE,javafx.scene.paint.Color.LIMEGREEN,javafx.scene.paint.Color.YELLOW,javafx.scene.paint.Color.DEEPPINK,javafx.scene.paint.Color.DARKVIOLET};
	private boolean rebounded = false;

	/**
	 * Constructeur de la classe Ballz. Utilise le constructeur de la classe 
	 * abstraite Ball. Détermine une couleur au hasard pour l'objet ballz
	 * créé.
	 * @param xPos La position en X.
	 * @param yPos La position en Y.
	 * @param size Le rayon de l'objet.
	 * @param xVel La vélocité en X.
	 * @param yVel La vélocité en Y.
	 * @see Ball
	 * @see Ball#setColor(Paint)
	 */
	public Ballz(double xPos, double yPos, int size, double xVel, double yVel)
	{
		super(xPos, yPos, size, xVel, yVel);
		Random random = new Random();
		int colorIndex = random.nextInt(6);
		super.setColor((Color)colors[colorIndex]);
	}

	/**
	 * Gère les rebonds sur le côté de l'objet ballz.
	 * @see Ball#setxVel(double)
	 * @see Ball#getxVel()
	 * @see Bouncy#sideRebound()
	 */
	@Override
	public void sideRebound()
	{
		super.setxVel(-super.getxVel());
	}
	
	/**
	 * Gère les rebonds sur le bas de l'objet ballz.
	 * @see Ball#setyVel(double)
	 * @see Ball#getyVel()
	 * @see Bouncy#bottomRebound()
	 * @return Vrai si l'objet ballz n'a pas encore rebondit sur l'objet 
	 * platform, sinon faux.
	 */
	@Override
	public boolean bottomRebound()
	{
		if(!this.rebounded)
		{
			super.setyVel(-super.getyVel());
			this.rebounded = true;
			return true;
		}
		else
		{
			return false;
		}

	}

	/**
	 * Gère les explosions lorsqu'un objet ballz entre en collision avec un 
	 * mur ou l'objet platform.
	 * @param engine Objet engine, qui gère les explosions.
	 * @param x Position du point central de l'explosion en X.
	 * @param y Position du point central de l'explosion en Y.
	 * @param maxParticle Le nombre maximum d'objet particle qui peuvent être créé.
	 * @param color Couleur des objets particle créés.
	 * @see Engine
	 * @see Explosive
	 * @see Explosive#setExplosion(Engine, int, int, int, Color)
	 */
	@Override
	public void setExplosion(Engine engine, int x, int y, int maxParticle,Color color) 
	{
		engine.setExplosion(x, y, maxParticle, color);
	}


}
