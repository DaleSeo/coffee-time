package plalab.jpa.study02.repository;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import plalab.jpa.study02.Study02Application;
import plalab.jpa.study02.domain.Category;
import plalab.jpa.study02.domain.Item;
import plalab.jpa.study02.domain.Movie;

import javax.transaction.Transactional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Study02Application.class)
@Transactional
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
    public void test1() {
        Category category1 = new Category();
        category1.setName("카테고리1");

        Category category2 = new Category();
        category2.setName("카테고리2");

        categoryRepository.save(category1);
        categoryRepository.save(category2);

        Category findCategory = categoryRepository.findOne(1L);
        assertEquals(findCategory.getName(), category1.getName());
       // assertEquals(findCategory.getItems().size(), 2);
    }

    @Test
    public void test2() {
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

        //category1.setItems(Arrays.asList(item1, item2));

        categoryRepository.saveAndFlush(category1);

        Category findCategory = categoryRepository.findOne(1L);
        assertEquals(findCategory.getName(), category1.getName());
       // assertEquals(findCategory.getItems().size(), 2);
    }

}