package starBallz.backend.test;

import org.junit.Assert;
import org.junit.Test;

import starBallz.backend.Ballz;
import starBallz.backend.GameEvent;

/**
 * 
 * @author Maxime Forgues, Patrick Arsenault, Françis Chandonnet
 *
 */
public class GameEventTest {

	/**
	 * Test le constructeur
	 */
	private GameEvent gameEvent = new GameEvent(500, 500, "Tetris.txt");
	@Test
	public void constructorTest()
	{
		Assert.assertEquals(500, gameEvent.getStageHeight());
		Assert.assertEquals(500, gameEvent.getStageWidth());
		Assert.assertEquals("Tetris.txt", gameEvent.getSongFileName());
	}

	/**
	 * Test la collision entre une Ballz et une Plateform
	 */
	@Test
	public void collisonTest()
	{

		Ballz ballz = new Ballz(150,630 , 5, 0, 0);
		gameEvent.getPlatform().setxPos(150);
		gameEvent.getPlatform().setyPos(630);
		Assert.assertEquals(true, gameEvent.plateformCollison(ballz));
	}

	/**
	 * Test la collision entre une Ballz et une Plateform
	 */
	@Test
	public void notCollisonTest()
	{

		Ballz ballz = new Ballz(0, 0 , 5, 0, 0);
		Assert.assertEquals(false, gameEvent.plateformCollison(ballz));
	}

	/**
	 * Test la création d'une Ballz
	 */
	@Test
	public void createBallzTest()
	{
		GameEvent gameEvent = new GameEvent(500, 500, "Tetris.txt");
		double initBallzNb = gameEvent.getBallzList().size();
		gameEvent.createBallz();
		Assert.assertEquals(initBallzNb + 1, gameEvent.getBallzList().size(), 0);
	}

	/**
	 * Test le score
	 */
	@Test
	public void scoreTest()
	{

		Assert.assertEquals(0, gameEvent.getScore(), 0);
	}
	
	/**
	 * Test fillTimeList()
	 */
	@Test
	public void notEmptyTimeList()
	{
		Assert.assertEquals(false, gameEvent.getTimeList().isEmpty());
	}


}
