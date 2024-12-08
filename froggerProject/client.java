package froggerProject;

import java.awt.Color;
import java.awt.Container;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.*;
import java.net.*;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class client extends JFrame implements KeyListener, Runnable {
    private Player player = new Player(400, 700);
    private JLabel FrogLabel = new JLabel();
    private ImageIcon FrogImage = new ImageIcon(getClass().getResource("frog.png"));
    private Container content = getContentPane();
    private Socket socket;
    private PrintWriter out;
    private BufferedReader in;

    public client() {
        setTitle("Frogger");
        setSize(800, 800);
        content.setBackground(Color.gray);
        setLayout(null);

        FrogLabel.setLocation(player.getX(), player.getY());
        FrogLabel.setSize(50, 50);
        FrogLabel.setIcon(FrogImage);
        add(FrogLabel);

        JButton startButton = new JButton("Start Game");
        startButton.setLocation(400, 600);
        startButton.setSize(100, 40);
        startButton.addActionListener(e -> startGame());
        add(startButton);

        content.addKeyListener(this);
        content.setFocusable(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        Game game = new Game();
        game.setVisible(true);
    }

    private void startGame() {
        try {
            socket = new Socket("localhost", 12345);  // Connect to the server
            out = new PrintWriter(socket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            // Start a thread to read the player's position from the server
            new Thread(this).start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void keyPressed(KeyEvent evt) {
        if (evt.getKeyCode() == KeyEvent.VK_UP) {
            out.println("UP");
        } else if (evt.getKeyCode() == KeyEvent.VK_DOWN) {
            out.println("DOWN");
        } else if (evt.getKeyCode() == KeyEvent.VK_LEFT) {
            out.println("LEFT");
        } else if (evt.getKeyCode() == KeyEvent.VK_RIGHT) {
            out.println("RIGHT");
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {}
    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void run() {
        try {
            String response;
            while ((response = in.readLine()) != null) {
                String[] coords = response.split(",");
                player.setX(Integer.parseInt(coords[0]));
                player.setY(Integer.parseInt(coords[1]));

                // Update player's label position
                FrogLabel.setLocation(player.getX(), player.getY());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
