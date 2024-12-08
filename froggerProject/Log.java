package froggerProject;

import javax.swing.JLabel;

public class Log {
	
	private int spriteX, spriteY;
	private String sprite;
	private int spriteL, spriteW;
	public int speed;

	public int getX() { return spriteX; }
	public int getY() { return spriteY; }
	public String getSprite() { return sprite; }
	public int getHeight() { return spriteL; }
	public int getWidth() { return spriteW; }
	public int getSpeed() { return speed; }
	
	public void setX(int spriteX) {  this.spriteX = spriteX; }
	public void setY(int spriteY) {  this.spriteY = spriteY; }
	public void setSprite(String sprite) {  this.sprite = sprite; }
	public void setHeight(int spriteL) {  this.spriteL = spriteL; }
	public void setWidth(int spriteW) {  this.spriteW = spriteW; }
	public void setSpeed(int speed) { this.speed = speed; }
	
	public Log() {
		spriteX=0; spriteY=0; sprite="log.png"; 
		spriteL=50; spriteW=100; speed=20;
	}
	
	public void updateLogLabel(JLabel logLabel) {
		logLabel.setLocation(spriteX, spriteY);
		logLabel.setSize(spriteW, spriteL);
	}
	
}

