package models;

import java.io.Serializable;
import java.util.ArrayList;
import Exception.InsufficientBalanceException;
import Exception.IllegalDepositException;

public class User implements Serializable {
    // properties
    private final String uniqueID;
    private final String username;
    private final String passwordHash;
    private long balance;
    private final ArrayList<String> ownedPropertyIDs;
    private final ArrayList<String> rentedPropertyIDs;
    private final ArrayList<String> contractIDs;

    // constructor
    public User(String uniqueID, String username, String passwordHash, long balance, ArrayList<String> ownedPropertyIDs, ArrayList<String> rentedPropertyIDs, ArrayList<String> contractIDs) {
        this.uniqueID = uniqueID;
        this.username = username;
        this.passwordHash = passwordHash;
        this.balance = balance;
        this.ownedPropertyIDs = ownedPropertyIDs;
        this.rentedPropertyIDs = rentedPropertyIDs;
        this.contractIDs = contractIDs;
    }

    // functions
    public void increaseBalance(long amount){
        if (amount < 0){
            throw new IllegalDepositException();
        }
        setBalance(balance + amount);
    }

    public void decreaseBalance(long amount){
        if (amount < 0){
            throw new IllegalDepositException();
        }
        if(getBalance() - amount < 0){
            throw new InsufficientBalanceException(getBalance(), amount);
        }
        setBalance(balance - amount);
    }

    public void add_to_owned_properties(String property_uniqueID){
        ownedPropertyIDs.add(property_uniqueID);
    }

    public void remove_from_owned_properties(String property_uniqueID) {
        ownedPropertyIDs.remove(property_uniqueID);
    }

    public void add_to_contracts(String contract_uniqueID){
        contractIDs.add(contract_uniqueID);
    }

    // --- getter and setters ---
    public String getUniqueID() {
        return uniqueID;
    }

    public String getUsername() {
        return username;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public long getBalance() {
        return balance;
    }

    public void setBalance(long balance) {
        this.balance = balance;
    }

    public ArrayList<String> getOwnedPropertyIDs() {
        return ownedPropertyIDs;
    }

    public ArrayList<String> getRentedPropertyIDs() {
        return rentedPropertyIDs;
    }

    public ArrayList<String> getContractIDs() {
        return contractIDs;
    }
}
