package edu.sam.spittr.repositories;

import edu.sam.spittr.domain.Spitter;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import javax.inject.Inject;
import java.io.Serializable;
import java.util.List;

/*
        Marks the class as a Spring component.
        The repository class is in a package
    covered by component-scanning. Therefore,
    it's always found.
*/

@Repository
public class HibernateSpitterRepository {

    // Automatic injection of a SessionFactory
    @Inject
    private SessionFactory sessionFactory;



    public HibernateSpitterRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public Spitter save(Spitter spitter) {
        Serializable id = currentSession().save(spitter);

        return new Spitter((long) id,
                spitter.getUsername(),
                spitter.getPassword(),
                spitter.getFullName(),
                spitter.getEmail(),
                spitter.isUpdateByEmail());
    }

    public Spitter find(long id) {
        return (Spitter) currentSession().get(Spitter.class, id);
    }

    public Spitter findByUsername(String username) {
        return (Spitter) currentSession()
                .createCriteria(Spitter.class)
                .add(Restrictions.eq("username", username))
                .list().get(0);
    }

    public List<Spitter> findAll() {
        return (List<Spitter>) currentSession()
                .createCriteria(Spitter.class)
                .list();
    }

    private Session currentSession() {
        return sessionFactory.getCurrentSession();
    }

    public long count() {
        return findAll().size();
    }
}
