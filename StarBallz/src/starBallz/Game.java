package starBallz;

import java.io.File;
import java.net.URL;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Game extends Application
{
	private static final int STAGEHEIGHT = 650;
	private static final int STAGEWIDTH = 500;
	private Canvas canvas = new Canvas(STAGEWIDTH, STAGEHEIGHT);
	private GameEvent gameEvent;
	private Thread gameLoop = new Thread(new GameLoop());
	private AnimationTimer animTimer;
	private String fileName = "";


	public Game(String fileName)
	{
		this.fileName = fileName;
		this.gameEvent = new GameEvent(STAGEWIDTH, STAGEHEIGHT, this.fileName + ".txt");
	}
	
	@Override
	public void start(Stage stage) throws Exception
	{
		Group group = new Group();
		group.getChildren().addAll(this.canvas);
		this.canvas.setOnMouseMoved(new mouseMouvement());
		stage.setScene(new Scene(group));
		stage.show();
		this.startMusic(this.fileName);
		//this.gameLoop.start();
		this.animTimer = new AnimationTimer() {

			@Override
			public void handle(long arg0) {
				GraphicsContext gc = Game.this.canvas.getGraphicsContext2D();
				gc.setFill(Color.rgb(0, 0, 0, 0.3));
				gc.fillRect(0, 0, STAGEWIDTH, STAGEHEIGHT);
				loop();
			}
		};
		
		this.animTimer.start();

	}
	
	public void startMusic(String fileName)
	{
		   String source = new File("ressources/" + fileName + ".mp3").toURI().toString();
           Media media = new Media(source);
           MediaPlayer mediaPlayer = new MediaPlayer(media);
           mediaPlayer.play();
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