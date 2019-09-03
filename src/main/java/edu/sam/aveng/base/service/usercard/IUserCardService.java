package edu.sam.aveng.base.service.usercard;

import edu.sam.aveng.base.contract.converter.ICollectionConverter;
import edu.sam.aveng.base.contract.service.ICrudService;
import edu.sam.aveng.base.model.domain.UserCard;
import edu.sam.aveng.base.model.domain.enumeration.Status;
import edu.sam.aveng.base.model.transfer.dto.UserCardDto;

public interface IUserCardService extends ICrudService<UserCard,
        UserCardDto,
        UserCardDto,
        ICollectionConverter<UserCard, UserCardDto>> {

    void create(long userId, long cardId, UserCardDto userCardDto);

    void swapFavorite(long id);

    void changeStatus(long id, Status status);

    void changeUserSample(long id, String sample);

//    void updateUserSample(String userSample);

}
