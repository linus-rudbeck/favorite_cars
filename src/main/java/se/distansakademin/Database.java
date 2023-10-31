package se.distansakademin;

import java.sql.*;
import java.util.ArrayList;

public class Database {
    public static final String DB_URL = "jdbc:sqlite:sample.db";

    private Connection connection;

    public Database() throws SQLException {
        connection = DriverManager.getConnection(DB_URL);

        createCarsTableIfNotExists();
    }

    private void createCarsTableIfNotExists() throws SQLException {
        Statement stmt = connection.createStatement();

        String sql = "CREATE TABLE IF NOT EXISTS cars (" +
                "id INTEGER PRIMARY KEY," +
                "make TEXT NOT NULL," +
                "model TEXT NOT NULL," +
                "year INTEGER NOT NULL);";

        stmt.execute(sql);
    }

    // Saves a car to the database
    public boolean saveCar(Car car) throws SQLException {
        PreparedStatement stmt = prepareInsertCarStatement(car);
        return stmt.executeUpdate() > 0;
    }

    private PreparedStatement prepareInsertCarStatement(Car car) throws SQLException {
        String sql = "INSERT INTO cars (make, model, year) VALUES (?, ?, ?)";

        PreparedStatement stmt = connection.prepareStatement(sql);

        stmt.setString(1, car.getMake());
        stmt.setString(2, car.getModel());
        stmt.setInt(3, car.getYear());

        return stmt;
    }

    /**
     * Selects all cars from selected db (...)
     *
     * @return A list of all cars in the database
     * @throws SQLException - if the database is inaccessible or has an incorrect structure
     */
    public ArrayList<Car> getAllCars() throws SQLException {
        ResultSet rs = executeSelectSql("SELECT * FROM cars");
        return getCarListFromResultSet(rs);
    }

    private ResultSet executeSelectSql(String sql) throws SQLException {
        // Mer tillåtet
        return connection.createStatement().executeQuery(sql);
    }

    private static ArrayList<Car> getCarListFromResultSet(ResultSet rs) throws SQLException {
        ArrayList<Car> cars = new ArrayList<>();

        while (rs.next()) { // For every row (car)
            // Mindre tillåtet
            cars.add(getCarFromResultSet(rs)); // Add our car object in the list
        }

        return cars;
    }

    private static Car getCarFromResultSet(ResultSet rs) throws SQLException {
        int id = rs.getInt("id");
        String make = rs.getString("make");
        String model = rs.getString("model");
        int year = rs.getInt("year");

        return new Car(id, make, model, year);
    }

    public boolean updateCar(Car car) {
        String sql = "UPDATE cars SET make = ?, model = ?, year = ? WHERE id = ?";

        ///

        return false;
    }
}
