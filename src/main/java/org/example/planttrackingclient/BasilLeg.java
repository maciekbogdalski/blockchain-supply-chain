package org.example.planttrackingclient;

public class BasilLeg {
    private long timestamp;  // Timestamp for the status update
    private String gpsPosition;  // GPS position

    // Constructor
    public BasilLeg() {
    }

    public BasilLeg(long timestamp, String gpsPosition) {
        this.timestamp = timestamp;
        this.gpsPosition = gpsPosition;
    }

    // Getter for timestamp
    public long getTimestamp() {
        return timestamp;
    }

    // Setter for timestamp
    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    // Getter for GPS position
    public String getGpsPosition() {
        return gpsPosition;
    }

    // Setter for GPS position
    public void setGpsPosition(String gpsPosition) {
        this.gpsPosition = gpsPosition;
    }

    // Optional: Override toString() method for easy printing
    @Override
    public String toString() {
        return "BasilLeg{" +
               "timestamp=" + timestamp +
               ", gpsPosition='" + gpsPosition + '\'' +
               '}';
    }
}
