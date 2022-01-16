package gr.hua.dit.ds.DistributedSystems.DAOs;

import gr.hua.dit.ds.DistributedSystems.Entities.Applications;
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
    public Applications validateApplication(Applications application) {
        application.setValidated("true");

        return application;
    }

    @Override
    @Transactional
    public Applications rejectApplication(Applications application) {
        application.setValidated("false");

        return application;
    }

}
