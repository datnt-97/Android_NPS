package base.core.mudules.database.DAO;

import androidx.room.Dao;

import base.core.model.metadata.ChannelModel;
import base.core.mudules.database.CookieDB;

@Dao
public abstract class ChannelDAO extends BaseDAO<ChannelModel> {
    public ChannelDAO() {
        super(ChannelModel.class);
    }
}

