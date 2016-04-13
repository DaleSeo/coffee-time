package time.coffee;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import time.coffee.domain.Member;
import time.coffee.service.CoffeeService;

import java.util.Arrays;
import java.util.List;

/**
 * 초기 데이터 적재 클래스
 * @author 서대영/Store기술개발팀/SKP
 */
@Component
// @Profile("local") TODO : 추후 프로파일 개념이 생기면 로컬 프로파일에서만 구동되도록 추가
public class InitialDataLoader {

    private static Logger logger = LoggerFactory.getLogger(InitialDataLoader.class);

    @Autowired
    private CoffeeService coffeeService;

    @EventListener
    public void handleContextRefresh(ContextRefreshedEvent event) {
        ApplicationContext applicationContext = event.getApplicationContext();
        if (applicationContext.getParent() != null) {
            return; // 서블릿 컨텍스트가 초기화 됐을 때는 생략
        }

        logger.info("Loading initial data onto the database.");

        List<Member> memberList= Arrays.asList(
                new Member("9230065", "염동환"),
                new Member("9230069", "임근대"),
                new Member("9230074", "최은봉"),
                new Member("9230082", "서대영"),
                new Member("9230088", "김희민"),
                new Member("9230090", "양해엽"),
                new Member("9230091", "정희원")
        );

        memberList.forEach(member -> coffeeService.addMember(member));
    }

}
