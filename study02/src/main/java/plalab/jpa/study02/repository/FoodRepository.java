package plalab.jpa.study02.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import plalab.jpa.study02.domain.Food;

public interface FoodRepository extends JpaRepository<Food, Long> {
}
