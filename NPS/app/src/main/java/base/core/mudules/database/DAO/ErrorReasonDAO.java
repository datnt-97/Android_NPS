package base.core.mudules.database.DAO;

import androidx.room.Dao;

import base.core.model.metadata.ErrorGroupModel;
import base.core.model.metadata.ErrorReasonModel;
@Dao
public abstract class ErrorReasonDAO extends BaseDAO<ErrorReasonModel> {
    public ErrorReasonDAO() {
        super(ErrorReasonModel.class);
    }
}