package plalab.jpa.study02.repository;

import jdk.nashorn.internal.ir.annotations.Ignore;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import plalab.jpa.study02.Study02Application;
import plalab.jpa.study02.domain.Album;
import plalab.jpa.study02.domain.Category;
import plalab.jpa.study02.domain.Item;
import sun.jvm.hotspot.utilities.Assert;

import javax.transaction.Transactional;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Study02Application.class)
@Transactional
//@Rollback(false)
public class ItemRepositoryTest {

    @Autowired
    ItemRepository itemRepository;

    @Autowired
    CategoryRepository categoryRepository;

    @Before
    public void setUp() {
        assertNotNull(itemRepository);
        assertNotNull(categoryRepository);

        initData();
    }

    @Ignore
    @Test
    public void test_연관테이블에값이들어가는지() {
        Item item1 = new Album();
        item1.setName("아이템1");
        item1.setPrice(100);
        item1.setStockQuantity(200);

        Category category1 = new Category();
        category1.setName("카테고리1");

        Category category2 = new Category();
        category2.setName("카테고리2");

        categoryRepository.save(category1);
        categoryRepository.save(category2);

        item1.getCategories().add(category1);
        item1.getCategories().add(category2);

        itemRepository.save(item1);

        Item findItem = itemRepository.findOne(item1.getId());
        assertEquals(findItem.getName(), item1.getName());
        assertEquals(findItem.getCategories().size(), 2);
    }

    @Ignore
    @Test (expected = org.hibernate.TransientObjectException.class)
    public void test_주인만save하면안되는군() {
        Item item1 = new Album();
        item1.setName("아이템1");
        item1.setPrice(100);
        item1.setStockQuantity(200);

        Category category1 = new Category();
        category1.setName("카테고리1");

        Category category2 = new Category();
        category2.setName("카테고리2");

//        categoryRepository.save(category1);
//        categoryRepository.save(category2);

        item1.getCategories().add(category1);
        item1.getCategories().add(category2);

        itemRepository.save(item1);

        fail();
    }

    @Test
    public void testSelect() {

        List<Item> items = itemRepository.findAll();
        for (Item item : items) {
            assertEquals(item.getCategories().size(), 2);
        }

//        List<Category> categories = categoryRepository.findAll();
//        for (Category category : categories) {
//            assertEquals(category.getItems().size(), 10);
//        }

    }

    private void initData() {

        Category category1 = new Category();
        category1.setName("카테고리1");

        Category category2 = new Category();
        category2.setName("카테고리2");

        categoryRepository.save(category1);
        categoryRepository.save(category2);

        for (int i = 1; i <= 10 ; i++) {
            Item item = new Album();
            item.setName("아이템" + i);

            item.getCategories().add(category1);
            item.getCategories().add(category2);
            itemRepository.save(item);
        }

    }

}