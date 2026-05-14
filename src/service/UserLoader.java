package service;

import java.io.*;
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
        } catch (EOFException ignore){
            extracted_users = new ArrayList<>();
        } catch (IOException e){
            System.out.print("Error during reading data: ");
            e.printStackTrace();
        } catch (Exception exception){
            System.out.print("Unexpected Error: ");
            exception.printStackTrace();
        }

        return extracted_users;
    }

    protected static void user_write(ArrayList<User> extracted_users){
        try (ObjectOutputStream oos = new ObjectOutputStream(
                new FileOutputStream("./data/Users.user"))) {
            // intellij starts from ap-exercise folder!
            oos.writeObject(extracted_users);
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
