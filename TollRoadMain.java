package TollRoadSimulator;

import java.io.*;

public class TollRoadMain {

    public TollRoad initialiseTollRoadFromFile() {

        TollRoad tollRoad = new TollRoad();
        try {
            File file = new File("customerData.txt");
            BufferedReader reader = new BufferedReader(new FileReader(file)); //BufferedReader does efficient reading of characters, arrays, and lines
            String line;
            while ((line = reader.readLine()) != null) {

                String[] customerRawData = line.split("#"); //splits on the hash character to separate each customer individual data

                for (String customerData : customerRawData) { //iterates through each customerRawData
                    String[] part = customerData.split(","); //splits on comma to split the information within each customer individual data
                    //the split of data depends on the vehicle type, then the constructor for that specific vehicle will be used to split the data appropriately
                    if (part[0].equals("Car")) {
                        //uses Customer Account and the sub-class vehicle constructors to efficiently allocate each part of the data as the class attributes
                        CustomerAccount CA = new CustomerAccount(part[2], part[3], new Car(part[1], part[4], Integer.parseInt(part[5])), Integer.parseInt(part[6]));
                        if (part[7].equals("STAFF")) {
                            CA.activateStaffDiscount();
                        } else if (part[7].equals("FRIENDS_AND_FAMILY")) {
                            CA.activeFriendsAndFamilyDiscount();
                        }
                        System.out.println(CA);
                        tollRoad.addCustomer(CA);
                    }

                    else if (part[0].equals("Van")) {
                        CustomerAccount CA = new CustomerAccount(part[2], part[3], new Van(part[1], part[4], Integer.parseInt(part[5])), Integer.parseInt(part[6]));
                        if (part[7].equals("STAFF")) {
                            CA.activateStaffDiscount();
                        } else if (part[7].equals("FRIENDS_AND_FAMILY")) {
                            CA.activeFriendsAndFamilyDiscount();
                        }
                        System.out.println(CA);
                        tollRoad.addCustomer(CA);
                    }

                    else if (part[0].equals("Truck")) {
                        CustomerAccount CA = new CustomerAccount(part[2], part[3], new Truck(part[1], part[4], Integer.parseInt(part[5])), Integer.parseInt(part[6]));
                        if (part[7].equals("STAFF")) {
                            CA.activateStaffDiscount();
                        } else if (part[7].equals("FRIENDS_AND_FAMILY")) {
                            CA.activeFriendsAndFamilyDiscount();
                        }
                        System.out.println(CA);
                        tollRoad.addCustomer(CA);
                    }
                }

                System.out.println(tollRoad);
            }
            reader.close();
        }
        //the exception caught here handles a situation where there is no file to be found
        catch (IOException e) {
            System.err.format("File not found!");
            e.printStackTrace();
        }

        return tollRoad;
    }

    public void simulateFromFile(TollRoad road) {

        try {
            File file = new File("transactions.txt");
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line;
            while ((line = reader.readLine()) != null) {

                String[] transactionRawData = line.split("\\$"); //regex used here was to effectively split on the dollar sign

                for (String transactionData : transactionRawData) {
                    String[] lineSplit = transactionData.split(",");

                    try {
                        if (lineSplit[0].equals("addFunds")) {
                            road.findCustomer(lineSplit[1]).addFunds(Integer.parseInt(lineSplit[2])); //adds funds to the customer account that has the matching reg number
                            System.out.println(lineSplit[1] + ": " + Integer.parseInt(lineSplit[2])+" added successfully");
                        }
                    }
                    catch (CustomerNotFoundException e) {
                        System.out.println(lineSplit[1] + ": addFunds failed. CustomerAccount does not exist");
                    }

                    if (lineSplit[0].equals("makeTrip")) {

                        try {
                            road.chargeCustomer(lineSplit[1]);
                            System.out.println(lineSplit[1] + ": Trip completed successfully");
                        }
                        catch (InsufficientAccountBalanceException e){
                            System.out.println(lineSplit[1] + ": makeTrip failed. Insufficient funds");
                        }
                        catch (CustomerNotFoundException e) {
                            System.out.println(lineSplit[1] + ": addFunds failed. CustomerAccount does not exist");
                        }

                    }
                }

            }
        }
        catch (IOException e) {
            System.err.format("File not found!");
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {

        TollRoad tollRoad;
        TollRoadMain trm = new TollRoadMain();
        tollRoad = trm.initialiseTollRoadFromFile();
        trm.simulateFromFile(tollRoad);
        System.out.println("Total money made by the toll road during the simulation was: " + tollRoad.getMoneyMade());
    }

}






