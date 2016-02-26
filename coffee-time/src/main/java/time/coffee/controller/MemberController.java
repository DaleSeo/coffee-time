package time.coffee.controller;

import com.google.common.collect.Lists;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
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

		Member member = service.findMemberByEmpNo(empNo);

		MemberDto memberDto = new MemberDto();
		if(member != null) BeanUtils.copyProperties(member, memberDto);

		return memberDto;
	}

	//TODO: 객체 Mapping을 Controller에서 구현
	@RequestMapping(method = RequestMethod.POST)
	public MemberDto addMember(@RequestBody MemberDto member) {
		Member newMember = member.convertToEntity();    // FIXME
		service.addMember(newMember);
		return new MemberDto(newMember);
	}

	@RequestMapping(value = "/{empNo}", method = RequestMethod.PUT)
	public MemberDto updateMember(@PathVariable String empNo, @RequestBody MemberDto memberDto) {
		Member member = new Member();
		memberDto.setEmpNo(empNo);
		if(memberDto != null) BeanUtils.copyProperties(memberDto, member);
		Member updateMember = service.updateMember(member);

		MemberDto result = new MemberDto();
		if(updateMember != null) BeanUtils.copyProperties(updateMember, result);
		return result;
	}

	@RequestMapping(value = "/{empNo}", method = RequestMethod.DELETE)
	public void deleteMember(@PathVariable String empNo) {
		service.deleteMemberByEmpNo(empNo);
	}




}
