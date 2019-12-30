package TollRoadSimulator;

import java.lang.Comparable;

public class CustomerAccount implements Comparable<CustomerAccount> {

    private enum DiscountType {NONE, STAFF, FRIENDS_AND_FAMILY}

    private String       firstName;
    private String       secondName;
    private Vehicle      vehicle;
    private int          currentAccBalance;
    private DiscountType discount;

    //default constructor
    public CustomerAccount() {

        firstName         = "";
        secondName        = "";
        this.vehicle      = vehicle;
        currentAccBalance = 0;
        discount          = DiscountType.NONE;
    }

    public CustomerAccount(String firstName, String secondName, Vehicle vehicle, int currentAccBalance) {

        this.firstName         = firstName;
        this.secondName        = secondName;
        this.vehicle           = vehicle;
        this.currentAccBalance = currentAccBalance;
        this.discount          = DiscountType.NONE; //the DiscountType is defaulted to NONE
    }

    public String getFirstName()      { return firstName; }
    public String getSecondName()     { return secondName; }
    public Vehicle getVehicle()       { return vehicle; }
    public int getCurrentAccBalance() { return currentAccBalance; }
    public DiscountType getDiscount() { return discount; }

    public void activateStaffDiscount() {
        this.discount = DiscountType.STAFF;
    }

    public void activeFriendsAndFamilyDiscount(){
        this.discount = DiscountType.FRIENDS_AND_FAMILY;
    }

    public void deactivateDiscount() {
        this.discount = DiscountType.NONE;
    }

    public void addFunds(int amount){
        this.currentAccBalance = currentAccBalance + amount;
    }

    public int makeTrip() throws InsufficientAccountBalanceException {

        int basicTripCost = this.vehicle.calculateBasicTripCost(); //calls the method from the vehicle class

        if (discount == DiscountType.STAFF) {
            basicTripCost = (int) (basicTripCost * 0.5); //0.5 is a float, this forces the conversion to an int item (this halves basicTripCost)
        }
        else if (discount == DiscountType.FRIENDS_AND_FAMILY) {
            basicTripCost = (int) (basicTripCost * 0.9); //multiplying by 0.9 will reduce the cost by 10%
        }

        if (basicTripCost < currentAccBalance) {
            currentAccBalance = currentAccBalance - basicTripCost;
            return basicTripCost;
        }
        else {
            throw new InsufficientAccountBalanceException();
        }

    }
    //formats the output of customer accounts
    @Override
    public String toString() {

        return firstName +" "+ secondName +" "+ currentAccBalance +" "+ discount +" "+ vehicle;
    }

    @Override
    public int compareTo(CustomerAccount other) {
        //creates compare integer, compares the customeraccounts vehicle and registration plate with another, using compareTo
        int compare = this.getVehicle().getRegistrationPlate().compareTo(other.getVehicle().getRegistrationPlate());

        //after the comparison, if compare is bigger than 0, return 1 - meaning that this customer account comes before the other customer account
        if (compare > 0) {
            return 1;
        }
        else if (compare < 0) {
            return -1;
        }
        else {
            return 0;
        }
    }
}
