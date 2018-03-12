package Domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.GenerationType.IDENTITY;

@Entity(name = "Groups")
public class Group implements Serializable {
    @Id
    @Column(name = "gid", unique = true, nullable = false)
    @GeneratedValue(strategy = IDENTITY)
    private Long id;
    @Column(name = "name", nullable = false, unique = true)
    String groupname;
    @ManyToMany(cascade = {CascadeType.MERGE},fetch = FetchType.EAGER)
    @JoinTable(
            name = "Groups_Users",
            joinColumns = @JoinColumn(name = "gid"),
            inverseJoinColumns = @JoinColumn(name = "uid"))
    List<User> users;

    public Group() {
    }

    public Group(String groupname) {
        this.groupname = groupname;
        this.users = new ArrayList<User>();
    }

    public String getGroupname() {
        return groupname;
    }

    public Long getId() {
        return id;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setGroupname(String groupname) {
        this.groupname = groupname;
    }

    public void addUser(User user) {
        this.users.add(user);
    }
}
