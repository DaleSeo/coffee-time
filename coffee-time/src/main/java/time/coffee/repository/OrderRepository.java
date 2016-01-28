package time.coffee.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import time.coffee.domain.Order;
import time.coffee.domain.Survey;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
