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
import time.coffee.domain.Shop;
import time.coffee.dto.ShopDto;
import time.coffee.service.CoffeeService;

import javax.transaction.Transactional;

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
public class ShopControllerTest {

	private MockMvc mockMvc;

	@Autowired
	private WebApplicationContext wac;

	@Autowired
	private CoffeeService coffeeService;

	ObjectMapper objectMapper = new ObjectMapper();

	@Before
	public void setUp() throws Exception {
		mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
	}

	@Test
	public void testFindAll() throws Exception {
		setUpData();
		mockMvc.perform(get("/shops")
						.accept(MediaType.APPLICATION_JSON_UTF8)
		)
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8));
	}

	@Test
	public void testFindOne() throws Exception {
		setUpData();
		mockMvc.perform(get("/shops/1")
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
		mockMvc.perform(post("/shops")
						.content(objectMapper.writeValueAsString(shopDto))
						.contentType(MediaType.APPLICATION_JSON_UTF8)
						.accept(MediaType.APPLICATION_JSON_UTF8)
		)
				.andDo(print())
				.andExpect(status().isOk());

		assertNotNull(coffeeService.findShopByName("머스트커피"));
	}

	@Test
	public void testUpdateShop() throws Exception {
		setUpData();

		ShopDto shopDto = new ShopDto();
		shopDto.setName("머스트커피2");
		shopDto.setTel("031-xxx-xxxx");
		shopDto.setDescription("맛나요 커피.");

		mockMvc.perform(put("/shops/{id}", 1)
						.content(objectMapper.writeValueAsString(shopDto))
						.contentType(MediaType.APPLICATION_JSON_UTF8)
						.accept(MediaType.APPLICATION_JSON_UTF8)
		)
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8));
	}

	@Test
	public void testDeleteMember() throws Exception {
		setUpData();
		mockMvc.perform(delete("/shops/{id}", 1)
		)
				.andDo(print())
				.andExpect(status().isOk());

		assertNull(coffeeService.findShopByName("머스트커피"));
	}

	private void setUpData() {
		Shop shop = new Shop("머스트커피", "031-xxx-xxxx", "맛나요 커피.");
		coffeeService.addShop(shop);
		coffeeService.addShop(new Shop("구라비티커피", "031-xxx-yyyy", "머스트커피 옆집."));
	}
}