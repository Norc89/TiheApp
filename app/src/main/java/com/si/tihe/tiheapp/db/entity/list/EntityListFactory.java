package com.si.tihe.tiheapp.db.entity.list;

import java.util.List;

/**
 * Created by lenovo on 11/3/2016.
 */

public interface EntityListFactory {

    /**
     * Lists classes requires to be mapped to SQLite using an ORM.
     * @return The list of Entity classes.
     */
    List<Class<?>> getEntityClasses();
}
