package edu.sam.aveng.base.suit.integration;

import edu.sam.aveng.base.config.DataAccessLayerConfig;
import edu.sam.aveng.base.dao.sample.ISampleDao;
import edu.sam.aveng.base.dao.sample.SampleDao;
import edu.sam.aveng.base.model.entity.Sample;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.test.context.transaction.TestTransaction;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringJUnitConfig(classes = {DataAccessLayerConfig.class, SampleDao.class})
@TestPropertySource("/test.app.properties")
@Transactional
public class SampleDaoTest {
    private ISampleDao sampleDao;

    @Autowired
    public void setSampleDao(ISampleDao sampleDao) {
        this.sampleDao = sampleDao;
    }

    @Test
    public void sampleMappingCreateSampleThenFindItSampleIsCreatedAndFound() {
        Sample sample = new Sample("Sample's content.");

        TestTransaction.flagForCommit();
        long sampleId = sampleDao.persist(sample);
        TestTransaction.end();

        TestTransaction.start();
        Sample foundSample = sampleDao.find(sampleId);
        TestTransaction.end();

        assertEquals(sample, foundSample);
    }
}
