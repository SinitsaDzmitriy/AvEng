package edu.sam.spittr.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import static org.springframework.web.bind.annotation.RequestMethod.GET;

// Declared to be a controller
@Controller

// Class-level @RequestMapping.
// Therefore it applies to all handler methods in the controller.
// HomeController handles / and /home requests.
@RequestMapping({"/", "/homepage"})
public class HomeController {

    // Method-level @RequestMapping.
    // It complements the class-level @RequestMapping.
    // With class-level @RequestMapping indicates that the home() method will handle GET requests for /.
    @RequestMapping(method=GET)
    public String home() {
        // view name
        return "home";
    }
}
