package base.core.mudules.database.DAO;

import androidx.room.Dao;

import base.core.model.metadata.ErrorGroupModel;
import base.core.model.metadata.ErrorHandleModel;
@Dao
public abstract class ErrorHandleDAO extends BaseDAO<ErrorHandleModel> {
    public ErrorHandleDAO() {
        super(ErrorHandleModel.class);
    }
}