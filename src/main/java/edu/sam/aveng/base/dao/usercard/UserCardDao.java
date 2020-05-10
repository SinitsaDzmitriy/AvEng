package edu.sam.aveng.base.dao.usercard;

import edu.sam.aveng.base.model.entity.*;
import edu.sam.aveng.base.model.enumeration.Lang;
import edu.sam.aveng.base.model.enumeration.StatementType;
import edu.sam.aveng.base.model.enumeration.Status;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.MatchMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.*;
import java.util.List;

@Repository
public class UserCardDao implements IUserCardDao {
    private SessionFactory sessionFactory;

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    private Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }

    @Override
    public List<UserCard> search(User owner, Lang lang, String input,
                                 List<Status> statusList,
                                 List<StatementType> typeList) {
        CriteriaBuilder criteriaBuilder = getCurrentSession().getCriteriaBuilder();

        CriteriaQuery<UserCard> criteria = criteriaBuilder.createQuery(UserCard.class);
        Root<UserCard> userCardRoot = criteria.from(UserCard.class);

        Predicate langPredicate = criteriaBuilder
                .equal(userCardRoot.get(UserCard_.card).get(Card_.lang), lang);
        Predicate ownerPredicate = criteriaBuilder
                .equal(userCardRoot.get(UserCard_.owner), owner);
        Predicate predicate = criteriaBuilder.and(langPredicate, ownerPredicate);

        if (input != null) {
            Predicate contentPredicate = criteriaBuilder
                    .like(userCardRoot.get(UserCard_.card).get(Card_.content), input + '%');
            predicate = criteriaBuilder.and(predicate, contentPredicate);
        }

        if (statusList != null) {
            Predicate statusPredicate = userCardRoot.get(UserCard_.status).in(statusList);
            predicate = criteriaBuilder.and(statusPredicate, predicate);
        }

        if (typeList != null) {
            Predicate statusPredicate = userCardRoot.get(UserCard_.card).get(Card_.type).in(typeList);
            predicate = criteriaBuilder.and(predicate, statusPredicate);
        }

        criteria.where(predicate);
        List<UserCard> userCardList = getCurrentSession().createQuery(criteria).getResultList();
        return userCardList;
    }
}