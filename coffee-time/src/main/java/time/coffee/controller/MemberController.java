package time.coffee.controller;

import com.google.common.collect.Lists;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import time.coffee.domain.Member;
import time.coffee.dto.MemberDto;
import time.coffee.service.CoffeeService;

import java.util.List;

@RestController
@RequestMapping("/members")
public class MemberController {

	@Autowired
	private CoffeeService service;

	@RequestMapping(method = RequestMethod.GET)
	public List<MemberDto> findAll() {

		return Lists.transform(service.findMembers(), member -> new MemberDto(member));
	}

	@RequestMapping(value = "/{empNo}",method = RequestMethod.GET)
	public MemberDto findOne(@PathVariable String empNo) {

		Member member = service.findMember(empNo);

		MemberDto memberDto = new MemberDto();
		if(member != null) BeanUtils.copyProperties(member, memberDto);

		return memberDto;
	}

	//TODO: 객체 Mapping을 Controller에서 구현
	@RequestMapping(method = RequestMethod.POST)
	public MemberDto save(MemberDto member) {
		Member newMember = member.convertToEntity();    // FIXME
		service.addMember(newMember);
		return new MemberDto(newMember);
	}


}
