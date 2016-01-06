/*
 * Copyright (c) 2013 SK planet.
 * All right reserved.
 *
 * This software is the confidential and proprietary information of SK planet.
 * You shall not disclose such Confidential Information and
 * shall use it only in accordance with the terms of the license agreement
 * you entered into with SK planet.
 */
package plalab.jpa.study02.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import plalab.jpa.study02.domain.Album;
import plalab.jpa.study02.domain.Category;
import plalab.jpa.study02.domain.Item;
import plalab.jpa.study02.domain.Movie;
import plalab.jpa.study02.repository.CategoryRepository;
import plalab.jpa.study02.repository.ItemRepository;
import plalab.jpa.study02.repository.MovieRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * DESC
 * </p>
 * Updated on : 2016. 01. 06 Updated by : 정희원, SK 플래닛.
 */
@Service
public class ItemService {

    @PersistenceContext
    EntityManager em;

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    ItemRepository itemRepository;

    @Autowired
    MovieRepository movieRepository;

    @Transactional
    public void executeAlbum() {
        Album album = new Album();
        album.setName("별들의 전쟁 노래");
        album.setArtist("외쿡인");
        album.setPrice(12000);
        album.setStockQuantity(100);
        album.setEtc("작년말에 콘서트 함");

        Category category1 = new Category();
        category1.setName("카테고리1");

        Category category2 = new Category();
        category2.setName("카테고리2");

        categoryRepository.save(category1);
        categoryRepository.save(category2);

        album.setCategories(new ArrayList<>());
        album.getCategories().add(category1);
        album.getCategories().add(category2);

        itemRepository.save(album);
    }

    @Transactional()
    public Movie executeMovie() {
        Movie movie = new Movie();
        movie.setName("별들의 전쟁");
        movie.setPrice(14000);
        movie.setStockQuantity(200);
        movie.setActor("흑형");
        movie.setDirector("그분");

        Category c2 = new Category();
        c2.setName("SF");

        Category c1 = new Category();
        c1.setName("영화");
        c1.setChild(new ArrayList<>());
        c1.getChild().add(c2);

        c2.setParent(c1);

        movie.getCategories().add(c2);

        categoryRepository.save(c2);
        categoryRepository.save(c1);
        movieRepository.save(movie);

        return movie;
    }

    @Transactional
    public List<Item> findByNameLike(String name) {
        return itemRepository.findByNameStartingWith(name);
    }
}
