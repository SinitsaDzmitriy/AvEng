package edu.sam.aveng.base.service.usercard;

import edu.sam.aveng.base.contract.converter.IShortConverter;
import edu.sam.aveng.base.contract.dao.IGenericDao;
import edu.sam.aveng.base.contract.service.SmartGenericCrudService;
import edu.sam.aveng.base.model.domain.Card;
import edu.sam.aveng.base.model.domain.User;
import edu.sam.aveng.base.model.domain.UserCard;
import edu.sam.aveng.base.model.domain.enumeration.Status;
import edu.sam.aveng.base.model.transfer.dto.UserCardDto;
import edu.sam.aveng.base.model.transfer.UserCardTableItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@EnableTransactionManagement
@Transactional
public class UserCardService
        extends SmartGenericCrudService<UserCard, UserCardDto, UserCardTableItem>
        implements IUserCardService {

    private IGenericDao<User> userDao;
    private IGenericDao<Card> cardDao;

    @Override
    @Autowired
    @Qualifier("userCardConverter")
    public void converter(IShortConverter<UserCard, UserCardDto, UserCardTableItem> converter) {
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

    @Override
    public void create(long userId, long cardId, UserCardDto draft) {

        User currentUser = (User) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal();

        // Such check is unacceptable for real REST api

        if (currentUser.getId() == userId) {

            User owner = userDao.findOne(userId);
            Card baseCard = cardDao.findOne(cardId);

            if (owner != null && baseCard != null) {

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
    public UserCardDto findOne(long id) {

        // ToDo: Rewrite awful cast
        User currentUser = (User) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal();

        UserCard userCard = dao.findOne(id);

        if (!userCard.getOwner().getId().equals(currentUser.getId())) {
            System.out.println("You don't have enough permissions");
            // ToDo: Throw proper type exception
            // throw new Exception();
            return null;
        }
        return converter.convertToDto(userCard);
    }

    @Override
    public List<UserCardTableItem> findAll() {

        // ToDo: Move user check in separate method (duplication)
        // ToDo: Rewrite awful cast
        User currentUser = (User) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal();

        return converter
                .convertToShortDto(dao.findAllEagerlyByProperty("owner.id", currentUser.getId()))
                .collect(Collectors.toList());
    }

    @Override
    public void delete(long id) {

        // ToDo: Rewrite awful cast
        User currentUser = (User) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal();

        UserCard userCardToDelete = dao.findOne(id);

        if (userCardToDelete.getOwner().getId().equals(currentUser.getId())) {
            dao.delete(userCardToDelete);
        } else {
            System.out.println("You don't have enough permissions");
            // ToDo: Throw proper type exception
            // throw new Exception();
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
