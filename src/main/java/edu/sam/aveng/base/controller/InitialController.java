package edu.sam.aveng.base.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@Controller
@RequestMapping({"/", "/initial"})
public class InitialController {

    private static final Logger LOGGER = LoggerFactory.getLogger(InitialController.class);

    @RequestMapping(method = GET)
    public String home() {
        LOGGER.info("Home page displaying.");
        LOGGER.debug("View name to render: viewName=\"{}\"", "home");
        return "initial";
    }
}
