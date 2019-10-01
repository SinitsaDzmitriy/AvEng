package edu.sam.aveng.base.infrastructure.dao;

import edu.sam.aveng.base.contract.dao.AbstractGenericHibernateDao;
import edu.sam.aveng.base.infrastructure.model.entity.ComposeTestEntity;
import org.springframework.stereotype.Repository;

@Repository
public class DirectComposeTestEntityDao
        extends AbstractGenericHibernateDao<ComposeTestEntity> {

    @Override
    protected void assignEntityClazz() {
        setEntityClass(ComposeTestEntity.class);
    }

}
