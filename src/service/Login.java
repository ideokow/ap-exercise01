package service;

import models.House;
import models.User;
import service.Interface;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Login {
    // private constructor to avoid making instance!
    private Login() {}

    public static void logging_in() {
        System.out.println("--- Welcome to Institution CLI Application ---");

        // load users
        ArrayList<User> extracted_users = new ArrayList<User>();
        try (ObjectInputStream ois = new ObjectInputStream(
                new FileInputStream("./data/Users.user")
        )){
            extracted_users = (ArrayList<User>) ois.readObject();
        } catch (IOException e){
            System.out.println("Warning: there is no user data or there is an error during reading user data");
//            e.printStackTrace();
        } catch (Exception exception){
            System.out.print("Unexpected Error: ");
            exception.printStackTrace();
        }

        while (true){
            Scanner scanner = new Scanner(System.in);
            System.out.println("Enter your Username or if you don't have account enter new Username:");
            String entered_username = scanner.next();

            // --- check username ---
            User the_user = null;
            // find user
            for (User user: extracted_users){
                if (user.getUsername().equals(entered_username)){
                    the_user = user;
                }
            }

            if (the_user == null){
                // new-user
                // pass
                System.out.print("Enter new password: ");
                String entered_pass = scanner.next();
                // recheck pass
                System.out.print("Enter password again: ");
                if (!scanner.next().equals(entered_pass)){
                    System.out.println("Failed to re-enter password. try again.");
                    break;
                }
                // id
                String unique_id = Integer.valueOf(extracted_users.size()).toString();

                the_user = new User(
                    unique_id,
                    entered_username,
                    PasswordHasher.hashPassword(entered_pass),
                    0,
                    new ArrayList<String>(),
                    new ArrayList<String>(),
                    new ArrayList<String>()
                );

                // write new user
                extracted_users.add(the_user);
                try (ObjectOutputStream oos = new ObjectOutputStream(
                        new FileOutputStream("./data/Users.user"))) {
                    // intellij starts from ap-exercise folder!
                    oos.writeObject(extracted_users);
                } catch (Exception e){
                    e.printStackTrace();
                }

                // enter interface
                Interface.interface_loader(the_user);

            } else {
                System.out.print("Enter Password: ");
                String entered_pass = scanner.next();
                if (PasswordHasher.hashPassword(entered_pass).equals(the_user.getPasswordHash())){
                    System.out.println("Logging In ...");
                    Interface.interface_loader(the_user);
                    break;
                } else {
                    System.out.println("Wrong Password! try again");
                }
            }
        }
    }
}
