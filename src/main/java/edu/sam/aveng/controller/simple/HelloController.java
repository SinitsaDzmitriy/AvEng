package edu.sam.aveng.controller.simple;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HelloController {

    @RequestMapping("/greeting")
    public String greeting(Model model, @RequestParam(value = "name", defaultValue = "World") String name) {
        model.addAttribute("name", name);
        return "index";
    }

}
