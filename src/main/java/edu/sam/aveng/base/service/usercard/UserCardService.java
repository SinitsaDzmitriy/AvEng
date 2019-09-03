package edu.sam.aveng.base.service.usercard;

import edu.sam.aveng.base.contract.converter.ICollectionConverter;
import edu.sam.aveng.base.contract.dao.IGenericDao;
import edu.sam.aveng.base.contract.service.StandardGenericCrudService;
import edu.sam.aveng.base.model.domain.Card;
import edu.sam.aveng.base.model.domain.User;
import edu.sam.aveng.base.model.domain.UserCard;
import edu.sam.aveng.base.model.domain.enumeration.Status;
import edu.sam.aveng.base.model.transfer.dto.UserCardDto;
import edu.sam.aveng.base.service.card.ICardService;
import edu.sam.aveng.base.service.user.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.transaction.Transactional;

@Service
@EnableTransactionManagement
@Transactional
public class UserCardService
        extends StandardGenericCrudService<UserCard, UserCardDto>
        implements IUserCardService {

    private IGenericDao<User> userDao;
    private IGenericDao<Card> cardDao;

    @Override
    @Autowired
    @Qualifier("userCardConverter")
    public void converter(ICollectionConverter<UserCard, UserCardDto> converter) {
        setConverter(converter);
    }

    @Autowired
    @Qualifier("genericHiberDao")
    public void setUserDao(IGenericDao<User> userDao) {
        userDao.setClazz(User.class);
        this.userDao = userDao;
    }

    @Autowired
    @Qualifier("genericHiberDao")
    public void setCardDao(IGenericDao<Card> cardDao) {
        cardDao.setClazz(Card.class);
        this.cardDao = cardDao;
    }

    public void create(long userId, long cardId, UserCardDto draft) {

        User currentUser = (User) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal();

        // Such check is unacceptable for real REST api

        if(currentUser.getId() == userId) {

            User owner = userDao.findOne(userId);
            Card baseCard = cardDao.findOne(cardId);

            if(owner != null && baseCard != null) {

                UserCard userCard = new UserCard();

                userCard.setOwner(owner);
                userCard.setCard(baseCard);
                userCard.setStatus(draft.getStatus());
                userCard.setUserSample(draft.getUserSample());

                dao.create(userCard);

            } else {
                // ToDo: handle this exception!
            }

        } else {
            // ToDo: handle this exception!
        }

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
