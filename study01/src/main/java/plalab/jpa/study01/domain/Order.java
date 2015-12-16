package plalab.jpa.study01.domain;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity(name = "ORDERS")
public class Order {

    @Id
    @GeneratedValue
    @Column(name = "ORDER_ID")
    Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    Member member;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "order")
    List<OrderItem> orderItems;

    @Temporal(value = TemporalType.TIMESTAMP)
    @Column(name = "ORDERDATE")
    Date orderDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "STATUS")
    OrderStatus orderStatus;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }
}
