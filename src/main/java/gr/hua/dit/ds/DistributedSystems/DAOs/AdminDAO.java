package gr.hua.dit.ds.DistributedSystems.DAOs;

import gr.hua.dit.ds.DistributedSystems.Entities.Users;

public interface AdminDAO {
    Users createUser(Users user);
    Users modifyUser(int id, String newUsername, String newPassword, String newEmail, String newTitle);
    Users deleteUser(Users user);
}
