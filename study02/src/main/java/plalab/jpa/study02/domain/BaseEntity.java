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

import javax.persistence.MappedSuperclass;
import java.util.Date;

/**
 * <p>
 * DESC
 * </p>
 * Updated on : 2015. 12. 30 Updated by : 정희원, SK 플래닛.
 */
@MappedSuperclass
public class BaseEntity {

    private Date createdDate;

    private Date lastModifiedDate;

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(Date lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }
}