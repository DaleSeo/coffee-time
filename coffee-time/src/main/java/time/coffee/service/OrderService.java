package time.coffee.service;

import com.google.common.base.Function;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import time.coffee.domain.Member;
import time.coffee.domain.Menu;
import time.coffee.domain.Order;
import time.coffee.domain.Survey;
import time.coffee.dto.OrderDto;
import time.coffee.repository.MemberRepository;
import time.coffee.repository.MenuRepository;
import time.coffee.repository.OrderRepository;
import time.coffee.repository.SurveyRepository;

import java.util.List;

/**
 * Updated on : 2016-03-10. Updated by : 양해엽, SK Planet.
 */
@Service
@Transactional(readOnly = true)
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private SurveyRepository surveyRepository;

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private MenuRepository menuRepository;

    @Transactional
    public Long addOrder(String empNo, Long surveyId, Long menuId, String msg) {

        Member mem = memberRepository.findByEmpNo(empNo);
        if(mem == null)
            throw new IllegalArgumentException("유효하지 않은 회원입니다.");

        Survey survey = surveyRepository.findOne(surveyId);

        if (!survey.isAvailable()) {
            throw new IllegalStateException();
        }

        // menuRepo.findByShopIdAndMenuId
//        Menu menu = getValidMenu(survey.getShop().getMenus(), menuId);
//        Menu menu = menuRepository.findByShop_IdAndId(survey.getShop().getId(), menuId);
        Menu menu = menuRepository.findByIdAndShop(menuId, survey.getShop());
        if (menu == null) {
            throw new IllegalArgumentException();
        }

        Order ord = new Order();
        ord.setSurvey(survey);
        ord.setMember(mem);
        ord.setMessage(msg);
        ord.setMenu(menu);

        orderRepository.save(ord);
        return ord.getId();
    }

    public List<OrderDto> findBySurveyId(Long surveyId) {
        List<Order> list = orderRepository.findBySurveyId(surveyId);
        List<OrderDto> res = Lists.newArrayList();
        for (Order order : list) {
            OrderDto r = new OrderDto();
            r.setEmpNm(order.getMember().getName());
            r.setMenuNm(order.getMenu().getName());
            r.setMessage(order.getMessage());
            r.setEmpNo(order.getMember().getEmpNo());
            res.add(r);
        }
        return res;
    }
}
