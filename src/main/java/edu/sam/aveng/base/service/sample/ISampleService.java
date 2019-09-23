package edu.sam.aveng.base.service.sample;

import edu.sam.aveng.legacy.contract.converter.ICollectionConverter;
import edu.sam.aveng.legacy.contract.service.ICrudService;
import edu.sam.aveng.base.model.entity.Sample;
import edu.sam.aveng.base.model.transfer.dto.SampleDto;

import java.util.List;

public interface ISampleService
        extends ICrudService<Sample,
        SampleDto,
        SampleDto,
        ICollectionConverter<Sample, SampleDto>> {

    List<SampleDto> likeSearch(String searchInput);
    List<SampleDto> fullTextSearch(String searchInput);
}
