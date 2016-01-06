/*
* Copyright (c) 2013 SK planet.
* All right reserved.
*
* This software is the confidential and proprietary information of SK planet.
* You shall not disclose such Confidential Information and
* shall use it only in accordance with the terms of the license agreement
* you entered into with SK planet.
*/
package plalab.jpa.study02.domain;

import javax.persistence.*;

/**
* <p>
* DESC
* </p>
* Updated on : 2015. 12. 30 Updated by : 정희원, SK 플래닛.
*/
@Entity(name="ORDER_ITEM")
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "ITEM_ID")
    private Item item;

    @ManyToOne
    @JoinColumn(name = "ORDER_ID")
    private Order order;

    private int orderPrice;

    private int count;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public int getOrderPrice() {
        return orderPrice;
    }

    public void setOrderPrice(int orderPrice) {
        this.orderPrice = orderPrice;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
