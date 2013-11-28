package starBallz;

import java.awt.event.KeyAdapter;
import java.util.ArrayList;
import java.util.List;
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


public class StarBallz extends Application
{
	
	private Group root = null;
	private Ballz testBallz = null;
	private Stage stage = null;
	private Scene scene = null;
	private UserPlatform userPlatform = null;
	private int distanceFromBottom = 20;
	ArrayList<Ballz> ballzList = new ArrayList<Ballz>();
	private boolean isLeftDown = false;
	private boolean isRightDown = false;
	private Engine engine = new Engine();
	
	public static void main(String[] args)
	{
		launch(args);
	}
	
	@Override
	public void start(Stage stage) throws Exception
	{
		this.ballzList = new ArrayList<Ballz>();
		this.stage = stage;
		this.root = new Group();
	    this.scene = new Scene(root, 500, 800, Color.BLACK);
		this.scene.setOnKeyPressed(new arrowKeyListener());
		this.scene.setOnKeyReleased(new arrowKeyReleaseListener());
		this.scene.setOnMouseMoved(new mouseMouvementListener());
		this.stage.setTitle("StarBallz");
		this.stage.setResizable(false);
		this.root.getChildren().add(this.engine);
		this.createBallz();
		this.createBallz();
		this.createPlatform();
		this.stage.setScene(scene);
		this.stage.show();
		Timer starBallzTimer = new Timer();
		starBallzTimer.schedule(new StarBallzTimer(), 1, 1);
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
		this.userPlatform = new UserPlatform(this.scene.getWidth()/2-(100/2),this.scene.getHeight()-this.distanceFromBottom,100,15,Color.RED);
		this.engine.getChildren().add(this.userPlatform);
	}
	
	public void createBallz()
	{
		Random random = new Random();
		double randomX = (double)random.nextInt((int)this.scene.getWidth()-40) + 20;
		double randomVelX = (((double)random.nextInt(99) + 1)/(double)100)* (double)Math.pow(-1,randomX);;
		Ballz ballz = new Ballz(randomX,-10,20,randomVelX,0.5,Color.BLUE);
		this.ballzList.add(ballz);
		root.getChildren().add(ballz);
	}
	
	private class StarBallzTimer extends TimerTask
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
					ArrayList<Ballz> listToDelete = new ArrayList<Ballz>();
					int i = 0;
					while (i < StarBallz.this.ballzList.size()-1)
					{
						Ballz b = StarBallz.this.ballzList.get(i);
						
						i++;
					}
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
										StarBallz.this.engine.setExplosion((int) b.getCenterX(), (int) (b.getCenterY() + b.getRadius()), 500);

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
								StarBallz.this.engine.setExplosion((int) b.getCenterX(), (int) (b.getCenterY() + b.getRadius()), 500);
							}

							if (b.getCenterY()+b.getRadius()<=0||b.getCenterY()-b.getRadius()>=StarBallz.this.scene.getHeight())
							{
								listToDelete.add(b);
								StarBallz.this.ballzList.remove(b);
								StarBallz.this.root.getChildren().remove(b);
								
								StarBallz.this.createBallz();
							}
						}
					}
					for (Ballz ballzToDelete : listToDelete)
					{
						
					}
					StarBallz.this.ballzList.trimToSize();
				}
			});
		}		
	}
}
