package edu.sam.aveng.base.service.usercard;

import edu.sam.aveng.base.contract.converter.ICollectionConverter;
import edu.sam.aveng.base.contract.service.StandardGenericCrudService;
import edu.sam.aveng.base.model.domain.UserCard;
import edu.sam.aveng.base.model.domain.enumeration.Status;
import edu.sam.aveng.base.model.transfer.dto.UserCardDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.transaction.Transactional;

@Service
@EnableTransactionManagement
@Transactional
public class UserCardService
        extends StandardGenericCrudService<UserCard, UserCardDto>
        implements IUserCardService {

    @Override
    @Autowired
    @Qualifier("userCardConverter")
    public void converter(ICollectionConverter<UserCard, UserCardDto> converter) {
        setConverter(converter);
    }

    @Override
    public void swapFavorite(long id) {
        // ToDo add check if boolean (or pass type to check inside)
        boolean favorite = (boolean) dao.getPropertyById("favorite", id);
        System.out.println(favorite);
        dao.updatePropertyById("favorite", !favorite, id);
    }

    @Override
    public void changeStatus(long id, Status status) {
        dao.updatePropertyById("status", status, id);
    }

    @Override
    public void changeUserSample(long id, String sample) {
        dao.updatePropertyById("userSample", sample, id);
    }
}
