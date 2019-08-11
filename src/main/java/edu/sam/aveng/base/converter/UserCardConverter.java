package edu.sam.aveng.base.converter;

import edu.sam.aveng.base.contract.converter.ICollectionConverter;
import edu.sam.aveng.base.model.domain.Card;
import edu.sam.aveng.base.model.domain.UserCard;
import edu.sam.aveng.base.model.transfer.dto.CardDto;
import edu.sam.aveng.base.model.transfer.dto.UserCardDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.stream.Stream;

@Component
public class UserCardConverter implements ICollectionConverter<UserCard, UserCardDto> {

    private ICollectionConverter<Card, CardDto> cardConverter;

    @Autowired
    @Qualifier("cardConverter")
    public void setCardConverter(ICollectionConverter<Card, CardDto> cardConverter) {
        this.cardConverter = cardConverter;
    }

    @Override
    public Stream<UserCardDto> convertToDto(Collection<UserCard> userCards) {
        return userCards.stream()
                .map(this::convertToDto);
    }

    @Override
    public Stream<UserCard> convertToEntity(Collection<UserCardDto> dtos) {
        return null;
    }

    @Override
    public UserCardDto convertToDto(UserCard userCard) {

        UserCardDto userCardDto = new UserCardDto();

        userCardDto.setId(userCard.getId());

        userCardDto.setFavorite(userCard.isFavorite());
        userCardDto.setStatus(userCard.getStatus());
        userCardDto.setUserSample(userCard.getUserSample());

        userCardDto.setCard(cardConverter.convertToDto(userCard.getCard()));

        return userCardDto;
    }

    @Override
    public UserCard convertToEntity(UserCardDto dto) {
        return null;
    }
}
