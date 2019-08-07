package edu.sam.aveng.controller.rest;

import edu.sam.aveng.controller.simple.CardController;
import edu.sam.aveng.dto.CardDto;
import edu.sam.aveng.service.ICardService;
import edu.sam.aveng.util.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/cards")
public class CardRestController {

    @Autowired
    private ICardService cardService;

    private static final Logger LOGGER = LoggerFactory.getLogger(CardController.class);

    @PostMapping("/create")
    public void create(@Valid @RequestBody CardDto cardDto, Errors errors) {
        cardService.create(cardDto);
    }

    @GetMapping
    public String create(Model model) {
        model.addAttribute(cardService.findAll());
        return "cardList";
    }

    @RequestMapping(value = "/{cardId}", method = RequestMethod.GET)
    public String spittle(Model model, @PathVariable long cardId) {
        LOGGER.info("Displaying card with id={}.", cardId);
        model.addAttribute(Constants.Model.CARD_DTO_KEY, cardService.findOne(cardId));
        return "card";
    }

    @RequestMapping(value = "/update/{cardId}", method = RequestMethod.GET)
    public String displayCardUpdateForm(@PathVariable long cardId, Model model) {
        LOGGER.info("Displaying update form for Card with id={}.", cardId);
        LOGGER.debug("Initial state of params: model={}", model);

        model.addAttribute(Constants.Model.CARD_DTO_KEY, cardService.findOne(cardId));

        LOGGER.debug("Final state of params: model={}", model);
        LOGGER.debug("View name to render: viewName=\"{}\"", Constants.View.CARD_UPDATE_FORM);
        return Constants.View.CARD_UPDATE_FORM;
    }

    @RequestMapping(value = "/update/{cardId}", method = RequestMethod.POST)
    public String update(@PathVariable long cardId, CardDto cardDto) {
        LOGGER.info("Updating Card with id={}.", cardId);
        LOGGER.debug("Updated Card: updatedCard={}",  cardDto);
        cardService.update(cardId, cardDto);
        LOGGER.debug("Redirection to \"{}\"", "/card/list");
        return "redirect:/card/list";
    }

    @RequestMapping(value = "/delete/{cardId}", method = RequestMethod.GET)
    public String delete(@PathVariable long cardId) {
        LOGGER.info("Deletion card with id={}.", cardId);
        cardService.delete(cardId);
        LOGGER.debug("Redirection to \"{}\"", "/card/list");
        return "redirect:/card/list";
    }
}
