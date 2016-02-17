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
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@Transactional
@Rollback
public class CoffeeServiceTest {

	@Autowired
	private CoffeeService service;

	@Before
	public void setup() {
		assertNotNull(service);
	}

	@Test
	public void testFindMembers() {
		//Given
		setUpData();

		//When
		List<Member> members = service.findMembers();

		//Then
		assertFalse(members.isEmpty());
		assertEquals(members.size(), 2);
	}

	@Test
	public void testFindMember() {
		//Given
		setUpData();

		//When
		Member member = service.findMemberByEmpNo("2");

		//Then
		assertEquals(member.getName(), "Heemin");

	}

	@Test
	public void testDeleteMemberByEmpno() {
		//Given
		setUpData();

		//When
		service.deleteMemberByEmpNo("1");

		List<Member> members = service.findMembers();

		//Then
		assertEquals(members.size(), 1);


	}

	private void setUpData() {
		Member member1 = new Member("1", "Daeyoung");
		Member member2 = new Member("2", "Heemin");

		service.addMember(member1);
		service.addMember(member2);
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

		Shop addedShop = service.addShop(shop);
		Menu menu = service.addMenu(addedShop.getId(), "default menu", "default menu desc");

		Survey survey = service.addSurvey(addedShop.getId(), new Date(), "description", menu.getId());

		Member member = new Member("empNo1", "member1");

		Order order = new Order();
		order.setMenu(menu);
		order.setMember(member);
		order.setMessage("message1");

		service.addMember(member);

		service.addOrder(order);

		List<Order> orders = service.findOrders(survey.getId());

		assertNotNull(orders);

	}


}