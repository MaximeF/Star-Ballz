package starBallz.backend.explosion.test;

import java.util.concurrent.TimeUnit;

import javafx.scene.paint.Color;

import org.junit.Assert;
import org.junit.Test;

import starBallz.backend.explosion.Explosion;

/**
 * Classe servant � tester Explosion
 * @author Maxime Forgues, Patrick Arsenault, Fran�is Chandonnet
 *
 */
public class ExplosionTest {
	
	/**
	 * Test le constructeur de Explosion et v�rifit leur valeur attribu�s
	 */
	@Test
	public void constructorTest()
	{
		Explosion explosion = new Explosion(0, 0, 10, Color.BLUE);
		Assert.assertEquals(true, explosion.getPosX() == 0);
		Assert.assertEquals(true, explosion.getPosY() == 0);
		Assert.assertEquals(true, explosion.getParticles().size() == 10);
		Assert.assertEquals(true, explosion.getColor() == Color.BLUE);
	}
	
	/**
	 * Test si l'explosion est morte apr�s le temps de vie d�termin�
	 */
	@Test
	public void isDeadTest()
	{
		Explosion explosion = new Explosion(0, 0, 10, Color.BLUE);
		try {
			TimeUnit.MILLISECONDS.sleep((long) explosion.getLifeTime());
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Assert.assertEquals(true, explosion.isDead());
	}
	

	
	

}
