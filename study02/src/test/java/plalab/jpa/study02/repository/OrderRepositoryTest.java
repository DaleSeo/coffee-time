package plalab.jpa.study02.repository;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import plalab.jpa.study02.Study02Application;
import plalab.jpa.study02.domain.*;

import javax.transaction.Transactional;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Study02Application.class)
@Transactional
public class OrderRepositoryTest {

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    ItemRepository itemRepository;

    @Autowired
    OrderRepository orderRepository;

    @Test
    @Transactional
    public void orderItemTest() {
        Member member = new Member();
        member.setName("pizza");

        memberRepository.save(member);

        Item item1 = new Movie();
        item1.setName("Movie1");
        item1.setPrice(1000);
        item1.setStockQuantity(1);

        Item item2 = new Movie();
        item2.setName("Movie2");
        item2.setPrice(2000);
        item2.setStockQuantity(3);

        itemRepository.save(item1);
        itemRepository.save(item2);

        Order order = new Order();
        order.setStatus(OrderStatus.ORDER);
        order.setMember(member);

        List<OrderItem> orderItemList = new ArrayList<>();
        OrderItem orderItem1 = new OrderItem();
        orderItem1.setCount(1);
        orderItem1.setItem(item1);
        OrderItem orderItem2 = new OrderItem();
        orderItem2.setCount(1);
        orderItem2.setItem(item2);

        orderItemList.add(orderItem1);
        orderItemList.add(orderItem2);

        order.setOrderItems(orderItemList);

        //order.getOrderItems().size();

        orderRepository.save(order);

        List<Order> orderList = orderRepository.findAll();
        for(Order o : orderList){
            System.out.println(o.getId());
        }
    }
}