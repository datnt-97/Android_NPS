package base.core.mudules.database.DAO;

import androidx.room.Dao;

import base.core.mudules.database.CookieDB;

@Dao
public abstract class CookieDAO extends BaseDAO<CookieDB> {
    public CookieDAO() {
        super(CookieDB.class);
    }
}
