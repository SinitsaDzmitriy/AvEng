package edu.sam.aveng.base.model.transfer.dto;

import edu.sam.aveng.base.model.domain.enumeration.Status;

public class UserCardDto {
    private Long id;
    private CardDto card;

    // Payload:

    private boolean favorite;
    private Status status;
    private String userSample;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public CardDto getCard() {
        return card;
    }

    public void setCard(CardDto card) {
        this.card = card;
    }

    public boolean isFavorite() {
        return favorite;
    }

    public void setFavorite(boolean favorite) {
        this.favorite = favorite;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getUserSample() {
        return userSample;
    }

    public void setUserSample(String userSample) {
        this.userSample = userSample;
    }
}
