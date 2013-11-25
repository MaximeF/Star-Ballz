package starBallz;

import java.awt.event.KeyAdapter;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import starBallz.UserPlatform;
import java.util.TimerTask;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;


public class StarBallz extends Application
{
	
	private Group root = null;
	private Ballz testBallz = null;
	private Stage stage = null;
	private Scene scene = null;
	private Timer starBallzTimer = null;
	private UserPlatform userPlatform = null;
	private int distanceFromBottom = 20;
	List<Ballz> ballzList = null;
	private boolean isLeftDown = false;
	private boolean isRightDown = false;
	
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
	    this.scene = new Scene(root, 500, 1000, Color.WHITE);
		this.scene.setOnKeyPressed(new arrowKeyListener());
		this.scene.setOnKeyReleased(new arrowKeyReleaseListener());
		this.stage.setTitle("StarBallz");
		this.stage.setResizable(false);
		this.createBallz();
		this.createPlatform();
		this.stage.setScene(scene);
		this.stage.show();
		this.starBallzTimer = new Timer();
		this.starBallzTimer.schedule(new StarBallzTimer(), 1, 1);
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
	
	private class arrowKeyListener implements EventHandler<KeyEvent>
	{
		public void handle(KeyEvent e)
		{
			if (e.getCode()==KeyCode.LEFT)
			{
				isLeftDown = true;
				if (StarBallz.this.userPlatform.getX()>0)
				{
				}
			}
			else if (e.getCode()==KeyCode.RIGHT)
			{
				isRightDown = true;
				if (StarBallz.this.userPlatform.getX()+StarBallz.this.userPlatform.getWidth()<StarBallz.this.scene.getWidth())
				{
				}
			}
		}
		
	}
	public void createPlatform()
	{
		this.userPlatform = new UserPlatform(this.scene.getWidth()/2-(100/2),this.scene.getHeight()-this.distanceFromBottom,100,15,Color.RED);
		this.root.getChildren().add(this.userPlatform);
	}
	
	public void createBallz()
	{
		this.testBallz = new Ballz(this.scene.getWidth()/2,-10,10,-1,1,Color.BLUE);
		root.getChildren().add(this.testBallz);
	}
	
	private class StarBallzPlatformTimer extends TimerTask 
	{
		@Override		
		public void run()
		{
			Platform.runLater(new Runnable() {
				public void run() 
				{
					if (isLeftDown)
					{
						userPlatform.moveLeft();
					}
					if (isRightDown)
					{
						userPlatform.moveRight();
					}
				}
			});
		}		
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
					
					for (Ballz b : StarBallz.this.ballzList)
					{
						
					}
					if (StarBallz.this.testBallz != null)
					{
						if (StarBallz.this.testBallz.intersects(StarBallz.this.userPlatform.getBoundsInLocal()))
						{
							if (StarBallz.this.testBallz.getCenterY()+StarBallz.this.testBallz.getRadius()==StarBallz.this.userPlatform.getY())
							{
								if (StarBallz.this.testBallz.getCenterX()>StarBallz.this.userPlatform.getX()-StarBallz.this.testBallz.getRadius()&&StarBallz.this.testBallz.getCenterX()<StarBallz.this.userPlatform.getX()+StarBallz.this.userPlatform.getWidth()+StarBallz.this.testBallz.getRadius())
								{
									StarBallz.this.testBallz.bottomRebound();
								}
								//else
								{
									//StarBallz.this.testBallz.sideRebound();
								}
							}
						}
						StarBallz.this.testBallz.move();
						if (StarBallz.this.testBallz.getCenterX()-StarBallz.this.testBallz.getRadius()<=0||StarBallz.this.testBallz.getCenterX()+StarBallz.this.testBallz.getRadius()>= StarBallz.this.scene.getWidth())
						{
							StarBallz.this.testBallz.sideRebound();
						}
						
						if (StarBallz.this.testBallz.getCenterY()+StarBallz.this.testBallz.getRadius()<=0||StarBallz.this.testBallz.getCenterY()-StarBallz.this.testBallz.getRadius()>=StarBallz.this.scene.getHeight())
						{
							StarBallz.this.testBallz = null;
							StarBallz.this.root.getChildren().remove(StarBallz.this.testBallz);
							StarBallz.this.createBallz();
						}
					}
				}
			});
		}		
	}
}
