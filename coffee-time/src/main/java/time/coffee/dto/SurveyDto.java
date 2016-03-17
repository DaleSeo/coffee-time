package time.coffee.dto;


import time.coffee.domain.Menu;
import time.coffee.domain.Shop;

import java.util.Date;

public class SurveyDto {

    private Long id;

    private Menu defaultMenu;

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
}
