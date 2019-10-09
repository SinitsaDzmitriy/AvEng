package edu.sam.aveng.base.controller;

import edu.sam.aveng.base.contract.v1.controller.AbstractCrudRestController;
import edu.sam.aveng.base.model.entity.UserCard;
import edu.sam.aveng.base.model.enumeration.Status;
import edu.sam.aveng.base.model.dto.UserCardDto;
import edu.sam.aveng.base.model.dto.UserCardShortDto;
import edu.sam.aveng.base.service.usercard.IUserCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api/user_cards")
public class UserCardRestController extends AbstractCrudRestController<UserCard, UserCardDto,
        UserCardShortDto, IUserCardService> {

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
    public void create(@RequestParam long cardId, @RequestBody UserCardDto userCardDraft) {
        service.create(cardId, userCardDraft);
    }

    @PatchMapping("/update/{id}/swap-favorite")
    public void swapFavorite(@PathVariable Long id) {
        service.swapFavorite(id);
    }

    @PatchMapping("/update/{id}/change-status")
    public void changeState(@PathVariable Long id, @RequestParam("status") Status newStatus) {
        service.changeStatus(id, newStatus);
    }

    @PatchMapping("/update/{id}/change-user-sample")
    public void changeState(@PathVariable Long id, @RequestBody String updatedSample) {
        service.changeUserSample(id, updatedSample);
    }

    // Disable redundant request mappings
    @Override
    public void create(UserCardDto dto) {
        throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }

    @Override
    public void update(long id, UserCardDto dto) {
        throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }
}
