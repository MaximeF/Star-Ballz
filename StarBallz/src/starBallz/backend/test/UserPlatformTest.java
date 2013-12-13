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
	 * Cette méthode teste si le constructeur avec 5 paramètres donne les bonnes valeurs au attributs de l'objet.
	 */
	@Test
	public void constructorFiveParametersTest()
	{
		UserPlatform platform = new UserPlatform(0, 0);
		Assert.assertEquals(0,platform.getxPos(),0);
		Assert.assertEquals(0,platform.getyPos(),0);
	}
	
}
