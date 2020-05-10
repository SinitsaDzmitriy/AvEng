package edu.sam.aveng.base.service.usercard;

import edu.sam.aveng.base.contract.v1.converter.IShortConverter;
import edu.sam.aveng.base.contract.v1.service.ICrudService;
import edu.sam.aveng.base.model.entity.User;
import edu.sam.aveng.base.model.entity.UserCard;
import edu.sam.aveng.base.model.enumeration.Lang;
import edu.sam.aveng.base.model.enumeration.StatementType;
import edu.sam.aveng.base.model.enumeration.Status;
import edu.sam.aveng.base.model.dto.UserCardDto;
import edu.sam.aveng.base.model.dto.UserCardShortDto;

import java.util.List;

public interface IUserCardService extends ICrudService<UserCard,
        UserCardDto,
        UserCardShortDto,
        IShortConverter<UserCard, UserCardDto, UserCardShortDto>> {

    void create(long cardId, UserCardDto userCardDto);

    List<UserCardShortDto> search(User owner, Lang lang, String query,
                                  List<Status> statusList, List<StatementType> typeList);

    void swapFavorite(long id);

    void changeStatus(long id, Status status);

    void changeUserSample(long id, String sample);
}
