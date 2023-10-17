package se.distansakademin;

import java.sql.*;
import java.util.ArrayList;
import java.util.LinkedList;

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

    // Get all cars from our database
    public ArrayList<Car> getCars(){
        String sql = "SELECT * FROM cars"; // SQL query for getting all cars

        ArrayList<Car> cars = null; // Create list outside try-catch so we can return it later

        try {
            // Prepare and execute our database query
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            cars = new ArrayList<>(); // Set cars to an empty list

            while(rs.next()){ // For every row (car)

                // Get all properties
                int id = rs.getInt("id");
                String make = rs.getString("make");
                String model = rs.getString("model");
                int year = rs.getInt("year");

                Car car = new Car(id, make, model, year); // Create new car object

                cars.add(car); // Add our car object in the list
            }

        } catch (SQLException e) {
            // Log or handle error...
        }

        return cars; // Returns all cars (if it worked) or null (if not)
    }
}
