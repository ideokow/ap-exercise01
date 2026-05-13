package models;

public class Penthouse extends House {
    // properties
    private final double terraceArea;

    // constant
    protected static final long TERRACE_PRICE_PER_METER = 10_000_000L;
    protected static final double LUXURY_COEFFICIENT = 1.5;

    // constructor
    public Penthouse(String uniqueID, String ownerID, String tenantID, double area, Neighborhood neighborhood, int bedrooms, int bathrooms, PropertyStatus status, double terraceArea) {
        super(uniqueID, ownerID, tenantID, area, neighborhood, bedrooms, bathrooms, status);
        this.terraceArea = terraceArea;
    }

    // function
    @Override
    long calculateSalePrice(){
        return (long) ((calculateBasePrice() * LUXURY_COEFFICIENT) + (terraceArea * TERRACE_PRICE_PER_METER));
    }

    // --- getter ---
    public double getTerraceArea() {
        return terraceArea;
    }
}
