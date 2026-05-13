package service;

import java.util.ArrayList;
import java.util.Scanner;

import models.Apartment;
import models.Villa;
import models.Penthouse;
import models.Neighborhood;
import service.PropertyLoader;
import models.House;

public class Interface {
    // private constructor to avoid making instance!
    private Interface() {}

    // main function
    protected static void interface_loader(String user_ID) {

        System.out.println("Hello " + user_ID);

        // load properties
        ArrayList<House> properties = PropertyLoader.property_loader();

        // display properties
        display_all_properties(properties, 0);

        // listener
        Scanner scanner = new Scanner(System.in);
        String command = null;
        System.out.print("> ");
        while (!"exit".equals(command)){
            // read command
            System.out.print("> ");
            command = scanner.nextLine();

            switch (command.split(" ")[0]){
                case "select":
                    selecting_in_main_menu(command.split(" ")[1], properties);
                case "exit":
                    break;
            }

        }

    }

    // selector with unique ID
    private static void selecting_in_main_menu(String property_id, ArrayList<House> property_list){
        System.out.println("You hava selected property with this ID: " + property_id);
        House selected = null;

        for (House h: property_list){
            if (h.getUniqueID().equals(property_id)){
                selected = h;
                break;
            }
        }

        if (selected != null && selected.getStatus().getState() != 4){
            display_a_property(selected);
        } else {
            System.out.println("You have entered wrong ID! Try again.");
        }
    }

    private static void display_a_property(House the_house){

        // --- property type ---
        if (the_house instanceof Apartment) System.out.print("Apartment");
        else if (the_house instanceof Villa) System.out.print("Villa");
        else if (the_house instanceof Penthouse) System.out.print("Penthouse");

        // --- general detail ---
        System.out.println(" with ID: " + the_house.getUniqueID());
        System.out.println("Area: " + the_house.getArea() + " square-meters");
        System.out.println("The property is in \"" + the_house.getNeighborhood().getName() + "\" neighborhood");
        System.out.println("Bedrooms: " + the_house.getBedrooms() + " | Bathrooms: " + the_house.getBathrooms());

        // --- specific detail ---
        if (the_house instanceof Apartment){
            System.out.println("Floor: " + ((Apartment) the_house).getFloors());
        } else if (the_house instanceof Villa) {
            System.out.println("Floor: " + ((Villa) the_house).getFloors());
            System.out.println("Yard-Area: " + ((Villa) the_house).getYardArea() + " square-meters");
        } else if (the_house instanceof Penthouse) {
            System.out.println("Terrace-Area: " + ((Penthouse) the_house).getTerraceArea() + " square-meters");
        }

        // --- price ---
        // if for sale
        if (the_house.getStatus().getState() == 1){
            System.out.println("Sale Price: " + the_house.getSalePrice());
        }
        // if for rent
        else if (the_house.getStatus().getState() == 2){
            System.out.println("Rent Price: " + the_house.getRentPrice());
        }
        // if for both rent and sale
        else if (the_house.getStatus().getState() == 3){
            System.out.println("Sale Price: " + the_house.getSalePrice());
            System.out.println("Rent Price: " + the_house.getRentPrice());
        }
        // not for sale or rent
        else {
            System.out.println("It is not for sale or rent");
        }

    }

    private static void display_all_properties(ArrayList<House> property_list, int filter){
        int c=1;
        for (House the_house: property_list){
            if (filter == 0 && the_house.getStatus().getState() != 4){
                System.out.println("#" + c + " ID: " + the_house.getUniqueID());
                // --- one line detail + pricing ---
                if (the_house instanceof Apartment){
                    // summary
                    System.out.println("Apartment | " + the_house.getArea() + " square-meters | Floor: " + ((Apartment) the_house).getFloors());
                    // sale price (if available)
                    if (the_house.getStatus().getState() == 1 || the_house.getStatus().getState() == 3){
                        System.out.println("Sale Price: " + ((Apartment) the_house).getSalePrice());
                    }
                    // rent price (if available)
                    if (the_house.getStatus().getState() == 2 || the_house.getStatus().getState() == 3){
                        System.out.println("Rent Price: " + ((Apartment) the_house).getRentPrice());
                    }
                } else if (the_house instanceof Villa) {
                    // summary
                    System.out.println("Villa | " + the_house.getArea() + " square-meters | yard: " + ((Villa) the_house).getYardArea() + " square-meters");
                    // sale price (if available)
                    if (the_house.getStatus().getState() == 1 || the_house.getStatus().getState() == 3) {
                        System.out.println("Sale Price: " + ((Villa) the_house).getSalePrice());
                    }
                    // rent price (if available)
                    if (the_house.getStatus().getState() == 2 || the_house.getStatus().getState() == 3) {
                        System.out.println("Rent Price: " + ((Villa) the_house).getRentPrice());
                    }
                } else if (the_house instanceof Penthouse) {
                    // summary
                    System.out.println("Penthouse | " + the_house.getArea() + " square-meters | terrace: " + ((Penthouse) the_house).getTerraceArea() + " square-meters");
                    // sale price (if available)
                    if (the_house.getStatus().getState() == 1 || the_house.getStatus().getState() == 3) {
                        System.out.println("Sale Price: " + ((Penthouse) the_house).getSalePrice());
                    }
                    // rent price (if available)
                    if (the_house.getStatus().getState() == 2 || the_house.getStatus().getState() == 3) {
                        System.out.println("Rent Price: " + ((Penthouse) the_house).getRentPrice());
                    }
                }
                c += 1;
            }
        }
    }
}
