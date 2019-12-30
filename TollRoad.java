package TollRoadSimulator;

import java.util.ArrayList;

public class TollRoad {

    private int moneyMade;
    private ArrayList<CustomerAccount> customerAccounts; //array list with type CustomerAccount to store customer accounts

    public TollRoad() {

        customerAccounts = new ArrayList();
        moneyMade        = 0;
    }

    //constructor for initialising moneyMade and customerAccounts as ArrayList
    public TollRoad(ArrayList customerAccounts, int moneyMade) {

        customerAccounts = new ArrayList();
        this.moneyMade   = moneyMade;
    }

    public int getMoneyMade() { return moneyMade; }
    public ArrayList<CustomerAccount> getCustomerAccounts(){ return customerAccounts; }

    public void addCustomer(CustomerAccount acc){

        customerAccounts.add(acc);
    }

    public CustomerAccount findCustomer(String regNum) throws CustomerNotFoundException {

        for (CustomerAccount customer : customerAccounts ) { //iterates through each item in array and stores them as variable customer

            if (customer.getVehicle().getRegistrationPlate().contains(regNum)) {
                return customer;
            }
        }
            throw new CustomerNotFoundException();
    }

    public void chargeCustomer(String registrationNumber) throws InsufficientAccountBalanceException, CustomerNotFoundException {

        CustomerAccount temp = findCustomer(registrationNumber); //findCustomer method used so I do not have to repeat code here
                moneyMade += temp.makeTrip(); //the operator will make moneyMade equal to moneyMade + makeTrip()
            }

}
