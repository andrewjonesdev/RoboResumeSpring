package byaj.models;


import byaj.models.Role;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by student on 6/28/17.
 */
@Entity
@Table(name = "userData")
public class User {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int id;

    private String username;

    private String password;

    private boolean enabled = true;

    @Column(columnDefinition="integer default -1")
    private int userResume;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(joinColumns = @JoinColumn(name = "user_id"),inverseJoinColumns =
    @JoinColumn(name="role_id"))
    private Set<Role> roles;

    public int getID(){
        return id;
    };

    public String getUsername(){
        return username;
    }

    public void setUsername(String username){
        this.username = username;
    }

    public String getPassword(){
        return password;
    }

    public void setPassword(String password){
        this.password = password;
    }

    public boolean getEnabled(){
        return enabled;
    }

    public void setEnabled(boolean enabled){
        this.enabled = enabled;
    }

    public Set<Role> getRoles(){
        return roles;
    }

    public void setRoles(Set<Role> roles){
        this.roles = roles;
    }
    
    public int getUserResume() {
        return userResume;
    }

    public void setUserResume (int userResume) {
        this.userResume = userResume;
    }
}