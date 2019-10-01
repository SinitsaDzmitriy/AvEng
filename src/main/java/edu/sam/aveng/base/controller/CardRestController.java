package edu.sam.aveng.base.controller;

import edu.sam.aveng.base.model.transfer.dto.CardDto;
import edu.sam.aveng.base.service.card.ICardService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/cards")
public class CardRestController {

    private ICardService cardService;

    @Autowired
    public void setCardService(ICardService cardService) {
        this.cardService = cardService;
    }

    @PostMapping("/create")
    public void create(@RequestBody @Valid CardDto cardDto) {
        cardService.create(cardDto);
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable long id) {
        cardService.delete(id);
    }

}
