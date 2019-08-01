package edu.sam.aveng.controller;

import edu.sam.aveng.dto.CardDto;
import edu.sam.aveng.dto.SampleDto;
import edu.sam.aveng.service.ICardService;
import edu.sam.aveng.util.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

@Controller
@RequestMapping(value = "/card")
public class CardController {

    @Autowired
    private ICardService cardService;

    private final static Logger LOGGER = LoggerFactory.getLogger(CardController.class);

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String displayCardCreationForm(Model model) {

        LOGGER.info("Card creation form displaying.");
        LOGGER.debug("Initial state of params: model={}", model);

        CardDto cardDto = new CardDto();
        cardDto.getSamples().add(new SampleDto(""));

        model.addAttribute(Constants.Model.CARD_DTO_KEY, cardDto);

        LOGGER.debug("Final state of params: model={}", model);
        LOGGER.debug("View name to render: viewName=\"{}\"", Constants.View.CARD_CREATION_FORM);

        return Constants.View.CARD_CREATION_FORM;
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String create(@Valid @ModelAttribute("cardDto") CardDto cardDto, Errors errors) {
        cardService.create(cardDto);
        return "redirect: list";
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
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
