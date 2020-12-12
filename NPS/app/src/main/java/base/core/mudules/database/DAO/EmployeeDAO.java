package base.core.mudules.database.DAO;

import androidx.room.Dao;

import base.core.model.metadata.EmployeeModel;
import base.core.model.metadata.ErrorModel;

@Dao
public abstract class EmployeeDAO extends BaseDAO<EmployeeModel> {
    public EmployeeDAO() {
        super(EmployeeModel.class);
    }
}
