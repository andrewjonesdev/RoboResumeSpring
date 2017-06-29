package byaj.models;

/**
 * Created by student on 6/20/17.
 */

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;

/*create table Acc(

    accDate date,
    accBalance varChar (255),
    accNumber varChar (255),
    accUrl double,
    accID integer auto_increment,
    primary key (accID)
);*/
@Entity
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    //@NotNull
    //@Min(1)
    private int accID;
    @NotNull
    @Size(min=2, max=100)
    private String accNumber = "1000";
    @NotNull
    @Min(-1)
    private int accRes = -1;

    public int getAccID() {
        return accID;
    }

    /*public void setMateID(int accID) {
        this.accID = accID;
    }*/
    public String getAccNumber() {
        return accNumber;
    }

    public void setAccNumber (String accNumber) {
        this.accNumber = accNumber;
    }

    public int getAccRes() {
        return accRes;
    }

    public void setAccRes (int accRes) {
        this.accRes = accRes;
    }


}