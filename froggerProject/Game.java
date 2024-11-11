package froggerProject;

import java.awt.Color;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Game extends JFrame implements ActionListener, KeyListener {

    private Frog player = new Frog();
    private EndZone scoreZone = new EndZone();
    
    //arrays of cars rows
    private Car[] cars0 = new Car[4];  
    private Car[] cars1 = new Car[4]; 
    private Car[] cars2 = new Car[4];  
    
    //arrays of log rows
    private Log[] logs0 = new Log[4];  
    private Log[] logs1 = new Log[4];
    private Log[] logs2 = new Log[4];  
    
    //frog label
    private JLabel FrogLabel = new JLabel();
    
    //score zone label
    private JLabel EndZoneLabel = new JLabel();
    
    //score tracking label
    private JLabel scoreLabel = new JLabel("Score: 0");
    
    //array of car labels
    private JLabel[] CarLabels0 = new JLabel[4];  
    private JLabel[] CarLabels1 = new JLabel[4];  
    private JLabel[] CarLabels2 = new JLabel[4];  
    
    //array of log labels
    private JLabel[] LogLabels0 = new JLabel[4];  
    private JLabel[] LogLabels1 = new JLabel[4];  
    private JLabel[] LogLabels2 = new JLabel[4];  
    
    //buttons
    private JButton StartButton = new JButton("Start Game");
    private JButton PlayAgainButton = new JButton("Play Again");
    private JButton EndGameButton = new JButton("End Game");
    
    //set images
    private ImageIcon FrogImage = new ImageIcon(getClass().getResource(player.getSprite()));
    private ImageIcon EndZoneImage = new ImageIcon(getClass().getResource("river.png"));
    private ImageIcon CarImage = new ImageIcon(getClass().getResource("car.png"));
    private ImageIcon LogImage = new ImageIcon(getClass().getResource("log.png"));

    private Container content = getContentPane();

    private final int screenWidth = 800;
    private final int screenHeight = 800;
    private final int characterStep = 50;
    private int score = 0;
    private boolean gameStarted = false;
    private boolean isRunning = false; 

    public Game() {
        setTitle("Frogger");
        setSize(screenWidth, screenHeight);
        content.setBackground(Color.gray);
        setLayout(null);
        
        Random rand = new Random();
        
        //set players location
        player.setX(400);
        player.setY(700);
        player.setWidth(50);
        player.setHeight(50);
        
        //set row car0 location
        for (int i = 0; i < cars0.length; i++) {
            cars0[i] = new Car();
            cars0[i].setWidth(rand.nextInt(120) + 50); 
            cars0[i].setHeight(50);  
            cars0[i].setSpeed(20);  
            cars0[i].setX(rand.nextInt(screenWidth - cars0[i].getWidth())); // Random X position
            cars0[i].setY(100); 
        }
        
        //set row car1 location
        for (int i = 0; i < cars1.length; i++) {
            cars1[i] = new Car();
            cars1[i].setWidth(rand.nextInt(120) + 50); 
            cars1[i].setHeight(50);  
            cars1[i].setSpeed(-20);  
            cars1[i].setX(rand.nextInt(screenWidth - cars1[i].getWidth())); 
            cars1[i].setY(150); 
        }
        
        //set row car2 location
        for (int i = 0; i < cars2.length; i++) {
            cars2[i] = new Car();
            cars2[i].setWidth(rand.nextInt(120) + 50); 
            cars2[i].setHeight(50);  
            cars2[i].setSpeed(-10);  
            cars2[i].setX(rand.nextInt(screenWidth - cars2[i].getWidth()));
            cars2[i].setY(200);  
        }

        //set log rows locations
        //set row log0 location
        for (int i = 0; i < logs0.length; i++) {
        	logs0[i] = new Log();
        	logs0[i].setWidth(rand.nextInt(120) + 50); 
        	logs0[i].setHeight(50);  
        	logs0[i].setSpeed(20);  
        	logs0[i].setX(rand.nextInt(screenWidth - logs0[i].getWidth())); 
            logs0[i].setY(300);  
        }
        
        //set row log1 location
        for (int i = 0; i < logs1.length; i++) {
        	logs1[i] = new Log();
        	logs1[i].setWidth(rand.nextInt(120) + 50); 
        	logs1[i].setHeight(50);  
        	logs1[i].setSpeed(10);  
        	logs1[i].setX(rand.nextInt(screenWidth - logs1[i].getWidth()));
            logs1[i].setY(350); 
        }
        
        //set row log2 location
        for (int i = 0; i < logs2.length; i++) {
        	logs2[i] = new Log();
        	logs2[i].setWidth(rand.nextInt(120) + 50); 
        	logs2[i].setHeight(50);  
        	logs2[i].setSpeed(-15);  
        	logs2[i].setX(rand.nextInt(screenWidth - logs2[i].getWidth())); 
            logs2[i].setY(400);  
        }

        //set score location
        scoreZone.setX(0);
        scoreZone.setY(0);
        scoreZone.setWidth(800);
        scoreZone.setHeight(5);

        //add frog
        FrogLabel.setLocation(player.getX(), player.getY());
        FrogLabel.setSize(player.getWidth(), player.getHeight());
        FrogLabel.setIcon(FrogImage);
        add(FrogLabel);
        
        //ADD CAR ROWS
        //add row car0
        for (int i = 0; i < cars0.length; i++) {
            CarLabels0[i] = new JLabel();
            CarLabels0[i].setLocation(cars0[i].getX(), cars0[i].getY());
            CarLabels0[i].setSize(cars0[i].getWidth(), cars0[i].getHeight());
            CarLabels0[i].setIcon(CarImage);
            add(CarLabels0[i]);
        }

        //add row car1
        for (int i = 0; i < cars1.length; i++) {
            CarLabels1[i] = new JLabel();
            CarLabels1[i].setLocation(cars1[i].getX(), cars1[i].getY());
            CarLabels1[i].setSize(cars1[i].getWidth(), cars1[i].getHeight());
            CarLabels1[i].setIcon(CarImage);
            add(CarLabels1[i]);
        }
        
        //add row car2
        for (int i = 0; i < cars2.length; i++) {
            CarLabels2[i] = new JLabel();
            CarLabels2[i].setLocation(cars2[i].getX(), cars2[i].getY());
            CarLabels2[i].setSize(cars2[i].getWidth(), cars2[i].getHeight());
            CarLabels2[i].setIcon(CarImage);
            add(CarLabels2[i]);
        }
        
        //ADD LOG ROWS
        //add row log0
        for (int i = 0; i < logs0.length; i++) {
            LogLabels0[i] = new JLabel();
            LogLabels0[i].setLocation(logs0[i].getX(), logs0[i].getY());
            LogLabels0[i].setSize(logs0[i].getWidth(), logs0[i].getHeight());
            LogLabels0[i].setIcon(LogImage);
            add(LogLabels0[i]);
        }

        //add row log1
        for (int i = 0; i < logs1.length; i++) {
            LogLabels1[i] = new JLabel();
            LogLabels1[i].setLocation(logs1[i].getX(), logs1[i].getY());
            LogLabels1[i].setSize(logs1[i].getWidth(), logs1[i].getHeight());
            LogLabels1[i].setIcon(LogImage);
            add(LogLabels1[i]);
        }
        
        //add row log2
        for (int i = 0; i < logs2.length; i++) {
            LogLabels2[i] = new JLabel();
            LogLabels2[i].setLocation(logs2[i].getX(), logs2[i].getY());
            LogLabels2[i].setSize(logs2[i].getWidth(), logs2[i].getHeight());
            LogLabels2[i].setIcon(LogImage);
            add(LogLabels2[i]);
        }
        
        //add score zone label/image
        EndZoneLabel.setLocation(scoreZone.getX(), scoreZone.getY());
        EndZoneLabel.setSize(scoreZone.getWidth(), scoreZone.getHeight());
        EndZoneLabel.setIcon(EndZoneImage);
        add(EndZoneLabel);

        //set location of score label
        scoreLabel.setLocation(10, 10);
        scoreLabel.setSize(200, 30);
        scoreLabel.setForeground(Color.white);
        add(scoreLabel);

        //add buttons
        StartButton.setLocation(400, 600);
        StartButton.setSize(100, 40);
        StartButton.addActionListener(this);
        add(StartButton);

        PlayAgainButton.setLocation(400, 600);
        PlayAgainButton.setSize(100, 40);
        PlayAgainButton.addActionListener(this);
        PlayAgainButton.setVisible(false); // Initially hidden
        add(PlayAgainButton);

        EndGameButton.setLocation(400, 640);
        EndGameButton.setSize(100, 40);
        EndGameButton.addActionListener(this);
        EndGameButton.setVisible(false); // Initially hidden
        add(EndGameButton);

        content.addKeyListener(this);
        content.setFocusable(true);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

   //call game object in main routine
    public static void main(String[] args) {
        Game myGame = new Game();
        myGame.setVisible(true);
    }
    
    
    //check if score
    public void checkCollisionScore() {
        if (player.getY() <= scoreZone.getY() + scoreZone.getHeight()) {
            score += 50;
            System.out.println("Score: " + score);
            updateScoreLabel();
            resetPlayerPosition();
        }
    }
    
    
    //check if collison with car inside all 3 car arrays
    public void checkCollisionCar() {
        for (int i = 0; i < cars0.length; i++) {
            if (player.getX() + player.getWidth() > cars0[i].getX()
                    && player.getX() < cars0[i].getX() + cars0[i].getWidth()
                    && player.getY() + player.getHeight() > cars0[i].getY()
                    && player.getY() < cars0[i].getY() + cars0[i].getHeight()) {
                score -= 50;
                System.out.println("Score: " + score);
                updateScoreLabel();
                resetPlayerPosition();
            }
        }
        
        for (int i = 0; i < cars1.length; i++) {
            if (player.getX() + player.getWidth() > cars1[i].getX()
                    && player.getX() < cars1[i].getX() + cars1[i].getWidth()
                    && player.getY() + player.getHeight() > cars1[i].getY()
                    && player.getY() < cars1[i].getY() + cars1[i].getHeight()) {
                score -= 50;
                System.out.println("Score: " + score);
                updateScoreLabel();
                resetPlayerPosition();
            }
        }
        
        for (int i = 0; i < cars2.length; i++) {
            if (player.getX() + player.getWidth() > cars2[i].getX()
                    && player.getX() < cars2[i].getX() + cars2[i].getWidth()
                    && player.getY() + player.getHeight() > cars2[i].getY()
                    && player.getY() < cars2[i].getY() + cars2[i].getHeight()) {
                score -= 50;
                System.out.println("Score: " + score);
                updateScoreLabel();
                resetPlayerPosition();
            }
        }
    }

    public void checkCollisionLog() {
        for (int i = 0; i < logs0.length; i++) {
            if (player.getX() + player.getWidth() > logs0[i].getX()
                    && player.getX() < logs0[i].getX() + logs0[i].getWidth()
                    && player.getY() + player.getHeight() > logs0[i].getY()
                    && player.getY() < logs0[i].getY() + logs0[i].getHeight()) {
                System.out.println("On logs0");
            }
        }
        
        for (int i = 0; i < logs1.length; i++) {
            if (player.getX() + player.getWidth() > logs1[i].getX()
                    && player.getX() < logs1[i].getX() + logs1[i].getWidth()
                    && player.getY() + player.getHeight() > logs1[i].getY()
                    && player.getY() < logs1[i].getY() + logs1[i].getHeight()) {
            	   System.out.println("On logs1");
            }
        }
        
        for (int i = 0; i < logs2.length; i++) {
            if (player.getX() + player.getWidth() > logs2[i].getX()
                    && player.getX() < logs2[i].getX() + logs2[i].getWidth()
                    && player.getY() + player.getHeight() > logs2[i].getY()
                    && player.getY() < logs2[i].getY() + logs2[i].getHeight()) {
                System.out.println("On logs2");
             
            }
        }
    }
    
    public void resetPlayerPosition() {
        player.setX(400);
        player.setY(700);
        FrogLabel.setLocation(player.getX(), player.getY());
        stopGame();
        if (gameStarted) {
            PlayAgainButton.setVisible(true);
            EndGameButton.setVisible(true);
        }
    }

    public void updateScoreLabel() {
        scoreLabel.setText("Score: " + score);
    }

    public void startGame() {
        gameStarted = true;
        isRunning = true;
        StartButton.setVisible(false);
        PlayAgainButton.setVisible(false); 
        EndGameButton.setVisible(false); 
        content.requestFocusInWindow();
        startGameLoop(); 
    }
    
    
    //stop game
    public void stopGame() {
        isRunning = false;  
    }

    //game loops to run cars and check for collisions
    public void startGameLoop() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (isRunning) {
                    moveCars();
                    moveLogs();
                    checkCollisionCar();
                    checkCollisionLog();
                    checkCollisionScore();

                    try {
                        Thread.sleep(50);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }

    public void moveCars() {
    	//car0 rows
        for (int i = 0; i < cars0.length; i++) {
            int tX = cars0[i].getX();
            int tY = cars0[i].getY();

            tX -= cars0[i].getSpeed();

            if (tX + cars0[i].getWidth() < 0) {
                tX = screenWidth;
            }

            cars0[i].setX(tX);
            CarLabels0[i].setLocation(tX, tY);
        }
        
        //cars1 array
        for (int i = 0; i < cars1.length; i++) {
            int tX = cars1[i].getX();
            int tY = cars1[i].getY();

            tX -= cars1[i].getSpeed();

            if (tX > screenWidth) {
                tX = -1 * cars1[i].getWidth(); 
            }

            cars1[i].setX(tX);
            CarLabels1[i].setLocation(tX, tY);
        }
        
        //cars2 array
        for (int i = 0; i < cars2.length; i++) {
            int tX = cars2[i].getX();
            int tY = cars2[i].getY();

            tX -= cars2[i].getSpeed();
            
            if (tX > screenWidth) {
                tX = -1 * cars2[i].getWidth(); 
            }

            cars2[i].setX(tX);
            CarLabels2[i].setLocation(tX, tY);
        }
    }
    
    public void moveLogs() {
    	//logs0 rows
        for (int i = 0; i < logs0.length; i++) {
            int tX = logs0[i].getX();
            int tY = logs0[i].getY();

            tX += logs0[i].getSpeed();

            if (tX > screenWidth) {
                tX = -1 * logs0[i].getWidth();
            }

            logs0[i].setX(tX);
            LogLabels0[i].setLocation(tX, tY);
        }
        
        //logs1 array
        for (int i = 0; i < logs1.length; i++) {
        	int tX = logs1[i].getX();
            int tY = logs1[i].getY();

            tX += logs1[i].getSpeed();

            if (tX > screenWidth) {
                tX = -1 * logs1[i].getWidth();
            }

            logs1[i].setX(tX);
            LogLabels1[i].setLocation(tX, tY);
        }
        
        //logs2 array
        for (int i = 0; i < logs2.length; i++) {
        	int tX = logs2[i].getX();
            int tY = logs2[i].getY();

            tX += logs2[i].getSpeed();

            if (tX + logs2[i].getWidth() < 0) {
                tX = screenWidth;
            }

            logs2[i].setX(tX);
            LogLabels2[i].setLocation(tX, tY);
        }
    }

    public void keyPressed(KeyEvent evt) {
    	//only let use of keys if game is running
        if (!isRunning) return; 

        int drX = player.getX();
        int drY = player.getY();

        // Move up
        if (evt.getKeyCode() == KeyEvent.VK_UP) {
            drY -= characterStep;
            if (drY + player.getHeight() < 0) { drY = screenHeight; }
        }

        // Move down
        if (evt.getKeyCode() == KeyEvent.VK_DOWN) {
            if (drY < 700) {
                drY += characterStep;
            }
        }

        // Move left
        if (evt.getKeyCode() == KeyEvent.VK_LEFT) {
            drX -= characterStep;
            if (drX + player.getWidth() < 0) { drX = screenWidth; }
        }

        // Move right
        if (evt.getKeyCode() == KeyEvent.VK_RIGHT) {
            drX += characterStep;
            if (drX + player.getWidth() > screenWidth) { drX = 0; }
        }

        player.setX(drX);
        player.setY(drY);
        FrogLabel.setLocation(player.getX(), player.getY());
    }

    public void keyReleased(KeyEvent e) {}
    public void keyTyped(KeyEvent e) {}
    
    //action performed for button actions
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == StartButton) {
            startGame();
        }
        if (e.getSource() == PlayAgainButton) {
            resetGame();
            startGame();
        }
        if (e.getSource() == EndGameButton) {
            System.exit(0);
        }
    }
    
    
    //reset player,logs,cars and 
    public void resetGame() {
        player.setX(400);
        player.setY(700);
     
        //reset cars
        Random rand = new Random();
        for (int i = 0; i < cars0.length; i++) {
            cars0[i].setX(rand.nextInt(screenWidth - cars0[i].getWidth())); // Random X position
            cars0[i].setY(100);  // Vertically spaced cars
            CarLabels0[i].setLocation(cars0[i].getX(), cars0[i].getY());
        }
        
        for (int i = 0; i < cars1.length; i++) {
            cars1[i].setX(rand.nextInt(screenWidth - cars1[i].getWidth())); // Random X position
            cars1[i].setY(150);  // Vertically spaced cars
            CarLabels1[i].setLocation(cars1[i].getX(), cars1[i].getY());
        }
        
        for (int i = 0; i < cars2.length; i++) {
            cars2[i].setX(rand.nextInt(screenWidth - cars2[i].getWidth())); // Random X position
            cars2[i].setY(200);  // Vertically spaced cars
            CarLabels2[i].setLocation(cars2[i].getX(), cars2[i].getY());
        }

        //reset logs
        for (int i = 0; i < logs0.length; i++) {
        	logs0[i].setX(rand.nextInt(screenWidth - logs0[i].getWidth())); // Random X position
        	logs0[i].setY(300);  // Vertically spaced cars
            LogLabels0[i].setLocation(logs0[i].getX(), logs0[i].getY());
        }
        
        for (int i = 0; i < logs1.length; i++) {
        	logs1[i].setX(rand.nextInt(screenWidth - logs1[i].getWidth())); // Random X position
            logs1[i].setY(350);  // Vertically spaced cars
            LogLabels1[i].setLocation(logs1[i].getX(), logs1[i].getY());
        }
        
        for (int i = 0; i < logs2.length; i++) {
        	logs2[i].setX(rand.nextInt(screenWidth - logs2[i].getWidth())); // Random X position
        	logs2[i].setY(400);  // Vertically spaced cars
            LogLabels2[i].setLocation(logs2[i].getX(), logs2[i].getY());
        }
        
        PlayAgainButton.setVisible(false);
        EndGameButton.setVisible(false);
    }
}
