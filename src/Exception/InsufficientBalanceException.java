package Exception;

public class InsufficientBalanceException extends RuntimeException {
    private final long userBalance;
    private final long requestedBalance;

    public InsufficientBalanceException(long userBalance, long requestedBalance) {
        super("InsufficientBalanceException: userBalance=" + userBalance + " & requestedBalance=" + requestedBalance);
        this.userBalance = userBalance;
        this.requestedBalance = requestedBalance;
    }

    public long getMoneyShortage() {
        return requestedBalance - userBalance;
    }
}
