package plalab.jpa.study02;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import plalab.jpa.study02.repository.DeliveryRepository;
import plalab.jpa.study02.repository.MemberRepository;
import plalab.jpa.study02.repository.OrderRepository;

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

}
