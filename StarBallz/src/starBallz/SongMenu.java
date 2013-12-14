package starBallz;

/**
 * La classe SongMenu représente le menu de sélection de chansons du
 * jeu. On peut y lancer une partie en choisissant une chanson.
 * @author  Patrick Arsenault, Maxime Forgues, Francis Chandonnet
 */

import java.io.BufferedReader;
import java.io.FileReader;

import com.sun.glass.ui.Window;

import starBallz.backend.Game;
import starBallz.backend.Song;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class SongMenu extends Application
{
	private static final int SONGNUMBER = 3;
	private GridPane gridPane;
	private Stage stage = null;

	
	/**
	 * Constructeur de la classe SongMenu.
	 * @param stage Le stage de l'application.
	 */
	public SongMenu(Stage stage)
	{
		try {
			this.start(stage);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Initialise, bâtit et place les éléments et la configuration du menu.
	 * Affiche ensuite la scène.
	 * @param stage Le stage de l'application.
	 * @see onKeyPressed
	 * @see #addSongMenu()
	 */
	@Override
	public void start(Stage stage) throws Exception
	{
		this.stage = stage;
		Group root = new Group();
		Image icon = new Image("icon.png");
		this.stage.getIcons().add(icon);
		this.stage.centerOnScreen();
		this.gridPane = new GridPane();
		this.gridPane.setVgap(10);
		this.gridPane.setHgap(10);
		Scene scene = new Scene(root, 500, 650, Color.BLACK);
		scene.setOnKeyPressed(new onKeyPressed());
		this.addSongMenu();
		root.getChildren().add(this.gridPane);
		this.stage.setScene(scene);
		this.stage.show();
	}

	
	/**
	 * Obtient les chansons disponibles dans l'énumération Song.
	 * @see Song
	 * @see songLabelClicked
	 */
	public void addSongMenu()
	{
		Song[] songList = Song.values();
		for(int i = 0; i < SONGNUMBER; i++)
		{
			String fileName = songList[i].getFileName();

			BufferedReader reader;
			try {
				reader = new BufferedReader(new FileReader("ressources/"+fileName+".txt"));
				String highScore = reader.readLine();
				String difficulty = reader.readLine();
				reader.close();
				Label label = new Label("Chanson: " + fileName + " - Difficulté: " + difficulty + " - Meil. score: " + highScore);
				label.setStyle("-fx-font: 10 montalban;");
				label.setTextFill(Color.RED);
				label.setId(fileName);
				label.setOnMouseClicked(new songLabelClicked());
				this.gridPane.add(label, 0, i);
			} 
			catch (Exception e) 
			{
				e.printStackTrace();
			}
		}
	}

	/**
	 * Gestion de l'événement du clique sur le label de la chanson voulue.
	 * Démarre une partie en fonction de la chanson voulue.
	 * @see Game
	 */
	private class songLabelClicked implements EventHandler<MouseEvent>
	{
		public void handle(MouseEvent e)
		{
			Label label = (Label)e.getSource();
			String fileName = label.getId();
			@SuppressWarnings("unused")
			Game game = new Game(fileName,stage);
		}
	}
	
	/**
	 * Gestion de l'événement du clique sur la touche Backspace du clavier.
	 * Retourne l'utilisateur au menu principal de l'application.
	 * @see MainMenu
	 */
	private class onKeyPressed implements EventHandler<KeyEvent>
	{
		public void handle(KeyEvent e)
		{
			if (e.getCode() == KeyCode.BACK_SPACE)
			{
				Window.getFocusedWindow().close();
				
				MainMenu mMenu = new MainMenu();
				try {
					mMenu.start(new Stage());
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		}
	}
}
