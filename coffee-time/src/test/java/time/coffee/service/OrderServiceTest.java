package time.coffee.service;

import junit.framework.Assert;
import junit.framework.TestCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import time.coffee.Application;
import time.coffee.domain.Member;
import time.coffee.domain.Menu;
import time.coffee.domain.Shop;
import time.coffee.domain.Survey;
import time.coffee.repository.MemberRepository;
import time.coffee.repository.MenuRepository;
import time.coffee.repository.ShopRepository;
import time.coffee.repository.SurveyRepository;

import java.util.Date;

/**
 * Updated on : 2016-03-10. Updated by : 양해엽, SK Planet.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
public class OrderServiceTest extends TestCase {

    @Autowired
    private OrderService orderService;

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private SurveyRepository surveyRepository;

    @Autowired
    private MenuRepository menuRepository;

    @Autowired
    private ShopRepository shopRepository;

    @Test
    public void testPlaceOrder() {
        Shop shop = new Shop();
        shop.setName("스파커피");
        shopRepository.save(shop);

        Menu menu = new Menu();
        menu.setName("아메리카노");
        menu.setShop(shop);
        menuRepository.save(menu);

        Member member = new Member();
        member.setEmpNo("9230001");
        member.setName("양해엽");
        memberRepository.save(member);

        Survey survey = new Survey();
        survey.setShop(shop);
        survey.setDeadline(new Date());
        surveyRepository.save(survey);

        Long orderId = orderService.addOrder("9230001", survey.getId(), menu.getId(), "감삼다");
        assertNotNull(orderId);
    }

}