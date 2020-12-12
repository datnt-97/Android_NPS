package base.core.model.metadata;

import androidx.room.ColumnInfo;
import androidx.room.Entity;

import base.core.lib.constant;
import base.core.model.base.BaseModel;
import base.core.mudules.anotation.OverrideTable;

@OverrideTable(name = constant.TABLE_PROCESS_STATUS_TABLE_NAME)
@Entity(tableName = constant.TABLE_PROCESS_STATUS_TABLE_NAME)
public class ProcessStatusModel extends BaseModel {

    @ColumnInfo
    private String ten_trang_thai;
    @ColumnInfo
    private String mo_ta;
    @ColumnInfo
    private String mau;

    public String getTen_trang_thai() {
        return ten_trang_thai;
    }

    public void setTen_trang_thai(String ten_trang_thai) {
        this.ten_trang_thai = ten_trang_thai;
    }

    public String getMo_ta() {
        return mo_ta;
    }

    public void setMo_ta(String mo_ta) {
        this.mo_ta = mo_ta;
    }

    public String getMau() {
        return mau;
    }

    public void setMau(String mau) {
        this.mau = mau;
    }

    @Override
    public String getString() {
        return null;
    }
}
