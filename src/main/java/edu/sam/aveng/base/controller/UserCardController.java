package edu.sam.aveng.base.controller;

import edu.sam.aveng.base.model.dto.UserCardDto;
import edu.sam.aveng.base.service.usercard.IUserCardService;
import edu.sam.aveng.base.util.Constants;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping({"/user_cards"})
public class UserCardController {
    private IUserCardService userCardService;

    @Autowired
    public void setUserCardService(IUserCardService userCardService) {
        this.userCardService = userCardService;
    }

    @GetMapping("/display")
    public String displayAll(Model model) {
        model.addAttribute(Constants.Model.USER_CARD_TABLE_ITEMS, userCardService.findAll());
        return Constants.View.PERSONAL_DICTIONARY;
    }

    @GetMapping("/display/{user_card_id}")
    public String displayOneById(Model model, @PathVariable("user_card_id") long userCardId) {
        String view = Constants.View.USER_CARD;
        UserCardDto userCardDto = userCardService.findOne(userCardId);
        if(userCardDto == null) {
            view = "redirect:/errors/404Error";
        } else {
            model.addAttribute(Constants.Model.USER_CARD_DTO_KEY, userCardService.findOne(userCardId));
        }
        return view;
    }
}
