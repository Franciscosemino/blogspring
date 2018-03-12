package Domain;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "Users")
public class User implements Serializable {
    @Id
    @Column(name = "uid", unique = true, nullable = false)
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @Column(name = "uname",nullable = false,unique = true)
    String name;

    @Column(name = "email")
    String email;

    @ManyToMany(mappedBy = "users")
    List<Group> mygroups;

    public User() {
    }


    public User(String name, String email) {
        this.name = name;
        this.email = email;
        this.mygroups = new ArrayList<Group>();
    }

    public Long getId() {
        return id;
    }

    public List<Group> getMygroups() {
        return mygroups;
    }

    public String getEmail() {

        return email;
    }

    public String getName() {

        return name;
    }

    public void addGroup(Group group) {
        this.mygroups.add(group);
    }

    public List<Group> getMyGroups() {

        return mygroups;
    }

    public void setName(String name) {
        this.name = name;
    }


    public void setEmail(String email) {
        this.email = email;
    }

    public void setMygroups(List<Group> mygroups) {
        this.mygroups = mygroups;
    }
}