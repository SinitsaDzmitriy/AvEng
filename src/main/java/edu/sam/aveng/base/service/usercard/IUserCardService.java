package edu.sam.aveng.base.service.usercard;

import edu.sam.aveng.base.contract.v1.converter.IShortConverter;
import edu.sam.aveng.base.contract.v1.service.ICrudService;
import edu.sam.aveng.base.model.entity.UserCard;
import edu.sam.aveng.base.model.enumeration.Status;
import edu.sam.aveng.base.model.dto.UserCardDto;
import edu.sam.aveng.base.model.dto.UserCardShortDto;

public interface IUserCardService extends ICrudService<UserCard,
        UserCardDto,
        UserCardShortDto,
        IShortConverter<UserCard, UserCardDto, UserCardShortDto>> {

    void create(long userId, long cardId, UserCardDto userCardDto);

    void swapFavorite(long id);

    void changeStatus(long id, Status status);

    void changeUserSample(long id, String sample);

//    void updateUserSample(String userSample);

}
