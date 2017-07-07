package byaj.models;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Created by student on 6/27/17.
 */
@Entity
public class JobBuilder {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    //@NotNull
    //@Min(1)
    private int jbID;

    private String jbValue;

    @Column(columnDefinition="integer default -1")
    private int jbRes;


    public int getJbID() {
        return jbID;
    }

    /*public void setMateID(int jbID) {
        this.jbID = jbID;
    }*/
    public String getJbValue() {
        return jbValue;
    }

    public void setJbValue (String jbValue) {
        this.jbValue = jbValue;
    }

    public int getJbRes() {
        return jbRes;
    }

    public void setJbRes (int jbRes) {
        this.jbRes = jbRes;
    }
}
