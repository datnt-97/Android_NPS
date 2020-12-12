package base.core.mudules.database.DAO;

import androidx.room.Dao;

import base.core.model.UserModel;

@Dao
public abstract class UserDAO extends BaseDAO<UserModel> {
    public UserDAO() {
        super(UserModel.class);
    }
}
