package byaj.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Created by student on 6/27/17.
 */
@Entity
public class Duty {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    //@NotNull
    //@Min(1)
    private int dutyID;
    @NotNull
    @Size(min=2, max=100)
    private String dutyTitle = "  ";
    @NotNull
    @Min(1)
    private int dutyWork = 1;


    public int getDutyID() {
        return dutyID;
    }

    /*public void setMateID(int dutyID) {
        this.dutyID = dutyID;
    }*/
    public String getDutyTitle() {
        return dutyTitle;
    }

    public void setDutyTitle (String dutyTitle) {
        this.dutyTitle = dutyTitle;
    }

    public int getDutyWork() {
        return dutyWork;
    }

    public void setDutyWork (int dutyWork) {
        this.dutyWork = dutyWork;
    }
}
