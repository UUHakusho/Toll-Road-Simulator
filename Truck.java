package TollRoadSimulator;

public class Truck extends Vehicle {

    private int numTrailers;

    public Truck(String registrationPlate, String manufacturer, int numTrailers) {

        super(registrationPlate, manufacturer);
        this.numTrailers = numTrailers;
    }

    @Override
    public String toString() {

        return this.getRegistrationPlate() +" "+this.getManufacturer() +" "+numTrailers;
    }

    @Override
    public int calculateBasicTripCost() {

        if (numTrailers <= 1) {
            return 1250;
        }
        else {
            return 1500;
        }
    }
}
