package edu.sam.aveng.base.controller.simple;

import edu.sam.aveng.base.model.domain.Card;
import edu.sam.aveng.base.model.domain.UserCard;
import edu.sam.aveng.base.model.domain.enumeration.Lang;
import edu.sam.aveng.base.model.domain.enumeration.Status;
import edu.sam.aveng.base.model.transfer.dto.CardDto;
import edu.sam.aveng.base.model.transfer.dto.UserCardDto;
import edu.sam.aveng.base.service.usercard.IUserCardService;
import edu.sam.aveng.base.util.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@Controller
@RequestMapping({"/test"})
public class TestController {

    @Autowired
    IUserCardService tempService;

    @RequestMapping(value = "/main", method = GET)
    public String test() {
        return "test";
    }

    @RequestMapping(value = "/temp", method = GET)
    public String temp(Model model) {

//        UserCardDto tempUserCard = new UserCardDto();
//
//        CardDto tempCard = new CardDto();
//        tempCard.setLang(Lang.GERMAN);
//
//        tempUserCard.setCard(tempCard);
//        tempUserCard.setStatus(Status.NEW);

        return "temp";
    }

}
