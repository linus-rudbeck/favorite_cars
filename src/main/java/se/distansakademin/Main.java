package se.distansakademin;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    private static Database db;

    public static void main(String[] args) {
        try {
            runProgram();
        } catch (SQLException exception) {
            System.out.println("An error occurred with the database. Contact support immediately!");
            System.out.println(exception.getMessage());
        }
    }

    private static void runProgram() throws SQLException {
        db = new Database(); // Connect to the db and save connection

        while (true) {
            var selection = mainMenu();
            handleMenuSelection(selection);
        }
    }

    // Show main meu and return users selection
    private static int mainMenu() {
        // CRUD: Create, Read, Update, Delete

        while (true) { // Show menu until user selects a number
            try { // Try so we can catch invalid inputs (non integer values)
                printMainMenu();

                return getMenuSelection();
            } catch (NumberFormatException e) { // If invalid input, print error
                System.out.println("Invalid menu selection, please try again");
            }
        }
    }

    private static int getMenuSelection() throws NumberFormatException {
        // Get user input and convert to int
        Scanner scanner = new Scanner(System.in);
        String input = scanner.next();

        // Throws error if invalid input

        return Integer.parseInt(input);
    }

    private static void printMainMenu() {
        // Prints main menu
        System.out.print("\n### Main Menu ###\n" +
                "1. Show cars\n" + // Read
                "2. Add car\n" + // Create
                "3. Update car (not implemented)\n" + // Update
                "4. Delete car (not implemented)\n" + // Delete
                "Enter selection: ");
    }

    // Calls correct function based on input selection
    private static void handleMenuSelection(int selection) throws SQLException {
        switch (selection) {
            case 1:
                showCars();
                break;
            case 2:
                addNewCar();
                break;
            case 3:
                updateCar();
                break;
            default: // If not valid input, print error
                System.out.println("Invalid selection");
                break;
        }
    }

    // Update an existing car in the database
    public static void updateCar() {
        // 1. Ta emot uppgifter
        Scanner scanner = new Scanner(System.in);

        // Make our user select an ID to update
        System.out.print("Enter id to update: ");
        int id = scanner.nextInt();

        // Tell our user to enter a make
        System.out.print("Enter make: ");
        String make = scanner.next(); // Retrieve user input

        // Tell our user to enter a model
        System.out.print("Enter model: ");
        String model = scanner.next(); // Retrieve user input

        // Tell our user to enter a year
        System.out.print("Enter year: ");
        int year = scanner.nextInt();

        // 2. Skapa nytt bil-objekt
        Car car = new Car(id, make, model, year);

        // 3. Skicka bilen till databasen
        db.updateCar(car);

        // 4. Skriv ut om det fungerade!
    }

    // Create a new car and save to db
    private static void addNewCar() throws SQLException {
        Scanner scanner = new Scanner(System.in); // Scanner is used to retrieve user input

        // Tell our user to enter a make
        System.out.print("Enter make: ");
        String make = scanner.next(); // Retrieve user input

        // Tell our user to enter a model
        System.out.print("Enter model: ");
        String model = scanner.next(); // Retrieve user input

        // Tell our user to enter a year
        System.out.print("Enter year: ");
        int year = scanner.nextInt();

        Car car = new Car(make, model, year);

        // Save car to db and save result (true/false) to variable
        boolean result = db.saveCar(car);

        if (result) { // If result is true: Save successful
            System.out.println("Car saved");
        } else { // If not: Print error
            System.out.println("Error saving car");
        }
    }

    private static void showCars() throws SQLException {
        ArrayList<Car> cars = db.getAllCars(); // Get all cars from db
        printCars(cars);
    }

    private static void printCars(ArrayList<Car> cars) {
        // Loop over all cars
        for (Car car : cars) {
            System.out.println(car); // Print each car
        }
    }

    private static void showCarsNeedingService() throws SQLException {
        ArrayList<Car> cars = db.getAllCars(); // Get all cars from db

        // Loop over all cars
        for (Car car : cars) {

            if (car.needsService()) {
                System.out.println(car); // Print each car
            }
        }

    }
}
