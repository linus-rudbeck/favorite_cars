package se.distansakademin;

import java.sql.*;

public class Database {

    private Connection conn;

    public Database(){
        try {
            conn = DriverManager.getConnection("jdbc:sqlite:sample.db");

            Statement stmt = conn.createStatement();

            String sql = "CREATE TABLE IF NOT EXISTS cars (" +
                    "id INTEGER PRIMARY KEY," +
                    "make TEXT NOT NULL," +
                    "model TEXT NOT NULL," +
                    "year INTEGER NOT NULL)";

            stmt.execute(sql);

            stmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean saveCar(Car car){
        String sql = "INSERT INTO cars (make, model, year) VALUES (?, ?, ?)";

        boolean result;

        try {
            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setString(1, car.getMake());
            stmt.setString(2, car.getModel());
            stmt.setInt(3, car.getYear());

            result = stmt.executeUpdate() > 0;

            stmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return result;
    }
}
