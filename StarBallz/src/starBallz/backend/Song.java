package starBallz.backend;

/**
* �num�ration des chansons inclues dans le jeu.
* @author  Patrick Arsenault, Maxime Forgues, Francis Chandonnet
*/

public enum Song 
{
	MARIOBROS("Mario Bros"),
	TETRIS("Tetris"),
	WRECKINGBALL("Wrecking Ball");
	
	private String fileName;
	
	/**
	* Constructeur de l'�num�ration Song. 
	* @param fileName Nom des fichier son et texte reli�s � la chanson.
	*/
	private Song(String fileName)
	{
		this.fileName = fileName;
	}
	
	/** 
	* @return Le nom des fichier son et texte reli�s � la chanson.
	*/
	public String getFileName()
	{
		return this.fileName;
	}
}
