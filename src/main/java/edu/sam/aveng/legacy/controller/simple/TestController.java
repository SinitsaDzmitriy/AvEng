package edu.sam.aveng.legacy.controller.simple;

import edu.sam.aveng.base.service.usercard.IUserCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Locale;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@Controller
@RequestMapping({"/test"})
@EnableTransactionManagement
public class TestController {

    @Autowired
    IUserCardService tempService;

    @RequestMapping(value = "/main", method = GET)
    public String test() {
        return "test";
    }

    @RequestMapping(value = "/temp", method = GET)
    public String temp(Model model) {

        Locale userLocale = LocaleContextHolder.getLocale();
        String userLocaleAsString = userLocale.getLanguage();
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
