package froggerProject;

import java.awt.Color;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Game extends JFrame implements ActionListener, KeyListener, Runnable {
	
	//PLAYER OBJECT AND LABEL --------------- 
    private Player player = new Player();
    private JLabel FrogLabel = new JLabel();
    
    //LOG ARRAY 1 AND LABELS ---------------
    private Log[] logs1 = new Log[4];
    private JLabel[] logLabels1 = new JLabel[4];
    
    //LOG ARRAY 2 AND LABELS  ---------------
    
    //LOG ARRAY 3 AND LABELS  ---------------
  
    
    //CAR ARRAY 1 AND LABELS ---------------
    
    //CAR ARRAY 2 AND LABELS ---------------
    
    //CAR ARRAY 3 AND LABELS ---------------
  
    
    //START BUTTON --------------
    private JButton StartButton = new JButton("Start Game");
    
    //IMAGE ICONS
    private ImageIcon FrogImage = new ImageIcon(getClass().getResource(player.getSprite()));
    private ImageIcon LogImage = new ImageIcon(getClass().getResource("log.png"));
    //private ImageIcon cARImage = new ImageIcon(getClass().getResource("CAR.png"));
    
    //CONTAINER
    private Container content = getContentPane();
    
    //CHECK IF GAME RUNNING ---------------
    private boolean isRunning = false;
    
    //SCREEN SIZE
    private final int screenWidth = 800;
    private final int screenHeight = 800;
    
    //PLAYER STARTING SPAWN COORDS
    private final int startingPositionX = 400;
    private final int startingPositionY = 700;
    
    //PLAYER STEP SIZE ---------------
    private final int characterStep = 50; 

    //CHECK FOR PLAYER ON LOG
    private boolean playerOnLogs1 = false;
    
    /*----------------------------------- MAIN GAME WINDOW --------------------------------------*/
    public Game() {
    	
    	//SET TITLE OF GAME WINDOW ---------------
        setTitle("Frogger");
        //DISPLAY SCREEN BY SIZE ---------------
        setSize(screenWidth, screenHeight);
        //SET BACKRGOUND COLOR ---------------
        content.setBackground(Color.gray);
        setLayout(null);

        //SET PLAYER OBJECT SCREEN ---------------
        player.setX(startingPositionX);
        player.setY(startingPositionY);
        player.setWidth(characterStep);
        player.setHeight(characterStep);
        
        //SET PLAYER OBJECTS IMAGE ICON SCREEN ---------------
        FrogLabel.setLocation(player.getX(), player.getY());
        FrogLabel.setSize(player.getWidth(), player.getHeight());
        FrogLabel.setIcon(FrogImage);
        player.setLabel(FrogLabel);
        add(FrogLabel);
        
        //ADD LOG ARRAYS TO SCREEN ---------------
        addLogArray(logs1, logLabels1, 100, 5);
        	//LOG ARRAY 2
        	//LOG ARRAY 3
        
        //ADD CAR ARRAYS TO SCREEN ---------------
          //addCarArray(cars1, carLabels1, 300, 5);
        	//CAR ARRAY 2
        	//CAR ARRAY 3
        
        //SET START BUTTON TO SCREEN ---------------
        StartButton.setLocation(400, 600);
        StartButton.setSize(100, 40);
        StartButton.addActionListener(this);
        add(StartButton);
        
        //SET ENDZONE ON SCREEN --------------
        
        //SET PLAYER SCORE TO SCREEN
        
        content.addKeyListener(this);
        content.setFocusable(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    /*------------------------------------ GAME WINDOW ENDS  --------------------------------------*/ 
    
    /*----------------------------------------MAIN ENTRY--------------------------------------*/ 
    public static void main(String[] args) {
        Game myGame = new Game();
        myGame.setVisible(true);
    }
    
    
    /*---------------------------------------- LOG LOGIC STARTS--------------------------------------*/ 
    
    //FUNCTION TO ADD LOG ARRAYS
    //PROTOTYPE YTO ADD LOG ARRAYS, LABELS, Y COORD AND SPEED ---------------
    public void addLogArray(Log[] logs, JLabel[] logLabels, int y, int speed) {
    	//SET MULTIPLE LOGS
        for (int i = 0; i < logs.length; i++) {
            logs[i] = new Log();
            logs[i].setX(100 + i * 200); 
            logs[i].setY(y);
            logs[i].setWidth(100);
            logs[i].setHeight(50);
            logs[i].setSpeed(speed);

            //SET CORRESPONDING LABEL
            logLabels[i] = new JLabel();
            logLabels[i].setLocation(logs[i].getX(), logs[i].getY());
            logLabels[i].setSize(logs[i].getWidth(), logs[i].getHeight());
            logLabels[i].setIcon(LogImage);

            //ADD LABEL TO WINDOW
            add(logLabels[i]);
        }
    }
     
    //FUNCTION TO MOVE LOG ARRAY
    //MOVE LOG ARRAY AND U0PDATE LABELS ---------------
    public void moveLogs(Log[] logs, JLabel[] logLabels) {
        for (int i = 0; i < logs.length; i++) {
            Log log = logs[i];

            // Update the log's position
            log.setX(log.getX() + log.getSpeed());

            // Reset log position if it goes off-screen
            if (log.getX() > screenWidth) {
                log.setX(-log.getWidth());
            }

            // Update the log's visual location
            logLabels[i].setLocation(log.getX(), log.getY());
        }
    }
    
    //FUNCTION TO DETECT IF PLAYER AND LOG OVER LAP? IF TRUE RETURN TRUE
    
    /*---------------------------------------- LOG LOGIC ENDS --------------------------------------*/
    
    
    
    /*---------------------------------------- CAR LOGIC STARTS--------------------------------------*/ 
    
    //FUNCTION TO ADD CAR ARRAYS
    //public void addCarArray(Car[] cars, JLabel[] logLabels, int y, int speed) {
     
    //MOVE CAR ARRAY AND UPDATE LABELS ---------------
    //public void moveCars(Car[] cars, JLabel[] logLabels) {
    
    //FUNCTION TO DETECT IF PLAYER AND LOG OVER LAP? IF TRUE RETURN TRUE
       
    
    /*---------------------------------------- CAR LOGIC ENDS --------------------------------------*/
    
/*---------------------------------------- COLLISION LOGIC STARTS--------------------------------------*/ 
    
    //FUNCTION IS PLAYER TOUCHING LOG AND IN RIVER? 
    
    //FUNCTION IS PLAYER TOUCHING CAR?
    
    //FUNCTION IS PLAYER ENDZONE? 
    
    //FUNCTION RESET PLAYER LOCATION
    
    //FUNCTION UPDATE SCORE
    
    /*---------------------------------------- CAR LOGIC ENDS --------------------------------------*/
    
    //START BUTTON CALLS ACTIONS ---------------
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == StartButton) {
            isRunning = true;
            StartButton.setVisible(false);
            Thread gameThread = new Thread(this);
            gameThread.start();
        }
    }
    
    //KEYS PRESSED ---------------
    @Override
    public void keyPressed(KeyEvent evt) {
        if (!isRunning) return;

        switch (evt.getKeyCode()) {
            case KeyEvent.VK_UP -> player.moveUp(characterStep);
            case KeyEvent.VK_DOWN -> player.moveDown(characterStep, screenHeight);
            case KeyEvent.VK_LEFT -> player.moveLeft(characterStep);
            case KeyEvent.VK_RIGHT -> player.moveRight(characterStep, screenWidth);
        }
        player.Display();
    }
    
    //OVERRIDES FOR KEYS ---------------
    @Override
    public void keyReleased(KeyEvent e) {}
    @Override
    public void keyTyped(KeyEvent e) {}
    //OVERRIDES FOR KEYS ---------------
    
    
    /* ---------------------------------- GAME LOGIC LOOPS STARTS ---------------------------------------*/
    @Override
    public void run() {
        while (isRunning) {
        	
            moveLogs(logs1, logLabels1);
            
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    
    /* ---------------------------------- GAME LOGIC LOOPS ENDS ---------------------------------------*/
    
}
