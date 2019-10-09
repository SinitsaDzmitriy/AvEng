package edu.sam.aveng.base.controller;

import edu.sam.aveng.base.model.enumeration.Lang;
import edu.sam.aveng.base.model.dto.CardDto;
import edu.sam.aveng.base.service.card.ICardService;
import edu.sam.aveng.base.util.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/cards")
public class CardController {
    private final String DEFAULT_CARD_TABLE_SIZE = "20";
    private final String DEFAULT_CARD_TABLE_PAGE_NUMBER = "1";

    private ICardService cardService;

    private static final Logger LOGGER = LoggerFactory.getLogger(CardController.class);

    @Autowired
    public void setCardService(ICardService cardService) {
        this.cardService = cardService;
    }

    @GetMapping("/create")
    public String displayCardCreationForm() {
        return Constants.View.CARD_CREATION_FORM;
    }

    @GetMapping("/display/{cardId}")
    public String displayCard(Model model, @PathVariable long cardId) {
        LOGGER.info("Displaying card with id={}.", cardId);
        model.addAttribute(Constants.Model.CARD_DTO_KEY, cardService.findOne(cardId));
        return Constants.View.CARD;
    }

    @GetMapping("/display/table")
    public String displayCardTable(Model model,
            @RequestParam(defaultValue = DEFAULT_CARD_TABLE_SIZE) int pageSize,
            @RequestParam(defaultValue = DEFAULT_CARD_TABLE_PAGE_NUMBER) int pageNum) {
        boolean isListFirst = false;
        boolean isListLast = false;

        int firstCardPositionToRead = (pageSize * (pageNum - 1));
        long totalNumberOfCards = cardService.countAll();

        if(firstCardPositionToRead >= totalNumberOfCards) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

        long restNumberOfCards = totalNumberOfCards - (firstCardPositionToRead + 1);

        if(pageNum == 1) {
            isListFirst = true;
        }
        if (restNumberOfCards <= pageSize) {
            isListLast = true;
        }

        model.addAttribute(Constants.Model.IS_LIST_FIRST_KEY, isListFirst);
        model.addAttribute(Constants.Model.IS_LIST_LAST_KEY, isListLast);
        model.addAttribute(Constants.Model.CARD_TABLE_ITEMS_KEY, cardService.findAllAsTableItems(pageSize, firstCardPositionToRead));

        return Constants.View.CARD_TABLE;
    }

    @GetMapping("/update/{cardId}")
    public String displayCardUpdateForm(@PathVariable long cardId, Model model) {
        LOGGER.info("Displaying update form for Card with id={}.", cardId);

        CardDto cardToUpdate = cardService.findOne(cardId);

        model.addAttribute(Constants.Model.CARD_DTO_KEY, cardToUpdate);

        LOGGER.debug("Card to update: cardDto={}", cardToUpdate);
        LOGGER.debug("View name to render: viewName=\"{}\"", Constants.View.CARD_UPDATE_FORM);

        return Constants.View.CARD_UPDATE_FORM;
    }

    @PostMapping("/update/{cardId}")
    public String update(@PathVariable long cardId, CardDto updatedCard) {
        LOGGER.info("Updating Card with id={}.", cardId);

        final String REDIRECTION = "redirect:/cards/display/table";
        cardService.update(cardId, updatedCard);

        LOGGER.debug("Updated Card: cardDto={}", updatedCard);
        LOGGER.debug("Redirection to \"{}\"", REDIRECTION);

        return REDIRECTION;
    }

    @GetMapping("/search")
    public String search(Model model,
            @RequestParam(value = "usedLang", required = false) String usedLangStr,
            @RequestParam(value = "desiredLang", required = false) String desiredLangStr,
            @RequestParam String userInput){
        Lang usedLang = null;
        Lang desiredLang = null;

        if(usedLangStr != null && !usedLangStr.isEmpty()) {
            usedLang = Lang.fromName(usedLangStr);
        }

        if(desiredLangStr != null && !desiredLangStr.isEmpty()) {
            desiredLang = Lang.fromName(desiredLangStr);
        }

        if(usedLang == null || desiredLang == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Provide correct supported languages", new IllegalArgumentException());
        } else {
            List<Map> searchOutput = cardService.search(usedLang, desiredLang, userInput);
            model.addAttribute(Constants.Model.SEARCH_INPUT_LANG, usedLang);
            model.addAttribute(Constants.Model.SEARCH_INPUT, userInput);
            model.addAttribute(Constants.Model.SEARCH_OUTPUT, searchOutput);
        }
        return Constants.View.SEARCH_OUTPUT;
    }
}
