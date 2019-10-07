package edu.sam.aveng.base.controller;

import edu.sam.aveng.base.model.enumeration.Lang;
import edu.sam.aveng.base.contract.v1.controller.AbstractCrudRestController;
import edu.sam.aveng.base.model.entity.Sample;
import edu.sam.aveng.base.model.dto.SampleDto;
import edu.sam.aveng.base.service.sample.ISampleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/samples")
public class SampleRestController
        extends AbstractCrudRestController<Sample,
        SampleDto,
        SampleDto,
        ISampleService> {

    @Override
    @Autowired
    public void service(ISampleService service) {
        this.service = service;
    }

    @Override
    protected Class<Sample> entityType() {
        return Sample.class;
    }

    @RequestMapping("/search")
    public List<SampleDto> search(@RequestParam String input, @RequestParam Lang lang) {
        return service.fullTextSearch(input, lang);
    }

}

