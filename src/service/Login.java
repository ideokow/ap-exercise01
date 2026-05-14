package service;

import java.util.ArrayList;
import java.util.Scanner;

import models.*;

public class Login {
    // private constructor to avoid making instance!
    private Login() {}

    public static void logging_in() {
        System.out.println("--- Welcome to Institution CLI Application ---");

        // load users
        ArrayList<User> extracted_users = UserLoader.user_loader();

        // login page!
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
                String unique_id = Integer.valueOf(extracted_users.size()+1).toString();

                the_user = new User(
                    unique_id,
                    entered_username,
                    PasswordHasher.hashPassword(entered_pass),
                    0,
                    new ArrayList<>(),
                    new ArrayList<>(),
                    new ArrayList<>()
                );

                // write new user
                extracted_users.add(the_user);
                UserLoader.user_write(extracted_users);

                // Hello!
                System.out.println("Hello " + the_user.getUsername() + "!!");
                Interface.interface_loader(the_user.getUniqueID());

            } else {
                System.out.print("Enter Password: ");
                String entered_pass = scanner.next();
                if (PasswordHasher.hashPassword(entered_pass).equals(the_user.getPasswordHash())){
                    System.out.println("Logging In ...");
                    // Hello!
                    System.out.println("Hello " + the_user.getUsername() + "!!");
                    Interface.interface_loader(the_user.getUniqueID());
                    break;
                } else {
                    System.out.println("Wrong Password! try again");
                }
            }
        }
    }
}
