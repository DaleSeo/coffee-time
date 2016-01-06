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

import org.springframework.data.jpa.repository.JpaRepository;
import plalab.jpa.study02.domain.Movie;

import java.util.List;

/**
 * <p>
 * DESC
 * </p>
 * Updated on : 2016. 01. 06 Updated by : 정희원, SK 플래닛.
 */
public interface MovieRepository extends JpaRepository<Movie, Long> {

    List<Movie> findByName(String name);

}
