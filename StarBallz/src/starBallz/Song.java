package starBallz;

public enum Song 
{
	MARIOBROS("Mario Bros"),
	TETRIS("Tetris");
	
	private String fileName;
	
	private Song(String fileName)
	{
		this.fileName = fileName;
	}
	
	public String getFileName()
	{
		return this.fileName;
	}
}
