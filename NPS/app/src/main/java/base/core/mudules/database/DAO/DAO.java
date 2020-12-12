package base.core.mudules.database.DAO;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.RawQuery;
import androidx.sqlite.db.SimpleSQLiteQuery;

import base.core.model.base.BaseModel;

import java.util.List;

@Dao
public interface DAO<Model extends BaseModel> {

    @RawQuery
    List<Model> findAllWithQuery(SimpleSQLiteQuery query);

    @RawQuery
    Model findByIDWithQuery(SimpleSQLiteQuery query);

    @Insert
    void insertAll(Model... models);

    @Insert
    void insertAll(List<Model> models);

    @Delete
    void delete(Model models);


    @Delete
    void deleteAll(List<Model> models);

}
