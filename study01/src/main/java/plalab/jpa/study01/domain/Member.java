package plalab.jpa.study01.domain;

import javax.persistence.*;
import java.util.List;

@Entity
public class Member {

    @Id @GeneratedValue
    Long id;
    String name;
    String city;
    String street;
    String zipcode;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "member")
    List<Order> orders;

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

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }
}
