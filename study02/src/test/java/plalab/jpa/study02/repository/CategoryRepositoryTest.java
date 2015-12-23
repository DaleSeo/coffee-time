package plalab.jpa.study02.repository;

import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import plalab.jpa.study02.Study02Application;
import plalab.jpa.study02.domain.Category;
import plalab.jpa.study02.domain.Item;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Study02Application.class)
public class CategoryRepositoryTest {


    @Autowired
    CategoryRepository categoryRepository;

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
//        assertEquals(findCategory.getItems().size(), 2);
    }

}