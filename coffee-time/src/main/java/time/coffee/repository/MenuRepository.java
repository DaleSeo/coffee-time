package time.coffee.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import time.coffee.domain.Menu;

/**
 * Created by 1001256 on 16. 1. 28..
 */
public interface MenuRepository extends JpaRepository<Menu, Long> {
}
