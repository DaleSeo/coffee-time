package plalab.jpa.study02.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Updated on : 2015-12-23. Updated by : 양해엽, SK Planet.
 */
@Entity
@Table(name="ITEM")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "DTYPE")
public abstract class Item {

    @Column (name="ITEM_ID") @Id @GeneratedValue
    private Long id;

    private String name;

    private int price;

    private int stockQuantity;

    @ManyToMany
    @JoinTable(name = "CATEGORY_ITEM",
            joinColumns = @JoinColumn(name = "ITEM_ID"),
            inverseJoinColumns = @JoinColumn(name = "CATEGORY_ID"))
    private List<Category> categories = new ArrayList<>();

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

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getStockQuantity() {
        return stockQuantity;
    }

    public void setStockQuantity(int stockQuantity) {
        this.stockQuantity = stockQuantity;
    }

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

    @Override
    public String toString() {
        return "Item{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", stockQuantity=" + stockQuantity +
                ", categories=" + categories +
                '}';
    }
}
