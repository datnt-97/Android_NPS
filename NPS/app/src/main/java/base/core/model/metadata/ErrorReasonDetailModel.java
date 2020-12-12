package base.core.model.metadata;

import androidx.room.ColumnInfo;
import androidx.room.Entity;

import base.core.lib.constant;
import base.core.model.base.BaseModel;
import base.core.mudules.anotation.OverrideTable;

@OverrideTable(name = constant.TABLE_ERROR_REASON_DETAIL_TABLE_NAME)
@Entity(tableName = constant.TABLE_ERROR_REASON_DETAIL_TABLE_NAME)
public class ErrorReasonDetailModel extends BaseModel {

    @ColumnInfo
    private int reason_detail;
    @ColumnInfo
    private String reason_detail_name;
    @ColumnInfo
    private int reason_id;
    @ColumnInfo
    private int status;

    public int getReason_detail() {
        return reason_detail;
    }

    public void setReason_detail(int reason_detail) {
        this.reason_detail = reason_detail;
    }

    public String getReason_detail_name() {
        return reason_detail_name;
    }

    public void setReason_detail_name(String reason_detail_name) {
        this.reason_detail_name = reason_detail_name;
    }

    public int getReason_id() {
        return reason_id;
    }

    public void setReason_id(int reason_id) {
        this.reason_id = reason_id;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Override
    public String getString() {
        return null;
    }
}
