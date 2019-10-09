package edu.sam.aveng.base.controller;

import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping({"/demo-samples"})
public class DemoController {
    @GetMapping(value = "/like-vs-fts")
    public String displayComparisonPage() {
        return "likeVsFts";
    }
}
