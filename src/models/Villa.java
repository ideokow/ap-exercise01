package models;

public class Villa extends House {
    // properties
    private final double yardArea;
    private final int floors;

    // constants
    protected static final long FLOOR_PREMIUM = 200_000_000L;
    protected static final long YARD_PRICE_PER_METER = 20_000_000L;

    // constructor
    public Villa(String uniqueID, String ownerID, String tenantID, double area, Neighborhood neighborhood, int bedrooms, int bathrooms, PropertyStatus status, double yardArea, int floors) {
        super(uniqueID, ownerID, tenantID, area, neighborhood, bedrooms, bathrooms, status);
        this.yardArea = yardArea;
        this.floors = floors;
    }

    // functions
    @Override
    long calculateSalePrice(){
        return (long) (calculateBasePrice() + (yardArea * YARD_PRICE_PER_METER) + (floors * FLOOR_PREMIUM));
    }

    // --- getters ---
    public double getYardArea() {
        return yardArea;
    }

    public int getFloors() {
        return floors;
    }
}
