package gr.hua.dit.ds.DistributedSystems.DAOs;

import gr.hua.dit.ds.DistributedSystems.Entities.Authorities;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
public class AuthoritiesDAOImpl implements AuthoritiesDAO {
    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    public AuthoritiesDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<Authorities> getAllAuthorities() {
        Query theQuery = entityManager.createQuery("select A from Authorities AS A");
        List<Authorities> authorities = theQuery.getResultList();

        return authorities;
    }
    @Override
    public Authorities getAuthorityByUsername(String username) {
        Query theQuery = entityManager.createQuery("select A from Authorities AS A where A.username=:username");
        theQuery.setParameter("username", username);
        List<Authorities> result = theQuery.getResultList();
        Authorities authority = result.get(0);

        return authority;
    }

    @Override
    @Transactional
    public Authorities addAuthority(Authorities authority) {
        entityManager.persist(authority);
        entityManager.flush();

        return authority;
    }

    @Override
    @Transactional
    public Authorities modifyAuthority(String newUsername, String newAuth) {
        Authorities authority = getAuthorityByUsername(newUsername);
        authority.setAuthority(newAuth);

        return authority;
    }
}
