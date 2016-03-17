package time.coffee.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import time.coffee.domain.Menu;
import time.coffee.domain.Shop;
import time.coffee.dto.MenuDto;
import time.coffee.service.CoffeeService;
import time.coffee.service.MenuService;
import time.coffee.util.BeanConverter;

/**
 * @author 서대영/Store기술개발팀/SKP
 */
@RestController
@RequestMapping("/menus")
public class MenuController {

	@Autowired
	private MenuService menuService;

	@Autowired
	private CoffeeService coffeeService;

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public MenuDto findById(@PathVariable long id) {
		Menu found = menuService.findById(id);
		MenuDto menuDto = BeanConverter.convert(found, MenuDto.class);
		menuDto.setShopId(found.getShop().getId());
		return menuDto;
	}

	@RequestMapping(method = RequestMethod.POST)
	public MenuDto addMenu(@RequestBody MenuDto menuDto) {
		Menu menu = BeanConverter.convert(menuDto, Menu.class);
		Shop shop = coffeeService.findShop(menuDto.getShopId());
		menu.setShop(shop);
		menuDto = BeanConverter.convert(menuService.addMenu(menu), MenuDto.class);
		menuDto.setShopId(menu.getShop().getId());
		return menuDto;
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public MenuDto updateMember(@PathVariable long id, @RequestBody MenuDto menuDto) {
		menuDto.setId(id);
		Menu updateMenu = menuService.updateMenu(BeanConverter.convert(menuDto, Menu.class));
		return BeanConverter.convert(updateMenu, MenuDto.class);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public void deleteMenu(@PathVariable long id) {
		menuService.deleteById(id);
	}


}
