package time.coffee.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import time.coffee.domain.Member;
import time.coffee.domain.Survey;
import time.coffee.repository.MemberRepository;

@Service
@Transactional(readOnly = true)
public class CoffeeService {

	@Autowired
	private MemberRepository memberRepository;

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
}
