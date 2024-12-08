package froggerProject;

import java.awt.event.*;
import java.io.*;
import java.net.*;
import javax.swing.*;

public class client extends JFrame implements KeyListener {
    private Socket socket;
    private PrintWriter out;
    private BufferedReader in;
    private Player player = new Player();
    private JLabel FrogLabel = new JLabel();
    
    // Player Starting Position
    private int playerX = 400;
    private int playerY = 700;
    private static final int CHARACTER_STEP = 50;

    // Score Tracking
    private int score = 0;
    private JLabel scoreLabel = new JLabel("Score: " + score);
    
    public client() {
        setTitle("Frogger Client");
        setSize(800, 800);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Initialize player label
        FrogLabel.setLocation(playerX, playerY);
        FrogLabel.setSize(50, 50);
        FrogLabel.setIcon(new ImageIcon(getClass().getResource("frog.png")));
        add(FrogLabel);
        
        // Initialize score label
        scoreLabel.setBounds(10, 10, 100, 30);
        add(scoreLabel);
        
        addKeyListener(this);
        setFocusable(true);
        
        try {
            socket = new Socket("localhost", 12345);  // Connect to server
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream(), true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Handle key press events to send player position to server
    @Override
    public void keyPressed(KeyEvent e) {
        String direction = "";
        switch (e.getKeyCode()) {
            case KeyEvent.VK_UP: direction = "UP"; break;
            case KeyEvent.VK_DOWN: direction = "DOWN"; break;
            case KeyEvent.VK_LEFT: direction = "LEFT"; break;
            case KeyEvent.VK_RIGHT: direction = "RIGHT"; break;
        }

        if (!direction.isEmpty()) {
            out.println(playerX + "," + playerY + "," + direction);  // Send position and direction to server
            
            // Read updated coordinates from the server
            try {
                String response = in.readLine();
                String[] updatedPosition = response.split(",");
                playerX = Integer.parseInt(updatedPosition[0]);
                playerY = Integer.parseInt(updatedPosition[1]);
                
                // Check if the player has reached the top of the window
                if (playerY <= 0) {
                    playerX = 400;  // Reset player to initial position
                    playerY = 700;
                    score += 50;  // Increase score by 50
                    scoreLabel.setText("Score: " + score);  // Update score label
                }
                
                // Update the player label position
                FrogLabel.setLocation(playerX, playerY);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {}
    @Override
    public void keyTyped(KeyEvent e) {}

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
        	client client = new client();
            client.setVisible(true);
        });
    }
}

