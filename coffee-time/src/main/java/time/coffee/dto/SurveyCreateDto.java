package time.coffee.dto;


import java.util.Date;

public class SurveyCreateDto {

    private Long defaultMenuId;

    private Long shopId;

    private Date deadline;

    private String description;

    public Long getDefaultMenuId() {
        return defaultMenuId;
    }

    public void setDefaultMenuId(Long defaultMenuId) {
        this.defaultMenuId = defaultMenuId;
    }

    public Long getShopId() {
        return shopId;
    }

    public void setShopId(Long shopId) {
        this.shopId = shopId;
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
