package starBallz.backend;


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
import starBallz.backend.explosion.Engine;

/**
 * Classe servent à gérer la logique du jeu
 * @author Maxime Forgues, Patrick Arsenault, Françis Chandonnet
 *
 */
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
	private int returnedBallz = 0;
	private int ballzTotal = 0;
	private ArrayList<Ballz> ballzList = new ArrayList<Ballz>();
	private UserPlatform platform = new UserPlatform(150, 630);
	private float hitBallzNb = 0;
	private float totalBallzNb = 0;
	private float score = 0;
	private int ballzIterator = 0;
	private boolean running = true;


	/**
	 * Initialise une partie en préparant les balles à être lancées
	 * @param width la lageur de la fenêtre où jouer
	 * @param height la hauter de la fenêtre où y jouer
	 * @param song nom de la chanson à jouer
	 */
	public GameEvent(int width, int height, String song)
	{
		this.stageWidth = width;
		this.stageHeight = height;
		this.songFileName = song;
		this.platform = new UserPlatform(this.stageWidth/2 -50, this.stageHeight - 20);
		this.fillTimeList();
		this.ballzSpawnTimer.schedule(new BallzSpawnTimer(), 30, 1);

	}

	/**
	 * Déplace chacun des boules et vérifie les collisions avec les bordures et la plateforme
	 * Gère les rebonds et crée les explosions
	 */
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
				this.returnedBallz++;
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


			if (b.getyPos() + b.getSize() <= 0||b.getyPos() - b.getSize() >= this.stageHeight)
			{

				this.ballzList.remove(b);
			}

			b.move();
		}
	}

	/**
	 * Vérifie les collisions entre la plateforme et les ballz
	 * @param ballz une Balle
	 * @return false si il n'y a aucun contact, sinon true
	 */
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

	/**
	 * Crée une Ballz et l'ajoute dans l'array
	 */
	public void createBallz()
	{
		Random random = new Random();
		double randomX = (double)random.nextInt(this.stageWidth-40) + 20;
		double randomVelX = (((double)random.nextInt(360) + 1)/(double)100)* (double)Math.pow(-1,randomX);
		Ballz ballz = new Ballz(randomX,-10,MAXRADIUS,randomVelX, 7);
		this.ballzList.add(ballz);
	}

	/**
	 * Remplie la liste qui sert à déterminer quand lancer une Ballz
	 */
	public void fillTimeList()
	{
		BufferedReader reader;
		try {
			reader = new BufferedReader(new FileReader("ressources/"+this.songFileName));
			String line = reader.readLine();
			line = reader.readLine();
			line = reader.readLine();

			while(line != null)
			{
				try 
				{
					this.timeList.add(Integer.parseInt(line));
					this.ballzTotal++;
				}
				catch(NumberFormatException ex){
					this.timeList.add(1);
				}
				line = reader.readLine();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		this.totalBallzNb = this.timeList.size() - 1;
	}

	private class BallzSpawnTimer extends TimerTask
	{                 
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

	/**
	 * Dessine les éléments graphiques sur le canvas
	 * @param gc GraphicsContext du canvas
	 */
	public void draw(GraphicsContext gc)
	{
		if(this.running)
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
		else
		{
			gc.setFill(Color.RED);
			Font font = new Font("Dialog", 16);
			gc.setFont(font);
			gc.fillText("Appuies sur Backspace pour retourner au menu", this.stageWidth/2 - 150, this.stageHeight/2);
		}

	}

	/**
	 * Met à jour les éléments logique du jeu et vérifie la fin d'une partie
	 */
	public void refresh()
	{
		if(running)
		{
			this.background.update();
			this.engine.refresh();
			this.move();
		}
		if(this.ballzList.isEmpty() && this.ballzIterator == this.timeList.size() - 1)
		{
			running = false;
		}

	}

	/**
	 * 
	 * @return platform la plateform de l'utilisateur
	 */
	public UserPlatform getPlatform()
	{
		return this.platform;

	}

	/**
	 * Le pourcentage de Ballz renvoyées
	 * @return score le pointage de la partie
	 */
	public float getScore()
	{
		this.score = (this.hitBallzNb/ this.totalBallzNb)*100;

		return this.score;

	}

	/**
	 * Calcule de nombre d'images par seconde
	 */
	public void tick() 
	{
		currentFPS++;
		if(System.nanoTime() - start >= 1000000000) 
		{
			FPS = this.returnedBallz;
			currentFPS = 0;
			start = System.nanoTime();
		}
	}


	/**
	 * 
	 * @return FPS nombre d'images par seconde
	 */
	public int getFPS() {
		return FPS;
	}

	/**
	 * 
	 * @return returnBallz le nombrede Ballz retournées
	 */
	public double getReturnedBallz() {
		return this.returnedBallz;
	}

	/**
	 * 
	 * @return ballzTotal le nombre Ballz total
	 */
	public double getBallzTotal() {
		return this.ballzTotal;
	}

	/**
	 * 
	 * @return stageHieght la hauteur de la fenêtre
	 */
	public int getStageHeight() {
		return stageHeight;
	}

	/**
	 * 
	 * @return stageWidth la largeur de la fenêtre
	 */
	public int getStageWidth() {
		return stageWidth;
	}

	/**
	 * 
	 * @return sonfFileName le nom de la chanson
	 */
	public String getSongFileName() {
		return songFileName;
	}

	/**
	 * 
	 * @return ballzList la liste de Ballz
	 */
	public ArrayList<Ballz> getBallzList() {
		return ballzList;
	}

	/**
	 * 
	 * @return timeList la list contenant le temps de départ des Ballz
	 */
	public ArrayList<Integer> getTimeList() {
		return timeList;
	}
	

}


