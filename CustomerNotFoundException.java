package TollRoadSimulator;

import java.lang.Exception;

public class CustomerNotFoundException extends Exception {

    public CustomerNotFoundException(){

        super("Customer Not Found!");
    }
}
