package froggerProject;

import javax.swing.JLabel;

public class Player {
	
	private int spriteX, spriteY;
	private String name, sprite;
	private int width, height;
	private JLabel playerLabel;
	
	public int getX() { return spriteX; }
	public int getY() { return spriteY; }
	public String getName() { return name; }
	public String getSprite() { return sprite; }
	public int getHeight() { return height; }
	public int getWidth() { return width; }
	
	public void setX(int spriteX) {  this.spriteX = spriteX; }
	public void setY(int spriteY) {  this.spriteY = spriteY; }
	public void setName(String name) {  this.name = name; }
	public void setSprite(String sprite) {  this.sprite = sprite; }
	public void setHeight(int height) {  this.height = height; }
	public void setWidth(int width) {  this.width = width; }

	public Player() {
		spriteX=0; spriteY=0; name="Player1"; 
		sprite="frog.png"; height=50; width=50;
		playerLabel = new JLabel();
	}
	
	public Player(int x, int y) {
		spriteX=x; spriteY=y; name="Player1"; 
		sprite="frog.png"; height=50; width=50;
		playerLabel = new JLabel();
	}
	
	// Assign JLabel to Player
    public void setLabel(JLabel label) {
        this.playerLabel = label;
    }

    // Move the player and update the JLabel position
    public void moveUp(int step) {
        if (spriteY > 0) {
            spriteY -= step;
            updateLabelPosition();
        }
    }

    public void moveDown(int step, int screenHeight) {
        if (spriteY + height < screenHeight) {
            spriteY += step;
            updateLabelPosition();
        }
    }

    public void moveLeft(int step) {
        if (spriteX > 0) {
            spriteX -= step;
            updateLabelPosition();
        }
    }

    public void moveRight(int step, int screenWidth) {
        if (spriteX + width < screenWidth) {
            spriteX += step;
            updateLabelPosition();
        }
    }

    private void updateLabelPosition() {
        if (playerLabel != null) {
            playerLabel.setLocation(spriteX, spriteY);
        }
    }
	
	public void Display() {
		System.out.println("Player: "+name);
		System.out.println("dX,dY: "+spriteX+","+spriteY);
	}

}
	