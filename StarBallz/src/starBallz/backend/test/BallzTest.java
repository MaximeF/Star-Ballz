package starBallz.backend.test;


import org.junit.Assert;
import org.junit.Test;
import starBallz.backend.Ballz;

/**
 * La classe BallzTest est une classe utilisée pour tester les methodes de la classe Ballz.
 * @author  Patrick Arsenault, Maxime Forgues, Francis Chandonnet
 */
public class BallzTest 
{
	/**
	 * Cette méthode teste si le constructeur avec 5 paramètres donne les bonnes valeurs au attributs de l'objet.
	 */
	@Test
	public void constructorFiveParametersTest()
	{
		Ballz testBallz = new Ballz(0, 0, 0, 0, 0);
		Assert.assertEquals(0,testBallz.getSize(),0);
		Assert.assertEquals(0,testBallz.getxPos(),0);
		Assert.assertEquals(0,testBallz.getxVel(),0);
		Assert.assertEquals(0,testBallz.getyPos(),0);
		Assert.assertEquals(0,testBallz.getyVel(),0);
		Assert.assertNotEquals(null,testBallz.getColor());
	}
	
	/**
	 * Cette méthode teste si la ballz rebondit et ne rebondit sur la platforme pas lorsqu'elle a déjà rebonti.
	 */
	@Test
	public void bottomReboundTwiceTest()
	{
		Ballz testBallz = new Ballz(0, 0, 0, 0, 0);
		Assert.assertEquals(true,testBallz.bottomRebound());
		Assert.assertEquals(false,testBallz.bottomRebound());
	}
	
	/**
	 * Cette méthode teste si la ballz rebondit sur la platforme et son déplacement en Y change de signe.
	 */
	@Test
	public void bottomReboundTest()
	{
		Ballz testBallz = new Ballz(0, 0, 0, 0, 1);
		Assert.assertEquals(true,testBallz.bottomRebound());
		Assert.assertEquals(-1,testBallz.getyVel(),0);
	}

	/**
	 * Cette méthode teste si la ballz rebondit sur le côté et son déplacement en X change de signe.
	 */
	@Test
	public void sideReboundTest()
	{
		Ballz testBallz = new Ballz(0, 0, 0, 1, 0);
		testBallz.sideRebound();
		Assert.assertEquals(-1,testBallz.getxVel(),0);
	}
	
	
}
