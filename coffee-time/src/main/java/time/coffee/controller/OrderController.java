package time.coffee.controller;

import com.google.common.collect.Maps;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import time.coffee.dto.OrderDto;
import time.coffee.service.OrderService;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Updated on : 2016-03-10. Updated by : 양해엽, SK Planet.
 * TODO
 * [User]
 * - 주문 만들기
 *
 * [Admin]
 * - 주문 결과 상세 조회 by surveyId
 * - 주문용 통계 by surveyId
 */
@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @RequestMapping(method = RequestMethod.POST)
    public Map order(@RequestBody @Valid OrderDto req) {

        Long orderId = orderService.addOrder(req.getEmpNo(), req.getSurveyId(), req.getMenuId(), req.getMessage());

        HashMap<Object, Object> res = Maps.newHashMap();
        res.put("orderId", orderId);
        return res;
    }

    @RequestMapping(method = RequestMethod.GET)
    public List findOrdersBySurveyId(@RequestParam Long surveyId) {
        return orderService.findBySurveyId(surveyId);
    }
}
