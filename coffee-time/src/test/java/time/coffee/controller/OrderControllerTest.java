package time.coffee.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import time.coffee.Application;
import time.coffee.domain.Member;
import time.coffee.domain.Menu;
import time.coffee.domain.Shop;
import time.coffee.domain.Survey;
import time.coffee.dto.OrderDto;
import time.coffee.repository.MemberRepository;
import time.coffee.repository.MenuRepository;
import time.coffee.repository.ShopRepository;
import time.coffee.repository.SurveyRepository;
import time.coffee.service.OrderService;

import javax.transaction.Transactional;

import java.util.Date;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Updated on : 2016-03-10. Updated by : 양해엽, SK Planet.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
public class OrderControllerTest {

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext wac;

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private SurveyRepository surveyRepository;

    @Autowired
    private MenuRepository menuRepository;

    @Autowired
    private ShopRepository shopRepository;

    private ObjectMapper objectMapper = new ObjectMapper();

    @Before
    public void setUp() throws Exception {
        mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }

    private void makeTestData() {
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
    }

    @Test
//    @Transactional
    public void testPlaceOrder() throws Exception {
        makeTestData();

        OrderDto req = new OrderDto();
        req.setEmpNo("9230001");
        req.setMenuId(1L);
        req.setSurveyId(1L);
        req.setMessage("감삼다");

        mockMvc.perform(post("/orders")
                        .content(objectMapper.writeValueAsString(req))
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .accept(MediaType.APPLICATION_JSON_UTF8)
        )
                .andDo(print())
                .andExpect(status().isOk());
    }

}