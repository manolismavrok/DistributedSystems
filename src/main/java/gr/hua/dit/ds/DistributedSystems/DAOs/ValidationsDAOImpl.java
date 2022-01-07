package gr.hua.dit.ds.DistributedSystems.DAOs;

import gr.hua.dit.ds.DistributedSystems.Entities.Validations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
public class ValidationsDAOImpl implements ValidationsDAO {
    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    public ValidationsDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<Validations> getAllValidations() {
        Query theQuery = entityManager.createQuery("select V from Validations AS V");
        List<Validations> validations = theQuery.getResultList();

        return validations;
    }

    @Override
    public Validations getValidationsById(int id) {
        Query theQuery = entityManager.createQuery("select V from Validations AS V where V.id=:id");
        theQuery.setParameter("id", id);
        List<Validations> result = theQuery.getResultList();
        Validations validations = result.get(0);

        return validations;
    }
}
