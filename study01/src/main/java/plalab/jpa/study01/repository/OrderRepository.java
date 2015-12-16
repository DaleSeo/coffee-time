package plalab.jpa.study01.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import plalab.jpa.study01.domain.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {

}
