package edu.sam.aveng.base.controller;

import edu.sam.aveng.base.model.entity.User;
import edu.sam.aveng.base.model.enumeration.Lang;
import edu.sam.aveng.base.model.enumeration.StatementType;
import edu.sam.aveng.base.model.transfer.dto.CardDto;
import edu.sam.aveng.base.model.transfer.dto.PronunciationDto;
import edu.sam.aveng.base.model.transfer.dto.SampleDto;
import edu.sam.aveng.base.model.transfer.user.credentials.IUserCredentials;
import edu.sam.aveng.base.model.transfer.user.credentials.UserCredentials;
import edu.sam.aveng.base.service.user.IUserService;
import edu.sam.aveng.base.service.usercard.IUserCardService;

import edu.sam.aveng.base.util.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

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

        CardDto cardDto = new CardDto();

        PronunciationDto pronDto = new PronunciationDto();
        pronDto.setTranscription("test");

        List<SampleDto> sampleDtos = new ArrayList<>(2);
        sampleDtos.add(new SampleDto("None of our products are tested on animals."));
        sampleDtos.add(new SampleDto("I'm testing this web-page."));

        cardDto.setId(0L);
        cardDto.setLang(Lang.ENGLISH);
        cardDto.setContent("test");
        cardDto.setType(StatementType.VERB);
        cardDto.setPron(pronDto);
        cardDto.setDefinition("to do something in order to discover if something is safe, works correctly, etc");
        cardDto.setSamples(sampleDtos);

        model.addAttribute(Constants.Model.CARD_DTO_KEY, cardDto);

        return "temp";
    }

    @Autowired
    private IUserService userService;

    @GetMapping(value = "/regAdmin")
    public void regAdmin() {

        UserCredentials adminCredentials = new UserCredentials();

        adminCredentials.setEmail("admin");
        adminCredentials.setPassword("admin");

        userService.create(adminCredentials);

    }

}
