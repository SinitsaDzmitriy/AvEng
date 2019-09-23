package edu.sam.aveng.legacy.controller.simple;

import edu.sam.aveng.base.converter.search.ISearchInputConverter;
import edu.sam.aveng.base.converter.search.SampleSearchInputConverter;
import edu.sam.aveng.base.model.entity.Sample;
import edu.sam.aveng.base.service.usercard.IUserCardService;
import edu.sam.aveng.legacy.contract.dao.IGenericDao;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.search.FullTextSession;
import org.hibernate.search.Search;
import org.hibernate.search.query.dsl.QueryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
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

    // =================================================================================================================



}
