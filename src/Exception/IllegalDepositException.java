package Exception;

public class IllegalDepositException extends RuntimeException {
    public IllegalDepositException() {
        super("IllegalDepositException: your entered amount is negative!");
    }
}
