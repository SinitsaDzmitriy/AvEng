package edu.sam.aveng.base.controller;

import edu.sam.aveng.base.contract.v1.controller.AbstractCrudRestController;
import edu.sam.aveng.base.model.entity.User;
import edu.sam.aveng.base.model.entity.UserCard;
import edu.sam.aveng.base.model.enumeration.Lang;
import edu.sam.aveng.base.model.enumeration.StatementType;
import edu.sam.aveng.base.model.enumeration.Status;
import edu.sam.aveng.base.model.dto.UserCardDto;
import edu.sam.aveng.base.model.dto.UserCardShortDto;
import edu.sam.aveng.base.service.usercard.IUserCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

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

    @GetMapping("/search")
    public List<UserCardShortDto> search(@RequestParam(required = false) Lang lang,
                                         @RequestParam(required = false) String query,
                                         @RequestParam(required = false) List<Status> statusList,
                                         @RequestParam(required = false) List<StatementType> typeList) {
        User currentUser = (User) SecurityContextHolder.getContext()
                    .getAuthentication().getPrincipal();
        return service.search(currentUser, lang, query, statusList, typeList);
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
