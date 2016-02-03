package time.coffee.repository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import time.coffee.Application;

import static org.hibernate.validator.internal.util.Contracts.assertNotNull;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = Application.class)
public class MenuRepositoryTest {

	@Autowired
	private MenuRepository repository;

	@Test
	public void test() {
		assertNotNull(repository);
	}

}