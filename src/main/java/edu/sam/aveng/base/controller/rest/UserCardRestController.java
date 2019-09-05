package edu.sam.aveng.base.controller.rest;

import edu.sam.aveng.base.contract.controller.AbstractCrudRestController;
import edu.sam.aveng.base.model.domain.UserCard;
import edu.sam.aveng.base.model.domain.enumeration.Status;
import edu.sam.aveng.base.model.transfer.dto.UserCardDto;
import edu.sam.aveng.base.model.transfer.dto.UserCardTableItem;
import edu.sam.aveng.base.service.usercard.IUserCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user_cards")
public class UserCardRestController extends AbstractCrudRestController<UserCard,
        UserCardDto,
        UserCardTableItem,
        IUserCardService> {

    @Override
    protected Class<UserCard> entityType() {
        return UserCard.class;
    }

    @Override
    @Autowired
    @Qualifier("userCardService")
    public void service(IUserCardService service) {
        this.service = service;
    }

    @PostMapping("/assign")
    public void create(@RequestParam long userId, @RequestParam long cardId,
                       @RequestBody UserCardDto userCardDraft) {

        service.create(userId, cardId, userCardDraft);
    }

    @PatchMapping("/update/{id}/swap-favorite")
    public void swapFavorite(@PathVariable Long id) {
        service.swapFavorite(id);
    }

    @PatchMapping("/update/{id}/change-status")
    public void changeState(@PathVariable Long id,
                            @RequestParam("status") Status newStatus) {
        service.changeStatus(id, newStatus);
    }

    @PatchMapping("/update/{id}/change-user-sample")
    public void changeState(@PathVariable Long id,
                            @RequestBody String updatedSample) {
        service.changeUserSample(id, updatedSample);
    }

    // Disable redundant request mappings

    @Override
    public void create(UserCardDto dto) {
    }

    @Override
    public void update(long id, UserCardDto dto) {
    }
}
