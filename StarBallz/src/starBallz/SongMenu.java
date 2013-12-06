package starBallz;

import java.io.BufferedReader;
import java.io.FileReader;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class SongMenu extends Application
{
	private static final int SONGNUMBER = 1;
	private GridPane gridPane;
	private Stage stage = null;
	
	@Override
	public void start(Stage stage) throws Exception
	{
		this.stage = stage;
		Group root = new Group();
		this.gridPane = new GridPane();
	    Scene scene = new Scene(root, 500, 800, Color.BLACK);
	    addSongMenu();
		stage.setResizable(false);
		root.getChildren().add(this.gridPane);
		stage.setScene(scene);
		stage.show();
	}

	public static void main(String[] args)
	{
		launch(args);
	}
	
	public void addSongMenu()
	{
		Song[] songList = Song.values();
		for(int i =0;i<SONGNUMBER;i++)
		{
			String fileName = songList[i].getFileName();
			
			BufferedReader reader;
			try {
				reader = new BufferedReader(new FileReader("ressources/"+fileName+".txt"));
				String highScore = reader.readLine();
				String difficulty = reader.readLine();
				reader.close();
				Label label = new Label(fileName + difficulty + highScore);
				label.setTextFill(Color.WHITE);
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
			StarBallz game = new StarBallz(fileName,stage);
			try {
				Stage stage = new Stage();
				game.start(stage);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
	}
}
