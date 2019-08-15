package edu.sam.aveng.base.controller.rest;

import edu.sam.aveng.base.contract.controller.AbstractCrudRestController;
import edu.sam.aveng.base.model.domain.Card;
import edu.sam.aveng.base.model.transfer.dto.CardDto;
import edu.sam.aveng.base.model.transfer.dto.ShortCardDto;
import edu.sam.aveng.base.service.card.ICardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/cards")
public class CardRestController extends AbstractCrudRestController<Card, CardDto, ShortCardDto, ICardService> {

    @Override
    @Autowired
    public void service(ICardService service) {
        this.service = service;
    }

    @Override
    protected Class<Card> entityType() {
        return Card.class;
    }
}
