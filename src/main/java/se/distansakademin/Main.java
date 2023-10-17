package se.distansakademin;

public class Main {
    public static void main(String[] args) {
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