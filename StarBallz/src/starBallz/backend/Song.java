package starBallz.backend;

/**
* Énumération des chansons inclues dans le jeu.
* @author  Patrick Arsenault, Maxime Forgues, Francis Chandonnet
*/

public enum Song 
{
	MARIOBROS("Mario Bros"),
	TETRIS("Tetris"),
	WRECKINGBALL("Wrecking Ball");
	
	private String fileName;
	
	/**
	* Constructeur de l'énumération Song. 
	* @param fileName Nom des fichier son et texte reliés à la chanson.
	*/
	private Song(String fileName)
	{
		this.fileName = fileName;
	}
	
	/** 
	* @return Le nom des fichier son et texte reliés à la chanson.
	*/
	public String getFileName()
	{
		return this.fileName;
	}
}
