package starBallz.backend.explosion.test;


import javafx.scene.paint.Color;
import org.junit.Assert;
import org.junit.Test;

import starBallz.backend.explosion.Engine;

/**
 * Classe servant à tester Engine
 * 
 */
public class EngineTest {


	/**
	 * Test le constructeur d'Engine
	 * 
	 */
	@Test
	public void construtorTest()
	{
		Engine engine = new Engine();
		engine.setExplosion(0, 0, 50, Color.BLUE);
		Assert.assertEquals(true, engine.getExplosions().size() == 1);

	}

	@Test 
	public void refreshTest()
	{
		
	}


}


