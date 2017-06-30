package byaj.models;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Created by student on 6/27/17.
 */
@Entity
public class Skill {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    //@NotNull
    //@Min(1)
    private int skillID;

    private String skillName;

    private String skillRating;
    @Column(columnDefinition="integer default -1")
    private int skillRes;


    public int getSkillID() {
        return skillID;
    }

    /*public void setMateID(int skillID) {
        this.skillID = skillID;
    }*/
    public String getSkillName() {
        return skillName;
    }

    public void setSkillName (String skillName) {
        this.skillName = skillName;
    }

    public String getSkillRating() {
        return skillRating;
    }

    public void setSkillRating (String skillRating) {
        this.skillRating = skillRating;
    }


    public int getSkillRes() {
        return skillRes;
    }

    public void setSkillRes (int skillRes) {
        this.skillRes = skillRes;
    }
}
