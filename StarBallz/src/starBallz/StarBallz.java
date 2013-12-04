package starBallz;

import java.io.BufferedReader;
import java.io.FileReader;
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
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
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
	private String songFileName;
	private Timer ballzMovementTimer = null;
	private Timer ballzSpawnTimer = null;
	private static final int MAXRADIUS = 16; 
	private static final int STAGEHEIGHT = 800;
	
	public static void main(String[] args)
	{
		launch(args);
	}
	
	public StarBallz(String fileName)
	{
		this.songFileName = fileName;
	}
	
	@Override
	public void start(Stage stage) throws Exception
	{
		this.ballzList = new ArrayList<Ballz>();
		this.ballzIterator = 0;
		this.timeIterator = 0;
		this.timeList = new ArrayList<Integer>();
		this.fillTimeList();
		this.stage = stage;
		this.root = new Group();
	    this.scene = new Scene(root, 500, STAGEHEIGHT, Color.BLACK);
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
	
	public void fillTimeList()
	{
		BufferedReader reader;
		try {
			reader = new BufferedReader(new FileReader("ressources/"+this.songFileName+".txt"));
			String line = reader.readLine();
			line = reader.readLine();
			line = reader.readLine();
			while (line != null) 
			{
				this.timeList.add(Integer.parseInt(line));
				line = reader.readLine();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void createPlatform()
	{
		this.userPlatform = new UserPlatform(this.scene.getWidth()/2-(100/2),this.scene.getHeight()-this.distanceFromBottom,100,15,Color.RED);
		this.engine.getChildren().add(this.userPlatform);
	}
	
	public void createBallz()
	{
		Random random = new Random();
		double randomX = (double)random.nextInt((int)this.scene.getWidth()-40) + 20;
		double randomVelX = (((double)random.nextInt(59) + 1)/(double)100)* (double)Math.pow(-1,randomX);
		Ballz ballz = new Ballz(randomX,-10,MAXRADIUS,randomVelX,0.5,Color.BLUE);
		this.ballzList.add(ballz);
		root.getChildren().add(ballz);
	}
	
	public void removeNullsFromList(ArrayList<?> list)
	{
		for (Object object : list)
		{
			if (object == null)
			{
				list.remove(object);
			}
		}
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
					removeNullsFromList(StarBallz.this.ballzList);
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
										StarBallz.this.engine.setExplosion((int) b.getCenterX(), (int) (b.getCenterY() + b.getRadius()), 250,(Color)b.getFill());
										b.setFill(Color.WHITE);

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
								StarBallz.this.engine.setExplosion((int) b.getCenterX(), (int) (b.getCenterY() + b.getRadius()), 250,(Color)b.getFill());
							}

							if (b.getCenterY()+b.getRadius()<=0||b.getCenterY()-b.getRadius()>=StarBallz.this.scene.getHeight())
							{
								StarBallz.this.root.getChildren().remove(b);
								b = null;
							}
							
						}
					}
				}
			});
		}		
	}
}
