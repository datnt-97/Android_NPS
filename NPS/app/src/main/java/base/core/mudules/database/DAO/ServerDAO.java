package base.core.mudules.database.DAO;

import androidx.room.Dao;

import base.core.model.ServerModel;

@Dao
public abstract class ServerDAO extends BaseDAO<ServerModel> {
    public ServerDAO() {
        super(ServerModel.class);
    }
}
