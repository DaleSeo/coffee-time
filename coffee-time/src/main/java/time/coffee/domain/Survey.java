package time.coffee.domain;


import javax.persistence.*;
import java.util.Date;

@Entity
public class Survey {

    @Id @GeneratedValue
    private Long id;

    @ManyToOne (fetch = FetchType.LAZY)
    private Menu defaultMenu;

    @ManyToOne (fetch = FetchType.LAZY)
    private Shop shop;

    private Date deadline;

    private String description;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Menu getDefaultMenu() {
        return defaultMenu;
    }

    public void setDefaultMenu(Menu defaultMenu) {
        this.defaultMenu = defaultMenu;
    }

    public Shop getShop() {
        return shop;
    }

    public void setShop(Shop shop) {
        this.shop = shop;
    }

    public Date getDeadline() {
        return deadline;
    }

    public void setDeadline(Date deadline) {
        this.deadline = deadline;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isAvailable() {
        // TODO 현재 시간관 비교해서 유효한지 판단해야 함
        return true;
    }
}
