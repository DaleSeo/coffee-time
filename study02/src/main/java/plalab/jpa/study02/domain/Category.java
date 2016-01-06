package plalab.jpa.study02.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Updated on : 2015-12-23. Updated by : 양해엽, SK Planet.
 */
@Entity
@Table(name = "CATEGORY")
public class Category {
    @Id
    @GeneratedValue
    @Column(name = "CATEGORY_ID")
    private Long id;
    private String name;

    public Category() {
    }

    public Category(String name) {
        this.name = name;
    }

    @ManyToMany(mappedBy = "categories")
    private List<Item> items = new ArrayList<>();

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

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    @Override
    public String toString() {
        return "Category{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", items=" + items +
                '}';
    }
}
