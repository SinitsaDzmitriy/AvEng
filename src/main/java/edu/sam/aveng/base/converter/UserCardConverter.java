package edu.sam.aveng.base.converter;

import edu.sam.aveng.base.contract.v1.converter.IShortConverter;
import edu.sam.aveng.base.model.entity.UserCard;
import edu.sam.aveng.base.model.dto.UserCardDto;
import edu.sam.aveng.base.model.dto.UserCardShortDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.stream.Stream;

@Component
public class UserCardConverter implements IShortConverter<UserCard, UserCardDto, UserCardShortDto> {

    private CardConverter cardConverter;

    @Autowired
    public void setCardConverter(CardConverter cardConverter) {
        this.cardConverter = cardConverter;
    }

    @Override
    public UserCardDto convertToDto(UserCard userCard) {
        UserCardDto userCardDto = null;

        if (userCard != null) {
            userCardDto = new UserCardDto();

            userCardDto.setId(userCard.getId());
            userCardDto.setStatus(userCard.getStatus());
            userCardDto.setUserSample(userCard.getUserSample());
            userCardDto.setCard(cardConverter.convertToDto(userCard.getCard()));
        }

        return userCardDto;
    }

    @Override
    public Stream<UserCardDto> convertToDto(Collection<UserCard> userCards) {
        return userCards.stream()
                .flatMap(userCard -> Stream.of(convertToDto(userCard)));
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

    @Override
    public UserCard convertToEntity(UserCardDto dto) {
        throw new IllegalStateException();
    }

    @Override
    public Stream<UserCard> convertToEntity(Collection<UserCardDto> dtos) {
        throw new IllegalStateException();
    }
}
