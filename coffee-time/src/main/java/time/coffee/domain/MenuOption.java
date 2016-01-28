package time.coffee.domain;

import javax.persistence.*;

// Todo 추가논의 필요, option을 가격으로 옮길지...
@Entity
public class MenuOption {
    @Id
    @GeneratedValue
    private Long id;

    private String name;

    private Integer price;

    @ManyToOne(fetch = FetchType.LAZY)
    private Menu menu;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Menu getMenu() {
        return menu;
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
    }
}
