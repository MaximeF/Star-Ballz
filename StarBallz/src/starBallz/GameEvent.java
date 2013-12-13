package starBallz;


import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import javafx.application.Platform;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import starBallz.backend.Background;
import starBallz.backend.Ballz;
import starBallz.backend.UserPlatform;
import starBallz.backend.explosion.Engine;

public class GameEvent {

	private int stageHeight;
	private int stageWidth;
	private Engine engine = new Engine();
	private String songFileName;
	private ArrayList<Integer> timeList = new ArrayList<Integer>();
	private static final int MAXRADIUS = 16; 
	private Timer ballzSpawnTimer = new Timer();
	public int currentFPS = 0;
	public int FPS = 0;
	public long start = 0;
	private Background background = new Background(new Image("starscape.jpg"), 4, 0);
	private ArrayList<Ballz> ballzList = new ArrayList<Ballz>();
	private UserPlatform platform;
	private float hitBallzNb = 0;
	private float totalBallzNb = 0;
	private float score = 0;


	public GameEvent(int width, int height, String song)
	{
		this.stageWidth = width;
		this.stageHeight = height;
		this.songFileName = song;
		this.platform = new UserPlatform(this.stageWidth/2 -50, this.stageHeight - 20);
		this.fillTimeList();
		this.ballzSpawnTimer.schedule(new BallzSpawnTimer(), 30, 1);

	}

	public void move()
	{
		if (this.platform.getxPos() < 0)
		{
			this.platform.setxPos(0);
		}
		if (this.platform.getxPos() + this.platform.getWidth() > this.stageWidth)
		{
			this.platform.setxPos( this.stageWidth - this.platform.getWidth());
		}
		for (int i = 0; i < this.ballzList.size() ; i++)
		{
			Ballz b = this.ballzList.get(i);
			if (this.plateformCollison(b))
			{

				if(b.bottomRebound())
				{
					this.hitBallzNb ++;
				}
				b.setExplosion(this.engine, (int) (b.getxPos() + b.getSize()), (int) (b.getyPos() + b.getSize()), 250,(Color) b.getColor());
				if (b.getColor()==Color.RED)
				{
					this.platform.setImage("redPlatform.jpg");
				}
				else if (b.getColor()==Color.DARKTURQUOISE)
				{
					this.platform.setImage("turquoisePlatform.jpg");
				}
				else if (b.getColor()==Color.LIMEGREEN)
				{
					this.platform.setImage("greenPlatform.jpg");
				}
				else if (b.getColor()==Color.YELLOW)
				{
					this.platform.setImage("yellowPlatform.jpg");
				}
				else if (b.getColor()==Color.DEEPPINK)
				{
					this.platform.setImage("pinkPlatform.jpg");
				}
				else if (b.getColor()==Color.DARKVIOLET)
				{
					this.platform.setImage("violetPlatform.jpg");
				}
				b.setColor(Color.GRAY);

			}

			if (b.getxPos() - b.getSize() <= 0)
			{
				b.sideRebound();
				b.setExplosion(this.engine,(int) b.getxPos(), (int) (b.getyPos() + b.getSize()), 250, (Color)b.getColor());

			}
			if (b.getxPos() + b.getSize() >= this.stageWidth)
			{
				b.sideRebound();
				b.setExplosion(this.engine,(int) (b.getxPos() + b.getSize()), (int) (b.getyPos() + b.getSize()), 250, (Color)b.getColor());
			}


			if (b.getyPos() + b.getSize() <= 0||b.getyPos() >= this.stageHeight)
			{

				this.ballzList.remove(b);
			}

			b.move();
		}
	}

	public boolean plateformCollison(Ballz ballz)
	{
		if(ballz.getyPos() + ballz.getSize() <= this.platform.getyPos())
		{
			return false;
		}
		if(ballz.getyPos() >= this.platform.getyPos() + this.platform.getHeight())
		{
			return false;
		}
		if(ballz.getxPos() + ballz.getSize()<= this.platform.getxPos())
		{
			return false;
		}
		if(ballz.getxPos() >= this.platform.getxPos() + this.platform.getWidth())
		{
			return false;
		}
		return true;
	}

	public void createBallz()
	{
		Random random = new Random();
		double randomX = (double)random.nextInt(this.stageWidth-40) + 20;
		double randomVelX = (((double)random.nextInt(360) + 1)/(double)100)* (double)Math.pow(-1,randomX);
		Ballz ballz = new Ballz(randomX,-10,MAXRADIUS,randomVelX, 7);
		this.ballzList.add(ballz);
	}

	public void fillTimeList()
	{
		BufferedReader reader;
		try {
			reader = new BufferedReader(new FileReader("ressources/"+this.songFileName));
			String line = reader.readLine();
			line = reader.readLine();
			line = reader.readLine();
			while (line != null) 
			{
				try 
				{
					this.timeList.add(Integer.parseInt(line));
				}
				catch(NumberFormatException ex){
					this.timeList.add(1);
				}
				line = reader.readLine();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		this.totalBallzNb = this.timeList.size();
	}

	private class BallzSpawnTimer extends TimerTask
	{
		private int ballzIterator = 0;
		private int timeIterator = 0;

		@Override
		public void run()
		{
			Platform.runLater(new Runnable() 
			{


				public void run()
				{
					if (timeIterator == timeList.get(ballzIterator) && ballzIterator < timeList.size()-1)
					{
						createBallz();
						timeIterator = 0;
						ballzIterator++;
					}
					timeIterator++;
				}
			});
		}
	}

	public void draw(GraphicsContext gc)
	{
		this.background.draw(gc);
		gc.setGlobalAlpha(1);
		this.engine.draw(gc);
		this.platform.draw(gc);

		for(int i = 0; i < this.ballzList.size(); i++)
		{
			this.ballzList.get(i).draw(gc);
		}

		gc.setFill(Color.RED);
		Font font = new Font("Dialog", 16);
		gc.setFont(font);
		gc.fillText("Score "+this.getScore() +"% Hits "+ this.hitBallzNb + " Total "+ this.totalBallzNb, 20, 20);

	}

	public void refresh()
	{
		this.background.update();
		this.engine.refresh();
		this.move();

	}

	public UserPlatform getPlatform()
	{
		return this.platform;

	}
	
	public float getScore()
	{
		this.score = (this.hitBallzNb/ this.totalBallzNb)*100;
		
		return this.score;
		
	}

	public void tick() 
	{
		currentFPS++;
		if(System.nanoTime() - start >= 1000000000) 
		{
			FPS = currentFPS;
			currentFPS = 0;
			start = System.nanoTime();
		}
	}

	public int getFPS() {
		return FPS;
	}

}


