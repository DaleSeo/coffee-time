package time.coffee.dto;

import time.coffee.domain.Menu;

import java.util.List;

public class ShopDto {

	private Long id;

	private String name;

	private String tel;

	private String description;

	private List<Menu> menus;

	public ShopDto() {
	}

	public ShopDto(String name, String tel, String description) {
		this.name = name;
		this.tel = tel;
		this.description = description;
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

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<Menu> getMenus() {
		return menus;
	}

	public void setMenus(List<Menu> menus) {
		this.menus = menus;
	}
}
