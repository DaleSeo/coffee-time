package time.coffee.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import time.coffee.Application;
import time.coffee.domain.Member;

import java.util.List;

import static junit.framework.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = Application.class)
@Transactional
@Rollback
public class CoffeeAdminServiceTest {

	@Autowired
	CoffeeAdminService coffeeAdminService;

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


}