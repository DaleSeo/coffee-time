package plalab.jpa.study01.repository;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import plalab.jpa.study01.Study01Application;

import static junit.framework.TestCase.assertNotNull;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Study01Application.class)
public class OrderRepositoryTest {

    @Autowired
    OrderRepository orderRepository;

    @Before
    public void setup() {
        assertNotNull(orderRepository);
    }

    @Test
    public void test() {

    }

}
