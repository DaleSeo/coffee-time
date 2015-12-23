package plalab.jpa.study02.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import plalab.jpa.study02.domain.Category;
import plalab.jpa.study02.domain.Item;

/**
 * Updated on : 2015-12-23. Updated by : 양해엽, SK Planet.
 */
public interface ItemRepository extends JpaRepository<Item, Long> {
}
