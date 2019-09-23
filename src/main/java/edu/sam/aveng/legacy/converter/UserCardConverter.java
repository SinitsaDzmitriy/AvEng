package edu.sam.aveng.legacy.converter;

import edu.sam.aveng.legacy.contract.converter.IShortConverter;
import edu.sam.aveng.base.model.entity.Card;
import edu.sam.aveng.base.model.entity.UserCard;
import edu.sam.aveng.base.model.transfer.dto.CardDto;
import edu.sam.aveng.base.model.transfer.dto.CardTableItem;
import edu.sam.aveng.base.model.transfer.dto.UserCardDto;
import edu.sam.aveng.base.model.transfer.dto.UserCardShortDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.stream.Stream;

@Component
public class UserCardConverter implements IShortConverter<UserCard, UserCardDto, UserCardShortDto> {

    private IShortConverter<Card, CardDto, CardTableItem> cardConverter;

    @Autowired
    @Qualifier("oldCardConverter")
    public void setCardConverter(IShortConverter<Card, CardDto, CardTableItem> cardConverter) {
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

    @Override
    public Stream<UserCardShortDto> convertToShortDto(Collection<UserCard> userCards) {
        return userCards.stream()
                .map(userCard -> {
                    UserCardShortDto item = new UserCardShortDto();

                    item.setUserCardId(userCard.getId());
                    item.setStatus(userCard.getStatus());
                    item.setLang(userCard.getCard().getLang());
                    item.setContent(userCard.getCard().getContent());
                    item.setType(userCard.getCard().getType());
                    item.setDefinition(userCard.getCard().getDefinition());


                    return item;
                });
    }
}
