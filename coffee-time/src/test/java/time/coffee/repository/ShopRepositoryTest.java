package time.coffee.repository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import time.coffee.Application;
import time.coffee.domain.Shop;

import static org.hamcrest.Matchers.is;
import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.junit.Assert.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = Application.class)
public class ShopRepositoryTest {

	@Autowired
	private ShopRepository repository;

	@Test
	public void test() {
		assertNotNull(repository);
	}

	@Test
	public void testAddShop() {
		Shop shop = new Shop();
		shop.setName("testShop");

		repository.save(shop);
		Shop foundShop = repository.findOne(shop.getId());

		assertThat(foundShop.getName(), is(shop.getName()));
	}
}