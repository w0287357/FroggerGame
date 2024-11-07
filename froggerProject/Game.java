package froggerProject;

import java.awt.Color;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JFrame;

public class Game extends JFrame implements ActionListener, KeyListener {

    private Frog myFrog = new Frog();
    private Car car0 = new Car();
    private Log[] logs = new Log[4];
    
    private JLabel FrogLabel = new JLabel();
    private JLabel CarLabel = new JLabel();
    private JLabel[] LogLabels = new JLabel[4];
    
    private ImageIcon FrogImage = new ImageIcon(getClass().getResource(myFrog.getSprite()));
    private ImageIcon CarImage = new ImageIcon(getClass().getResource(car0.getSprite()));
    private ImageIcon LogImage = new ImageIcon(getClass().getResource(new Log().getSprite())); 
    
    private Container content = getContentPane();

    private final int screenWidth = 800;
    private final int screenHeight = 800;
    private final int characterStep = 10;

    public Game() {
        setSize(screenWidth, screenHeight);
        content.setBackground(Color.gray);
        setLayout(null);

        myFrog.setX(400);
        myFrog.setY(730);
        myFrog.setWidth(10);
        myFrog.setHeight(10);

        FrogLabel.setLocation(myFrog.getX(), myFrog.getY());
        FrogLabel.setSize(myFrog.getWidth(), myFrog.getHeight());
        FrogLabel.setIcon(FrogImage);
        add(FrogLabel);

        car0.setX(300);
        car0.setY(500);
        car0.setWidth(100);
        car0.setHeight(10);

        CarLabel.setLocation(car0.getX(), car0.getY());
        CarLabel.setSize(car0.getWidth(), car0.getHeight());
        CarLabel.setIcon(CarImage);
        add(CarLabel);

        for (int i = 0; i < 4; i++) {
            logs[i] = new Log();
            logs[i].setX(200 + (i * 190));  
            logs[i].setY(500);
            logs[i].setWidth(100);
            logs[i].setHeight(10);

            LogLabels[i] = new JLabel();
            LogLabels[i].setLocation(logs[i].getX(), logs[i].getY());
            LogLabels[i].setSize(logs[i].getWidth(), logs[i].getHeight());
            LogLabels[i].setIcon(LogImage);
            add(LogLabels[i]);
        }

        content.addKeyListener(this);
        content.setFocusable(true);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        new Thread(new Runnable() {
            public void run() {
                moveLogs();             }
        }).start();
    }
    
    public void moveLogs() {
        while (true) {
            try {
                Thread.sleep(50); 
                for (int i = 0; i < 4; i++) {
                    int logX = logs[i].getX();

                    
                    if (logX + logs[i].getWidth() < screenWidth) {
                        logX += 5;
                    } else {
                        logX = -logs[i].getWidth(); 
                    }

                    
                    logs[i].setX(logX);
                    LogLabels[i].setLocation(logX, logs[i].getY());
                }

             
                repaint();

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        Game myGame = new Game();
        myGame.setVisible(true);
    }
    
    //player movement
    @Override
    public void keyPressed(KeyEvent evt) {
        // Movement based on key input
        switch (evt.getKeyCode()) {
            case KeyEvent.VK_UP -> myFrog.moveUp();
            case KeyEvent.VK_DOWN -> myFrog.moveDown();
            case KeyEvent.VK_LEFT -> myFrog.moveLeft();
            case KeyEvent.VK_RIGHT -> myFrog.moveRight();
        }

        //boundary
        myFrog.stayWithinBounds(screenWidth, screenHeight);

        //update player location
        FrogLabel.setLocation(myFrog.getX(), myFrog.getY());
    }

    public void keyReleased(KeyEvent arg0) {
        
    }

    public void keyTyped(KeyEvent arg0) {
        
    }

    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub

    }
}


