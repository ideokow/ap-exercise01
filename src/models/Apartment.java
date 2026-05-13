package models;

public class Apartment extends House {
    // properties
    private final int floors;

    // constructor
    public Apartment(String uniqueID, String ownerID, String tenantID, double area, Neighborhood neighborhood, int bedrooms, int bathrooms, PropertyStatus status, int floors) {
        super(uniqueID, ownerID, tenantID, area, neighborhood, bedrooms, bathrooms, status);
        this.floors = floors;
    }

    // functions
    @Override
    long calculateSalePrice(){
        return (long) (calculateBasePrice() * (1 + (0.03*getBedrooms())) * (1 + (0.01*getFloors())));
    }

    // --- getters ---
    public int getFloors() {
        return floors;
    }
}
