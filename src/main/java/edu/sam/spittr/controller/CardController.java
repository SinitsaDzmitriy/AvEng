package edu.sam.spittr.controller;

import edu.sam.spittr.domain.Sample;
import edu.sam.spittr.dto.CardDto;
import edu.sam.spittr.dto.SpittleDTO;
import edu.sam.spittr.util.Constants;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

@Controller
@RequestMapping(value = "/card")
public class CardController {

    private final static Logger LOGGER = LoggerFactory.getLogger(SpittleController.class);

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String displayCardCreationForm(Model model) {

        LOGGER.info("Card creation form displaying.");
        LOGGER.debug("Initial state of params: model={}", model);

        CardDto cardDto = new CardDto();
        cardDto.getSamples().add(new Sample(""));

        model.addAttribute(Constants.Model.CARD_DTO_KEY, cardDto);

        LOGGER.debug("Final state of params: model={}", model);
        LOGGER.debug("View name to render: viewName=\"{}\"", Constants.View.CARD_CREATION_FORM);

        return Constants.View.CARD_CREATION_FORM;
    }

//    @RequestMapping(value = "/create", method = RequestMethod.POST)
//    public String create(@Valid @ModelAttribute("spittleDTO") SpittleDTO spittleToPersist, Errors errors) {
//        Class reflectionClass = SpittleDTO.class;
//
//        if (errors.hasErrors()) {
//            String rejectedValue;
//            for (FieldError fieldError : errors.getFieldErrors()) {
//
//                // Add quotes to rejected value if it is an object of the String class.
//                try {
//                    if (reflectionClass.getDeclaredField(fieldError.getField()).getType() == String.class) {
//                        rejectedValue = new StringBuilder()
//                                .append('\"')
//                                .append(fieldError.getRejectedValue())
//                                .append('\"')
//                                .toString();
//                    } else {
//                        rejectedValue = fieldError.getRejectedValue().toString();
//                    }
//
//                    LOGGER.warn("Field {} with value {} was rejected in {} object.",
//                            fieldError.getField(), rejectedValue, fieldError.getObjectName());
//                } catch (NoSuchFieldException e) {
//                    LOGGER.error("Attempt to obtain a non-existent \"{}\" field from {}.\n{}",
//                            e.getMessage(), reflectionClass, ExceptionUtils.getStackTrace(e));
//                }
//            }
//            return "spittleCreationForm";
//        }
//
//        LOGGER.info("New spittle creation.");
//        LOGGER.debug("Spittle to persist: spittleToPersist={}", spittleToPersist);
//        spittleRepository.create(spittleToPersist);
//        LOGGER.debug("Redirection to \"{}\"", "/spittles");
//        return "redirect:/spittles";
//    }

}
