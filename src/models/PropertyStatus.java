package models;

public enum PropertyStatus {
    FOR_SALE(1),
    FOR_RENT(2),
    FOR_BOTH(3);

    private final int state;

    PropertyStatus(int state) {
        this.state = state;
    }

    public int getState() {
        return state;
    }
}
