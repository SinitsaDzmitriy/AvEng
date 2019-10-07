package edu.sam.aveng.base.converter;

import edu.sam.aveng.base.contract.v1.converter.ICollectionConverter;
import edu.sam.aveng.base.model.entity.Sample;
import edu.sam.aveng.base.model.dto.SampleDto;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.stream.Stream;

@Component
public class OldSampleConverter implements ICollectionConverter<Sample, SampleDto> {

    @Override
    public SampleDto convertToDto(Sample sample) {
        SampleDto sampleDto = new SampleDto();
        sampleDto.setContent(sample.getContent());
        sampleDto.setId(sample.getId());
        return sampleDto;
    }

    @Override
    public Stream<SampleDto> convertToDto(Collection<Sample> entities) {
        return entities.stream()
                .map(this::convertToDto);
    }

    @Override
    public Sample convertToEntity(SampleDto dto) {
        Sample sample = new Sample();
        sample.setContent(dto.getContent());
        return sample;
    }

    @Override
    public Stream<Sample> convertToEntity(Collection<SampleDto> dtos) {
        return dtos.stream()
                .map(this::convertToEntity);
    }
}
