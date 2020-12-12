package base.core.mudules.database.DAO;

import androidx.room.Dao;

import base.core.model.metadata.ErrorModel;
import io.reactivex.internal.util.ErrorMode;

@Dao
public abstract class ErrorDAO extends BaseDAO<ErrorModel> {
    public ErrorDAO() {
        super(ErrorModel.class);
    }
}
