package edu.sam.aveng.base.model.entity;

import edu.sam.aveng.base.contract.v2.model.Identifiable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

@Entity
public class VerificationToken implements Serializable, Identifiable {

    @Id
    @GeneratedValue
    private Long id;

    private String token;

    @OneToOne(targetEntity = User.class, fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    @JoinColumn(nullable = false, name = "user_id")
    private User user;

    private Date expiryDate;

    public VerificationToken() {
    }

    public VerificationToken(User user) {

        this.user = user;
        this.expiryDate = new Date(System.currentTimeMillis() + 24 * 60 * 60 * 1000);

        generateToken();

    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public String getToken() {
        return token;
    }

    public User getUser() {
        return user;
    }

    public Date getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(Date expiryDate) {
        this.expiryDate = expiryDate;
    }

    public void generateToken() {

        if(this.user != null && this.expiryDate != null) {
            this.token = Integer.toString(Objects.hash(this.user.getEmail(), this.expiryDate));
        } else {
            throw new IllegalStateException("null-value user or expire date");
        }

    }

}
