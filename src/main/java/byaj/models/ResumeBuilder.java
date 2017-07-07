package byaj.models;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Created by student on 6/27/17.
 */
@Entity
public class ResumeBuilder {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    //@NotNull
    //@Min(1)
    private int rbID;

    private String rbValue;

    @Column(columnDefinition="integer default -1")
    private int rbRes;


    public int getRbID() {
        return rbID;
    }

    /*public void setMateID(int rbID) {
        this.rbID = rbID;
    }*/
    public String getRbValue() {
        return rbValue;
    }

    public void setRbValue (String rbValue) {
        this.rbValue = rbValue;
    }
    
    public int getRbRes() {
        return rbRes;
    }

    public void setRbRes (int rbRes) {
        this.rbRes = rbRes;
    }
}
