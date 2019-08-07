package edu.sam.aveng.controller.rest;

import edu.sam.aveng.dto.SampleDto;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sample")
public class SampleRestController {



    @PostMapping
    public void create(@RequestBody SampleDto sampleDto) {
    }
}

