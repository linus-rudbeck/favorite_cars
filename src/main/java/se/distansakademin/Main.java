package se.distansakademin;

// Comment to show git push
// Another comment

import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    // db object will be a connection to
    // the database in all other static functions
    private static Database db;

    public static void main(String[] args) {
        db = new Database(); // Connect to the db and save connection

        while (true) {
            var selection = mainMenu();
            handleMenuSelection(selection);
        }
    }

    // Show main meu and return users selection
    private static int mainMenu(){
        // CRUD: Create, Read, Update, Delete

        while (true) { // Show menu until user selects a number
            try { // Try so we can catch invalid inputs (non integer values)

                // Prints main menu
                System.out.print("\n### Main Menu ###\n" +
                        "1. Show cars\n" + // Read
                        "2. Add car (not implemented)\n" + // Create
                        "3. Update car (not implemented)\n" + // Update
                        "4. Delete car (not implemented)\n" + // Delete
                        "Enter selection: ");

                // Get user input and convert to int
                Scanner scanner = new Scanner(System.in);
                String input = scanner.next();

                // Throws error if invalid input
                int inputSelection = Integer.parseInt(input);

                return inputSelection; // Return selection if everything worked

            } catch (NumberFormatException e) { // If invalid input, print error
                System.out.println("INVALID NUMBER");
            }
        }
    }

    // Calls correct function based on input selection
    private static void handleMenuSelection(int selection){
        switch (selection){
            case 1:
                showCars();
                break;
            case 2:
                addNewCar();
                System.exit(0);
                break;
            default: // If not valid input, print error
                System.out.println("Invalid selection");
                break;
        }
    }

    // Create a new car and save to db
    private static void addNewCar() {
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

        // Create new car from user inputs
        Car car = new Car(make, model, year);

        // Save car to db and save result (true/false) to variable
        boolean result = db.saveCar(car);

        if(result){ // If result is true: Save successful
            System.out.println("Car saved");
        }
        else { // If not: Print error
            System.out.println("Error saving car");
        }
    }

    private static void showCars(){
        ArrayList<Car> cars = db.getCars(); // Get all cars from db

        // Loop over all cars
        for (Car car : cars) {
            System.out.println(car); // Print each car
        }
    }

    private static void newCar(){
        var myVolvo = new Car("Volvo", "S90", 2023);

        System.out.println(myVolvo);

        var db = new Database();

        var res = db.saveCar(myVolvo);

        if(res){
            System.out.println("Car saved!");
        }
        else{
            System.out.println("Car not saved");
        }
    }
}
