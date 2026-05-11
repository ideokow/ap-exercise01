package models;

public enum ContractType {
    SALE(1),
    RENT(2);

    private final int state;

    ContractType(int state) {
        this.state = state;
    }

    public int getState() {
        return state;
    }
}
