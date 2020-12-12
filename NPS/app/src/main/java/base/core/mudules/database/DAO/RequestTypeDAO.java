package base.core.mudules.database.DAO;

import androidx.room.Dao;

import base.core.model.metadata.RequestTypeModel;

@Dao
public abstract class RequestTypeDAO extends BaseDAO<RequestTypeModel> {
    public RequestTypeDAO() {
        super(RequestTypeModel.class);
    }
}
