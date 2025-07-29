package com.example.lap32;

public class RegularUser extends BaseUser {

    public RegularUser(String username, String email, String password) {
        // Call the constructor of the parent class (BaseUser)
        super(username, email, password);
    }

    @Override
    public void displayProfile() {
        System.out.println("--- Regular User Profile ---");
        System.out.println("Username: " + getUsername());
        System.out.println("Email: " + getEmail());
        System.out.println("--------------------------");
    }
}
