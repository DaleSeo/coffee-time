package plalab.jpa.study01.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import plalab.jpa.study01.domain.Item;

public interface ItemRepository extends JpaRepository<Item, Long> {

}
