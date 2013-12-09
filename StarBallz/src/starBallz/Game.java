package starBallz;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Game extends Application
{
	private static final int STAGEHEIGHT = 800;
	private static final int STAGEWIDTH = 500;
	private Canvas canvas = new Canvas(STAGEWIDTH, STAGEHEIGHT);
	private GameEvent gameEvent = new GameEvent(STAGEWIDTH, STAGEHEIGHT, "Mario Bros.txt");
	private Thread gameLoop = new Thread(new GameLoop());
	private AnimationTimer animTimer;


	@Override
	public void start(Stage stage) throws Exception
	{
		Group group = new Group();
		group.getChildren().addAll(this.canvas);
		this.canvas.setOnMouseMoved(new mouseMouvement());
		stage.setScene(new Scene(group));
		stage.show();
		//this.gameLoop.start();
		this.animTimer = new AnimationTimer() {

			@Override
			public void handle(long arg0) {
				GraphicsContext gc = Game.this.canvas.getGraphicsContext2D();
				gc.setFill(Color.rgb(0, 0, 0, 1));
				gc.fillRect(0, 0, STAGEWIDTH, STAGEHEIGHT);
				loop();
			}
		};
		
		this.animTimer.start();

	}

	public void loop() 
	{
		updateGame();
		renderGame();
	}

	public void updateGame() 
	{

		this.gameEvent.refresh();
		this.gameEvent.tick();

	}

	public void renderGame() 
	{
		GraphicsContext gc = canvas.getGraphicsContext2D();
		this.gameEvent.draw(gc);

	}

	private class mouseMouvement implements EventHandler<MouseEvent>
	{
		public void handle(MouseEvent e)
		{
			if (e.getSceneX() <= STAGEWIDTH && e.getSceneX() >= 0 )
			{
				gameEvent.getPlatform().setxPos(e.getSceneX());
			}
		}
	}

	public static void main(String arg[]) 
	{
		launch(arg);
	}

	private class GameLoop implements Runnable
	{
		private boolean running = true;
		@Override
		public void run() {

			while(this.running)
			{
				GraphicsContext gc = Game.this.canvas.getGraphicsContext2D();
				gc.setFill(Color.rgb(0, 0, 0, 1));
				gc.fillRect(0, 0, STAGEWIDTH, STAGEHEIGHT);
				Game.this.loop();
			}
		}

	}
}