package time.coffee.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import time.coffee.Application;
import time.coffee.domain.*;

import java.util.Date;
import java.util.List;

import static junit.framework.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@Transactional
@Rollback
public class CoffeeAdminServiceTest {
	@Autowired
	private CoffeeService coffeeAdminService;

	@Before
	public void setup() {
		assertNotNull(coffeeAdminService);
	}

	@Test
	public void addAndFindMember() {
		//Given
		String empNo = "1234";
		String name = "test";
		Member member = new Member(empNo, name);

		//When
		Long memberId = coffeeAdminService.addMember(member);
		List<Member> members = coffeeAdminService.findMembers();

		//Then
		assertNotNull(memberId);
		assertEquals(members.size(), 1);
	}

	@Test
	public void addAndFindShop() {
		//Given
		//FIXME:
		//When
//		Member member = coffeeAdminService.addShop(shop);
//		List<Member> members = coffeeAdminService.findMembers();
//
//		//Then
//		assertNotNull(member);
//		assertEquals(members.size(), 1);
	}

	@Test
	public void findOrders() {
		Shop shop = new Shop();
		shop.setName("shop");

		Shop addedShop = coffeeAdminService.addShop(shop);
		Menu menu = coffeeAdminService.addMenu(addedShop.getId(), "default menu", "default menu desc");

		Survey survey = coffeeAdminService.addSurvey(addedShop.getId(), new Date(), "description", menu.getId());

		Member member = new Member("empNo1", "member1");

		Order order = new Order();
		order.setMenu(menu);
		order.setMember(member);
		order.setMessage("message1");

		coffeeAdminService.addMember(member);

		coffeeAdminService.addOrder(order);

		List<Order> orders = coffeeAdminService.findOrders(survey.getId());

		assertNotNull(orders);

	}


}