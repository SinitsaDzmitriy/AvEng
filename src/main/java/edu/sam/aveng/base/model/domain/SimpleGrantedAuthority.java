package edu.sam.aveng.base.model.domain;

import org.springframework.security.core.GrantedAuthority;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import java.util.Objects;

@Entity
@Table(name = "authorities",
        uniqueConstraints =
            @UniqueConstraint(columnNames = "role", name = "uq_role"))

public class SimpleGrantedAuthority implements GrantedAuthority {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String role;

    public SimpleGrantedAuthority() {
    }

    public SimpleGrantedAuthority(String role) {
        this.role = role;
    }

    public Long getId() {
        return id;
    }

    @Override
    public String getAuthority() {
        return role;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }

        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        SimpleGrantedAuthority authority = (SimpleGrantedAuthority) obj;
        return Objects.equals(role, authority.role);
    }

    @Override
    public int hashCode() {
        return role.hashCode();
    }

    @Override
    public String toString() {
        return role;
    }
}
