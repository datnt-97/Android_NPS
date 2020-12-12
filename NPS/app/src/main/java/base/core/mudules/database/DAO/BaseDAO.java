package base.core.mudules.database.DAO;

import androidx.room.Dao;
import androidx.room.Query;
import androidx.sqlite.db.SimpleSQLiteQuery;

import base.core.model.base.BaseModel;
import base.core.mudules.anotation.OverrideTable;

import java.lang.reflect.ParameterizedType;
import java.util.List;

@Dao
public abstract class BaseDAO<Model extends BaseModel> implements DAO<Model> {
    SimpleSQLiteQuery query = new SimpleSQLiteQuery("SELECT * FROM ");
    Class<Model> modelClass;

    public SimpleSQLiteQuery getQuery() {
        return query;
    }

    public void setQuery(SimpleSQLiteQuery query) {
        this.query = query;
    }

    public Class<Model> getModelClass() {
        return modelClass;
    }

    public void setModelClass(Class<Model> modelClass) {
        this.modelClass = modelClass;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    String tableName;


    public BaseDAO(Class<Model> modelClass) {

        this.modelClass = modelClass;
        OverrideTable table = modelClass.getAnnotation(OverrideTable.class);
        if (table != null) {
            tableName = table.name();
            query = new SimpleSQLiteQuery("SELECT * FROM " + tableName);
        }
    }

    public List<Model> findAll() {
        return findAllWithQuery(query);
    }

    public Model findById(int id) {
        query = new SimpleSQLiteQuery("SELECT * FROM " + tableName + " where id=" + id);
        return findByIDWithQuery(query);
    }

    public <T> Class<T> getClassOfT() {
        final ParameterizedType type = (ParameterizedType) this.getClass()
                .getGenericSuperclass();
        Class<T> clazz = (Class<T>) type.getActualTypeArguments()[0];
        return clazz;
    }

    public void getABC(List<Model> models) {

    }
}
