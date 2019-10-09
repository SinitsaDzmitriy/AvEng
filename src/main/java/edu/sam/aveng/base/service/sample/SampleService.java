package edu.sam.aveng.base.service.sample;

import edu.sam.aveng.base.dao.sample.ISampleDao;
import edu.sam.aveng.base.model.enumeration.Lang;
import edu.sam.aveng.base.contract.v1.converter.ICollectionConverter;
import edu.sam.aveng.base.contract.v1.service.StandardGenericCrudService;
import edu.sam.aveng.base.converter.search.ISearchInputConverter;
import edu.sam.aveng.base.model.entity.Sample;
import edu.sam.aveng.base.model.dto.SampleDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class SampleService
        extends StandardGenericCrudService<Sample, SampleDto>
        implements ISampleService {

    private ISampleDao sampleDao;
    private ISearchInputConverter searchInputConverter;

    @Autowired
    public void setSampleDao(ISampleDao sampleDao) {
        this.sampleDao = sampleDao;
    }

    @Autowired
    public void setSearchInputConverter(ISearchInputConverter searchInputConverter) {
        this.searchInputConverter = searchInputConverter;
    }

    @Override
    @Autowired
    @Qualifier("oldSampleConverter")
    public void converter(ICollectionConverter<Sample, SampleDto> converter) {
        setConverter(converter);
    }

    @Override
    public List<SampleDto> likeSearch(String searchInput) {

        List<SampleDto> sampleDtos = null;

        List<Sample> samples = sampleDao
                .likeSearch(searchInputConverter.convertToLikeCriterias(searchInput));

        if (samples != null) {
            sampleDtos = converter.convertToDto(samples).collect(Collectors.toList());
        }

        return sampleDtos;
    }

    @Override
    public List<SampleDto> fullTextSearch(String searchInput, Lang searchLang) {

        List<SampleDto> sampleDtos = null;

        List<Sample> samples = sampleDao.fullTextSearch(searchInput, searchLang);

        if (samples != null) {
            sampleDtos = converter.convertToDto(samples).collect(Collectors.toList());
        }

        return sampleDtos;

    }
}
