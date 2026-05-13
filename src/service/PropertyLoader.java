package service;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.lang.reflect.Executable;
import java.util.ArrayList;

import models.Apartment;
import models.House;
import models.Neighborhood;
import models.PropertyStatus;

public class PropertyLoader {

    private PropertyLoader() {}

    protected static ArrayList<House> property_loader(){

        ArrayList<House> extracted_houses = null;

        try (ObjectInputStream ois = new ObjectInputStream(
                new FileInputStream("./data/Houses.house")
        )){
            extracted_houses = (ArrayList<House>) ois.readObject();
        } catch (IOException e){
            System.out.print("Error during reading data: ");
            e.printStackTrace();
        } catch (Exception exception){
            System.out.print("Unexpected Error: ");
            exception.printStackTrace();
        }

        return extracted_houses;
    }
}
