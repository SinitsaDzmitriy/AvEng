package edu.sam.aveng.controller;

import edu.sam.aveng.dto.UserDTO;
import edu.sam.aveng.service.IUserService;
import edu.sam.aveng.util.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

@Controller
public class UserController {

    @Autowired
    private IUserService userService;

    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String displayUserRegistrationForm(Model model) {
        LOGGER.info("User registration form displaying.");
        LOGGER.debug("Initial state of params: model={}", model);
        model.addAttribute(Constants.Model.USER_DTO_KEY, new UserDTO());
        LOGGER.debug("Final state of params: model={}", model);
        LOGGER.debug("View name to render: viewName=\"{}\"", Constants.View.USER_REGISTRATION_FORM);
        return Constants.View.USER_REGISTRATION_FORM;
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String registerUser(
            @Valid
            @ModelAttribute(Constants.Model.USER_DTO_KEY)
                    UserDTO spittleToPersist, Errors errors) {
        if (errors.hasErrors()) {
            return Constants.View.USER_REGISTRATION_FORM;
        }
        userService.register(spittleToPersist);
        return "redirect:/login";
    }
}
