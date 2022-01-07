package gr.hua.dit.ds.DistributedSystems.DAOs;

import gr.hua.dit.ds.DistributedSystems.Entities.Users;

import java.util.List;

public interface UsersDAO {
    List<Users> getAllUsers();
    Users getUserById(int id);
    Users getUserByUsername(String username);
    Users getUserByEmail(String email);
    Users signIn(Users user);
}
