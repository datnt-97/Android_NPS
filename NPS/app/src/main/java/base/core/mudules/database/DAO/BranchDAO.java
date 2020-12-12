package base.core.mudules.database.DAO;

import androidx.room.Dao;

import base.core.model.metadata.BranchModel;
import base.core.model.metadata.ErrorReasonGroupModel;

@Dao
public abstract class BranchDAO extends BaseDAO<BranchModel> {
    public BranchDAO() {
        super(BranchModel.class);
    }
}
