package byaj;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Created by student on 6/27/17.
 */
public class Education {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    //@NotNull
    //@Min(1)
    private int eduID;
    @NotNull
    @Size(min=2, max=100)
    private String eduCourseOfStudy = "  ";
    @NotNull
    @Size(min=2, max=100)
    private String eduDegree = "  ";
    @NotNull
    @Size(min=2, max=100)
    private String eduSchool = "  ";
    @NotNull
    @Min(1)
    private int eduGradYear = 1;
    @NotNull
    @Min(1)
    private int eduRes = 1;
    


    public int getEduID() {
        return eduID;
    }

    /*public void setMateID(int eduID) {
        this.eduID = eduID;
    }*/
    public String getEduCourseOfStudy() {
        return eduCourseOfStudy;
    }

    public void setEduCourseOfStudy (String eduCourseOfStudy) {
        this.eduCourseOfStudy = eduCourseOfStudy;
    }

    public String getEduDegree() {
        return eduDegree;
    }

    public void setEduDegree (String eduDegree) {
        this.eduDegree = eduDegree;
    }


    public String getEduSchool() {
        return eduSchool;
    }

    public void setEduSchool (String eduSchool) {
        this.eduSchool = eduSchool;
    }

    public int getEduGradYear() {
        return eduGradYear;
    }

    public void setEduGradYear (int eduGradYear) {
        this.eduGradYear = eduGradYear;
    }

    public int getEduRes() {
        return eduRes;
    }

    public void setEduRes (int eduRes) {
        this.eduRes = eduRes;
    }
}
