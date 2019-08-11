package edu.sam.aveng.base.service.sample;

import edu.sam.aveng.base.contract.converter.ICollectionConverter;
import edu.sam.aveng.base.contract.service.ICrudService;
import edu.sam.aveng.base.model.domain.Sample;
import edu.sam.aveng.base.model.transfer.dto.SampleDto;

public interface ISampleService
        extends ICrudService<Sample,
        SampleDto,
        SampleDto,
        ICollectionConverter<Sample, SampleDto>> {
}
