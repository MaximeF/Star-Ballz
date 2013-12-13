package starBallz;

import java.io.BufferedReader;
import java.io.FileReader;

import starBallz.backend.Game;
import starBallz.backend.Song;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class SongMenu extends Application
{
	private static final int SONGNUMBER = 3;
	private GridPane gridPane;
	private Stage stage = null;

	public SongMenu(Stage stage)
	{
		try {
			this.start(stage);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	@Override
	public void start(Stage stage) throws Exception
	{
		this.stage = stage;
		Group root = new Group();
		Image icon = new Image("icon.png");
		this.stage.getIcons().add(icon);
		this.gridPane = new GridPane();
		this.gridPane.setVgap(10);
		this.gridPane.setHgap(10);
		Scene scene = new Scene(root, 500, 650, Color.BLACK);
		addSongMenu();
		root.getChildren().add(this.gridPane);
		this.stage.setScene(scene);
		this.stage.show();
	}

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
}
