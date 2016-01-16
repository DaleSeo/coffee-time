package plalab.jpa.study02.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import plalab.jpa.study02.domain.Category;
import plalab.jpa.study02.domain.Item;

import java.util.List;

/**
 * Updated on : 2015-12-23. Updated by : 양해엽, SK Planet.
 */
public interface CategoryRepository extends JpaRepository<Category, Long> {

    List<Category> findByName(String name);

}
