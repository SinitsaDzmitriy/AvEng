package edu.sam.spittr.domain;

import java.time.LocalTime;

// Domain Spittle class to work with hibernate

public class Spittle {
    private long id;
    private String message;
    // Local DateTime
    private LocalTime time;
    private Double latitude;
    private Double longitude;

    public Spittle() { }

    public long getId() {
        return id;
    }

    public void setId(long id) { this.id = id; }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) { this.message = message; }

    public LocalTime getTime() { return time; }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }
}
