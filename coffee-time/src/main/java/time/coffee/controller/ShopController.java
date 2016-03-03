package time.coffee.controller;

import com.google.common.collect.Lists;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import time.coffee.domain.Shop;
import time.coffee.dto.ShopDto;
import time.coffee.service.CoffeeService;

import java.util.List;

@RestController
@RequestMapping("/shops")
public class ShopController {

	@Autowired
	private CoffeeService service;

	@RequestMapping(method = RequestMethod.GET)
	public List<ShopDto> findAll() {
		return Lists.transform(service.findShops(), shop -> convert(shop));
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ShopDto findOne(@PathVariable Long id) {
		return convert(service.findShop(id));
	}

	@RequestMapping(method = RequestMethod.POST)
	public ShopDto addShop(@RequestBody ShopDto shopDto) {
		Shop shop = convert(shopDto);
		service.addShop(shop);
		return convert(shop);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ShopDto updateShop(@PathVariable Long id, @RequestBody ShopDto shopDto) {
		shopDto.setId(id);
		Shop update = service.updateShop(convert(shopDto));
		return convert(update);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public void deleteMember(@PathVariable Long id) {
		service.deleteShop(id);
	}


	private ShopDto convert(Shop shop) {
		ShopDto shopDto = new ShopDto();
		if(shop != null) BeanUtils.copyProperties(shop, shopDto);
		return shopDto;
	}

	private Shop convert(ShopDto shopDto) {
		Shop shop = new Shop();
		if(shopDto != null) BeanUtils.copyProperties(shopDto, shop);
		return shop;
	}

}
