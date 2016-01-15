package plalab.jpa.study02.repository;

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

import javax.transaction.Transactional;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Study02Application.class)
@Transactional
@Rollback(false)
public class ItemRepositoryMemoTest {

    @Autowired
    ItemRepository itemRepository;

    @Autowired
    CategoryRepository categoryRepository;

    @Before
    public void setUp() {
        assertNotNull(itemRepository);
        assertNotNull(categoryRepository);

//        initData();
    }

//    @Ignore
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

    @Test
    public void test_Cascade라면_주인만save해도_됨() {
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
    }

    @Test
    public void 고아객체는_다대다에서_제공안함() {
        Item item1 = new Album();
        item1.setName("아이템1");
        item1.setPrice(100);
        item1.setStockQuantity(200);

        Category category1 = new Category();
        category1.setName("카테고리1");

        Category category2 = new Category();
        category2.setName("카테고리2");

        item1.getCategories().add(category1);
        item1.getCategories().add(category2);

        itemRepository.save(item1);

        item1.getCategories().remove(category1);

        Category findCategory = categoryRepository.findOne(category1.getId());

        assertNotNull(findCategory);
    }

    @Test
    public void test_Cascade라면_주인을_삭제하면_연관엔티티도_모두_삭제됨() {
        Category category1 = new Category();
        category1.setName("카테고리1");

        Category category2 = new Category();
        category2.setName("카테고리2");

        Item item = new Album();
        item.setName("아이템1");

        item.getCategories().add(category1);
        item.getCategories().add(category2);
        itemRepository.save(item);

        itemRepository.delete(1L);

        Category category3 = new Category();
        category1.setName("카테고리3");

        Item item2 = new Album();
        item2.setName("아이템2");

        item2.getCategories().add(category3);
        itemRepository.save(item2);

        assertNull(itemRepository.findOne(1L));
        assertNull(categoryRepository.findOne(1L));
        assertNull(categoryRepository.findOne(2L));
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

        // Cascade 옵션을 줘서 필요 없음
        // categoryRepository.save(category1);
//        categoryRepository.save(category2);

        for (int i = 1; i <= 10 ; i++) {
            Item item = new Album();
            item.setName("아이템" + i);

            item.getCategories().add(category1);
            item.getCategories().add(category2);
            itemRepository.save(item);
        }

    }

}