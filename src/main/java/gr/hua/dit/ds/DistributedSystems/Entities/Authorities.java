package gr.hua.dit.ds.DistributedSystems.Entities;

import javax.persistence.*;

@Entity
@Table(name = "authorities")
public class Authorities {

    @Id
    @JoinColumn(name = "username", nullable = false)
    private String username;

    @Column(name = "authority", nullable = false, length = 20)
    private String authority;

    public Authorities() {

    }
    public Authorities(String username, String authority) {
        this.username = username;
        this.authority = authority;
    }

    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
