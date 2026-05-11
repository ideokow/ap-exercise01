package models;

import java.util.ArrayList;

public class Institution {
    // it's a singleton class!
    private static Institution instance;

    // properties
    private final String uniqueId = "InstitutionID";
    private final ArrayList<String> availablePropertiesIDs = new ArrayList<String>();

    // private constructor
    private Institution() {}

    // get instance thing!
    public static Institution getInstance() {
        if (instance == null) {
            instance = new Institution();
        }
        return instance;
    }

    // fast sale
    public void acquireProperty(String propertyID) {
        this.availablePropertiesIDs.add(propertyID);
    }

    // getter
    public ArrayList<String> getAvailablePropertiesIDs() {
        return availablePropertiesIDs;
    }
}
