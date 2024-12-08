package froggerProject;

import java.sql.*;

public class DatabaseManager {

    private Connection connection;
    private String url = "game_scores.db";

    public DatabaseManager() {
        try {
            connection = DriverManager.getConnection(url);
            Statement stmt = connection.createStatement();
            String createTableSQL = "CREATE TABLE IF NOT EXISTS scores ("
                    + "id INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + "name TEXT NOT NULL, "
                    + "score INTEGER NOT NULL)";
            stmt.execute(createTableSQL);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void insertScore(String name, int score) {
        try {
            String insertSQL = "INSERT INTO scores (name, score) VALUES (?, ?)";
            PreparedStatement pstmt = connection.prepareStatement(insertSQL);
            pstmt.setString(1, name);
            pstmt.setInt(2, score);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void close() {
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

