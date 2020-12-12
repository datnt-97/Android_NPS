package base.core.model.metadata;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import base.core.lib.constant;
import base.core.model.base.BaseModel;
import base.core.mudules.anotation.OverrideTable;
@OverrideTable(name = constant.TABLE_CHANEL_TABLE_NAME)
@Entity(tableName = constant.TABLE_CHANEL_TABLE_NAME)
public class ChannelModel extends BaseModel {
    @ColumnInfo
    private String mo_ta;
    @ColumnInfo
    private boolean trang_thai;

    public String getMo_ta() {
        return mo_ta;
    }
    public void setMo_ta(String mo_ta) {
        this.mo_ta = mo_ta;
    }

    public boolean isTrang_thai() {
        return trang_thai;
    }

    public void setTrang_thai(boolean trang_thai) {
        this.trang_thai = trang_thai;
    }

    @Override
    public String getString() {
        return null;
    }
}
