package com.si.tihe.tiheapp.db.entity.list;


import com.si.tihe.tiheapp.db.entity.PersonData;

import java.util.Arrays;
import java.util.List;

/**
 * Created by lenovo on 11/3/2016.
 */

public class DefaultEntityListFactory implements EntityListFactory {

    private List<Class<?>> entities = Arrays.asList(new Class<?>[]{PersonData.class});

    public DefaultEntityListFactory(){

    }

    @Override
    public List<Class<?>> getEntityClasses() {
        return entities;
    }
}
