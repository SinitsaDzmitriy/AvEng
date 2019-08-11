package edu.sam.aveng.base.controller.rest;

import edu.sam.aveng.base.contract.controller.AbstractCrudRestController;
import edu.sam.aveng.base.model.domain.Sample;
import edu.sam.aveng.base.model.transfer.dto.SampleDto;
import edu.sam.aveng.base.service.sample.ISampleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/samples")
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

}

