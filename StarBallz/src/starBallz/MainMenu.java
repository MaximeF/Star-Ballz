package starBallz;

/**
 * La classe MainMenu représente le menu principal du jeu. On peut y 
 * retrouver les règles, accéder au menu des chansons et quitter
 * l'application.
 * @author  Patrick Arsenault, Maxime Forgues, Francis Chandonnet
 */

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;
import starBallz.backend.explosion.test.EngineTest;
import starBallz.backend.explosion.test.PartcleTest;
import starBallz.backend.test.BackgroundTest;
import starBallz.backend.test.BallzTest;
import starBallz.backend.test.UserPlatformTest;

import javafx.application.Application;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;


public class MainMenu extends Application
{

	private Group root = new Group();
	private GridPane gPane = new GridPane();
	private ImageView sballzImage = new ImageView("starballz.png");
	private Button btnPlay = new Button("Jouer");
	private Button btnRules = new Button("Règles");
	private Button btnQuit = new Button("Quitter");
	private Stage stage = null;

	/**
	 * Constructeur de la classe MainMenu.
	 */
	public MainMenu()
	{
	}

	/**
	 * Initialise, bâtit et place les éléments et la configuration du menu.
	 * Affiche ensuite la scène.
	 * @param stage Le stage de l'application.
	 */
	@Override
	public void start(Stage stage) throws Exception 
	{
		Scene scene = new Scene(root, 400,550, Color.BLACK);
		this.stage = stage;
		this.stage.setResizable(false);
		this.stage.centerOnScreen();
		this.stage.setTitle("StarBallz");
		Image icon = new Image("icon.png");
		this.stage.getIcons().add(icon);
		this.stage.setScene(scene);
		this.gPane.setVgap(10);
		this.gPane.setHgap(10);

		this.btnPlay.setStyle("-fx-font: 22 montalban; -fx-base: #000000;");
		this.btnPlay.setOnAction(new onMenuButtonPlayClick());
		this.btnRules.setStyle("-fx-font: 22 montalban; -fx-base: #000000;");
		this.btnRules.setOnAction(new onMenuButtonRulesClick());
		this.btnQuit.setStyle("-fx-font: 22 montalban; -fx-base: #000000;");
		this.btnQuit.setOnAction(new onMenuButtonQuitClick());
		this.btnPlay.setMaxSize(160, 60);
		this.btnRules.setMaxSize(160, 60);
		this.btnQuit.setMaxSize(160, 60);

		TilePane tileButtons = new TilePane(Orientation.VERTICAL);
		tileButtons.setPadding(new Insets(20, 10, 20, 0));
		tileButtons.setHgap(10.0);
		tileButtons.setVgap(24.0);
		tileButtons.getChildren().addAll(this.sballzImage, this.btnPlay, this.btnRules, this.btnQuit);
		this.gPane.add(tileButtons, 8, 3);

		this.root.getChildren().add(gPane);
		this.stage.setScene(scene);
		this.stage.show();
	}

	/**
	 * Lance les tests unitaires et l'application si ceux-ci passent.
	 */
	public static void main(String[] args) 
	{
		JUnitCore junit = new JUnitCore(); 
		Result result = junit.run(BallzTest.class, UserPlatformTest.class, EngineTest.class, PartcleTest.class, BackgroundTest.class);

		if (result.getFailureCount() == 0)
		{
			String s = new File("").getAbsolutePath();
			System.out.println(s);

			Application.launch(MainMenu.class, args);
		}
		else
		{
			List<Failure> listeEchecs = result.getFailures();
			System.out.println("Voici les tests qui échouent: ");

			for (Failure f: listeEchecs)
			{
				System.out.println(f.toString());
			}                    
		}             
	}

	/**
	 * Gestion de l'événement du clique sur le bouton Quitter. Ferme
	 * l'application.
	 */
	private class onMenuButtonQuitClick implements EventHandler<ActionEvent>
	{

		@Override public void handle(ActionEvent e) 
		{
			System.exit(0);
		}

	}

	/**
	 * Gestion de l'événement du clique sur le bouton Règles. Affiche le but
	 * du jeu ainsi que les instructions, les noms des développeurs, la 
	 * version du logiciel et l'année de création. Va chercher les régles
	 * dans un fichier texte.
	 * @see #readTextFile(String path)
	 */
	private class onMenuButtonRulesClick implements EventHandler<ActionEvent>
	{
		@Override public void handle(ActionEvent e) 
		{

			Stage myDialog = new Stage();
			myDialog.initModality(Modality.WINDOW_MODAL);
			myDialog.setResizable(false);
			Image icon = new Image("icon.png");
			myDialog.getIcons().add(icon);
			myDialog.setTitle("Règlements");
			Group root2 = new Group();
			GridPane gPane = new GridPane();
			Scene myDialogScene = new Scene(root2,550,460,Color.BLACK);
			String[] stringTable = null;
			try {
				stringTable = readTextFile(System.getProperty("user.dir") + "/ressources/Rules.txt");
			} catch (IOException e1) {
				e1.printStackTrace();
			}

			int i = 0;
			for (String string : stringTable)
			{
				Text text = new Text(string);
				text.setFill(Color.WHITE);
				text.setStyle("-fx-font: 14 montalban;");
				gPane.add(text, 0, i);
				i++;
			}

			root2.getChildren().add(gPane);
			myDialog.setScene(myDialogScene);
			myDialog.show();

		}
	}

	/**
	 * Gestion de l'événement du clique sur le bouton Jouer. Affiche le menu
	 * de sélection des chansons.
	 * @see SongMenu
	 */
	private class onMenuButtonPlayClick implements EventHandler<ActionEvent>
	{
		@Override public void handle(ActionEvent e) 
		{
			@SuppressWarnings("unused")
			SongMenu sMenu = new SongMenu(stage);
		}

	}

	/**
	 * Méthode servant à lire dans un fichier texte.
	 * @param path Le chemin d'accès au fichier texte.
	 * @return Un tableau de Strings contenant les lignes du fichier texte.
	 */
	public String[] readTextFile(String path) throws IOException
	{

		FileReader fileR = new FileReader(path);
		BufferedReader textReader = new BufferedReader(fileR);
		int fileLenght = 0;
		while(textReader.readLine() != null)
		{
			fileLenght++;
		}
		textReader.close();
		fileR.close();

		FileReader fileR2 = new FileReader(path);
		BufferedReader textReader2 = new BufferedReader(fileR2);

		String[] textData = new String[fileLenght];

		int i = 0;
		while(i < fileLenght)
		{
			textData[i] = textReader2.readLine();
			i++;
		}

		textReader2.close();
		fileR2.close();

		return textData;
	}
}