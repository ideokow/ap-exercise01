package service;

import java.io.*;
import java.util.ArrayList;

import models.Contract;

public class ContractLoader {

    private ContractLoader() {}

    protected static ArrayList<Contract> contract_loader(){

        ArrayList<Contract> extracted_contracts = null;

        try (ObjectInputStream ois = new ObjectInputStream(
                new FileInputStream("./data/Contracts.contract")
        )) {
            extracted_contracts = (ArrayList<Contract>) ois.readObject();
        } catch (EOFException ignore) {
            extracted_contracts = new ArrayList<>();
        } catch (IOException e){
            System.out.print("Error during reading data: ");
            e.printStackTrace();
        } catch (Exception exception){
            System.out.print("Unexpected Error: ");
            exception.printStackTrace();
        }

        return extracted_contracts;
    }

    protected static void contract_write(ArrayList<Contract> contracts){
        try (ObjectOutputStream oos = new ObjectOutputStream(
                new FileOutputStream("./data/Contracts.contract"))) {
            // intellij starts from ap-exercise folder!
            oos.writeObject(contracts);
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    protected static void add_contract(Contract new_contract){
        ArrayList<Contract> contracts = contract_loader();
        contracts.add(new_contract);
        contract_write(contracts);
    }

    protected static int contract_numbers(){
        return contract_loader().size();
    }
}
