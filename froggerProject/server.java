package froggerProject;

import java.io.*;
import java.net.*;

public class server {
    private static final int PORT = 12345;
    private static Player player = new Player(400, 700);  // Starting position

    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            System.out.println("Server started. Waiting for client connections...");

            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("Client connected.");
                new ClientHandler(clientSocket).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static class ClientHandler extends Thread {
        private Socket clientSocket;
        private PrintWriter out;
        private BufferedReader in;

        public ClientHandler(Socket socket) {
            this.clientSocket = socket;
        }

        @Override
        public void run() {
            try {
                in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                out = new PrintWriter(clientSocket.getOutputStream(), true);

                String message;
                while ((message = in.readLine()) != null) {
                    // Update player position based on message (up, down, left, right)
                    switch (message) {
                        case "UP":
                            player.setY(player.getY() - 50);
                            break;
                        case "DOWN":
                            player.setY(player.getY() + 50);
                            break;
                        case "LEFT":
                            player.setX(player.getX() - 50);
                            break;
                        case "RIGHT":
                            player.setX(player.getX() + 50);
                            break;
                    }

                    // Send updated player position back to client
                    out.println(player.getX() + "," + player.getY());
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
