package starBallz.backend;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Paint;

public abstract class Ball {

	private double xPos;
	private double yPos;
	private double xVel;
	private double yVel;
	private double size;
	private Paint color; 
	public Ball(double xPos, double yPos, int size, double xVel, double yVel, Paint color)
	{
		this.xPos = xPos;
		this.yPos = yPos;
		this.xVel = xVel;
		this.yVel = yVel;
		this.size = size;
		this.color = color;
	}

	public Ball(double xPos, double yPos, int size, double xVel, double yVel)
	{
		this.xPos = xPos;
		this.yPos = yPos;
		this.xVel = xVel;
		this.yVel = yVel;
		this.size = size;
	}

	public void move() {
		this.xPos += this.xVel;
		this.yPos += this.yVel;

	}

	public void draw(GraphicsContext gc) 
	{
		gc.setFill(this.color);
		gc.fillOval(this.xPos-this.size, this.yPos-this.size, this.size + this.size, this.size + this.size);

	}

	public double getxPos() {
		return xPos;
	}
	public void setxPos(double xPos) {
		this.xPos = xPos;
	}
	public double getyPos() {
		return yPos;
	}
	public void setyPos(double yPos) {
		this.yPos = yPos;
	}
	public double getxVel() {
		return xVel;
	}
	public void setxVel(double xVel) {
		this.xVel = xVel;
	}
	public double getyVel() {
		return yVel;
	}
	public void setyVel(double yVel) {
		this.yVel = yVel;
	}

	public double getSize() {
		return size;
	}

	public void setColor(Paint color) {
		this.color = color;
	}

	public Paint getColor() {
		return color;
	}




}
