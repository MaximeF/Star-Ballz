package starBallz;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class MainMenu extends Application
{
	
	private Group root;
	private BorderPane bPane;
	private Label l;
	
	public MainMenu()
	{
		this.root = new Group();
		this.bPane = new BorderPane();
		this.l = new Label("Texte du message de la carte ici");
	}

	@Override
	public void start(Stage stage) throws Exception 
	{
		Scene scene = new Scene(root, 600, 600 , Color.WHITE);
		stage.setResizable(false);
		stage.setTitle("Star Ballz");
		this.bPane.setTop(this.l);
		this.root.getChildren().add(bPane);
		stage.setScene(scene);
		stage.show();
		
	}
	
	public static void main(String[] args) 
	{
		launch(args);
	}
	
}
