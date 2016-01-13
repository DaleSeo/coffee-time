package plalab.jpa.study02.repository;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import plalab.jpa.study02.Study02Application;
import plalab.jpa.study02.domain.Category;
import plalab.jpa.study02.domain.Item;
import plalab.jpa.study02.domain.Movie;

import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Study02Application.class)
@Transactional
@Rollback(false)
public class CategoryRepositoryTest {

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    ItemRepository itemRepository;

    @Before
    public void setUp() {
        assertNotNull(categoryRepository);
    }

    @Test
    public void test_주인이아닌것_혼자만넣는다() {
        Category category1 = new Category();
        category1.setName("카테고리1");

        Category category2 = new Category();
        category2.setName("카테고리2");

        categoryRepository.save(category1);
        categoryRepository.save(category2);

        Category findCategory = categoryRepository.findOne(1L);
        assertEquals(findCategory.getName(), category1.getName());
    }

    @Test
    public void test_주인이아닌곳에서_주인을넣었을때_연관관계테이블에_안들어가야함() {
        Item item1 = new Movie();
        item1.setName("아이템1");
        item1.setPrice(100);
        item1.setStockQuantity(200);

        Item item2 = new Movie();
        item2.setName("아이템2");
        item2.setPrice(200);
        item2.setStockQuantity(400);

        itemRepository.save(item1);
        itemRepository.save(item2);

        Category category1 = new Category();
        category1.setName("카테고리1");

        category1.setItems(Arrays.asList(item1, item2));
        categoryRepository.save(category1);

        List<Category> findCategories = categoryRepository.findByName(category1.getName());
        assertEquals(1, findCategories.size());

        List<Item> findItems = itemRepository.findByName(item1.getName());
        assertEquals(1, findItems.size());

        // TODO: 연관관계 테이블에는 없다는 것을 확인하는 로직 추가
    }

}