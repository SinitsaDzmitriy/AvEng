package edu.sam.aveng.base.service.sample;

import edu.sam.aveng.legacy.contract.converter.ICollectionConverter;
import edu.sam.aveng.legacy.contract.service.StandardGenericCrudService;
import edu.sam.aveng.base.converter.search.ISearchInputConverter;
import edu.sam.aveng.base.converter.search.SampleSearchInputConverter;
import edu.sam.aveng.base.model.domain.Sample;
import edu.sam.aveng.base.model.transfer.dto.SampleDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@EnableTransactionManagement
@Transactional
public class SampleService
        extends StandardGenericCrudService<Sample, SampleDto>
        implements ISampleService {

    // ToDo: Replace it with a Bean
    private final ISearchInputConverter searchInputConverter = new SampleSearchInputConverter();

    @Override
    @Autowired
    @Qualifier("sampleConverter")
    public void converter(ICollectionConverter<Sample, SampleDto> converter) {
        setConverter(converter);
    }

    @Override
    public List<SampleDto> search(String searchInput) {

        List<Sample> selection = dao
                .findWithLikeCriterias("content", searchInputConverter.convertToLikeCriterias(searchInput));

        if (selection == null) {
            return null;
        } else {
            return converter.convertToDto(selection).collect(Collectors.toList());
        }
    }
}
