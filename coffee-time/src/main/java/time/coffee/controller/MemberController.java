package time.coffee.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import time.coffee.domain.Member;
import time.coffee.dto.MemberDto;
import time.coffee.service.CoffeeAdminService;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/members")
public class MemberController {

	@Autowired
	private CoffeeAdminService service;

	@RequestMapping(method = RequestMethod.GET)
	public List<MemberDto> findAll() {
		List<Member> entityList = service.findMembers();
		List<MemberDto> dtoList = new ArrayList<>(entityList.size());
		entityList.forEach(entity -> {
			dtoList.add(new MemberDto(entity));
		});
		return dtoList;
	}

}
