package starBallz.backend;

/**
 * L'interface Bouncy donne des caractéristiques rebondissantes à l'objet 
 * Ballz.
 * @see Ballz
 * @author  Patrick Arsenault, Maxime Forgues, Francis Chandonnet
 */

public interface Bouncy 
{
	/**
	 * @see Ballz#sideRebound()
	 */
	public void sideRebound();
	
	/**
	 * @see Ballz#bottomRebound()
	 */
	public boolean bottomRebound();
}