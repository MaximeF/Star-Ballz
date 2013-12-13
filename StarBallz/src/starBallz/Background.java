package starBallz;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Background {

	// Image of background
	private Image image;

	// Speed of backgrount
	private double speed;

	// Positions of background
	private int xPosition;
	private double yPositions[];


	public Background (Image image, double speed, int xPosition)
	{
		this.image = image;
		this.speed = speed;

		this.xPosition = xPosition;

		// We divide frame size with image size do that we get how many times we need to draw image to screen.
		int numberOfPositions = 2; // We need to add 2 so that we don't get blank spaces between images.
		yPositions = new double[numberOfPositions];

		// Set x coordinate for each image that we need to draw.
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
			// Move image
			yPositions[i] += speed;

			// If image is out of the screen, it moves it to the end of line of images.
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