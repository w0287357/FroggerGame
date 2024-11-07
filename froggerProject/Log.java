package froggerProject;

public class Log {
	
	private int spriteX, spriteY;
	private String sprite;
	private int spriteL, spriteW;
	private int speed;
	private boolean movingRight;
	
	public int getX() { return spriteX; }
	public int getY() { return spriteY; }
	public String getSprite() { return sprite; }
	public int getHeight() { return spriteL; }
	public int getWidth() { return spriteW; }
	public int getSpeed() { return speed; }
	public boolean getMovingRight() { return movingRight; }
	
	public void setX(int spriteX) {  this.spriteX = spriteX; }
	public void setY(int spriteY) {  this.spriteY = spriteY; }
	public void setSprite(String sprite) {  this.sprite = sprite; }
	public void setHeight(int spriteL) {  this.spriteL = spriteL; }
	public void setWidth(int spriteW) {  this.spriteW = spriteW; }
	public void setSpeed(int speed) {  this.speed = speed; }
	public void setMovingRight(boolean movingRight) {  this.movingRight = movingRight; }
	
	public Log() {
		spriteX=0;		spriteY=0; 		sprite="log.png"; 
		spriteL=0; 		spriteW=0;		speed=0;
		movingRight=true;
	}
	
	//movelog
	public void move() {
        if (movingRight) {
            spriteX += speed;
        } else {
            spriteX -= speed;
        }
    }
	
	public void boundary(int screenWidth) {
        if (movingRight && spriteX > screenWidth) {
            spriteX = -spriteW;  // Wrap around from the left side
        } else if (!movingRight && spriteX + spriteW < 0) {
            spriteX = screenWidth;  // Wrap around from the right side
        }
    }
	

}
