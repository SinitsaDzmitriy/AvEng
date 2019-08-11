package edu.sam.aveng.base.service.sample;

import edu.sam.aveng.base.contract.converter.ICollectionConverter;
import edu.sam.aveng.base.contract.service.StandardGenericCrudService;
import edu.sam.aveng.base.model.domain.Sample;
import edu.sam.aveng.base.model.transfer.dto.SampleDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.transaction.Transactional;

@Service
@EnableTransactionManagement
@Transactional
public class SampleService
        extends StandardGenericCrudService<Sample, SampleDto>
        implements ISampleService {

    @Override
    @Autowired
    @Qualifier("sampleConverter")
    public void converter(ICollectionConverter<Sample, SampleDto> converter) {
        setConverter(converter);
    }
}
