package service;

import models.*;

import java.util.ArrayList;

public class Dealer {
    private Dealer(){}

    protected static int buy_house(House the_house, String buyer){
        ArrayList<House> properties = PropertyLoader.property_loader();
        ArrayList<User> users = UserLoader.user_loader();

        // check balance
        for (User user : users) {
            if(user.getUniqueID().equals(buyer)){
                if (user.getBalance() - the_house.getSalePrice() < 0){
                    return 0;
                }
            }
        }

        // make contract
        Contract contract = new Contract(
                Integer.valueOf(ContractLoader.contract_numbers()+1).toString(),
                the_house.getUniqueID(),
                buyer,
                the_house.getOwnerID(),
                ContractType.SALE,
                the_house.getSalePrice()
        );

        // update info
        User seller=null; boolean flag = true;

        for (User user: users){
            if (user.getUniqueID().equals(buyer)){
                user.decreaseBalance(the_house.getSalePrice());
                user.add_to_owned_properties(the_house.getUniqueID());
                user.add_to_contracts(contract.getUniqueID());
                flag = false;
            }
            if (user.getUniqueID().equals(the_house.getOwnerID())){
                seller = user;
                seller.increaseBalance(the_house.getSalePrice());
                user.remove_from_owned_properties(the_house.getUniqueID());
                user.add_to_contracts(contract.getUniqueID());
            }
        }

        if (flag) return -1;
        if (seller == null) return -1;

        for (House house: properties){
            if (house.getUniqueID().equals(the_house.getUniqueID())){
                house.setOwnerID(buyer);
                house.setStatus(PropertyStatus.NO_BOTH);
            }
        }

        PropertyLoader.property_write(properties);
        UserLoader.user_write(users);

        // save contract
        try {
            ContractLoader.add_contract(contract);
        } catch (Exception e){
            e.printStackTrace();
            return -1;
        }
        return 1;
    }

    protected static int rent_house(House the_house, String buyer){
        ArrayList<House> properties = PropertyLoader.property_loader();
        ArrayList<User> users = UserLoader.user_loader();

        // check balance
        for (User user : users) {
            if(user.getUniqueID().equals(buyer)){
                if (user.getBalance() - the_house.getRentPrice() < 0){
                    return 0;
                }
            }
        }

        // make contract
        Contract contract = new Contract(
                Integer.valueOf(ContractLoader.contract_numbers()+1).toString(),
                the_house.getUniqueID(),
                buyer,
                the_house.getOwnerID(),
                ContractType.RENT,
                the_house.getRentPrice()
        );

        // update info
        User seller=null; boolean flag = true;

        for (User user: users){
            if (user.getUniqueID().equals(buyer)){
                user.decreaseBalance(the_house.getRentPrice());
                user.add_to_rented_properties(the_house.getUniqueID());
                user.add_to_contracts(contract.getUniqueID());
                flag = false;
            }
            if (user.getUniqueID().equals(the_house.getOwnerID())){
                seller = user;
                seller.increaseBalance(the_house.getSalePrice());
                user.add_to_contracts(contract.getUniqueID());
            }
        }

        if (flag) return -1;
        if (seller == null) return -1;

        for (House house: properties){
            if (house.getUniqueID().equals(the_house.getUniqueID())){
                house.setTenantID(buyer);
                house.setStatus(PropertyStatus.NO_BOTH);
            }
        }

        PropertyLoader.property_write(properties);
        UserLoader.user_write(users);

        // save contract
        try {
            ContractLoader.add_contract(contract);
        } catch (Exception e){
            e.printStackTrace();
            return -1;
        }
        return 1;
    }

}
