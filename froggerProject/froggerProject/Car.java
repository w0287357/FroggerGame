package froggerProject;

public class Car {
	
	private int spriteX, spriteY;
	private String sprite;
	private int spriteL, spriteW;
	public int speed;
	public boolean moving;
	private boolean movingRight;

	public int getX() { return spriteX; }
	public int getY() { return spriteY; }
	public String getSprite() { return sprite; }
	public int getHeight() { return spriteL; }
	public int getWidth() { return spriteW; }
	public int getSpeed() { return speed; }
	public Boolean getStatus() { return moving; }
	public boolean getDirection() { return movingRight; }
	
	public void setX(int spriteX) {  this.spriteX = spriteX; }
	public void setY(int spriteY) {  this.spriteY = spriteY; }
	public void setSprite(String sprite) {  this.sprite = sprite; }
	public void setHeight(int spriteL) {  this.spriteL = spriteL; }
	public void setWidth(int spriteW) {  this.spriteW = spriteW; }
	public void setSpeed(int speed) { this.speed = speed; }
	public void setStatus(Boolean moving) { this.moving = moving; }
	public void setDirection(boolean movingRight) { this.movingRight = movingRight; }

	public Car() {
		spriteX=0; spriteY=0; sprite="car.png"; 
		spriteL=50; spriteW=100; speed=20;
		moving=false;
		movingRight=true;
	}
	
	public void Display() {
		System.out.println("dX,dY: "+spriteX+","+spriteY);
	}
	
}
