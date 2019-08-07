package edu.sam.aveng.service;

import edu.sam.aveng.domain.Sample;
import edu.sam.aveng.dto.SampleDto;

import java.util.List;

public interface ISampleService {

    void create(SampleDto sampleDto);

    List<Sample> findAll();

    SampleDto findOne(long id);

    void update(long id, SampleDto sampleDto);

    void delete(long id);

}
