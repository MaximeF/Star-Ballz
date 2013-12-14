package starBallz.backend.test;

import org.junit.Assert;
import org.junit.Test;

import starBallz.backend.UserPlatform;

/**
 * La classe UserPlatformTest est une classe utilis�e pour tester les methodes de la classe UserPlatform.
 * @author  Patrick Arsenault, Maxime Forgues, Francis Chandonnet
 */
public class UserPlatformTest 
{
	/**
	 * Cette m�thode teste si le constructeur donne les bonnes valeurs au attributs de l'objet.
	 */
	@Test
	public void constructorTest()
	{
		UserPlatform platform = new UserPlatform(0, 0);
		Assert.assertEquals(0,platform.getxPos(),0);
		Assert.assertEquals(0,platform.getyPos(),0);
	}
	
	/**
	 * Cette m�thode teste si la fonction moveRight d�place la platforme vers la droite.
	 */
	@Test
	public void moveRightTest()
	{
		UserPlatform platform = new UserPlatform(0, 0);
		platform.moveRight();
		Assert.assertEquals(1,platform.getxPos(),0);
	}
	
	/**
	 * Cette m�thode teste si la fonction moveLeft d�place la platforme vers la gauche.
	 */
	@Test
	public void moveLeftTest()
	{
		UserPlatform platform = new UserPlatform(0, 0);
		platform.moveLeft();
		Assert.assertEquals(-1,platform.getxPos(),0);
	}
	
	/**
	 * Cette m�thode teste si la fonction setxPos fonctionne si on passe une valeur n�gative en param�tre.
	 */
	@Test
	public void setxPosNegativeTest()
	{
		UserPlatform platform = new UserPlatform(0, 0);
		platform.setxPos(-10);
		Assert.assertEquals(-10,platform.getxPos(),0);
	}
	
	/**
	 * Cette m�thode teste si la fonction setxPos fonctionne si on passe la valeur zero en param�tre.
	 */
	@Test
	public void setxPosZeroTest()
	{
		UserPlatform platform = new UserPlatform(0, 0);
		platform.setxPos(0);
		Assert.assertEquals(0,platform.getxPos(),0);
	}
	
	/**
	 * Cette m�thode teste si la fonction setxPos fonctionne si on passe une grande valeur en param�tre.
	 */
	@Test
	public void setxPosBigNumberTest()
	{
		UserPlatform platform = new UserPlatform(0, 0);
		platform.setxPos(99999);
		Assert.assertEquals(99999,platform.getxPos(),0);
	}
	
	/**
	 * Cette m�thode teste si la fonction setyPos fonctionne si on passe une valeur n�gative en param�tre.
	 */
	@Test
	public void setyPosNegativeTest()
	{
		UserPlatform platform = new UserPlatform(0, 0);
		platform.setyPos(-10);
		Assert.assertEquals(-10,platform.getyPos(),0);
	}
	
	/**
	 * Cette m�thode teste si la fonction setyPos fonctionne si on passe la valeur zero en param�tre.
	 */
	@Test
	public void setyPosZeroTest()
	{
		UserPlatform platform = new UserPlatform(0, 0);
		platform.setyPos(0);
		Assert.assertEquals(0,platform.getyPos(),0);
	}
	
	/**
	 * Cette m�thode teste si la fonction setyPos fonctionne si on passe une grande valeur en param�tre.
	 */
	@Test
	public void setyPosBigNumberTest()
	{
		UserPlatform platform = new UserPlatform(0, 0);
		platform.setyPos(99999);
		Assert.assertEquals(99999,platform.getyPos(),0);
	}
	
}