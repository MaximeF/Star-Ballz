package starBallz.backend.test;

import org.junit.Assert;
import org.junit.Test;

import starBallz.backend.UserPlatform;

/**
 * La classe UserPlatformTest est une classe utilisée pour tester les methodes de la classe UserPlatform.
 * @author  Patrick Arsenault, Maxime Forgues, Francis Chandonnet
 */
public class UserPlatformTest 
{
	/**
	 * Cette méthode teste si le constructeur donne les bonnes valeurs au attributs de l'objet.
	 */
	@Test
	public void constructorTest()
	{
		UserPlatform platform = new UserPlatform(0, 0);
		Assert.assertEquals(0,platform.getxPos(),0);
		Assert.assertEquals(0,platform.getyPos(),0);
	}
	
	/**
	 * Cette méthode teste si la fonction moveRight déplace la platforme vers la droite.
	 */
	@Test
	public void moveRightTest()
	{
		UserPlatform platform = new UserPlatform(0, 0);
		platform.moveRight();
		Assert.assertEquals(1,platform.getxPos(),0);
	}
	
	/**
	 * Cette méthode teste si la fonction moveLeft déplace la platforme vers la gauche.
	 */
	@Test
	public void moveLeftTest()
	{
		UserPlatform platform = new UserPlatform(0, 0);
		platform.moveLeft();
		Assert.assertEquals(-1,platform.getxPos(),0);
	}
	
	/**
	 * Cette méthode teste si la fonction setxPos fonctionne si on passe une valeur négative en paramètre.
	 */
	@Test
	public void setxPosNegativeTest()
	{
		UserPlatform platform = new UserPlatform(0, 0);
		platform.setxPos(-10);
		Assert.assertEquals(-10,platform.getxPos(),0);
	}
	
	/**
	 * Cette méthode teste si la fonction setxPos fonctionne si on passe la valeur zero en paramètre.
	 */
	@Test
	public void setxPosZeroTest()
	{
		UserPlatform platform = new UserPlatform(0, 0);
		platform.setxPos(0);
		Assert.assertEquals(0,platform.getxPos(),0);
	}
	
	/**
	 * Cette méthode teste si la fonction setxPos fonctionne si on passe une grande valeur en paramètre.
	 */
	@Test
	public void setxPosBigNumberTest()
	{
		UserPlatform platform = new UserPlatform(0, 0);
		platform.setxPos(99999);
		Assert.assertEquals(99999,platform.getxPos(),0);
	}
	
	/**
	 * Cette méthode teste si la fonction setyPos fonctionne si on passe une valeur négative en paramètre.
	 */
	@Test
	public void setyPosNegativeTest()
	{
		UserPlatform platform = new UserPlatform(0, 0);
		platform.setyPos(-10);
		Assert.assertEquals(-10,platform.getyPos(),0);
	}
	
	/**
	 * Cette méthode teste si la fonction setyPos fonctionne si on passe la valeur zero en paramètre.
	 */
	@Test
	public void setyPosZeroTest()
	{
		UserPlatform platform = new UserPlatform(0, 0);
		platform.setyPos(0);
		Assert.assertEquals(0,platform.getyPos(),0);
	}
	
	/**
	 * Cette méthode teste si la fonction setyPos fonctionne si on passe une grande valeur en paramètre.
	 */
	@Test
	public void setyPosBigNumberTest()
	{
		UserPlatform platform = new UserPlatform(0, 0);
		platform.setyPos(99999);
		Assert.assertEquals(99999,platform.getyPos(),0);
	}
	
}