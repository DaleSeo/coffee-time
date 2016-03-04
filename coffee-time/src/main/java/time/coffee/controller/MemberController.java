package time.coffee.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import time.coffee.domain.Member;
import time.coffee.dto.MemberDto;
import time.coffee.service.CoffeeService;
import time.coffee.util.BeanConverter;

import java.util.List;

@RestController
@RequestMapping("/members")
public class MemberController {

	@Autowired
	private CoffeeService service;

	@RequestMapping(method = RequestMethod.GET)
	public List<MemberDto> findAll() {
		return BeanConverter.listTransform(service.findMembers(), MemberDto.class);
	}

	@RequestMapping(value = "/{empNo}",method = RequestMethod.GET)
	public MemberDto findOne(@PathVariable String empNo) {
		Member member = service.findMemberByEmpNo(empNo);
		return BeanConverter.convert(member, MemberDto.class);
	}

	@RequestMapping(method = RequestMethod.POST)
	public MemberDto addMember(@RequestBody MemberDto memberDto) {
		Member newMember = BeanConverter.convert(memberDto, Member.class);
		service.addMember(newMember);
		return BeanConverter.convert(newMember, MemberDto.class);
	}

	@RequestMapping(value = "/{empNo}", method = RequestMethod.PUT)
	public MemberDto updateMember(@PathVariable String empNo, @RequestBody MemberDto memberDto) {
		memberDto.setEmpNo(empNo);
		Member updateMember = service.updateMember(BeanConverter.convert(memberDto, Member.class));
		return BeanConverter.convert(updateMember, MemberDto.class);
	}

	@RequestMapping(value = "/{empNo}", method = RequestMethod.DELETE)
	public void deleteMember(@PathVariable String empNo) {
		service.deleteMemberByEmpNo(empNo);
	}




}
