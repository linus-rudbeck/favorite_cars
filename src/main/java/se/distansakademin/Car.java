package se.distansakademin;

public class Car {
    private int id;
    private String make;
    private String model;
    private int year;
    private int mileage = 20000;
    private int gasLevel = 50;

    public Car(String make, String model, int year) {
        this.make = make;
        this.model = model;
        this.year = year;
    }

    public Car(int id, String make, String model, int year) {
        this.id = id;
        this.make = make;
        this.model = model;
        this.year = year;
    }

    public boolean needsService() { // car.needsService() <-- Rekommenderas
        int SERVICE_AT_MILEAGE = 20000;
        return mileage > SERVICE_AT_MILEAGE;
    }


    public static boolean needsService(int mileage) { // Car.needsService(car.getMileage()) <-- Rekommenderas ej
        int SERVICE_AT_MILEAGE = 20000;
        return mileage > SERVICE_AT_MILEAGE;
    }


    public String getGasWarning() {
        if (gasLevel > 80) {
            return "GOOD";
        } else if (gasLevel > 60) {
            return "OKAY";
        } else if (gasLevel > 40) {
            return "HALF WAY";
        } else if (gasLevel > 20) {
            return "RUNNING OUT";
        } else {
            return "WARNING";
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        make = make.toLowerCase();
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMileage() {
        return mileage;
    }

    public void setMileage(int mileage) {
        this.mileage = mileage;
    }

    public int getGasLevel() {
        return gasLevel;
    }

    public void setGasLevel(int gasLevel) {
        this.gasLevel = gasLevel;
    }

    @Override
    public String toString() { // Format string output
        return id + ": " + make + " " + model + " (" + year + ")";
    }
}
