package com.si.tihe.tiheapp.db.base;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.google.inject.Inject;
import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import com.si.tihe.tiheapp.db.entity.PersonData;
import com.si.tihe.tiheapp.db.entity.list.DefaultEntityListFactory;

import java.sql.SQLException;
import java.util.List;

/**
 * Created on 5.11.2016.
 *
 * Is database orm helper
 *
 * @author Mišel Mojzeš
 */
public class TiheOrmDbHelper extends OrmLiteSqliteOpenHelper {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "TiheApp.db";

    private DefaultEntityListFactory entityListFactory;
    private Dao<PersonData, Long> personDao;
    private SQLiteDatabase database;

    @Inject
    public TiheOrmDbHelper(Context context, DefaultEntityListFactory entityListFactory) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.entityListFactory = entityListFactory;
    }

    @Override
    public void onCreate(SQLiteDatabase database, ConnectionSource connectionSource) {
        try {
            List<Class<?>> classesList = entityListFactory.getEntityClasses();
            for (Class<?> classes : classesList) {
                TableUtils.createTable(connectionSource, classes);
            }
        } catch (SQLException e) {
            throw new IllegalStateException("Databases can not be created.", e);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, ConnectionSource connectionSource, int oldVersion, int newVersion) {

    }


    /**
     * Returns an instance of the data access object
     * @return dao
     * @throws SQLException
     */
    public Dao<PersonData, Long> getDao() throws SQLException {
        if(personDao == null) {
            personDao = getDao(PersonData.class);
        }
        return personDao;
    }

    public synchronized SQLiteDatabase getDatabase() {
        if (database == null) {
            database = getWritableDatabase();
        }
        return database;
    }
}
