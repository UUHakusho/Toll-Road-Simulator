package TollRoadSimulator;

public class Car extends Vehicle {

    private int numberOfSeats;

    public Car(String registrationPlate, String manufacturer, int numberOfSeats) {

        super(registrationPlate, manufacturer); //uses it's inheritance from Vehicle
        this.numberOfSeats = numberOfSeats;
    }

    public int getNumberOfSeats() {
        return numberOfSeats;
    }

    //formats the output
    @Override
    public String toString(){

        return this.getRegistrationPlate() + " " + this.getManufacturer() + " " + numberOfSeats;
    }

    //overrides the abstract method from the superclass Vehicle
    @Override
    public int calculateBasicTripCost() {

        if (numberOfSeats <= 5) {
            return 500;
        }
        else
            {
            return 600;
            }
    }
}
