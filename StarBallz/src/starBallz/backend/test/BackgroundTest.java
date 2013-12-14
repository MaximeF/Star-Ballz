package starBallz.backend.test;

import javafx.scene.image.Image;

import org.junit.Assert;
import org.junit.Test;

import starBallz.backend.Background;
/**
 * Classe qui test Background
 *
 */
public class BackgroundTest {


	/**
	 * Test le constructeur
	 */
	@Test
	public void constructorTest()
	{
		Background background = new Background(new Image("starscape.jpg"), 4, 0);
		Assert.assertEquals(4, background.getSpeed(), 0);
		Assert.assertEquals(0, background.getxPosition(), 0);
	}

	/**
	 * Test update en vérifiant si la position en y à été modifiée
	 */
	@Test
	public void updateTest()
	{
		Background background = new Background(new Image("starscape.jpg"), 4, 0);
		double oldPos = background.getyPositions()[0];
		background.update();
		Assert.assertEquals(oldPos + background.getSpeed() , background.getyPositions()[0], 0);
		
	}

}
