package starBallz;

import java.io.File;
import java.util.ArrayList;
import java.util.Random;
import java.util.Timer;
import starBallz.UserPlatform;
import java.util.TimerTask;
import particle.engine.Engine;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;


public class StarBallz extends Application
{
	
	private Group root = null;
	private Stage stage = null;
	private Scene scene = null;
	private UserPlatform userPlatform = null;
	private int distanceFromBottom = 20;
	ArrayList<Ballz> ballzList = new ArrayList<Ballz>();
	private boolean isLeftDown = false;
	private boolean isRightDown = false;
	private Engine engine = new Engine();
	private int ballzIterator;
	private int timeIterator;
	private ArrayList<Integer> timeList;
	private Game game = null;
	private String songFileName;
	private Timer ballzMovementTimer = null;
	private Timer ballzSpawnTimer = null;
	private static final int MAXRADIUS = 16; 
	private static final int STAGEHEIGHT = 800;
	private static final int STAGEWIDTH = 500;
	
	
	public static void main(String[] args)
	{
		launch(args);
	}
	
	public StarBallz(String fileName,Stage stage)
	{
		this.songFileName = fileName;
		this.stage = stage;
	}
	
	@Override
	public void start(Stage stage) throws Exception
	{
		this.game = new Game();
		this.ballzList = new ArrayList<Ballz>();
		this.ballzIterator = 0;
		this.timeIterator = 0;
		this.timeList = new ArrayList<Integer>();
		this.game.fillTimeList(this.timeList,this.songFileName);
		this.root = new Group();
	    this.scene = new Scene(root, STAGEWIDTH, STAGEHEIGHT, Color.BLACK);
		this.scene.setOnKeyPressed(new arrowKeyListener());
		this.scene.setOnKeyReleased(new arrowKeyReleaseListener());
		this.scene.setOnMouseMoved(new mouseMouvementListener());
		this.stage.setOnCloseRequest(new closeAppListener());
		this.stage.setTitle("StarBallz");
		this.stage.setResizable(false);
		this.root.getChildren().add(this.engine);
		this.createPlatform();
		this.stage.setScene(scene);
		this.stage.show();
		this.startTimers();
		this.startMusic();
	}
	
	public void startMusic()
	{
		String source = new File("ressources/"+songFileName+".mp3").toURI().toString();
		Media media = null;
		media = new Media(source);
		MediaPlayer mediaPlayer = new MediaPlayer(media);
		mediaPlayer.play();
	}
	
	public void startTimers()
	{
		this.ballzMovementTimer = new Timer();
		this.ballzMovementTimer.schedule(new BallzMovementTimer(), 1, 1);
		this.ballzSpawnTimer = new Timer();
		this.ballzSpawnTimer.schedule(new BallzSpawnTimer(), 1, 1);
	}
	
	private class arrowKeyReleaseListener implements EventHandler<KeyEvent>
	{
		public void handle(KeyEvent e)
		{
			if (e.getCode()==KeyCode.LEFT)
			{
				isLeftDown = false;
			}
			else if (e.getCode()==KeyCode.RIGHT)
			{
				isRightDown = false;
			}
		}
	}
	
	private class closeAppListener implements EventHandler<WindowEvent>
	{
		public void handle(WindowEvent e)
		{
			ballzMovementTimer.cancel();
			ballzSpawnTimer.cancel();
			try {
				StarBallz.this.stop();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
	}
	
	private class mouseMouvementListener implements EventHandler<MouseEvent>
	{
		public void handle(MouseEvent e)
		{
			if (e.getSceneX()<StarBallz.this.scene.getWidth()-StarBallz.this.userPlatform.getWidth())
			{
				StarBallz.this.userPlatform.setX(e.getSceneX()); 
			}
		}
	}
	
	private class arrowKeyListener implements EventHandler<KeyEvent>
	{
		public void handle(KeyEvent e)
		{
			if (e.getCode()==KeyCode.LEFT)
			{
				isLeftDown = true;
			}
			else if (e.getCode()==KeyCode.RIGHT)
			{
				isRightDown = true;
			}
		}
	}
	
	
	
	public void createPlatform()
	{
		this.userPlatform = new UserPlatform(this.scene.getWidth()/2-(100/2),this.scene.getHeight()-this.distanceFromBottom,100,15);
		this.engine.getChildren().add(this.userPlatform);
	}
	
	public void createBallz()
	{
		Random random = new Random();
		double randomX = (double)random.nextInt((int)this.scene.getWidth()-40) + 20;
		double randomVelX = (((double)random.nextInt(59) + 1)/(double)100)* (double)Math.pow(-1,randomX);
		Ballz ballz = new Ballz(randomX,-10,MAXRADIUS,randomVelX,0.5);
		this.ballzList.add(ballz);
		root.getChildren().add(ballz);
	}
	
	
	
	private class BallzSpawnTimer extends TimerTask
	{
		@Override
		public void run()
		{
			
			Platform.runLater(new Runnable() 
			{
				public void run()
				{
					if (timeIterator==timeList.get(ballzIterator)&&ballzIterator<timeList.size()-1)
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
	
	public void setPlatformImage(Paint paint)
	{
		if (paint==javafx.scene.paint.Color.RED)
		{
			StarBallz.this.userPlatform.setFill(new ImagePattern(new Image("redPlatform.jpg")));
		}
		else if (paint==javafx.scene.paint.Color.DARKTURQUOISE)
		{
			StarBallz.this.userPlatform.setFill(new ImagePattern(new Image("turquoisePlatform.jpg")));
		}
		else if (paint==javafx.scene.paint.Color.LIMEGREEN)
		{
			StarBallz.this.userPlatform.setFill(new ImagePattern(new Image("greenPlatform.jpg")));
		}
		else if (paint==javafx.scene.paint.Color.DEEPPINK)
		{
			StarBallz.this.userPlatform.setFill(new ImagePattern(new Image("pinkPlatform.jpg")));
		}
		else if (paint==javafx.scene.paint.Color.YELLOW)
		{
			StarBallz.this.userPlatform.setFill(new ImagePattern(new Image("yellowPlatform.jpg")));
		}
		else if (paint==javafx.scene.paint.Color.DARKVIOLET)
		{
			StarBallz.this.userPlatform.setFill(new ImagePattern(new Image("violetPlatform.jpg")));
		}
	}
	private class BallzMovementTimer extends TimerTask
	{
		
		@Override		
		public void run()
		{
			Platform.runLater(new Runnable() {
				public void run() 
				{
					
					if (StarBallz.this.userPlatform.getX()>0)
					{
						if (isLeftDown)
						{
							userPlatform.moveLeft();
						}
					}
					
					if (StarBallz.this.userPlatform.getX()+StarBallz.this.userPlatform.getWidth()<StarBallz.this.scene.getWidth())
					{
						if (isRightDown)
						{
							userPlatform.moveRight();
						}
					}
					game.removeNullsFromList(StarBallz.this.ballzList);
					for (Ballz b : StarBallz.this.ballzList)
					{
						if (b != null)
						{
							
							if (b.intersects(StarBallz.this.userPlatform.getBoundsInLocal()))
							{
								if (b.getCenterY()+b.getRadius()==StarBallz.this.userPlatform.getY())
								{
									if (b.getCenterX()>StarBallz.this.userPlatform.getX()-b.getRadius()&&b.getCenterX()<StarBallz.this.userPlatform.getX()+StarBallz.this.userPlatform.getWidth()+b.getRadius())
									{
										b.bottomRebound();
										//StarBallz.this.engine.setExplosion((int) b.getCenterX(), (int) (b.getCenterY() + b.getRadius()), 250,(Color)b.getFill());
										userPlatform.setDropShadowColor((Color)b.getFill());
										setPlatformImage(b.getFill());
										b.setFill(Color.DARKGRAY);
									}
									//else
									{
										//StarBallz.this.testBallz.sideRebound();
									}
								}
							}
							b.move();
							if (b.getCenterX()-b.getRadius()<=0||b.getCenterX()+b.getRadius()>= StarBallz.this.scene.getWidth())
							{
								b.sideRebound();
								//StarBallz.this.engine.setExplosion((int) b.getCenterX(), (int) (b.getCenterY() + b.getRadius()), 250,(Color)b.getFill());
							}

							if (b.getCenterY()+b.getRadius()<=0||b.getCenterY()-b.getRadius()>=StarBallz.this.scene.getHeight())
							{
								StarBallz.this.root.getChildren().remove(b);
							}
							
						}
					}
				}
			});
		}		
	}
}
