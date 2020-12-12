package base.core.mudules.database.DAO;

import androidx.room.Dao;

import base.core.model.metadata.ContractModel;

@Dao
public abstract class ContractDAO extends BaseDAO<ContractModel> {
    public ContractDAO() {
        super(ContractModel.class);
    }
}
