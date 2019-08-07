package edu.sam.aveng.service;

import edu.sam.aveng.dao.IGenericDao;
import edu.sam.aveng.domain.Sample;
import edu.sam.aveng.dto.SampleDto;
import edu.sam.aveng.util.Converter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.transaction.Transactional;
import java.util.List;

@Service
@EnableTransactionManagement
@Transactional
public class SampleService implements ISampleService {

    private IGenericDao<Sample> sampleDao;

    @Autowired
    public void setDao(IGenericDao<Sample> daoToSet) {
        sampleDao = daoToSet;
        sampleDao.setClazz(Sample.class);
    }

    @Override
    public void create(SampleDto sampleDto) {
        sampleDao.create(Converter.convertToEntity(sampleDto));
    }

    @Override
    public List<Sample> findAll() {
        return sampleDao.findAll();
    }

    @Override
    public SampleDto findOne(long id) {
        return Converter.convertToDto(sampleDao.findOne(id));
    }

    @Override
    public void update(long id, SampleDto sampleDto) {
        sampleDto.setId(id);
        sampleDao.update(Converter.convertToEntity(sampleDto));
    }

    @Override
    public void delete(long id) {
        sampleDao.delete(sampleDao.findOne(id));
    }
}
