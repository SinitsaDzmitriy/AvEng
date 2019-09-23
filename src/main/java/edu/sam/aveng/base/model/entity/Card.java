package edu.sam.aveng.base.model.entity;

import edu.sam.aveng.base.model.enumeration.Lang;
import edu.sam.aveng.base.model.enumeration.StatementType;
import edu.sam.aveng.base.contract.model.Identifiable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "cards")
public class Card implements Identifiable, Serializable {
    @Id
    @GeneratedValue
    @Column(nullable = false)
    private Long id;

    @Column(nullable = false)
    private Lang lang;

    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    private StatementType type;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH},
            fetch = FetchType.LAZY)
    @JoinColumn(name = "pronunciation_id",
                    foreignKey = @ForeignKey(name = "PRON_ID_FK"))
    private Pronunciation pron;

    @Column(nullable = false)
    private String definition;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Set<Sample> samples = new HashSet<>();

    @ManyToOne(cascade = {CascadeType.ALL}, fetch = FetchType.LAZY)
    @JoinColumn(name = "image_id",
            foreignKey = @ForeignKey(name = "IMAGE_ID_FK"))
    private Image image;

    @OneToMany(mappedBy = "sourceCard",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    private List<CardMapping> cardMappings = new ArrayList<>();

    public Card() {
    }

    public Card(StatementType type, String content, Pronunciation pron, String definition) {
        this.type = type;
        this.content = content;
        this.pron = pron;
        this.definition = definition;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setType(StatementType type) {
        this.type = type;
    }

    public void setPron(Pronunciation pron) {
        this.pron = pron;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public void setDefinition(String definition) {
        this.definition = definition;
    }

    public void setSamples(Set<Sample> samples) {
        this.samples = samples;
    }

    public Set<Sample> getSamples() {
        return this.samples;
    }

    public Long getId() {
        return id;
    }

    public Pronunciation getPron() {
        return pron;
    }


    public void addMapping(Card destCard, double frequency) {
        CardMapping mapping = new CardMapping(this, destCard, frequency);
        cardMappings.add(mapping);
    }

    public void addSamples(Collection<Sample> additionalSamples) {
        samples.addAll(additionalSamples);
    }

    public void removeMapping(Card destCard) {
        CardMapping mapping = new CardMapping(this, destCard);
        cardMappings.remove(mapping);
    }

    public StatementType getType() {
        return type;
    }

    public String getContent() {
        return content;
    }

    public String getDefinition() {
        return definition;
    }

    public Lang getLang() {
        return lang;
    }

    public void setLang(Lang lang) {
        this.lang = lang;
    }

    public List<Card> getRelatedCards() {
        List<Card> relatedCards = new ArrayList<>();
        for (CardMapping mapping: cardMappings) {
            relatedCards.add(mapping.getDestCard());
        }
        return relatedCards;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Card card = (Card) o;
        return Objects.equals(type, card.type)
                && Objects.equals(content, card.content)
                && Objects.equals(definition, card.definition);
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, content, definition);
    }
}