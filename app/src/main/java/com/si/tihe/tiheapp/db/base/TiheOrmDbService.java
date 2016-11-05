package com.si.tihe.tiheapp.db.base;

import android.database.sqlite.SQLiteDatabase;

import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;

/**
 * Created on 5.11.2016.
 *
 * Is database orm service
 *
 * @author Mišel Mojzeš
 */
public class TiheOrmDbService<T>   {

    private Class<T> clazz;
    private TiheOrmDbHelper helper;
    private Dao<T, ?> dao;

    public TiheOrmDbService(Class<T> clazz, TiheOrmDbHelper helper){
        this.clazz = clazz;
        this.helper = helper;
    }

    protected synchronized Dao<T, ?> getDao() {
        if (dao == null) {
            try {
                dao = helper.getDao(clazz);
            } catch (SQLException e) {}
        }
        return dao;
    }

    public void resetDatabase() {
        try {
            getDao().deleteBuilder().delete();
        } catch (SQLException e) {}
    }

    /**
     * Returns the helper.
     * @return The orm helper.
     */
    protected TiheOrmDbHelper getHelper() {
        return helper;
    }

    /**
     * Returns a writable database.
     * @return The database.
     */
    protected SQLiteDatabase getDatabase() {
        return helper.getDatabase();
    }

    /**
     * Calls the {@link SQLiteDatabase}::setTransactionSuccessful() method.
     */
    public void setTransactionSuccessful() {
        getDatabase().setTransactionSuccessful();
    }

    /**
     * Calls the {@link SQLiteDatabase}::beginTransaction() method.
     */
    public void beginTransaction() {
        getDatabase().beginTransaction();
    }

    /**
     * Calls the {@link SQLiteDatabase}::endTransaction() method.
     */
    public void endTransaction() {
        getDatabase().endTransaction();
    }

}
