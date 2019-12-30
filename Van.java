package TollRoadSimulator;

public class Van extends Vehicle {

    private int payload;

    public Van(String registrationPlate, String manufacturer, int payload) {

        super(registrationPlate, manufacturer);
        this.payload = payload;
    }

    @Override
    public String toString(){
        return this.getRegistrationPlate() + " " + this.getManufacturer() + " " + payload;}

    @Override
    public int calculateBasicTripCost(){

         if (payload <= 600) {
             return 500;
         }

         else if (600 < payload && payload <= 800) {
             return 750;
         }

             return 1000;
    }
}
