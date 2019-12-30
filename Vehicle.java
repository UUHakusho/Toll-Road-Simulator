package TollRoadSimulator;

public abstract class Vehicle {
    //should have used protected instead of private
    private String registrationPlate;
    private String manufacturer;


    public String getRegistrationPlate() {
        return registrationPlate;
    }

    public String getManufacturer() { return manufacturer; }

    public Vehicle(String registrationPlate, String manufacturer) {

        this.registrationPlate = registrationPlate;
        this.manufacturer      = manufacturer;
    }

    //default constructor
    public Vehicle() {

        registrationPlate = "";
        manufacturer = "";
    }

    public abstract int calculateBasicTripCost();

}
