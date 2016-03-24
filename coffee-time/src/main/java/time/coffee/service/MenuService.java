package time.coffee.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import time.coffee.domain.Menu;
import time.coffee.repository.MenuRepository;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class MenuService {

    @Autowired
    private MenuRepository menuRepository;

    @Transactional
    public Menu addMenu(Menu menu) {
        return menuRepository.save(menu);
    }

    public List<Menu> findMenusByShopId(Long shopId) {
        return menuRepository.findByShopId(shopId);
    }

    public List<Menu> findByName(String name) {
        List<Menu> found = menuRepository.findByName(name);
        return found;
    }

	public Menu findById(long id) {
		Menu found = menuRepository.findOne(id);
		if (found == null) {
			throw  new IllegalArgumentException("Unknown menu id: " + id);
		}
		return found;
	}

    @Transactional
    public Menu updateMenu(Menu menu) {
        Menu found = findById(menu.getId());
        found.setName(menu.getName());
        found.setDescription(menu.getDescription());
	    return found;
    }

    @Transactional
    public void deleteById(long id) {
	    Menu found = findById(id);
        menuRepository.delete(found);
    }
}
