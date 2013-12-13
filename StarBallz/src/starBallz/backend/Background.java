package starBallz.backend;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Background {

	private Image image;
	private double speed;
	private int xPosition;
	private double yPositions[];

	public Background (Image image, double speed, int xPosition)
	{
		this.image = image;
		this.speed = speed;
		this.xPosition = xPosition;
		int numberOfPositions = 2; 
		yPositions = new double[numberOfPositions];
		for (int i = 0; i < yPositions.length; i++)
		{
			yPositions[i] = i * image.getHeight();
		}
	}

	/**
	 * Moves images.
	 */
	public void update()
	{
		for (int i = 0; i < yPositions.length; i++)
		{
			yPositions[i] += speed;
			if (yPositions[i] >= image.getHeight())
			{
				yPositions[i] = -image.getHeight();
			}
		}
	}

	public void draw(GraphicsContext gc)
	{
		for (int i = 0; i < yPositions.length; i++)
		{
			gc.setGlobalAlpha(0.3);
			gc.drawImage(image, xPosition, (int)yPositions[i]);
		}
	}
}