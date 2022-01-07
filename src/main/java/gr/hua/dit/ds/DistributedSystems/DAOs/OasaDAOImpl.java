package gr.hua.dit.ds.DistributedSystems.DAOs;

import gr.hua.dit.ds.DistributedSystems.Entities.Validations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class OasaDAOImpl implements OasaDAO {
    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    public OasaDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    @Transactional
    public Validations reject(Validations validation) {
        entityManager.remove(validation);
        entityManager.flush();

        return validation;
    }
}
