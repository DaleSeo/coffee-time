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

import javax.transaction.Transactional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Study02Application.class)
@Transactional
public class ItemRepositoryTest {

    @Autowired
    ItemRepository itemRepository;

    @Autowired
    CategoryRepository categoryRepository;

    @Before
    public void setUp() {
        assertNotNull(itemRepository);
        assertNotNull(categoryRepository);
    }

    @Test
    public void test1() {
        Item item1 = new Item();
        item1.setName("아이템1");
        item1.setPrice(100);
        item1.setStockQuantity(200);

        Category category1 = new Category();
        category1.setName("카테고리1");

        Category category2 = new Category();
        category2.setName("카테고리2");

         categoryRepository.save(category1);
         categoryRepository.save(category2);

//        item1.setCategories(Arrays.asList(category1, category2));
        item1.getCategories().add(category1);
        item1.getCategories().add(category2);

        itemRepository.save(item1);
        Item findItem = itemRepository.findOne(1L);
        assertEquals(findItem.getName(), item1.getName());
        assertEquals(findItem.getCategories().size(), 2);
    }


}