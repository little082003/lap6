package com.example.lap32;

public class AdminUser extends BaseUser {

    private String adminLevel; // Admin-specific attribute

    public AdminUser(String username, String email, String password, String adminLevel) {
        super(username, email, password);
        this.adminLevel = adminLevel;
    }

    public String getAdminLevel() {
        return adminLevel;
    }

    public void setAdminLevel(String adminLevel) {
        this.adminLevel = adminLevel;
    }

    @Override
    public void displayProfile() {
        System.out.println("--- Admin User Profile ---");
        System.out.println("Username: " + getUsername());
        System.out.println("Email: " + getEmail());
        System.out.println("Admin Level: " + this.adminLevel);
        System.out.println("--------------------------");
    }
}
