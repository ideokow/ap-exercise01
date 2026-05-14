package models;

import java.io.Serializable;

public class Contract implements Serializable {
    // properties
    private final String uniqueID;
    private final String propertyID;
    private final String partyOneID; // Owner
    private final String partyTwoID; // Buyer (or tenant)
    private final ContractType type;
    private final long finalPrice;
    private boolean isCancelled;

    // constants
    protected final double PENALTY_RATE = 0.1;

    // construction
    public Contract(String uniqueID, String propertyID, String partyOneID, String partyTwoID, ContractType type, long finalPrice) {
        this.uniqueID = uniqueID;
        this.propertyID = propertyID;
        this.partyOneID = partyOneID;
        this.partyTwoID = partyTwoID;
        this.type = type;
        this.finalPrice = finalPrice;
        isCancelled = false;
    }

    // functions
    double calculateCancellationPenalty(){
        return getFinalPrice() * PENALTY_RATE;
    }

    // --- getters & setters ---
    public String getUniqueID() {
        return uniqueID;
    }

    public String getPropertyID() {
        return propertyID;
    }

    public String getPartyOneID() {
        return partyOneID;
    }

    public String getPartyTwoID() {
        return partyTwoID;
    }

    public ContractType getType() {
        return type;
    }

    public long getFinalPrice() {
        return finalPrice;
    }

    public boolean isCancelled() {
        return isCancelled;
    }

    public void setCancelled(boolean cancelled) {
        isCancelled = cancelled;
    }
}
