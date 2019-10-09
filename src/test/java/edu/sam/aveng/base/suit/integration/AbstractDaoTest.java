package edu.sam.aveng.base.suit.integration;

import edu.sam.aveng.base.config.DataAccessLayerConfig;
import edu.sam.aveng.base.infrastructure.dao.DirectSimpleTestEntityDao;
import edu.sam.aveng.base.infrastructure.dao.DirectComposeTestEntityDao;

import edu.sam.aveng.base.infrastructure.model.entity.SimpleTestEntity;
import edu.sam.aveng.base.infrastructure.util.TestConstants;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.test.context.transaction.TestTransaction;
import org.springframework.test.jdbc.JdbcTestUtils;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringJUnitConfig(classes = {DataAccessLayerConfig.class,
        DirectSimpleTestEntityDao.class, DirectComposeTestEntityDao.class})
@TestPropertySource(locations = "/test.app.properties",
        properties = "hibernate.entity.package=edu.sam.aveng.base.infrastructure.model.entity")
@Transactional
public class AbstractDaoTest {
    private JdbcTemplate jdbcTemplate;
    private DirectSimpleTestEntityDao simpleEntityDao;

    @Autowired
    void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Autowired
    public void setSimpleEntityDao(DirectSimpleTestEntityDao simpleEntityDao) {
        this.simpleEntityDao = simpleEntityDao;
    }

    @Test
    public void persistTest() {
        int initialNumberOfRows = countRowsInTable(TestConstants.Table.SIMPLE_RECORDS);
        SimpleTestEntity simpleEntityToPersist = new SimpleTestEntity("Simple suit entity.");

        TestTransaction.flagForCommit();
        simpleEntityDao.persist(simpleEntityToPersist);
        // Auto flush on transaction commit
        TestTransaction.end();

        int finalNumberOfRows = countRowsInTable(TestConstants.Table.SIMPLE_RECORDS);
        // Check that entity has been persisted: + 1 row in table
        assertEquals(initialNumberOfRows + 1, finalNumberOfRows);

        // Clear table to prevent impact on other tests
        deleteFromTable(TestConstants.Table.SIMPLE_RECORDS);
    }

    @Test
    public void findTest() {
        int initialNumberOfRows = countRowsInTable(TestConstants.Table.SIMPLE_RECORDS);
        SimpleTestEntity persistedSimpleEntity = new SimpleTestEntity("Simple suit entity.");

        TestTransaction.flagForCommit();
        long persistedSimpleEntityId = simpleEntityDao.persist(persistedSimpleEntity);
        TestTransaction.end();

        int finalNumberOfRows = countRowsInTable(TestConstants.Table.SIMPLE_RECORDS);
        assertEquals(initialNumberOfRows + 1, finalNumberOfRows);

        TestTransaction.start();
        SimpleTestEntity foundSimpleEntity = simpleEntityDao.find(persistedSimpleEntityId);
        TestTransaction.end();
        
        assertEquals(persistedSimpleEntity, foundSimpleEntity);
        deleteFromTable(TestConstants.Table.SIMPLE_RECORDS);
    }

    @Test
    public void findAllTest() {
        int initialNumberOfRows = countRowsInTable(TestConstants.Table.SIMPLE_RECORDS);
        int numberOfEntitiesToPersist = 3;

        List<SimpleTestEntity> simpleEntitiesToPersist = generateSimpleEntities(numberOfEntitiesToPersist);

        TestTransaction.flagForCommit();
        for(SimpleTestEntity simpleEntity: simpleEntitiesToPersist) {
            simpleEntityDao.persist(simpleEntity);
        }
        TestTransaction.end();

        TestTransaction.start();
        List<SimpleTestEntity> foundSimpleEntities = simpleEntityDao.findAll();
        TestTransaction.end();

        assertEquals(initialNumberOfRows + numberOfEntitiesToPersist, foundSimpleEntities.size());
        assertTrue(foundSimpleEntities.containsAll(simpleEntitiesToPersist));
        assertEquals(simpleEntitiesToPersist, foundSimpleEntities);

        deleteFromTable(TestConstants.Table.SIMPLE_RECORDS);
    }

    @Test
    public void updateTest() {
        int initialNumberOfRows = countRowsInTable(TestConstants.Table.SIMPLE_RECORDS);

        SimpleTestEntity simpleEntityToPersist = new SimpleTestEntity("Simple suit entity.");

        TestTransaction.flagForCommit();
        long persistedSimpleEntityId = simpleEntityDao.persist(simpleEntityToPersist);
        TestTransaction.end();

        int finalNumberOfRows = countRowsInTable(TestConstants.Table.SIMPLE_RECORDS);
        assertEquals(initialNumberOfRows + 1, finalNumberOfRows);

        TestTransaction.start();
        TestTransaction.flagForCommit();
        SimpleTestEntity foundEntityBeforeUpdate = simpleEntityDao.find(persistedSimpleEntityId);
        TestTransaction.end();

        assertEquals(simpleEntityToPersist, foundEntityBeforeUpdate);
        SimpleTestEntity updatedEntity = foundEntityBeforeUpdate;
        updatedEntity.setString("Updated simple suit entity.");

        TestTransaction.start();
        TestTransaction.flagForCommit();
        simpleEntityDao.update(updatedEntity);
        TestTransaction.end();

        TestTransaction.start();
        SimpleTestEntity foundSimpleEntityAfterUpdate = simpleEntityDao.find(persistedSimpleEntityId);
        TestTransaction.end();

        assertEquals(updatedEntity, foundSimpleEntityAfterUpdate);

        deleteFromTable(TestConstants.Table.SIMPLE_RECORDS);
    }

    @Test
    public void deleteTest() {
        int initialNumberOfRows = countRowsInTable(TestConstants.Table.SIMPLE_RECORDS);

        SimpleTestEntity simpleEntityToPersist = new SimpleTestEntity("Simple suit entity.");

        TestTransaction.flagForCommit();
        long persistedSimpleEntityId = simpleEntityDao.persist(simpleEntityToPersist);
        TestTransaction.end();

        int currentNumberOfRows = countRowsInTable(TestConstants.Table.SIMPLE_RECORDS);
        assertEquals(initialNumberOfRows + 1, currentNumberOfRows);

        TestTransaction.start();
        TestTransaction.flagForCommit();
        simpleEntityDao.delete(persistedSimpleEntityId);
        TestTransaction.end();

        currentNumberOfRows = countRowsInTable(TestConstants.Table.SIMPLE_RECORDS);
        assertEquals(initialNumberOfRows, currentNumberOfRows);
    }

    private void deleteFromTable(String tableName) {
        JdbcTestUtils.deleteFromTables(this.jdbcTemplate, tableName);
    }

    private int countRowsInTable(String tableName) {
        return JdbcTestUtils.countRowsInTable(this.jdbcTemplate, tableName);
    }

    private List<SimpleTestEntity> generateSimpleEntities(int quantity) {
        if(quantity <= 0) {
            throw new IllegalArgumentException();
        }

        List<SimpleTestEntity> listOfSimpleEntities = new ArrayList<>(quantity);
        SimpleTestEntity simpleEntity;

        for(int i = 0; i < quantity; i++) {
            simpleEntity = new SimpleTestEntity("Simple suit Entity No" + i);
            listOfSimpleEntities.add(simpleEntity);
        }

        return listOfSimpleEntities;
    }
}
