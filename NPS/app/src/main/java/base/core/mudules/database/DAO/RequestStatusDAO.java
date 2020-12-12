package base.core.mudules.database.DAO;

import androidx.room.Dao;

import base.core.model.metadata.ChannelModel;
import base.core.model.metadata.RequestStatusModel;
@Dao
public abstract class RequestStatusDAO extends BaseDAO<RequestStatusModel> {
    public RequestStatusDAO() {
        super(RequestStatusModel.class);
    }
}