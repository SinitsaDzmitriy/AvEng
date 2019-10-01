package edu.sam.aveng.base.test.integration;

import edu.sam.aveng.base.config.DataAccessLayerConfig;
import edu.sam.aveng.base.infrastructure.dao.DirectSimpleTestEntityDao;
import edu.sam.aveng.base.infrastructure.dao.DirectComposeTestEntityDao;

import edu.sam.aveng.base.infrastructure.model.entity.SimpleTestEntity;
import edu.sam.aveng.base.infrastructure.util.TestConstants;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
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

@SpringJUnitConfig(classes = {DataAccessLayerConfig.class,
        DirectSimpleTestEntityDao.class, DirectComposeTestEntityDao.class})
@TestPropertySource(locations = "/test.app.properties",
        properties = "hibernate.entity.package=edu.sam.aveng.base.infrastructure.model.entity")
@EnableTransactionManagement
@Transactional
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

    @Test
    public void SimpleEntityMappingPersistAndFindMethodImpls_PersistSimpleEntityThenFindIt_SimpleEntityIsPersistedAndFound() {

        final int INITIAL_NUMBER_OF_ROWS = countRowsInTable(TestConstants.Database.SimpleRecords.TABLE_NAME);
        SimpleTestEntity simpleEntityToPersist = new SimpleTestEntity("Simple test entity.");

        TestTransaction.flagForCommit();
        long persistedSimpleEntityId = simpleEntityDao.persist(simpleEntityToPersist);
        TestTransaction.end();

        final int FINAL_NUMBER_OF_ROWS = countRowsInTable(TestConstants.Database.SimpleRecords.TABLE_NAME);

        // Check that entity has been persisted: + 1 row in table
        assertEquals(INITIAL_NUMBER_OF_ROWS + 1, FINAL_NUMBER_OF_ROWS);

        TestTransaction.start();
        SimpleTestEntity foundSimpleEntity = simpleEntityDao.find(persistedSimpleEntityId);
        TestTransaction.end();

        // Check that entity could be found and class mapping is correct
        assertEquals(simpleEntityToPersist, foundSimpleEntity);

    }

    @Test
    public void FindAllMethodImpl_PersistSimpleEntitiesThenFindThem_AllPersistedSimpleEntitiesIsFound() {

        final int NUMBER_OF_ENTITIES = 3;

        List<SimpleTestEntity> simpleEntitiesToPersist = generateSimpleEntities(NUMBER_OF_ENTITIES);

        TestTransaction.flagForCommit();

        for(SimpleTestEntity simpleEntity: simpleEntitiesToPersist) {
            simpleEntityDao.persist(simpleEntity);
        }

        TestTransaction.start();
        List<SimpleTestEntity> foundSimpleEntities = simpleEntityDao.findAll();
        TestTransaction.end();

        // Check that all persisted entities have been found
        assertEquals(simpleEntitiesToPersist, foundSimpleEntities);

    }

    @Test
    public void UpdateMethodImpl_PersistSimpleEntityThenUpdateIt_SimpleEntityIsUpdated() {

        SimpleTestEntity simpleEntityToPersist = new SimpleTestEntity("Initial 'string' field value.");
        final String UPDATED_STRING_FIELD_VALUE = "Updated 'string' field value.";

        // Persist SimpleEntity
        final long SIMPLE_ENTITY_ID = simpleEntityDao.persist(simpleEntityToPersist);

        getCurrentSession().flush();

        // Update persisted SimpleEntity
        SimpleTestEntity simpleEntityToUpdate = simpleEntityDao.find(SIMPLE_ENTITY_ID);
        simpleEntityToUpdate.setString(UPDATED_STRING_FIELD_VALUE);
        simpleEntityDao.update(simpleEntityToUpdate);

        getCurrentSession().flush();

//        getCurrentSession().clear();

        // Obtain updated SimpleEntity

        SimpleTestEntity simpleEntityToCompare = simpleEntityDao.find(SIMPLE_ENTITY_ID);

        // Check if updated instance and instance from database equal.
        assertEquals(simpleEntityToUpdate, simpleEntityToCompare);

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
