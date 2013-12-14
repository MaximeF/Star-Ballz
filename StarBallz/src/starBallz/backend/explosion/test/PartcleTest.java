package starBallz.backend.explosion.test;

import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.junit.Test;

import javafx.scene.paint.Color;
import starBallz.backend.explosion.Particle;
/**
 * Classe servant à tester Particle
 * @author Maxime Forgues, Patrick Arsenault, Françis Chandonnet
 *
 */
public class PartcleTest {

	/**
	 * Test les valeurs attribués à l'objet par le constructeur
	 */
	@Test
	public void constructorTest()
	{
		Particle particle = new Particle(0, 0, 0, 0, Color.BEIGE, 2, 1000);
		Assert.assertEquals(true, particle.getxPos() == 0);
		Assert.assertEquals(true, particle.getyPos() == 0);
		Assert.assertEquals(true, particle.getyVel() == 0);
		Assert.assertEquals(true, particle.getxVel() == 0);
		Assert.assertEquals(true, particle.getSize() == 2);
		Assert.assertEquals(true, particle.getColor() == Color.BEIGE);
	}



	/**
	 * Test si la particle est morte après le temps de vie déterminé
	 */
	@Test
	public void isDeadTest()
	{
		Particle particle = new Particle(0, 0, 0, 0, Color.BEIGE, 2, 1000);
		try {
			TimeUnit.MILLISECONDS.sleep(1001);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Assert.assertEquals(true, particle.isDead());
	}

	/**
	 * Test si la particle est morte après le temps de vie déterminé
	 */
	@Test
	public void isNotDeadTest()
	{
		Particle particle = new Particle(0, 0, 0, 0, Color.BEIGE, 2, 1000);
		try {
			TimeUnit.MILLISECONDS.sleep(900);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Assert.assertEquals(false, particle.isDead());
	}

	/**
	 * Test la méthode move en vérifant leur position en x et en y
	 */
	@Test
	public void testMove()
	{
		Particle particle = new Particle(0, 0, 10, 10, Color.BEIGE, 2, 1000);
		particle.move();
		Assert.assertEquals(true, particle.getxPos() == 10);
		Assert.assertEquals(true, particle.getyPos() == 10);


	}
	/**
	 * Test la méthode setColor et vérifie si la bonne couleur est retournée
	 */
	@Test
	public void setColor()
	{
		Particle particle = new Particle(0, 0, 10, 10, Color.BEIGE, 2, 1000);
		particle.setColor(Color.BLUE);
		Assert.assertEquals(true, particle.getColor() == Color.BLUE);

	}

}
