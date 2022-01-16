package gr.hua.dit.ds.DistributedSystems.DAOs;

import gr.hua.dit.ds.DistributedSystems.Entities.Authorities;

import java.util.List;

public interface AuthoritiesDAO {
    List<Authorities> getAllAuthorities();
    Authorities getAuthorityById(int id);
    Authorities getAuthorityByUsername(String username);
    Authorities addAuthority(Authorities authority);
    Authorities deleteAuthority(Authorities authority);
    Authorities modifyAuthority(int id, String newUsername, String newAuth);
}
