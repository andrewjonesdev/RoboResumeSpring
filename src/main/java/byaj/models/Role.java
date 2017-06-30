package byaj.models;


import javax.persistence.*;
import java.util.Set;

/**
 * Created by student on 6/28/17.
 */
@Entity
public class Role {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int id;

    private String role;

    @ManyToMany(mappedBy = "roles", fetch = FetchType.LAZY)
    private Set<User> users;

    public int getID(){
        return id;
    };

    public String getRole(){
        return role;
    }

    public void setRole(String role){
        this.role = role;
    }

    public Set<User> getUsers(){
        return users;
    }

    public void setUsers(Set<User> users){
        this.users = users;
    }
}
