package plalab.jpa.study02.domain;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Entity
@DiscriminatorValue("P")
public class Pizza extends Food {

    @Enumerated(EnumType.STRING)
    private FoodSize size;

    public FoodSize getSize() {
        return size;
    }

    public void setSize(FoodSize size) {
        this.size = size;
    }
}
