package edu.sam.aveng.base.controller;

import edu.sam.aveng.base.util.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping({"/", "/initial"})
public class InitialController {
    private static final Logger LOGGER = LoggerFactory.getLogger(InitialController.class);

    @GetMapping
    public String home() {
        LOGGER.info("Home page displaying.");
        LOGGER.debug("View name to render: viewName=\"{}\".", Constants.View.HOMEPAGE);
        return Constants.View.HOMEPAGE;
    }
}
