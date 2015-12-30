package plalab.jpa.study02.domain;

import javax.persistence.*;

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

//    @ManyToMany(mappedBy = "categories")
//    @ManyToMany
//    @JoinTable(name="CATEGORY_ITEM",
//        joinColumns = @JoinColumn(name="CATEGORY_ID"),
//        inverseJoinColumns = @JoinColumn(name="ITEM_ID")
//    )
 //   private List<Item> items = new ArrayList<>();


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

//    public List<Item> getItems() {
//        return items;
//    }
//
//    public void setItems(List<Item> items) {
//        this.items = items;
//    }

//    public List<Category> getChild() {
//        return child;
//    }
//
//    public void setChild(List<Category> child) {
//        this.child = child;
//    }
}
