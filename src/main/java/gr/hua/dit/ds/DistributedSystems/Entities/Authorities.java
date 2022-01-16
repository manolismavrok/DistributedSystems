package gr.hua.dit.ds.DistributedSystems.Entities;

import javax.persistence.*;

@Entity
@Table(name = "authorities", indexes = {
        @Index(name = "uk_authorities_usr_auth", columnList = "name, authority", unique = true)
})
public class Authorities {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name", nullable = false, length = 50)
    private String name;

    @Column(name = "authority", nullable = false, length = 20)
    private String authority;

    public Authorities() {

    }
    public Authorities(String name, String authority) {
        super();
        this.name = name;
        this.authority = authority;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }

    @Override
    public String toString() {
        return "Authorities{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", authority='" + authority + '\'' +
               // ", user=" + user +
                '}';
    }
}
