package service;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;

import models.User;

public class UserLoader {

    private UserLoader() {}

    protected static ArrayList<User> user_loader(){

        ArrayList<User> extracted_users = new ArrayList<>();

        try (ObjectInputStream ois = new ObjectInputStream(
                new FileInputStream("./data/Users.user")
        )){
            extracted_users = (ArrayList<User>) ois.readObject();
        } catch (IOException e){
            System.out.print("Error during reading data: ");
            e.printStackTrace();
        } catch (Exception exception){
            System.out.print("Unexpected Error: ");
            exception.printStackTrace();
        }

        return extracted_users;
    }
}
