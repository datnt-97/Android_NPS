package base.core.mudules.database.DAO;

import androidx.room.Dao;

import base.core.model.metadata.ErrorReasonDetailModel;
@Dao
public abstract class ErrorReasonDetailDAO extends BaseDAO<ErrorReasonDetailModel> {
    public ErrorReasonDetailDAO() {
        super(ErrorReasonDetailModel.class);
    }
}