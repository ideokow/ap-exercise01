package Exception;

public class InsufficientBalanceException extends RuntimeException {
    private final int userBalance;
    private final int requestedBalance;

    public InsufficientBalanceException(int userBalance, int requestedBalance) {
        super("InsufficientBalanceException: userBalance=" + userBalance + " & requestedBalance=" + requestedBalance);
        this.userBalance = userBalance;
        this.requestedBalance = requestedBalance;
    }

    public int getMoneyShortage() {
        return requestedBalance - userBalance;
    }
}
