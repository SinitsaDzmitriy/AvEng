package edu.sam.aveng.base.service.usercard;

import edu.sam.aveng.legacy.contract.converter.IShortConverter;
import edu.sam.aveng.legacy.contract.service.ICrudService;
import edu.sam.aveng.base.model.domain.UserCard;
import edu.sam.aveng.base.model.domain.enumeration.Status;
import edu.sam.aveng.base.model.transfer.dto.UserCardDto;
import edu.sam.aveng.base.model.transfer.UserCardTableItem;

public interface IUserCardService extends ICrudService<UserCard,
        UserCardDto,
        UserCardTableItem,
        IShortConverter<UserCard, UserCardDto, UserCardTableItem>> {

    void create(long userId, long cardId, UserCardDto userCardDto);

    void swapFavorite(long id);

    void changeStatus(long id, Status status);

    void changeUserSample(long id, String sample);

//    void updateUserSample(String userSample);

}
