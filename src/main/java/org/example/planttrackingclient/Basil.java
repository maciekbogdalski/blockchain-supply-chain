package org.example.planttrackingclient;

public class Basil {
    private String qr;         // QR code as a unique identifier
    private String extraInfo;  // Additional information about the plant
    private Owner owner;       // Owner of the plant

    // Public no-argument constructor
    public Basil() {
    }

    // Constructor with arguments
    public Basil(String qr, String extraInfo, Owner owner) {
        this.qr = qr;
        this.extraInfo = extraInfo;
        this.owner = owner;
    }

    // Getter for QR code
    public String getQr() {
        return qr;
    }

    // Setter for QR code
    public void setQr(String qr) {
        this.qr = qr;
    }

    // Getter for extra information
    public String getExtraInfo() {
        return extraInfo;
    }

    // Setter for extra information
    public void setExtraInfo(String extraInfo) {
        this.extraInfo = extraInfo;
    }

    // Getter for owner
    public Owner getOwner() {
        return owner;
    }

    // Setter for owner
    public void setOwner(Owner owner) {
        this.owner = owner;
    }

    // Optional: Override toString() method for easy printing
    @Override
    public String toString() {
        return "Basil{" +
               "qr='" + qr + '\'' +
               ", extraInfo='" + extraInfo + '\'' +
               ", owner=" + owner +
               '}';
    }
}
