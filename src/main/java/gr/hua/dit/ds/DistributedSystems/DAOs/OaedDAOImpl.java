package gr.hua.dit.ds.DistributedSystems.DAOs;

import gr.hua.dit.ds.DistributedSystems.Entities.Applications;
import gr.hua.dit.ds.DistributedSystems.Entities.Validations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class OaedDAOImpl implements OaedDAO {
    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    public OaedDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    @Transactional
    public Validations validateApplication(Validations validation) {
        entityManager.persist(validation);
        entityManager.flush();

        return validation;
    }

    @Override
    @Transactional
    public Applications rejectApplication(Applications application) {
        entityManager.remove(application);
        entityManager.flush();

        return application;
    }

}
