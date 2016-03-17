package time.coffee.dto;


import java.util.Date;

public class SurveyDto {

    private Long id;

//    private MenuDto defaultMenu;

    private ShopDto shop;

    private Date deadline;

    private String description;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

//    public Menu getDefaultMenu() {
//        return defaultMenu;
//    }
//
//    public void setDefaultMenu(Menu defaultMenu) {
//        this.defaultMenu = defaultMenu;
//    }


    public ShopDto getShop() {
        return shop;
    }

    public void setShop(ShopDto shop) {
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
