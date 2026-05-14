package service;

import java.io.*;
import java.util.ArrayList;

import models.House;

public class PropertyLoader {

    private PropertyLoader() {}

    protected static ArrayList<House> property_loader(){

        ArrayList<House> extracted_houses = null;

        try (ObjectInputStream ois = new ObjectInputStream(
            new FileInputStream("./data/Houses.house")
        )) {
            extracted_houses = (ArrayList<House>) ois.readObject();
        } catch (EOFException ignore) {
            extracted_houses = new ArrayList<>();
        } catch (IOException e){
            System.out.print("Error during reading data: ");
            e.printStackTrace();
        } catch (Exception exception){
            System.out.print("Unexpected Error: ");
            exception.printStackTrace();
        }

        return extracted_houses;
    }

    protected static void property_write(ArrayList<House> properties) {
        try (ObjectOutputStream oos = new ObjectOutputStream(
                new FileOutputStream("./data/Houses.house"))) {
            // intellij starts from ap-exercise folder!
            oos.writeObject(properties);
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
