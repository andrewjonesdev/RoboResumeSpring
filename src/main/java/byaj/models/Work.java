package byaj.models;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Created by student on 6/27/17.
 */
@Entity
public class Work {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    //@NotNull
    //@Min(1)
    private int workID;

    private String workTitle;

    private String workEmployer;

    private int workStartMonth;

    private int workEndMonth;

    private int workStartYear;

    private int workEndYear;
    
   @Lob
    private String workDuties;
    
    @Column(columnDefinition="integer default -1")
    private int workRes;



    public int getWorkID() {
        return workID;
    }

    /*public void setMateID(int workID) {
        this.workID = workID;
    }*/
    public String getWorkTitle() {
        return workTitle;
    }

    public void setWorkTitle (String workTitle) {
        this.workTitle = workTitle;
    }

    public String getWorkEmployer() {
        return workEmployer;
    }

    public void setWorkEmployer (String workEmployer) {
        this.workEmployer = workEmployer;
    }

    public int getWorkStartMonth() {
        return workStartMonth;
    }
    public String getWorkStringStartMonth() {
        return numToMonth(workStartMonth);
    }

    public void setWorkStartMonth (int workStartMonth) {
        this.workStartMonth = workStartMonth;
    }
    
    public int getWorkEndMonth() {
        return workEndMonth;
    }

    public String getWorkStringEndMonth() {
        return numToMonth(workEndMonth);
    }

    public void setWorkEndMonth (int workEndMonth) {
        this.workEndMonth = workEndMonth;
    }
    
    public int getWorkStartYear() {
        return workStartYear;
    }

    public void setWorkStartYear (int workStartYear) {
        this.workStartYear = workStartYear;
    }
    
    public int getWorkEndYear() {
        return workEndYear;
    }

    public void setWorkEndYear (int workEndYear) {
        this.workEndYear = workEndYear;
    }

    public String getWorkDuties() {
        return workDuties;
    }

    public void setWorkDuties (String workDuties) {
        this.workDuties = workDuties;
    }

    public int getWorkRes() {
        return workRes;
    }

    public void setWorkRes (int workRes) {
        this.workRes = workRes;
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
