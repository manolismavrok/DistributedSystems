package gr.hua.dit.ds.DistributedSystems.DAOs;

import gr.hua.dit.ds.DistributedSystems.Entities.Users;

public interface AdminDAO {
    Users createUser(Users user);
    Users modifyUser(String username, String newUsername, String newPassword, String newEmail, String newAuth) throws Exception;
    Users deleteUser(Users user);
}
