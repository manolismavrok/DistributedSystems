package gr.hua.dit.ds.DistributedSystems.Controllers;

import gr.hua.dit.ds.DistributedSystems.DAOs.AuthoritiesDAO;
import gr.hua.dit.ds.DistributedSystems.Entities.Authorities;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AuthoritiesController {
    @Autowired
    private AuthoritiesDAO authoritiesDAO;

    @GetMapping("/api/authorities/getAllAuthorities")
    public List<Authorities> getAllAuthorities() {
        List<Authorities> authorities = authoritiesDAO.getAllAuthorities();

        return authorities;
    }

    @GetMapping("/api/authorities/getAuthoritiesByUsername")
    public Authorities getAuthoritiesByUsername(@RequestParam String username) {
        Authorities authority = authoritiesDAO.getAuthorityByUsername(username);

        return authority;
    }
}
