package starBallz.backend;

/**
 * L'interface Explosive donne des caractéristiques explosives à l'objet 
 * Ballz.
 * @see Ballz
 * @see Engine
 * @author  Patrick Arsenault, Maxime Forgues, Francis Chandonnet
 */

import starBallz.backend.explosion.Engine;
import javafx.scene.paint.Color;

public interface Explosive
{
	/**
	 * @see Ballz#setExplosion(Engine, int, int, int, Color)
	 */
	public void setExplosion(Engine engine, int x, int y, int maxParticle,Color color);
}