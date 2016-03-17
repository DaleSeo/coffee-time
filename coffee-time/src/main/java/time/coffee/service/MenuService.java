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

    public Menu findByName(String name) {
        Menu found = menuRepository.findByName(name);
        if (found == null) {
            throw  new IllegalArgumentException("Unknown menu name: " + name);
        }
        return found;
    }

    @Transactional
    public void updateMenu(Menu menu) {
        Menu found = menuRepository.findOne(menu.getId());
        if (found == null) {
            throw  new IllegalArgumentException("Unknown menu id: " + menu.getId());
        }
        found.setName(menu.getName());
        found.setDescription(menu.getDescription());
    }

    @Transactional
    public void deleteMenu(Menu menu) {
        Menu found = menuRepository.findOne(menu.getId());
        if (found == null) {
            throw  new IllegalArgumentException("Unknown menu id: " + menu.getId());
        }
        menuRepository.delete(menu);
    }
}
