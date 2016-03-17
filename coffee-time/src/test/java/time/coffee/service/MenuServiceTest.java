package time.coffee.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import time.coffee.Application;
import time.coffee.domain.Menu;
import time.coffee.domain.Shop;

import java.util.List;

import static junit.framework.TestCase.assertNotNull;
import static junit.framework.TestCase.assertNull;
import static junit.framework.TestCase.fail;
import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = Application.class)
@Transactional
@Rollback
public class MenuServiceTest {

    @Autowired MenuService menuService;
    @Autowired CoffeeService coffeeService;


    @Test
    public void testAddMenu() throws Exception {
        Shop shop = new Shop("머스트커피", "031-xxx-xxxx", "맛나요 커피.");
        // coffeeService.addShop(shop);

        Menu menu = new Menu("카페라떼", "우유섞인커피");
        menu.setShop(shop);

        menuService.addMenu(menu);
        assertNotNull(menu.getId());
    }

    @Test
    public void testFindMenusByShop() {
        setUpData();

        Shop shop = coffeeService.findShopByName("머스트커피");
        List<Menu> menus = menuService.findMenusByShopId(shop.getId());

        assertEquals(1, menus.size());
    }

    @Test
    public void testFindMenuByName() {
        setUpData();

        Menu menu = menuService.findByName("카페라떼").get(0);
        assertEquals("우유섞인커피", menu.getDescription());
    }

    @Test
    public void testUpdateMenu() {
        setUpData();
        Menu menu = menuService.findByName("카페라떼").get(0);
        menu.setDescription("우유를 더 많이 섞은 커피");
        Menu updated = menuService.updateMenu(menu);
        assertEquals("우유를 더 많이 섞은 커피", updated.getDescription());
    }

    @Test
    public void testDeleteMenu() {
        setUpData();
        Menu menu = menuService.findByName("카페라떼").get(0);
        assertEquals("우유섞인커피", menu.getDescription());
        menuService.deleteById(menu.getId());
	    assertEquals(0, menuService.findByName("카페라떼").size());
    }

    private void setUpData() {
        Shop shop = new Shop("머스트커피", "031-xxx-xxxx", "맛나요 커피.");
        // coffeeService.addShop(shop);

        Menu menu = new Menu("카페라떼", "우유섞인커피");
        menu.setShop(shop);
        menuService.addMenu(menu);
    }

}
