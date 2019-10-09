package edu.sam.aveng.base.service.usercard;

import edu.sam.aveng.base.contract.v1.converter.IShortConverter;
import edu.sam.aveng.base.contract.v1.dao.IGenericDao;
import edu.sam.aveng.base.contract.v1.service.SmartGenericCrudService;
import edu.sam.aveng.base.model.entity.Card;
import edu.sam.aveng.base.model.entity.User;
import edu.sam.aveng.base.model.entity.UserCard;
import edu.sam.aveng.base.model.enumeration.Status;
import edu.sam.aveng.base.model.dto.UserCardDto;
import edu.sam.aveng.base.model.dto.UserCardShortDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class UserCardService
        extends SmartGenericCrudService<UserCard, UserCardDto, UserCardShortDto>
        implements IUserCardService {

    private IGenericDao<Card> cardDao;

    @Override
    @Autowired
    @Qualifier("userCardConverter")
    public void converter(IShortConverter<UserCard, UserCardDto, UserCardShortDto> converter) {
        setConverter(converter);
    }

    @Autowired
    @Qualifier("genericHiberDao")
    public void setCardDao(IGenericDao<Card> cardDao) {
        cardDao.setClazz(Card.class);
        this.cardDao = cardDao;
    }

    @Override
    public void create(long cardId, UserCardDto draft) {
        Card card = cardDao.find(cardId);
        if(card == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

        UserCard userCard = new UserCard();

        userCard.setOwner(getCurrentUser());
        userCard.setCard(card);

        if(draft != null) {
            userCard.setStatus(draft.getStatus());
            userCard.setUserSample(draft.getUserSample());
        } else {
            userCard.setStatus(Status.NEW);
        }

        dao.create(userCard);
    }

    @Override
    public UserCardDto findOne(long userCardId) {
        UserCard userCard = getUserCardSecurely(userCardId);
        return converter.convertToDto(userCard);
    }

    @Override
    public List<UserCardShortDto> findAll() {
        return converter
                .convertToShortDto(dao.findAllEagerlyByProperty("owner.id", getCurrentUser().getId()))
                .collect(Collectors.toList());
    }

    @Override
    public void delete(long userCardId) {
        UserCard userCardToDelete = getUserCardSecurely(userCardId);
        dao.delete(userCardToDelete);
    }

    @Override
    public void swapFavorite(long userCardId) {
        verifyUserCardOwner(userCardId);
        boolean favorite = (boolean) dao.getPropertyById("favorite", userCardId);
        dao.updatePropertyById("favorite", !favorite, userCardId);
    }

    @Override
    public void changeStatus(long userCardId, Status status) {
        verifyUserCardOwner(userCardId);
        dao.updatePropertyById("status", status, userCardId);
    }

    @Override
    public void changeUserSample(long userCardId, String sample) {
        verifyUserCardOwner(userCardId);
        dao.updatePropertyById("userSample", sample, userCardId);
    }

    private UserCard getUserCardSecurely(Long userCardId) {
        User currentUser = getCurrentUser();
        UserCard userCard = dao.find(userCardId);
        if(!currentUser.getId().equals(userCard.getOwner().getId())) {
            throw new AccessDeniedException("This User card doesn't belong to you!");
        }
        return userCard;
    }

    private void verifyUserCardOwner(long userCardId) {
        User owner = (User) dao.getPropertyById("owner", userCardId);
        if(!getCurrentUser().getId().equals(owner.getId())) {
            throw new AccessDeniedException("This User card doesn't belong to you!");
        }
    }

    private User getCurrentUser(){
        return (User) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal();
    }
}
