package edu.sam.aveng.base.infrastructure.dao;

import edu.sam.aveng.base.contract.v2.dao.AbstractGenericHibernateDao;
import edu.sam.aveng.base.infrastructure.model.entity.SimpleTestEntity;
import org.springframework.stereotype.Repository;

@Repository
public class DirectSimpleTestEntityDao
        extends AbstractGenericHibernateDao<SimpleTestEntity> {

    @Override
    protected void assignEntityClazz() {
        setEntityClass(SimpleTestEntity.class);
    }

}
