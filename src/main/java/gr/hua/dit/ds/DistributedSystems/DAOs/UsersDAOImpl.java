package gr.hua.dit.ds.DistributedSystems.DAOs;

import gr.hua.dit.ds.DistributedSystems.Entities.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
public class UsersDAOImpl implements UsersDAO {
    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    public UsersDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<Users> getAllUsers() {
        Query theQuery = entityManager.createQuery("select U from Users AS U");
        List<Users> users = theQuery.getResultList();

        return users;
    }

    @Override
    public Users getUserById(int id) {
        Query theQuery = entityManager.createQuery("select U from Users AS U where U.id=:id");
        theQuery.setParameter("id", id);
        List<Users> result = theQuery.getResultList();
        Users user = result.get(0);

        return user;
    }

    @Override
    public Users getUserByUsername(String username) {
        Query theQuery = entityManager.createQuery("select U from Users AS U where U.username=:username");
        theQuery.setParameter("username", username);
        List<Users> result = theQuery.getResultList();
        Users user = result.get(0);

        return user;
    }

    @Override
    public Users getUserByEmail(String email) {
        Query theQuery = entityManager.createQuery("select U from Users AS U where U.email=:email");
        theQuery.setParameter("email", email);
        List<Users> result = theQuery.getResultList();
        Users user = result.get(0);

        return user;
    }

    @Override
    public Boolean usernameExists(String username) {
        Query theQuery = entityManager.createQuery("select U from Users AS U where U.username=:username");
        theQuery.setParameter("username", username);
        List<Users> result = theQuery.getResultList();

        return !result.isEmpty();
    }

    @Override
    public Boolean emailExists(String email) {
        Query theQuery = entityManager.createQuery("select U from Users AS U where U.email=:email");
        theQuery.setParameter("email", email);
        List<Users> result = theQuery.getResultList();

        return !result.isEmpty();
    }

    @Override
    @Transactional
    public Users signIn(Users user) {
        entityManager.persist(user);
        entityManager.flush();

        return user;
    }

}

