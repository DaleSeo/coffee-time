package plalab.jpa.study01.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import plalab.jpa.study01.domain.OrderItem;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {

}
