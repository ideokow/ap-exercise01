package service;

import service.Interface;

public class Login {
    // private constructor to avoid making instance!
    private Login() {}

    public static void main(String[] args) {
        System.out.println("Here's Login page");
        Interface.interface_loader("someone");
    }
}
