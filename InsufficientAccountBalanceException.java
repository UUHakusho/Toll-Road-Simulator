package TollRoadSimulator;

import java.lang.Exception;

public class InsufficientAccountBalanceException extends Exception {

    public InsufficientAccountBalanceException(){

        super("Insufficient Account Balance!"); //uses the exception class to uniquely format the error message when there is a system error
    }
}
