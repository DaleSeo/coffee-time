package time.coffee.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import time.coffee.domain.Menu;
import time.coffee.domain.Shop;
import time.coffee.domain.Survey;
import time.coffee.dto.ShopDto;
import time.coffee.dto.SurveyCreateDto;
import time.coffee.dto.SurveyDto;
import time.coffee.service.SurveyService;
import time.coffee.util.BeanConverter;

import java.util.List;

@RestController
@RequestMapping("/surveys")
public class SurveyController {

	@Autowired
	private SurveyService service;

	@RequestMapping(method = RequestMethod.GET)
	public List<SurveyDto> findAll() {
		return BeanConverter.listTransform(service.findAll(), SurveyDto.class);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public SurveyDto findOne(@PathVariable Long id) {
		Survey survey = service.findOne(id);
		SurveyDto surveyDto = BeanConverter.convert(survey, SurveyDto.class);
		//TODO: Bug 순환참조. ShopDto.menu 객체가 Dto로 바뀌면 BeanConverter 로 변경
		//surveyDto.setShop(BeanConverter.convert(survey.getShop(), ShopDto.class));
		setSurveyDto(survey, surveyDto);

		return surveyDto;
	}

	@RequestMapping(method = RequestMethod.POST)
	public SurveyDto addShop(@RequestBody SurveyCreateDto surveyCreateDto) {

		Survey survey = BeanConverter.convert(surveyCreateDto, Survey.class);

		Menu defaultMenu = new Menu();
		defaultMenu.setId(surveyCreateDto.getDefaultMenuId());
		Shop shop = new Shop();
		shop.setId(surveyCreateDto.getShopId());
		survey.setShop(shop);
		survey.setDefaultMenu(defaultMenu);

		service.add(survey);

		SurveyDto surveyDto = BeanConverter.convert(survey, SurveyDto.class);
		//TODO: Bug 순환참조. ShopDto.menu 객체가 Dto로 바뀌면 BeanConverter 로 변경
		//surveyDto.setShop(BeanConverter.convert(survey.getShop(), ShopDto.class));
		setSurveyDto(survey, surveyDto);
		return surveyDto;
	}

	private void setSurveyDto(Survey survey, SurveyDto surveyDto) {
		Shop shop = survey.getShop();
		ShopDto shopDto = new ShopDto(shop.getName(), shop.getTel(), shop.getDescription());
		shopDto.setId(shop.getId());
		surveyDto.setShop(shopDto);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public SurveyDto updateShop(@PathVariable Long id, @RequestBody SurveyDto surveyDto) {
		surveyDto.setId(id);
		Survey update = service.update(BeanConverter.convert(surveyDto, Survey.class));
		return BeanConverter.convert(update, SurveyDto.class);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public void deleteMember(@PathVariable Long id) {
		service.delete(id);
	}

}
