package edu.sam.aveng.base.service.sample;

import edu.sam.aveng.base.model.enumeration.Lang;
import edu.sam.aveng.base.contract.v1.converter.ICollectionConverter;
import edu.sam.aveng.base.contract.v1.service.ICrudService;
import edu.sam.aveng.base.model.entity.Sample;
import edu.sam.aveng.base.model.dto.SampleDto;

import java.util.List;

public interface ISampleService
        extends ICrudService<Sample,
        SampleDto,
        SampleDto,
        ICollectionConverter<Sample, SampleDto>> {

    List<SampleDto> likeSearch(String searchInput);

    List<SampleDto> fullTextSearch(String searchInput, Lang searchLang);
}
