package byaj.models;

import org.hibernate.annotations.Type;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Created by student on 6/27/17.
 */
@Entity
public class Job {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    //@NotNull
    //@Min(1)
    private int jobID;

    private String jobTitle;

    private String jobEmployer;

    private int jobStartMonth;

    private float jobMinSalary;

    private int jobStartYear;

    private float jobMaxSalary;

    @Lob
    @Type(type = "text")
    private String jobDuties;

    @Column(columnDefinition="integer default -1")
    private int jobRes;



    public int getJobID() {
        return jobID;
    }

    /*public void setMateID(int jobID) {
        this.jobID = jobID;
    }*/
    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle (String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public String getJobEmployer() {
        return jobEmployer;
    }

    public void setJobEmployer (String jobEmployer) {
        this.jobEmployer = jobEmployer;
    }

    public int getJobStartMonth() {
        return jobStartMonth;
    }
    public String getJobStringStartMonth() {
        return numToMonth(jobStartMonth);
    }

    public void setJobStartMonth (int jobStartMonth) {
        this.jobStartMonth = jobStartMonth;
    }

    public float getJobMinSalary() {
        return jobMinSalary;
    }

    public void setJobMinSalary (float jobMinSalary) {
        this.jobMinSalary = jobMinSalary;
    }

    public int getJobStartYear() {
        return jobStartYear;
    }

    public void setJobStartYear (int jobStartYear) {
        this.jobStartYear = jobStartYear;
    }

    public float getJobMaxSalary() {
        return jobMaxSalary;
    }

    public void setJobMaxSalary (float jobMaxSalary) {
        this.jobMaxSalary = jobMaxSalary;
    }

    public String getJobDuties() {
        return jobDuties;
    }

    public void setJobDuties (String jobDuties) {
        this.jobDuties = jobDuties;
    }

    public int getJobRes() {
        return jobRes;
    }

    public void setJobRes (int jobRes) {
        this.jobRes = jobRes;
    }

    private static String numToMonth(int month){
        String output = "";
        if(month>0&&month<13){
            switch(month){
                case 1:
                    output = "January";
                    break;
                case 2:
                    output = "February";
                    break;
                case 3:
                    output = "March";
                    break;
                case 4:
                    output = "April";
                    break;
                case 5:
                    output = "May";
                    break;
                case 6:
                    output = "June";
                    break;
                case 7:
                    output = "July";
                    break;
                case 8:
                    output = "August";
                    break;
                case 9:
                    output = "September";
                    break;
                case 10:
                    output = "October";
                    break;
                case 11:
                    output = "November";
                    break;
                case 12:
                    output = "December";
                    break;
            }
        }
        else{
            output = "Invalid Month";
        }
        return output;
    }
    private static String numToMonthAbr(int month){
        if(month>0&&month<13){
            return	numToMonth(month).substring(0,3);
        }
        else return numToMonth(month);
    }
}
