package edu.sam.aveng.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

// Declared to be a controller
@Controller

// Class-level @RequestMapping.
// Therefore it applies to all handler methods in the controller.
// HomeController handles / and /home requests.
@RequestMapping({"/", "/initial"})
public class InitialController {

    private static final Logger LOGGER = LoggerFactory.getLogger(InitialController.class);

    // Method-level @RequestMapping.
    // It complements the class-level @RequestMapping.
    // With class-level @RequestMapping indicates that the home() method will handle GET requests for /.
    @RequestMapping(method = GET)
    public String home() {
        // boolean test = SecurityContextHolder.getContext().getAuthentication().isAuthenticated();
        LOGGER.info("Home page displaying.");
        LOGGER.debug("View name to render: viewName=\"{}\"", "home");
        // view name
        return "initial";
    }
}
