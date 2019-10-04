package edu.sam.aveng.base.model.entity;

import edu.sam.aveng.base.contract.v2.model.Identifiable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
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

public class User implements UserDetails, Identifiable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false, length = 64)
    private String password;

    @Column(nullable = false)
    private Boolean enabled;

    @ManyToMany(cascade = {CascadeType.PERSIST}, fetch = FetchType.EAGER)
    @JoinTable(name = "user_authority",
            joinColumns = {@JoinColumn(name = "user_id")},
            inverseJoinColumns = {@JoinColumn(name = "authority_id")})
    private Set<SimpleGrantedAuthority> authorities = new HashSet<>();

    @Column(name = "last_logging_date")
    private Date lastLoggingDate;

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
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

    public void addAuthority(SimpleGrantedAuthority authority) {
        authorities.add(authority);
    }

    public void removeAuthority(SimpleGrantedAuthority authority) {
        authorities.remove(authority);
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setLastLoggingDate(Date lastLoggingDate) {

        this.lastLoggingDate = new Date(lastLoggingDate.getTime());
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public String getPassword() {
        return password;
    }

    public Date getLastLoggingDate() {
        return new Date(lastLoggingDate.getTime());
    }

    public Boolean getEnabled() {
        return enabled;
    }
}

    /*

        @OneToMany(mappedBy = "owner",
            cascade = {CascadeType.PERSIST},
            orphanRemoval = true)
        private List<UserCard> cardMappings = new ArrayList<>();

    */