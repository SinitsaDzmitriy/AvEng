package edu.sam.aveng.base.controller.simple;

import edu.sam.aveng.base.model.transfer.dto.CardDto;
import edu.sam.aveng.base.model.transfer.dto.SampleDto;
import edu.sam.aveng.base.service.card.simple.ISimpleCardService;
import edu.sam.aveng.base.util.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

@Controller
@RequestMapping(value = "/card")
public class CardController {

    @Autowired
    private ISimpleCardService cardService;

    private static final Logger LOGGER = LoggerFactory.getLogger(CardController.class);

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String displayCardCreationForm(Model model) {

        LOGGER.info("Card creation form displaying.");
        LOGGER.debug("Initial state of params: model={}", model);

        CardDto cardDto = new CardDto();
        cardDto.getSamples().add(new SampleDto(""));

        model.addAttribute(Constants.Model.CARD_DTO_KEY, cardDto);

        LOGGER.debug("Final state of params: model={}", model);
        LOGGER.debug("View name to render: viewName=\"{}\"", Constants.View.CARD_CREATION_FORM);

        // ToDo: Temp view for test purposes only
        return Constants.View.CARD_CREATION_FORM;
    }


    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String create(@Valid @ModelAttribute("cardDto") CardDto cardDto, Errors errors) {

        if (errors.hasErrors()) {
            return Constants.View.CARD_CREATION_FORM;
        }

        cardService.create(cardDto);

        return "redirect: list";
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String create(Model model) {
        model.addAttribute(cardService.findAll());
        return "cardList";
    }

    @RequestMapping(value = "/read/{cardId}", method = RequestMethod.GET)
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

    @PostMapping("/update/{cardId}")
    public String update(@PathVariable long cardId, CardDto cardDto) {

        // ToDo: Fix logging

        LOGGER.info("Updating Card with id={}.", cardId);
        LOGGER.debug("Updated Card: updatedCard={}", cardDto);
        cardService.update(cardId, cardDto);
        LOGGER.debug("Redirection to \"{}\"", "/card/list");

        return "redirect:/card/read/" + cardId;
    }

    @RequestMapping(value = "/delete/{cardId}", method = RequestMethod.GET)
    public String delete(@PathVariable long cardId) {
        LOGGER.info("Deletion card with id={}.", cardId);
        cardService.delete(cardId);
        LOGGER.debug("Redirection to \"{}\"", "/card/list");
        return "redirect:/card/list";
    }

}
