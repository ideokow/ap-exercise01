package models;

import java.util.ArrayList;
import Exception.InsufficientBalanceException;
import Exception.IllegalDepositException;

public class User {
    // properties
    private final String uniqueID;
    private final String username;
    private final String passwordHash;
    private long balance;
    private final ArrayList<String> ownedPropertyIDs;
    private final ArrayList<String> rentedPropertyIDs;
    private final ArrayList<String> contractIDs;

    // constructor
    public User(String uniqueID, String username, String passwordHash, int balance, ArrayList<String> ownedPropertyIDs, ArrayList<String> rentedPropertyIDs, ArrayList<String> contractIDs) {
        this.uniqueID = uniqueID;
        this.username = username;
        this.passwordHash = passwordHash;
        this.balance = balance;
        this.ownedPropertyIDs = ownedPropertyIDs;
        this.rentedPropertyIDs = rentedPropertyIDs;
        this.contractIDs = contractIDs;
    }

    // functions
    public boolean increaseBalance(long amount){
        if (amount < 0){
            throw new IllegalDepositException();
        }
        setBalance(balance + amount);
        return true;
    }

    public boolean decreaseBalance(long amount){
        if (amount < 0){
            throw new IllegalDepositException();
        }
        if(getBalance() - amount < 0){
            throw new InsufficientBalanceException(getBalance(), amount);
        }
        setBalance(balance - amount);
        return true;
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
