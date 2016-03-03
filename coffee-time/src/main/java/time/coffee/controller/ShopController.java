package time.coffee.controller;

import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import time.coffee.domain.Shop;
import time.coffee.dto.ShopDto;
import time.coffee.service.CoffeeService;
import time.coffee.util.BeanConverter;

import java.util.List;

@RestController
@RequestMapping("/shops")
public class ShopController {

	@Autowired
	private CoffeeService service;

	@RequestMapping(method = RequestMethod.GET)
	public List<ShopDto> findAll() {
		return Lists.transform(service.findShops(), shop -> BeanConverter.convert(shop, ShopDto.class));
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ShopDto findOne(@PathVariable Long id) {
		return BeanConverter.convert(service.findShop(id), ShopDto.class);
	}

	@RequestMapping(method = RequestMethod.POST)
	public ShopDto addShop(@RequestBody ShopDto shopDto) {
		Shop shop = BeanConverter.convert(shopDto, Shop.class);
		service.addShop(shop);
		return BeanConverter.convert(shop, ShopDto.class);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ShopDto updateShop(@PathVariable Long id, @RequestBody ShopDto shopDto) {
		shopDto.setId(id);
		Shop update = service.updateShop(BeanConverter.convert(shopDto, Shop.class));
		return BeanConverter.convert(update, ShopDto.class);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public void deleteMember(@PathVariable Long id) {
		service.deleteShop(id);
	}

}
