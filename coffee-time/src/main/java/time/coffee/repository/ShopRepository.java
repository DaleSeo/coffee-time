package time.coffee.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import time.coffee.domain.Shop;

public interface ShopRepository extends JpaRepository<Shop, Long>{
}
