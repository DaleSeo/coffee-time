package plalab.jpa.study02.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import plalab.jpa.study02.domain.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {

}
