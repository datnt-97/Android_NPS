package base.core.mudules.database.DAO;

import androidx.room.Dao;

import base.core.model.metadata.ProcessStatusModel;

@Dao
public abstract class ProcessStatusDAO extends BaseDAO<ProcessStatusModel> {
    public ProcessStatusDAO() {
        super(ProcessStatusModel.class);
    }
}
