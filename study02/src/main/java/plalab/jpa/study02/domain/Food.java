package plalab.jpa.study02.domain;

import javax.persistence.*;
import java.util.Date;

@Entity
@DiscriminatorValue("F")
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "DTYPE")
public class Food extends Item {

    //유통기한
    Date expiryDate;

    Integer calorie;

    public Date getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(Date expiryDate) {
        this.expiryDate = expiryDate;
    }

    public Integer getCalorie() {
        return calorie;
    }

    public void setCalorie(Integer calorie) {
        this.calorie = calorie;
    }
}
