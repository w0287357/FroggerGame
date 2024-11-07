package froggerProject;

public class Frog {

	private int spriteX, spriteY;
	private String sprite;
	private int spriteL, spriteW;
	private int step = 10;
	
	public int getX() { return spriteX; }
	public int getY() { return spriteY; }
	public String getSprite() { return sprite; }
	public int getHeight() { return spriteL; }
	public int getWidth() { return spriteW; }
	public int getStep() { return step; }
	
	public void setX(int spriteX) {  this.spriteX = spriteX; }
	public void setY(int spriteY) {  this.spriteY = spriteY; }
	public void setSprite(String sprite) {  this.sprite = sprite; }
	public void setHeight(int spriteL) {  this.spriteL = spriteL; }
	public void setWidth(int spriteW) {  this.spriteW = spriteW; }
	public void setStep(int step) {  this.step = step; }
	
	//default constructor
	public Frog() {
		spriteX=0; 		spriteY=0; 		sprite="frog.png"; 
		spriteL=0; 		spriteW=0; 		step=10;
	}
	
	//movement
    public void moveUp() {
        this.spriteY -= step;
    }

    public void moveDown() {
        this.spriteY += step;
    }

    public void moveLeft() {
        this.spriteX -= step;
    }

    public void moveRight() {
        this.spriteX += step;
    }
    
    public void stayWithinBounds(int screenWidth, int screenHeight) {
        if (spriteX < 0) spriteX = 0;
        if (spriteY < 0) spriteY = 0;
        if (spriteX + spriteW > screenWidth) spriteX = screenWidth - spriteW;
        if (spriteY + spriteL > screenHeight) spriteY = screenHeight - spriteL;
    }
    
    public void rideLog(int logSpeed) {
        this.spriteX += logSpeed; // Adjusts frog's x-position with the log's speed
    }
	
}

