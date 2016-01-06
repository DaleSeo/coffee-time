/*
 * Copyright (c) 2013 SK planet.
 * All right reserved.
 *
 * This software is the confidential and proprietary information of SK planet.
 * You shall not disclose such Confidential Information and
 * shall use it only in accordance with the terms of the license agreement
 * you entered into with SK planet.
 */
package plalab.jpa.study02.repository;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import plalab.jpa.study02.Study02Application;
import plalab.jpa.study02.domain.Item;
import plalab.jpa.study02.domain.Movie;
import plalab.jpa.study02.service.ItemService;

import javax.transaction.Transactional;
import java.util.List;

import static org.junit.Assert.assertNotNull;

/**
 * <p>
 * DESC
 * </p>
 * Updated on : 2016. 01. 06 Updated by : 정희원, SK 플래닛.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Study02Application.class)
public class ItemRepository2Test {

    @Autowired
    ItemRepository itemRepository;

    @Autowired
    MovieRepository movieRepository;

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    ItemService itemService;

    @Test
    public void test1() {
        itemService.executeAlbum();
        System.out.println("END");
    }

    @Test
    @Transactional
    public void test2() {
        Movie created = itemService.executeMovie();
        Item item = itemRepository.findOne(created.getId());
        List<Movie> found1 = movieRepository.findByName(created.getName());
        List<Item> found2 = itemRepository.findByName(created.getName());

        assert (created == item) && (item == found1.get(0)) && (item == found2.get(0));
    }

    @Test
    public void test3() {
        itemService.executeAlbum();
        Movie created = itemService.executeMovie();
        List<Item> items = itemService.findByNameLike(created.getName());
        List<Movie> found = movieRepository.findByName(created.getName());
        assert true;
    }
}
