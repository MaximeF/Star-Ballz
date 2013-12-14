package starBallz;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Vector;

import starBallz.SongMenu;
import starBallz.backend.GameEvent;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

/**
 * Classe servant de stage pour la fenêtre du jeu et son bon déroulement
 * @author Maxime Forgues, Patrick Arsenault, Françis Chandonnet
 *  
 */
public class Game extends Application
{
	private static final int STAGEHEIGHT = 750;
	private static final int STAGEWIDTH = 500;
	private Canvas canvas = new Canvas(STAGEWIDTH, STAGEHEIGHT);
	private GameEvent gameEvent;
	private AnimationTimer animTimer;
	private String fileName = "";
	private MediaPlayer mediaPlayer = null;
	private Stage stage = null;

	/**
	 * Constructeur de Game, créer un GameEvent et remplace la scene
	 * @param fileName nom du fichier de la chanson à jouer
	 * @param stage provenant de SongMenu, on remplace la scene
	 */
	public Game(String fileName, Stage stage)
	{
		this.fileName = fileName;
		this.gameEvent = new GameEvent(STAGEWIDTH, STAGEHEIGHT, this.fileName + ".txt");
		try {
			this.start(stage);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Contient la boucle servant à rafraichir le jeu et les éléments de la scene
	 */
	@Override
	public void start(Stage stage) throws Exception
	{	
		this.stage = stage;
		Group group = new Group();
		this.stage.centerOnScreen();
		group.getChildren().addAll(this.canvas);
		this.canvas.setOnMouseMoved(new mouseMouvement());
		Scene scene =  new Scene(group);
		scene.setCursor(Cursor.NONE);
		stage.setScene(scene);
		stage.show();

		this.startMusic(this.fileName);
		scene.setOnKeyPressed(new onKeyPressed());
		this.animTimer = new AnimationTimer() {
			@Override
			public void handle(long arg0) {
				loop();
			}
		};

		this.animTimer.start();

	}

	
	private class onKeyPressed implements EventHandler<KeyEvent>
	{
		public void handle(KeyEvent e)
		{
			if (e.getCode() == KeyCode.BACK_SPACE)
			{
				fillScore();
				quitGame();
			}
		}
	}

	/**
	 * Appelé lorsqu'on quitte la fênetre, arrêter la musique et le jeu
	 */
	public void quitGame()
	{
		this.mediaPlayer.stop();
		this.mediaPlayer = null;
		this.animTimer.stop();
		this.gameEvent = null;
		@SuppressWarnings("unused")
		SongMenu sMenu = new SongMenu(stage);

	}

	/**
	 * Joue un fichier son
	 * @param fileName nom du fichier son
	 */
	public void startMusic(String fileName)
	{
		String source = new File("ressources/" + fileName + ".mp3").toURI().toString();
		Media media = new Media(source);
		this.mediaPlayer = new MediaPlayer(media);
		mediaPlayer.play();
	}

	/**
	 * Rafraichie les éléments du jeu et l'affiche sur la fenêtre
	 */
	public void loop() 
	{
		updateGame();
		renderGame();
	}

	/**
	 * Rafraichie tout ce qui est background
	 */
	public void updateGame() 
	{

		this.gameEvent.refresh();
		this.gameEvent.tick();

	}

	/**
	 * Affiche les éléments graphique sur le canvas
	 */
	public void renderGame() 
	{
		GraphicsContext gc = canvas.getGraphicsContext2D();
		this.gameEvent.draw(gc);

	}

	/**
	 * Remplie le score dans un fichier text selon le mielleur score
	 */
	public void fillScore()
	{
		try {
			BufferedReader fileReader = new BufferedReader(new FileReader("ressources/"+ this.fileName + ".txt"));

			Vector<String> oldText = new Vector<String>();
			fileReader.readLine();
			String text = fileReader.readLine();
			while(text != null)
			{
				oldText.addElement(text);
				text = fileReader.readLine();
			}


			BufferedWriter fileWriter = new BufferedWriter(new FileWriter("ressources/"+ this.fileName + ".txt"));
			fileWriter.write(""+this.gameEvent.getScore());
			fileWriter.newLine();
			for(String line : oldText)
			{
				fileWriter.write(String.valueOf(line));
				fileWriter.newLine();
			}
			fileReader.close();
			fileWriter.close();
		} catch (IOException e) 
		{

		}
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




}