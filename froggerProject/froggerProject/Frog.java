package froggerProject;

public class Frog {
	
	private int spriteX, spriteY;
	private String sprite;
	private int spriteL, spriteW;
	
	public int getX() { return spriteX; }
	public int getY() { return spriteY; }
	public String getSprite() { return sprite; }
	public int getHeight() { return spriteL; }
	public int getWidth() { return spriteW; }
	
	public void setX(int spriteX) {  this.spriteX = spriteX; }
	public void setY(int spriteY) {  this.spriteY = spriteY; }
	public void setSprite(String sprite) {  this.sprite = sprite; }
	public void setHeight(int spriteL) {  this.spriteL = spriteL; }
	public void setWidth(int spriteW) {  this.spriteW = spriteW; }

	public Frog() {
		spriteX=0; spriteY=0; sprite="frog.png"; 
		spriteL=0; spriteW=0;
	}
	
	public void Display() {
		System.out.println("dX,dY: "+spriteX+","+spriteY);
	}
	
}
