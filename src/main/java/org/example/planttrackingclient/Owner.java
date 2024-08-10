package org.example.planttrackingclient;

public class Owner {
    private String org;  // Organization of the owner
    private String user; // User within the organization

    // No-argument constructor for deserialization
    public Owner() {
    }

    // Constructor with arguments
    public Owner(String org, String user) {
        this.org = org;
        this.user = user;
    }

    // Getter for organization
    public String getOrg() {
        return org;
    }

    // Setter for organization
    public void setOrg(String org) {
        this.org = org;
    }

    // Getter for user
    public String getUser() {
        return user;
    }

    // Setter for user
    public void setUser(String user) {
        this.user = user;
    }

    // Optional: Override toString() method for easy printing
    @Override
    public String toString() {
        return "Owner{" +
               "org='" + org + '\'' +
               ", user='" + user + '\'' +
               '}';
    }
}
