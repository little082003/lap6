package com.example.lap32;

public abstract class BaseUser {
    // Encapsulation: Attributes are private
    private String username;
    private String email;
    private String password;

    // Constructor to initialize the object
    public BaseUser(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password; // In a real app, you should hash the password
    }

    // --- Public Getters and Setters for controlled access ---
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    // A safer method than a password getter
    public boolean verifyPassword(String password) {
        return this.password.equals(password);
    }

    // Abstraction: Abstract method to be implemented by subclasses
    public abstract void displayProfile();
}
