package plalab.jpa.study02.domain;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Order {
    @Id
    @GeneratedValue
    @Column(name = "ORDER_ID")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Member member;

    @OneToOne(fetch = FetchType.LAZY)
    private Delivery delivery;

    @Temporal(value = TemporalType.TIMESTAMP)
    @Column(name = "ORDERDATE")
    private Date orderDate;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;

}
