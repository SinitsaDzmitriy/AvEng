package edu.sam.spittr.dto;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class UserDTO {
    private String email;
    private String password;
    private Date lastLoggingDate;
    private boolean enabled;

    private Set<SimpleGrantedAuthority> authorities = new HashSet<>();

    public UserDTO() {
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) { this.password = password; }

    public String getEmail() {
        return email;
    }

    public String getPassword() { return password; }

    public boolean isEnabled() {
        return enabled;
    }
}
