package edu.sam.aveng.base.controller;

import edu.sam.aveng.base.model.enumeration.Lang;
import edu.sam.aveng.base.model.transfer.dto.CardDto;
import edu.sam.aveng.base.service.card.ICardService;
import edu.sam.aveng.base.util.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "/cards")
public class CardController {

    private ICardService cardService;

    private static final Logger LOGGER = LoggerFactory.getLogger(CardController.class);

    @Autowired
    public void setCardService(ICardService cardService) {
        this.cardService = cardService;
    }

    @GetMapping(value = "/create")
    public String create() {
        return Constants.View.CARD_CREATION_FORM;
    }

    @GetMapping(value = "/display/list")
    public String create(Model model) {
        model.addAttribute(cardService.findAllAsTableItems());
        return "cardList";
    }

    @RequestMapping(value = "/display/{cardId}", method = RequestMethod.GET)
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

        return "redirect:/cards/display/list";
    }

    @RequestMapping(value = "/delete/{cardId}", method = RequestMethod.GET)
    public String delete(@PathVariable long cardId) {
        LOGGER.info("Deletion card with id={}.", cardId);
        cardService.delete(cardId);
        LOGGER.debug("Redirection to \"{}\"", "/card/list");
        return "redirect:/cards/display/list";
    }

    @GetMapping("/search")
    public String search(Model model, @RequestParam(value = "usedLang", required = false) String usedLangStr,
                         @RequestParam(value = "desiredLang", required = false) String desiredLangStr, @RequestParam String userInput) throws MethodArgumentNotValidException {

        Lang usedLang = null;
        Lang desiredLang = null;

        if(usedLangStr != null && !usedLangStr.isEmpty()) {
            usedLang = Lang.fromName(usedLangStr);
        }

        if(desiredLangStr != null && !desiredLangStr.isEmpty()) {
            desiredLang = Lang.fromName(desiredLangStr);
        }

        if(usedLang == null || desiredLang == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Provide correct supported languages", new IllegalArgumentException());
        } else {
            List<Map> searchOutput = cardService.search(usedLang, desiredLang, userInput);
            model.addAttribute(Constants.Model.SEARCH_OUTPUT, searchOutput);
            model.addAttribute(Constants.Model.SEARCH_INPUT, userInput);
        }
        return Constants.View.SEARCH_OUTPUT;
    }

}
