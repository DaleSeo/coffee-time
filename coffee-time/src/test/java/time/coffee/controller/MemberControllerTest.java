package time.coffee.controller;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.TestRestTemplate;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import time.coffee.Application;
import time.coffee.domain.Member;
import time.coffee.service.CoffeeService;

import javax.transaction.Transactional;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
//@WebIntegrationTest(randomPort = true)
@Transactional
@Rollback
public class MemberControllerTest {

	// @Value("${local.server.port}")
	// private int port;

	private MockMvc mockMvc;

	@Autowired
	private WebApplicationContext wac;

	@Autowired
	private CoffeeService service;

	private TestRestTemplate testRestTemplate = new TestRestTemplate();

	@Before
	public void setUp() throws Exception {
		mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
	}

	@Test
	public void testFindAll() throws Exception {
		setUpData();

		//MemberDto memberDto = testRestTemplate.getForObject("http://localhost:" + port + "/members/{empNo}", MemberDto.class, "1");

		//System.out.println(memberDto.getName());
		//assertNotNull(memberDto);
		mockMvc.perform(get("/members")
				.accept(MediaType.APPLICATION_JSON_UTF8)
		)
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8));
	}

	@Test
	public void testFindOne() throws Exception {
		setUpData();
		mockMvc.perform(get("/members/{id}", 1)
						.accept(MediaType.APPLICATION_JSON_UTF8)
		)
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8));

	}

	private void setUpData() {
		Member member1 = new Member("1", "Daeyoung");
		Member member2 = new Member("2", "Heemin");

		service.addMember(member1);
		service.addMember(member2);
	}


	@Test
	@Ignore
	public void testFindAllWithRestTemplate() throws Exception {
		//Given
		String url = "/members";

		//When
		List result = testRestTemplate.getForObject(url, List.class);

		//Then
		System.out.println(result);
	}
}