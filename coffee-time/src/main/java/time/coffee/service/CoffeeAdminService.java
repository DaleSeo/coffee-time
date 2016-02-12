package time.coffee.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import time.coffee.domain.*;
import time.coffee.repository.*;

import java.util.Date;
import java.util.List;

/**
 * @author 서대영/Store기술개발팀/SKP
 */
@Service
@Transactional(readOnly = true)
public class CoffeeAdminService {

	@Autowired
	private MemberRepository memberRepository;

	@Autowired
	private ShopRepository shopRepository;

	@Autowired
	private MenuRepository menuRepository;

	@Autowired
	private SurveyRepository surveyRepository;

	@Autowired
	private OrderRepository orderRepository;


	/**
	 * 사용자를 추가합니다.
	 * @param member
	 */
	@Transactional
	public Long addMember(Member member) {
		memberRepository.save(member);
		return member.getId();
	}

	/**
	 * 등록된 사용자를 조회합니다.
	 * @return
	 */
	public List<Member> findMembers() {
		return memberRepository.findAll();
	}

	@Transactional
	public Shop addShop(Shop shop) {
		return shopRepository.save(shop);
	}

	public List<Shop> findShops() {
		return shopRepository.findAll();
	}

	/**
	 * 설문조사를 추가합니다.
	 */
	@Transactional
	public Survey addSurvey(Long shopId, Date deadline, String description, Long defaultMenuId ) {

		Shop shop = shopRepository.findOne(shopId);
		Menu menu = menuRepository.findOne(defaultMenuId);

		Survey survey = new Survey();
		survey.setDescription(description);
		survey.setShop(shop);
		survey.setDeadline(deadline);
		survey.setDefaultMenu(menu);

		return surveyRepository.save(survey);
	}


	@Transactional
	public Menu addMenu(long shopId, String name, String desc){
		Shop shop = shopRepository.findOne(shopId);
		Menu menu = new Menu();
		menu.setName(name);
		menu.setDescription(desc);
		menu.setShop(shop);
		return menuRepository.save(menu);

	}

	/**
	 * 설문조사를 조회합니다
	 * @return
	 */
	public List<Survey> findSurveys() {
		return surveyRepository.findAll();
	}

	/**
	 * 결과를 조회합니다.
	 * @return
	 */
	public List<Order> findOrders(Long surveyId) {
		return orderRepository.findBySurveyId(surveyId);

	}

	/**
	 * 주문을 등록합니다.
	 * @param order
	 * @return
	 */
	@Transactional
	public Order addOrder(Order order){
		return orderRepository.save(order);
	}
}
