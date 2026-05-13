package models;

import java.io.Serializable;

public abstract class House implements Serializable {

    // properties
    private final String uniqueID;
    private final String ownerID;
    private final String tenantID;
    private final double area;
    private final Neighborhood neighborhood;
    private final int bedrooms;
    private final int bathrooms;
    private final PropertyStatus status;

    // constants
    protected static final long BASE_PRICE_PER_METER = 50_000_000L;
    protected static final double RENT_RATE = 0.004;

    // constructor
    public House(String uniqueID, String ownerID, String tenantID, double area, Neighborhood neighborhood, int bedrooms, int bathrooms, PropertyStatus status) {
        this.uniqueID = uniqueID;
        this.ownerID = ownerID;
        this.tenantID = tenantID;
        this.area = area;
        this.neighborhood = neighborhood;
        this.bedrooms = bedrooms;
        this.bathrooms = bathrooms;
        this.status = status;
    }

    // functions
    long calculateBasePrice() {
        return (long) (getArea() * BASE_PRICE_PER_METER * getNeighborhood().getCoefficient());
    }

    long calculateSalePrice(){
        return (long) calculateBasePrice();
    };

    long calculateRentPrice(){
        return (long) (calculateSalePrice() * RENT_RATE);
    };

    // --- getters ---
    public String getUniqueID() {
        return uniqueID;
    }

    public String getOwnerID() {
        return ownerID;
    }

    public String getTenantID() {
        return tenantID;
    }

    public double getArea() {
        return area;
    }

    public Neighborhood getNeighborhood() {
        return neighborhood;
    }

    public int getBedrooms() {
        return bedrooms;
    }

    public int getBathrooms() {
        return bathrooms;
    }

    public PropertyStatus getStatus() {
        return status;
    }

    public double getSalePrice() {
        return calculateSalePrice();
    }

    public double getRentPrice() {
        return calculateRentPrice();
    }
}
