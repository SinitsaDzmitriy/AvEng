package edu.sam.aveng.base.converter;

import edu.sam.aveng.base.model.entity.Sample;
import edu.sam.aveng.base.model.dto.SampleDto;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.stream.Stream;

@Component
public class SampleConverter {

    public SampleDto convertToDto(Sample sample) {

        SampleDto sampleDto = new SampleDto();

        sampleDto.setId(sample.getId());
        sampleDto.setContent(sample.getContent());

        return sampleDto;
    }

    public Sample convertToEntity(SampleDto sampleDto) {

        Sample sample = new Sample();

        sample.setContent(sampleDto.getContent());

        return sample;
    }

    public Stream<SampleDto> convertToDto(Collection<Sample> entities) {
        return entities.stream().map(this::convertToDto);
    }

    public Stream<Sample> convertToEntity(Collection<SampleDto> dtos) {
        return dtos.stream().map(this::convertToEntity);
    }

}
