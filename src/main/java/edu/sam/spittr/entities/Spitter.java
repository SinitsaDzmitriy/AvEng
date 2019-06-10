package edu.sam.spittr.entities;

import javax.xml.ws.Service;

public class Spitter {
    private Long id;
    private String firstName;
    private String lastName;
    private String username;
    private String password;

    public Spitter() {}

    // ToDo: hardcoded constructor with ID
    public Spitter(Long id, String firstName, String lastName, String username, String password) {
        this(firstName, lastName, username, password);
        this.id = id;
    }

    // ToDo: hardcoded constructor without ID
    public Spitter(String firstName, String lastName, String username, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
    }

    // public void setId(Long id) { this.id = id; }

    public void setFirstName(String firstName) { this.firstName = firstName; }
    public void setLastName(String lastName) { this.lastName = lastName; }
    public void setUsername(String username) { this.username = username; }
    public void setPassword(String password) { this.password = password; }

    public String getUsername() { return username; }
    public String getFirstName() { return firstName; }
    public String getLastName() { return lastName; }
}
