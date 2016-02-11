package time.coffee.dto;


import time.coffee.domain.Member;

public class MemberDto {

	private Long id;

	private String name;

	private String empNo;

	public MemberDto(Member entity) {
		this.id = entity.getId();
		this.name = entity.getName();
		this.empNo = entity.getEmpNo();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmpNo() {
		return empNo;
	}

	public void setEmpNo(String empNo) {
		this.empNo = empNo;
	}
}
