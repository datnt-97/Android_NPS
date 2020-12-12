package base.core.mudules.database.DAO;

import androidx.room.Dao;

import base.core.model.metadata.StockModel;

@Dao
public abstract class StockDAO extends BaseDAO<StockModel> {
    public StockDAO() {
        super(StockModel.class);
    }
}
