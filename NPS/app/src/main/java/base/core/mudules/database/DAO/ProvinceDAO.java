package base.core.mudules.database.DAO;

import androidx.room.Dao;

import base.core.model.metadata.ProvinceModel;

@Dao
public abstract class ProvinceDAO extends BaseDAO<ProvinceModel> {
    public ProvinceDAO() {
        super(ProvinceModel.class);
    }
}
