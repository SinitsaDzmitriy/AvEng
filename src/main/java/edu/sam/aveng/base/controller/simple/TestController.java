package edu.sam.aveng.base.controller.simple;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@Controller
@RequestMapping({"/test"})
public class TestController {

    @RequestMapping(value = "/main", method = GET)
    public String test() {
        return "test";
    }

    @RequestMapping(value = "/temp", method = GET)
    public String temp() {
        return "temp";
    }

}
