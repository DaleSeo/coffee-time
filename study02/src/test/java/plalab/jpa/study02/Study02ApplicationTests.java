package plalab.jpa.study02;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import plalab.jpa.study02.domain.Delivery;
import plalab.jpa.study02.domain.Member;
import plalab.jpa.study02.domain.Order;
import plalab.jpa.study02.repository.DeliveryRepository;
import plalab.jpa.study02.repository.MemberRepository;
import plalab.jpa.study02.repository.OrderRepository;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Study02Application.class)
@WebAppConfiguration
public class Study02ApplicationTests {
    @Autowired
    MemberRepository memberRepository;

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    DeliveryRepository deliveryRepository;

	@Test
	public void contextLoads() {
	}

    @Test
    @Transactional
    public void simpleTest() {
        String name = "Heemin";
        Member member = new Member();
        member.setName(name);
        memberRepository.save(member);
        Member newMember = memberRepository.findOne(member.getId());
        assert(name.equals(newMember.getName()));
        System.out.println(newMember.getName());
    }

    @Test
    @Transactional
    public void orderRelationTest() {
        Member member = new Member();
        member.setName("Heemin");
        memberRepository.save(member);
        Order order = new Order();
        order.setMember(member);
        orderRepository.save(order);
        List<Member> memberList = memberRepository.findAll();
        assert (memberList.size() == 1);
    }

    @Test
    @Transactional
    public void deliveryTest() {
        Delivery delivery = new Delivery();
        delivery.setCity("City");
        deliveryRepository.save(delivery);
    }


}
