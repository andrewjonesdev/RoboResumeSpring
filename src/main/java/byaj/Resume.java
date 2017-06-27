package byaj;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Created by student on 6/27/17.
 */
public class Resume {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    //@NotNull
    //@Min(1)
    private int resID;
    @NotNull
    @Size(min=2, max=100)
    private String resFirstName = "  ";
    @NotNull
    @Size(min=2, max=100)
    private String resLastName = "  ";
    @NotNull
    @Size(min=2, max=100)
    private String resEmail = "  ";
    

    public int getResID() {
        return resID;
    }

    /*public void setMateID(int resID) {
        this.resID = resID;
    }*/
    public String getResFirstName() {
        return resFirstName;
    }

    public void setResFirstName (String resFirstName) {
        this.resFirstName = resFirstName;
    }

    public String getResLastName() {
        return resLastName;
    }

    public void setResLastName (String resLastName) {
        this.resLastName = resLastName;
    }


    public String getResEmail() {
        return resEmail;
    }

    public void setResEmail (String resEmail) {
        this.resEmail = resEmail;
    }
}
