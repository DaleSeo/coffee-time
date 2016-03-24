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
import time.coffee.domain.Menu;
import time.coffee.domain.Shop;
import time.coffee.domain.Survey;
import time.coffee.dto.ShopDto;
import time.coffee.repository.MenuRepository;
import time.coffee.repository.ShopRepository;
import time.coffee.service.SurveyService;
import time.coffee.test.SurveyTestUtil;

import javax.transaction.Transactional;
import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@Transactional
@Rollback
public class SurveyControllerTest {

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext wac;


    @Autowired
    private ShopRepository shopRepository;

    @Autowired
    private MenuRepository menuRepository;

    @Autowired
    private SurveyService surveyService;

    ObjectMapper objectMapper = new ObjectMapper();

    @Before
    public void setUp() throws Exception {
        mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }

    @Test
    public void testFindAll() throws Exception {
        setUpData();
        mockMvc.perform(get("/surveys")
                        .accept(MediaType.APPLICATION_JSON_UTF8)
        )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8));
    }

    @Test
    public void testFindOne() throws Exception {
        setUpData();
        mockMvc.perform(get("/surveys/1")
                        .accept(MediaType.APPLICATION_JSON_UTF8)
        )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8));
    }

    @Test
    public void testAdd() throws Exception {
        ShopDto shopDto = new ShopDto();
        shopDto.setName("머스트커피");
        shopDto.setTel("031-xxx-xxxx");
        shopDto.setDescription("맛나요 커피.");
        mockMvc.perform(post("/surveys")
                        .content(objectMapper.writeValueAsString(shopDto))
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .accept(MediaType.APPLICATION_JSON_UTF8)
        )
                .andDo(print())
                .andExpect(status().isOk());

        assertNotNull(surveyService.findOne(1l));
    }

    @Test
    public void testUpdateShop() throws Exception {
        setUpData();

        ShopDto shopDto = new ShopDto();
        shopDto.setName("머스트커피2");
        shopDto.setTel("031-xxx-xxxx");
        shopDto.setDescription("맛나요 커피.");

        mockMvc.perform(put("/surveys/{id}", 1)
                        .content(objectMapper.writeValueAsString(shopDto))
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .accept(MediaType.APPLICATION_JSON_UTF8)
        )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8));
    }

    @Test
    public void testDelete() throws Exception {
        List<Survey> surveys = setUpData();
        Long id = surveys.get(0).getId();
        mockMvc.perform(delete("/surveys/{id}", id)
        )
                .andDo(print())
                .andExpect(status().isOk());

        assertNull(surveyService.findOne(id));
    }

    private List<Survey> setUpData() {
        List<Survey> surveys = SurveyTestUtil.getDummySurveyList();
        for (Survey survey : surveys) {
            Shop shop = survey.getShop();
            shopRepository.save(survey.getShop());

            Menu menu = survey.getDefaultMenu();
            menu.setShop(shop);
            menuRepository.save(menu);

            surveyService.add(survey);
        }
        return surveys;
    }

}