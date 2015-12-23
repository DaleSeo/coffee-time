package plalab.jpa.study02.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import plalab.jpa.study02.domain.Delivery;

public interface DeliveryRepository extends JpaRepository<Delivery, Long> {
}
