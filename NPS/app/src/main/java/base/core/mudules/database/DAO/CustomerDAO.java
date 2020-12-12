package base.core.mudules.database.DAO;

import androidx.room.Dao;

import base.core.model.metadata.CustomerModel;
import base.core.mudules.database.CookieDB;

@Dao
public abstract class CustomerDAO extends BaseDAO<CustomerModel> {
    public CustomerDAO() {
        super(CustomerModel.class);
    }
}
