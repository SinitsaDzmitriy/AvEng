package edu.sam.aveng.domain;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.util.Date;
import edu.sam.aveng.domain.enumeration.Status;

@Entity
public class UserToCard {

    @Id
    @GeneratedValue
    private Long id;

    // Association:

    @ManyToOne
    @JoinColumn(name = "user_id", foreignKey =
        @ForeignKey(name = "USER_ID_FK"))
    private User owner;

    @ManyToOne(cascade = {CascadeType.PERSIST}, fetch = FetchType.LAZY)
    @JoinColumn(name = "card_id", foreignKey =
        @ForeignKey(name = "CARD_ID_FK"))
    private Card card;

    // Payload:

    private boolean favorite;
    private Status status;
    private int counter;
    private Date lastSeen;
}
