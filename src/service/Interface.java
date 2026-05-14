package service;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Scanner;

import models.*;

public class Interface {
    // private constructor to avoid making instance!
    private Interface() {}

    // main function
    protected static void interface_loader(User the_user) {
        Scanner scanner = new Scanner(System.in);

        // mode selection listener
        System.out.println("Select one of the operations:");
        System.out.println("[1:    for-SALE properties]");
        System.out.println("[2:    for-RENT properties]");
        System.out.println("[3: advertise new property]");
        System.out.println("[4:      view own property]");
        System.out.print("(Type one of 1/2/3/4) > ");

        while (true){
            String mode = scanner.next();
            if ("1".equals(mode)){
                saleViewer(the_user);
            } else if ("2".equals(mode)) {
                rentViewer(the_user);
            } else if ("3".equals(mode)) {
                newProperty(the_user);
            } else if ("4".equals(mode)) {
                ownViewer(the_user);
                // after own property check user will be back in main menu
            } else if ("exit".equals(mode)) break;
            System.out.print("(Type one of 1/2/3/4) > ");
        }
    }

    private static void ownViewer(User the_user) {
        // load properties
        ArrayList<House> properties = PropertyLoader.property_loader();

        // display
        System.out.println(" --- Here's your properties ---");
        display_all_properties(properties, 3, the_user);
    }

    private static void newProperty(User the_user){
        Scanner scanner = new Scanner(System.in);
        // load properties
        ArrayList<House> properties = PropertyLoader.property_loader();

        // ========= input =========
        System.out.println(" --- Enter your property detail --- ");
        // house type
        String type = null;
        while (!"Apartment".equals(type) && !"Villa".equals(type) && !"Penthouse".equals(type)){
            System.out.print("Enter your property type (Apartment / Villa / Penthouse): ");
            type = scanner.next();
        }

        // area
        System.out.print("Enter area: ");
        double area = -1;
        while (area < 0) {
            try {
                area = scanner.nextDouble();
                if (area <= 0) System.out.print("Enter a positive number: ");
            } catch (Exception ignored) {
                System.out.print("Enter valid number: ");
                scanner.nextLine(); // clear buff
                area = -1;
            }
        }

        // neighborhood
        String hood = null;
        while (!"Niavaran".equals(hood) && !"Piroozi".equals(hood) &&
                !"Shosh".equals(hood) && !"Varamin".equals(hood)){
            System.out.print("Enter neighborhood (Niavaran / Piroozi / Shosh / Varamin): ");
            hood = scanner.next();
        }
        Neighborhood hood_;
        hood_ = ("Niavaran".equals(hood) ? Neighborhood.REGION_1 : (
                "Piroozi".equals(hood) ? Neighborhood.REGION_2 : (
                "Shosh".equals(hood) ? Neighborhood.REGION_3 : Neighborhood.REGION_4)));

        // bedrooms
        System.out.print("Enter bedrooms number: ");
        int bedrooms = -1;
        while (bedrooms < 0) {
            try {
                bedrooms = scanner.nextInt();
                if (bedrooms < 0) System.out.print("Enter a non negative integer number: ");
            } catch (Exception ignored) {
                System.out.print("Enter valid number: ");
                scanner.nextLine(); // buff
                bedrooms = -1;
            }
        }

        // bathrooms
        System.out.print("Enter bathrooms number: ");
        int bathrooms = -1;
        while (bathrooms < 0) {
            try {
                bathrooms = scanner.nextInt();
                if (bathrooms < 0) System.out.print("Enter a non negative integer number: ");
            } catch (Exception ignored) {
                System.out.print("Enter valid number: ");
                scanner.nextLine(); // buff
                bathrooms = -1;
            }
        }

        // state
        String state = null;
        while (!"sale".equals(state) && !"rent".equals(state) && !"both".equals(state)){
            System.out.print("Enter preferred status (sale / rent / both): ");
            state = scanner.next();
        }
        PropertyStatus state_;
        state_ = ("sale".equals(state) ? PropertyStatus.FOR_SALE : (
                "rent".equals(state) ? PropertyStatus.FOR_RENT : PropertyStatus.FOR_BOTH));

        // specific properties
        House new_house;
        if ("Apartment".equals(type)) {

            // floors
            System.out.print("Enter floor number: ");
            Integer floors = null;
            while (floors == null) {
                try {
                    floors = scanner.nextInt();
                } catch (Exception ignored) {
                    System.out.print("Enter valid integer number: ");
                    scanner.nextLine(); // clear buff
                }
            }

            // making object
            new_house = new Apartment(
                    Integer.valueOf(properties.size()+1).toString(),
                    the_user.getUniqueID(),
                    "",
                    area,
                    hood_,
                    bedrooms,
                    bathrooms,
                    state_,
                    floors
            );

        } else if ("Villa".equals(type)){

            // floors
            System.out.print("Enter floor number: ");
            Integer floors = null;
            while (floors == null) {
                try {
                    floors = scanner.nextInt();
                } catch (Exception ignored) {
                    System.out.print("Enter valid integer number: ");
                    scanner.nextLine(); // clear buff
                }
            }

            // yard area
            System.out.print("Enter yard area: ");
            double yard_area = -1;
            while (yard_area < 0) {
                try {
                    yard_area = scanner.nextDouble();
                    if (yard_area <= 0) System.out.print("Enter a positive number: ");
                } catch (Exception ignored) {
                    System.out.print("Enter valid number: ");
                    scanner.nextLine(); // clear buff
                    yard_area = -1;
                }
            }

            // making object
            new_house = new Villa(
                    Integer.valueOf(properties.size()+1).toString(),
                    the_user.getUniqueID(),
                    "",
                    area,
                    hood_,
                    bedrooms,
                    bathrooms,
                    state_,
                    yard_area,
                    floors
            );

        } else {

            // terrace area
            System.out.print("Enter yard area: ");
            double terrace_area = -1;
            while (terrace_area < 0) {
                try {
                    terrace_area = scanner.nextDouble();
                    if (terrace_area <= 0) System.out.print("Enter a positive number: ");
                } catch (Exception ignored) {
                    System.out.print("Enter valid number: ");
                    scanner.nextLine(); // clear buff
                    terrace_area = -1;
                }
            }

            // making object
            new_house = new Penthouse(
                    Integer.valueOf(properties.size()+1).toString(),
                    the_user.getUniqueID(),
                    "",
                    area,
                    hood_,
                    bedrooms,
                    bathrooms,
                    state_,
                    terrace_area
            );

        }
        // =========================

        // write object
        properties.add(new_house);
        try (ObjectOutputStream oos = new ObjectOutputStream(
            new FileOutputStream("./data/Houses.house"))) {
            // intellij starts from ap-exercise folder!
            oos.writeObject(properties);
        } catch (Exception e){
            e.printStackTrace();
        }

        System.out.println("Your property added to system!!");
    }

    private static void saleViewer(User the_user){
        // load properties
        ArrayList<House> properties = PropertyLoader.property_loader();

        // display
        System.out.println(" --- Here's properties which are in-Sale --- ");
        display_all_properties(properties, 1, the_user);

        Scanner scanner = new Scanner(System.in);
        String command = null;

        while (!"exit".equals(command)){
            // read command
            System.out.print("(for-SALE mode) > ");
            command = scanner.nextLine();

            switch (command.split(" ")[0]){
                case "select":
                    selecting_in_main_menu(command.split(" ")[1], properties);
                case "exit":
                    break;
            }

        }
    }

    private static void rentViewer(User the_user){
        // load properties
        ArrayList<House> properties = PropertyLoader.property_loader();

        // display
        System.out.println(" --- Here's properties which are in-Rent --- ");
        display_all_properties(properties, 2, the_user);

        Scanner scanner = new Scanner(System.in);
        String command = null;

        while (!"exit".equals(command)){
            // read command
            System.out.print("(for-RENT mode) > ");
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

    private static void display_all_properties(ArrayList<House> property_list, int filter, User viewer){
        int c=1;
        for (House the_house: property_list){

            // set condition due to filter code
            boolean condition = (
                (filter == 1 &&
                        (the_house.getStatus().getState() == 1 || the_house.getStatus().getState() == 3) &&
                        (!viewer.getUniqueID().equals(the_house.getOwnerID())) &&
                        (!viewer.getUniqueID().equals(the_house.getTenantID()))) ||
                (filter == 2 &&
                        (the_house.getStatus().getState() == 2 || the_house.getStatus().getState() == 3) &&
                        (!viewer.getUniqueID().equals(the_house.getOwnerID())) &&
                        (!viewer.getUniqueID().equals(the_house.getTenantID()))) ||
                (filter == 3 &&
                        (viewer.getUniqueID().equals(the_house.getOwnerID())))
            );

            if (condition){
                System.out.println("#" + c + " ID: " + the_house.getUniqueID());
                // --- one line detail + pricing ---
                if (the_house instanceof Apartment){

                    // summary
                    System.out.println("Apartment | " + the_house.getArea() + " square-meters | Floor: " + ((Apartment) the_house).getFloors());

                    // sale price (if available)
                    if ((the_house.getStatus().getState() == 1 || the_house.getStatus().getState() == 3) && (filter == 1)){
                        System.out.println("Sale Price: " + the_house.getSalePrice());
                    }

                    // rent price (if available)
                    else if ((the_house.getStatus().getState() == 2 || the_house.getStatus().getState() == 3) && (filter == 2)){
                        System.out.println("Rent Price: " + the_house.getRentPrice());
                    }

                } else if (the_house instanceof Villa) {

                    // summary
                    System.out.println("Villa | " + the_house.getArea() + " square-meters | yard: " + ((Villa) the_house).getYardArea() + " square-meters");

                    // sale price (if available)
                    if ((the_house.getStatus().getState() == 1 || the_house.getStatus().getState() == 3) && (filter == 1)) {
                        System.out.println("Sale Price: " + the_house.getSalePrice());
                    }

                    // rent price (if available)
                    else if ((the_house.getStatus().getState() == 2 || the_house.getStatus().getState() == 3) && (filter == 2)) {
                        System.out.println("Rent Price: " + the_house.getRentPrice());
                    }

                } else if (the_house instanceof Penthouse) {

                    // summary
                    System.out.println("Penthouse | " + the_house.getArea() + " square-meters | terrace: " + ((Penthouse) the_house).getTerraceArea() + " square-meters");

                    // sale price (if available)
                    if ((the_house.getStatus().getState() == 1 || the_house.getStatus().getState() == 3) && (filter == 1)) {
                        System.out.println("Sale Price: " + the_house.getSalePrice());
                    }

                    // rent price (if available)
                    else if ((the_house.getStatus().getState() == 2 || the_house.getStatus().getState() == 3) && (filter == 2)) {
                        System.out.println("Rent Price: " + the_house.getRentPrice());
                    }

                }

                if (filter == 3){
                    switch (the_house.getStatus().getState()) {
                        case 1:
                            System.out.println("Your property is in-Sale with price: " +
                                    the_house.getSalePrice());
                        case 2:
                            System.out.println("Your property is in-Rent with price: " +
                                    the_house.getRentPrice());
                        case 3:
                            System.out.println("Your property is in both rent and sale with sale price: " +
                                    the_house.getSalePrice() + " and rent price: " + the_house.getRentPrice());
                    }
                }

                c += 1;
            }
        }
    }
}
