package time.coffee.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import time.coffee.domain.Order;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
	List<Order> findBySurveyId(Long surveyId);
}
