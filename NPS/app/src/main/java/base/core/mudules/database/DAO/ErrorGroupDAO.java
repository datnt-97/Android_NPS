package base.core.mudules.database.DAO;

import androidx.room.Dao;

import base.core.model.metadata.ErrorGroupModel;
import base.core.model.metadata.ErrorModel;

@Dao
public abstract class ErrorGroupDAO extends BaseDAO<ErrorGroupModel> {
    public ErrorGroupDAO() {
        super(ErrorGroupModel.class);
    }
}
