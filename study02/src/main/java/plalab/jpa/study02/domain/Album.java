/*
 * Copyright (c) 2013 SK planet.
 * All right reserved.
 *
 * This software is the confidential and proprietary information of SK planet.
 * You shall not disclose such Confidential Information and
 * shall use it only in accordance with the terms of the license agreement
 * you entered into with SK planet.
 */
package plalab.jpa.study02.domain;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * <p>
 * DESC
 * </p>
 * Updated on : 2015. 12. 30 Updated by : 정희원, SK 플래닛.
 */
@Entity
@DiscriminatorValue("A")
public class Album extends Item {

    private String artist;

    private String etc;

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getEtc() {
        return etc;
    }

    public void setEtc(String etc) {
        this.etc = etc;
    }
}
