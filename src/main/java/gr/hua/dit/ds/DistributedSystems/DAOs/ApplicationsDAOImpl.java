package gr.hua.dit.ds.DistributedSystems.DAOs;

import gr.hua.dit.ds.DistributedSystems.Entities.Applications;
import gr.hua.dit.ds.DistributedSystems.Entities.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
public class ApplicationsDAOImpl implements ApplicationsDAO {
    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    public ApplicationsDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<Applications> getAllApplications() {
        Query theQuery = entityManager.createQuery("select A from Applications AS A");
        List<Applications> applications = theQuery.getResultList();

        return applications;
    }

    @Override
    public Applications getApplicationById(int id) {
        Query theQuery = entityManager.createQuery("select A from Applications AS A where A.id=:id");
        theQuery.setParameter("id", id);
        List<Applications> result = theQuery.getResultList();
        Applications application = result.get(0);

        return application;
    }

    @Override
    public List<Applications> getNewApplications() {
        Query theQuery = entityManager.createQuery("select A from Applications AS A where A.validated is null and A.confirmed is null");
        List<Applications> applications = theQuery.getResultList();

        return applications;
    }

    @Override
    public List<Applications> getValidatedApplications() {
        Query theQuery = entityManager.createQuery("select A from Applications AS A where A.validated='true' and A.confirmed is null");
        List<Applications> applications = theQuery.getResultList();

        return applications;
    }

    @Override
    public Boolean applicationExists(int id) {
        Query theQuery = entityManager.createQuery("select A from Applications AS A where A.id=:id");
        theQuery.setParameter("id", id);
        List<Users> result = theQuery.getResultList();

        return !result.isEmpty();
    }


}
