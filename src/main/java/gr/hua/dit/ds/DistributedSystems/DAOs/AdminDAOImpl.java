package gr.hua.dit.ds.DistributedSystems.DAOs;

import gr.hua.dit.ds.DistributedSystems.Entities.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class AdminDAOImpl implements AdminDAO {
    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    public AdminDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Autowired
    private UsersDAO usersDAO;

    @Override
    public Users createUser(Users user) {
        usersDAO.signIn(user);

        return user;
    }

    @Override
    @Transactional
    public Users modifyUser(int id, String newUsername, String newPassword, String newEmail, String newAuth) {
        Users user = usersDAO.getUserById(id);
        user.setUsername(newUsername);
        user.setPassword(newPassword);
        user.setEmail(newEmail);

        return user;
    }

    @Override
    @Transactional
    public Users deleteUser(Users user) {
        entityManager.remove(user);
        entityManager.flush();

        return user;
    }
}
