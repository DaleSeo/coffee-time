package plalab.jpa.study02.domain;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import plalab.jpa.study02.Study02Application;
import plalab.jpa.study02.repository.AlbumRepo;
import plalab.jpa.study02.repository.CategoryRepo;
import plalab.jpa.study02.repository.ItemRepo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

import static org.junit.Assert.*;

/**
 * Created with IntelliJ IDEA.
 * User: 1002210
 * Date: 15. 12. 30.
 * Time: 오후 12:30
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {Study02Application.class})
public class AlbumTest {

    @Autowired
    private CategoryRepo categoryRepo;

    @Autowired
    private AlbumRepo albumRepo;

    @Autowired
    private ItemRepo itemRepo;  // 과연?

    @Test
    public void test() {

        Category c = new Category();
        c.setName("음악");
        c.setChild(new ArrayList<>());

        Category s1 = new Category();
        s1.setName("발라두");
        s1.setParent(c);

        Category s2 = new Category();
        s2.setName("롹");
        s2.setParent(c);

        c.getChild().add(s1);
        c.getChild().add(s2);

        categoryRepo.save(Arrays.asList(c, s1, s2));

        Album album = new Album();
        album.setName("성식이형");
        album.setArtist("이웃집 성식이");
        album.setEtc("오늘 콘서트함");
        album.setPrice(30000);
        album.setLastModifiedDate(new Date());
        album.setCategories(Arrays.asList(s1));

        itemRepo.save(album);


    }

}