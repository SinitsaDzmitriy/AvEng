package edu.sam.spittr.domain;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users",
        uniqueConstraints =
                @UniqueConstraint(columnNames = "email", name = "uq_email"))

public class User implements UserDetails {
    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false, length = 64)
    private String password;

    @Column(nullable = false)
    private Boolean enabled;

    @ManyToMany (cascade = CascadeType.PERSIST)
    @JoinTable(name = "user_authority",
            joinColumns = {@JoinColumn(name = "user_id")},
            inverseJoinColumns = {@JoinColumn(name = "authority_id")})
    private Set<SimpleGrantedAuthority> authorities = new HashSet<>();

    @Column(name = "last_logging_date", nullable = false)
    private Date lastLoggingDate;

    public User() {
    }

    public User(String email, String password) {
        // boolean def value - false :3
        this.email = email;
        this.password = password;

        // Represents the time at which it was created.
        this.lastLoggingDate = new Date();
    }

    public Long getId() {
        return id;
    }

    /*
        Methods required by UserDetails interface.
        start point
    */

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    // ToDo: Implement the methods below
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

    // end point

    public void addAuthority(SimpleGrantedAuthority authority) {
        authorities.add(authority);
    }

    public void removeAuthority(SimpleGrantedAuthority authority) {
        authorities.remove(authority);
    }

}
