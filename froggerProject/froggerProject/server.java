package froggerProject;

import java.io.*;
import java.net.*;
import java.util.concurrent.*;

public class server {
    private static final int PORT = 12345;  // Port for server connection
    private static final ExecutorService threadPool = Executors.newCachedThreadPool();
    
    public static void main(String[] args) {
        System.out.println("Server is running...");
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            while (true) {
                Socket clientSocket = serverSocket.accept();
                threadPool.submit(() -> handleClient(clientSocket));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void handleClient(Socket clientSocket) {
        try (
            InputStreamReader inputStreamReader = new InputStreamReader(clientSocket.getInputStream());
            BufferedReader reader = new BufferedReader(inputStreamReader);
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(clientSocket.getOutputStream());
            BufferedWriter writer = new BufferedWriter(outputStreamWriter)
        ) {
            String message;
            while ((message = reader.readLine()) != null) {
                // Process client request
                String[] parts = message.split(",");
                int x = Integer.parseInt(parts[0]);
                int y = Integer.parseInt(parts[1]);
                String direction = parts[2];
                
                // Move player based on direction
                if (direction.equals("UP")) y -= 50;
                if (direction.equals("DOWN")) y += 50;
                if (direction.equals("LEFT")) x -= 50;
                if (direction.equals("RIGHT")) x += 50;
                
                System.out.println("Player's new coordinates: X = " + x + ", Y = " + y);
                
                //IF PLAYER COORDS MATCH LOGS Y AND ALSO INSIDE LOG X && X+WIDTH === RIDE LOG
                	//IF PLAYER Y COORDS MATCH LOG Y BUT NOT INSIDE LOG X && LOG X+WIDTH == RESET AND LOSE -50 SCORE
                
                //IF PLATER COORDS MATCH CAR === RESET AND LOSE -50 SCORE
                
                //IF PLAYER COORDS MATCH SCORE ZONE === RESET AND SCORE 50
                
                
                // Send updated position to the client
                writer.write(x + "," + y + "\n");
                writer.flush();
            }
        } catch (IOException e) {
            System.out.println("Client disconnected or error occurred: " + e.getMessage());
        } finally {
            try {
                clientSocket.close();  // Make sure to close the socket when done
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

