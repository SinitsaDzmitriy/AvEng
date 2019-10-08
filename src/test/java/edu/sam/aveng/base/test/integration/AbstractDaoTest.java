package edu.sam.aveng.base.test.integration;

import edu.sam.aveng.base.config.DataAccessLayerConfig;
import edu.sam.aveng.base.infrastructure.dao.DirectSimpleTestEntityDao;
import edu.sam.aveng.base.infrastructure.dao.DirectComposeTestEntityDao;

import edu.sam.aveng.base.infrastructure.model.entity.SimpleTestEntity;
import edu.sam.aveng.base.infrastructure.util.TestConstants;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
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

@SpringJUnitConfig(classes = {DataAccessLayerConfig.class,
        DirectSimpleTestEntityDao.class, DirectComposeTestEntityDao.class})
@TestPropertySource(locations = "/test.app.properties",
        properties = "hibernate.entity.package=edu.sam.aveng.base.infrastructure.model.entity")
@EnableTransactionManagement
@Transactional
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class AbstractDaoTest {

    private JdbcTemplate jdbcTemplate;
    private SessionFactory sessionFactory;

    private DirectSimpleTestEntityDao simpleEntityDao;
    private DirectComposeTestEntityDao composeEntityDao;

    @Autowired
    void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Autowired
    public void setSimpleEntityDao(DirectSimpleTestEntityDao simpleEntityDao) {
        this.simpleEntityDao = simpleEntityDao;
    }

    @Autowired
    public void setComposeEntityDao(DirectComposeTestEntityDao composeEntityDao) {
        this.composeEntityDao = composeEntityDao;
    }

    private int test;

    @Test
    @Order(1)
    public void persistAndFindMethodImpls_persistSimpleEntityThenFindIt_simpleEntityIsPersistedAndFound() {
        final int INITIAL_NUMBER_OF_ROWS = countRowsInTable(TestConstants.Database.Tables.SimpleRecords.TABLE_NAME);
        SimpleTestEntity simpleEntityToPersist = new SimpleTestEntity("Simple test entity.");

        TestTransaction.flagForCommit();
        long persistedSimpleEntityId = simpleEntityDao.persist(simpleEntityToPersist);
        // Auto flush on transaction commit
        TestTransaction.end();

        final int FINAL_NUMBER_OF_ROWS = countRowsInTable(TestConstants.Database.Tables.SimpleRecords.TABLE_NAME);
        // Check that entity has been persisted: + 1 row in table
        assertEquals(INITIAL_NUMBER_OF_ROWS + 1, FINAL_NUMBER_OF_ROWS, "");

        TestTransaction.start();
        SimpleTestEntity fetchedSimpleEntity = simpleEntityDao.find(persistedSimpleEntityId);
        TestTransaction.end();

        // Check that correct entity could be found
        assertEquals(simpleEntityToPersist, fetchedSimpleEntity, "");

        // Clear table to prevent impact on other tests
        deleteFromTable(TestConstants.Database.Tables.SimpleRecords.TABLE_NAME);
    }

    @Test
    @Order(2)
    public void deleteMethodImpl_deleteSimpleEntity_simpleEntityIsDeleted() {
        final int INITIAL_NUMBER_OF_ROWS = countRowsInTable(TestConstants.Database.Tables.SimpleRecords.TABLE_NAME);
        SimpleTestEntity simpleEntityToPersist = new SimpleTestEntity("Simple test entity.");

        TestTransaction.flagForCommit();
        long persistedSimpleEntityId = simpleEntityDao.persist(simpleEntityToPersist);
        TestTransaction.end();

        TestTransaction.start();
        TestTransaction.flagForCommit();
        simpleEntityDao.delete(persistedSimpleEntityId);
        TestTransaction.end();

        final int FINAL_NUMBER_OF_ROWS = countRowsInTable(TestConstants.Database.Tables.SimpleRecords.TABLE_NAME);
        assertEquals(INITIAL_NUMBER_OF_ROWS, FINAL_NUMBER_OF_ROWS);
    }

    @Test
    @Order(3)
    public void updateMethodImpl_persistSimpleEntityThenUpdateIt_simpleEntityIsUpdated() {
        SimpleTestEntity simpleEntityToPersist = new SimpleTestEntity("Simple test entity.");

        TestTransaction.flagForCommit();
        long persistedSimpleEntityId = simpleEntityDao.persist(simpleEntityToPersist);
        TestTransaction.end();

        final int INITIAL_NUMBER_OF_ROWS = countRowsInTable(TestConstants.Database.Tables.SimpleRecords.TABLE_NAME);

        TestTransaction.start();
        TestTransaction.flagForCommit();
        SimpleTestEntity updatedSimpleEntity = simpleEntityDao.find(persistedSimpleEntityId);
        TestTransaction.end();

        updatedSimpleEntity.setString("Updated simple test entity.");

        TestTransaction.start();
        TestTransaction.flagForCommit();
        simpleEntityDao.update(updatedSimpleEntity);
        TestTransaction.end();

        TestTransaction.start();
        SimpleTestEntity fetchedSimpleEntity = simpleEntityDao.find(persistedSimpleEntityId);
        TestTransaction.end();

        final int FINAL_NUMBER_OF_ROWS = countRowsInTable(TestConstants.Database.Tables.SimpleRecords.TABLE_NAME);

        assertEquals(updatedSimpleEntity, fetchedSimpleEntity);
        assertEquals(INITIAL_NUMBER_OF_ROWS, FINAL_NUMBER_OF_ROWS);

        deleteFromTable(TestConstants.Database.Tables.SimpleRecords.TABLE_NAME);
    }

    @Test
    @Order(4)
    public void findAllMethodImpl_persistSimpleEntitiesThenFindThem_persistedSimpleEntitiesAreFound() {
        final int INITIAL_NUMBER_OF_ROWS = countRowsInTable(TestConstants.Database.Tables.SimpleRecords.TABLE_NAME);
        final int NUMBER_OF_ENTITIES_TO_PERSIST = 3;

        List<SimpleTestEntity> simpleEntitiesToPersist = generateSimpleEntities(NUMBER_OF_ENTITIES_TO_PERSIST);

        TestTransaction.flagForCommit();
        for(SimpleTestEntity simpleEntity: simpleEntitiesToPersist) {
            simpleEntityDao.persist(simpleEntity);
        }
        TestTransaction.end();

        TestTransaction.start();
        List<SimpleTestEntity> foundSimpleEntities = simpleEntityDao.findAll();
        TestTransaction.end();

        assertEquals(INITIAL_NUMBER_OF_ROWS + NUMBER_OF_ENTITIES_TO_PERSIST, foundSimpleEntities.size());
        assertEquals(simpleEntitiesToPersist, foundSimpleEntities);
    }

    private int deleteFromTable(String tableName) {
        return JdbcTestUtils.deleteFromTables(this.jdbcTemplate, tableName);
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
            simpleEntity = new SimpleTestEntity("Simple test Entity No" + i);
            listOfSimpleEntities.add(simpleEntity);
        }

        return listOfSimpleEntities;
    }

    public Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }
}
