package edu.sam.aveng.controller.rest;

import edu.sam.aveng.domain.Sample;
import edu.sam.aveng.dto.SampleDto;

import edu.sam.aveng.service.ISampleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/samples")
public class SampleRestController {

    @Autowired
    private ISampleService sampleService;

    @PostMapping(value = "/create")
    public void create(@RequestBody SampleDto sampleDto) {
        sampleService.create(sampleDto);
    }

    @GetMapping
    public List<Sample> findAll() {
        return sampleService.findAll();
    }

    @GetMapping("/{id}")
    public SampleDto findOne(@PathVariable long id) {
        return sampleService.findOne(id);
    }

    @PatchMapping("/update/{id}")
    public void update(@PathVariable long id,
                       @RequestBody SampleDto sampleDto) {
        sampleService.update(id, sampleDto);
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable long id) {
        sampleService.delete(id);
    }

}

