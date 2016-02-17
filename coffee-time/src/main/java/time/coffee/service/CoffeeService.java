package time.coffee.service;

import org.springframework.beans.factory.annotation.Autowired;
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
public class CoffeeService {

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

	/**
	 * 사번으로 유저를 조회합니다
	 * @param empId
	 * @return
	 */
	public Member getMember(String empId) {
		// TODO Impl.
		return null;
	}

	/**
	 * 현재 진행중인 서베이를 조회합니다.
	 * 응답에 별도의 VO가 필요
	 * @return 서베이 정보, 포함된 메뉴 정보
	 */
	public Survey findCurrentSurvey() {
		return null;
	}


	/**
	 * 사용자의 선택 결과를 저장합니다.
	 * @param memId
	 * @param menuId
	 */
	@Transactional
	public void saveSurvey(long memId, long menuId, String message) {

	}

	public Member findMember(String empNo) {
		return memberRepository.findByEmpNo(empNo);
	}
}
