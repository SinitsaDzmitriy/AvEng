package edu.sam.spittr.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "card_mappings", uniqueConstraints =
    @UniqueConstraint(name = "SOURCE_DEST_UQ", columnNames = {"source_card_id", "dest_card_id"}))
public class CardMapping implements Serializable {
    private Double frequency;

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    @JoinColumn(name = "source_card_id", foreignKey =
        @ForeignKey(name = "SOURCE_CARD_ID_FK"))
    private Card sourceCard;

    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "dest_card_id", foreignKey =
        @ForeignKey(name = "DEST_CARD_ID_FK"))
    private Card destCard;

    public CardMapping() {
    }

    public CardMapping(Card sourceCard, Card destCard) {
        this.sourceCard = sourceCard;
        this.destCard = destCard;
    }

    public CardMapping(Card sourceCard, Card destCard, double frequency) {
        this.sourceCard = sourceCard;
        this.destCard = destCard;
        this.frequency = frequency;
    }

    public void setSourceCard(Card sourceCard) {
        this.sourceCard = sourceCard;
    }

    public Card getDestCard() {
        return destCard;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        CardMapping mapping = (CardMapping) o;
        return Objects.equals(sourceCard, mapping.sourceCard)
                && Objects.equals(destCard, mapping.destCard);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sourceCard, destCard);
    }
}
