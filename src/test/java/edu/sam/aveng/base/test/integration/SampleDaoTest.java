package edu.sam.aveng.base.test.integration;

import edu.sam.aveng.base.config.DataAccessLayerConfig;
import edu.sam.aveng.base.dao.sample.ISampleDao;
import edu.sam.aveng.base.dao.sample.SampleDao;
import edu.sam.aveng.base.model.entity.Sample;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.test.context.transaction.TestTransaction;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringJUnitConfig(classes = {DataAccessLayerConfig.class, SampleDao.class})
@TestPropertySource("/test.app.properties")
@EnableTransactionManagement
@Transactional
public class SampleDaoTest {

    private ISampleDao sampleDao;

    @Autowired
    public void setSampleDao(ISampleDao sampleDao) {
        this.sampleDao = sampleDao;
    }

    @Test
    // test bla bla
    public void sampleMappingCreateSampleThenFindItSampleIsCreatedAndFound() {

        // 1. Arrange
        Sample sample = new Sample("Sample's content.");

        // 2. Act
        TestTransaction.flagForCommit();
        long sampleId = sampleDao.persist(sample);
        TestTransaction.end();

        TestTransaction.start();
        Sample foundSample = sampleDao.find(sampleId);
        TestTransaction.end();

        // 3. Assert
        assertEquals(sample, foundSample);

    }

}
