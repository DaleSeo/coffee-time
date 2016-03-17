package time.coffee.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Ignore;
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
import time.coffee.dto.MenuDto;
import time.coffee.service.CoffeeService;
import time.coffee.service.MenuService;

import javax.transaction.Transactional;

import static junit.framework.TestCase.fail;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@Transactional
@Rollback
public class MenuControllerTest {

	private MockMvc mockMvc;

	@Autowired
	private WebApplicationContext wac;

	@Autowired
	private MenuService menuService;

	@Autowired
	private CoffeeService coffeeService;

	private ObjectMapper objectMapper = new ObjectMapper();

	@Before
	public void setUp() throws Exception {
		mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
	}

	@Test
	public void testFindById() throws Exception {
		setUpData();
		mockMvc.perform(get("/menus/{id}", 1)
						.accept(MediaType.APPLICATION_JSON_UTF8)
		)
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8));


	}

	@Ignore
	@Test
	public void testAddMenu() throws Exception {
		setUpData();
		Shop shop = coffeeService.findShopByName("머스트커피");
		MenuDto menuDto = new MenuDto();
		menuDto.setShopId(shop.getId());
		menuDto.setName("카페라떼");
		menuDto.setDescription("haeyup");
		mockMvc.perform(post("/menus")
						.content(objectMapper.writeValueAsString(menuDto))
						.contentType(MediaType.APPLICATION_JSON_UTF8)
						.accept(MediaType.APPLICATION_JSON_UTF8)
		)
				.andDo(print())
				.andExpect(status().isOk());

		assertNotNull(menuService.findByName("카페라떼"));
	}

	@Test
	public void testUpdateMenu() throws Exception {
		setUpData();
		MenuDto menuDto = new MenuDto();
		menuDto.setName("haeyup");
		mockMvc.perform(put("/menus/{id}", 1)
						.content(objectMapper.writeValueAsString(menuDto))
						.contentType(MediaType.APPLICATION_JSON_UTF8)
						.accept(MediaType.APPLICATION_JSON_UTF8)
		)
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8));
	}


	@Test
	public void testDeleteMenu() throws Exception {
		setUpData();
		mockMvc.perform(delete("/menus/{id}", 1)
		)
				.andDo(print())
				.andExpect(status().isOk());

		try {
			menuService.findById(1);
			fail();
		} catch(IllegalArgumentException e) {
			assertEquals("Unknown menu id: 1", e.getMessage());
		}
	}

	private void setUpData() {
		Shop shop = new Shop("머스트커피", "031-xxx-xxxx", "맛나요 커피.");
		// coffeeService.addShop(shop);

		Menu menu = new Menu("카페라떼", "우유섞인커피");
		menu.setShop(shop);
		menuService.addMenu(menu);
	}

}