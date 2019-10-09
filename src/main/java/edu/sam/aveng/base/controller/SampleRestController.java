package edu.sam.aveng.base.controller;

import edu.sam.aveng.base.model.dto.SampleDto;
import edu.sam.aveng.base.model.enumeration.Lang;
import edu.sam.aveng.base.service.sample.ISampleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/samples")
public class SampleRestController {
    ISampleService sampleService;

    @Autowired
    public void setSampleService(ISampleService sampleService) {
        this.sampleService = sampleService;
    }

    @GetMapping("/search")
    public List<SampleDto> search(@RequestParam String input, @RequestParam Lang lang) {
        return sampleService.fullTextSearch(input, lang);
    }
}
