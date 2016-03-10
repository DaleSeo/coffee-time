package time.coffee.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import time.coffee.domain.Survey;
import time.coffee.repository.MenuRepository;
import time.coffee.repository.ShopRepository;
import time.coffee.repository.SurveyRepository;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class SurveyService {

	@Autowired
	private SurveyRepository surveyRepository;

	@Autowired
	private ShopRepository shopRepository;

	@Autowired
	private MenuRepository menuRepository;


	@Transactional
	public Long add(Survey survey) {
		//FIXME: shop, menu id 로만 등록하도록 변경 (?)
		if(survey.getShop() != null && survey.getShop().getId() != null)
			survey.setShop(shopRepository.findOne(survey.getShop().getId()));
		if(survey.getDefaultMenu() != null && survey.getDefaultMenu().getId() != null)
			survey.setDefaultMenu(menuRepository.findOne(survey.getDefaultMenu().getId()));
		surveyRepository.save(survey);
		return survey.getId();
	}

	public List<Survey> findAll() {
		return surveyRepository.findAll();
	}

	public Survey findOne(Long id) {
		return surveyRepository.findOne(id);
	}

	@Transactional
	public Survey update(Survey survey) {
		Survey found = findOne(survey.getId());
		found.setDeadline(survey.getDeadline());
		found.setDescription(survey.getDescription());

		//FIXME: shop, menu entity ?
		found.setDefaultMenu(survey.getDefaultMenu());
		found.setShop(survey.getShop());
		return found;
	}

	@Transactional
	public void delete(Long id) {
		surveyRepository.delete(id);
	}

}
