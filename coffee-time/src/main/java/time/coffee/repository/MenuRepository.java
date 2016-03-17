package time.coffee.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import time.coffee.domain.Menu;
import time.coffee.domain.Shop;

import java.util.List;

public interface MenuRepository extends JpaRepository<Menu, Long> {
    Menu findByShop_IdAndId(Long shopId, Long menuId);
    Menu findByIdAndShop(Long menuId, Shop shop);
    List<Menu> findByShopId(Long shopId);
    Menu findByName(String name);
}
