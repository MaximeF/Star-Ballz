package starBallz;

public enum Song 
{
	TETRIS("Tetris"),
	AUTRECHANSON("adsasd");
	
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
