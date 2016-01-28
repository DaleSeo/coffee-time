package time.coffee.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import time.coffee.domain.MenuOption;


public interface MenuOptionRepository extends JpaRepository<MenuOption, Long> {
}
