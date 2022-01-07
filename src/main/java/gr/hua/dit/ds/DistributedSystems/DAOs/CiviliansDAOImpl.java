package gr.hua.dit.ds.DistributedSystems.DAOs;

import gr.hua.dit.ds.DistributedSystems.Entities.Applications;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class CiviliansDAOImpl implements  CiviliansDAO {
    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    public CiviliansDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    @Transactional
    public Applications apply(Applications application) {
        entityManager.persist(application);
        entityManager.flush();

        return application;
    }
}
