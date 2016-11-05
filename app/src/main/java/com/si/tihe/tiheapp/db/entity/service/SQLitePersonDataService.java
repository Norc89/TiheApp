package com.si.tihe.tiheapp.db.entity.service;

import com.google.inject.Inject;
import com.j256.ormlite.dao.Dao;
import com.si.tihe.tiheapp.db.base.TiheOrmDbHelper;
import com.si.tihe.tiheapp.db.base.TiheOrmDbService;
import com.si.tihe.tiheapp.db.entity.PersonData;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created on 5.11.2016.
 *
 * SQLitePersonDataService
 *
 * @author Mišel Mojzeš
 */
public class SQLitePersonDataService extends TiheOrmDbService<PersonData> {

    @Inject
    public SQLitePersonDataService(TiheOrmDbHelper helper) {
        super(PersonData.class, helper);
    }

    public void insertPersonItem(PersonData personData) {
        try {
            Dao<PersonData, ?> dto = getDao();
            dto.create(personData);

        } catch (SQLException e) {}
    }

    public List<PersonData> getPersonRecords() {
        List<PersonData> dataList = new ArrayList<>();
        try {
            dataList = getDao().queryBuilder().query();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dataList;
    }
}
