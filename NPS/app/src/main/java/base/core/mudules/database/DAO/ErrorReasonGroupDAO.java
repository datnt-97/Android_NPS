package base.core.mudules.database.DAO;

import androidx.room.Dao;

import base.core.model.metadata.ErrorGroupModel;
import base.core.model.metadata.ErrorReasonGroupModel;
@Dao
public abstract class ErrorReasonGroupDAO extends BaseDAO<ErrorReasonGroupModel> {
    public ErrorReasonGroupDAO() {
        super(ErrorReasonGroupModel.class);
    }
}