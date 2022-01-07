package gr.hua.dit.ds.DistributedSystems.DAOs;

import gr.hua.dit.ds.DistributedSystems.Entities.Authorities;

import java.util.List;

public interface AuthoritiesDAO {
    List<Authorities> getAllAuthorities();
    Authorities getAuthorityByUsername(String username);
    Authorities addAuthority(Authorities authority);
    Authorities modifyAuthority(String newUsername, String newAuth);
}
