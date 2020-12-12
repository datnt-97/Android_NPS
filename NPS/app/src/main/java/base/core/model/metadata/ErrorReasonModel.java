package base.core.model.metadata;

import androidx.room.ColumnInfo;
import androidx.room.Entity;

import base.core.lib.constant;
import base.core.model.base.BaseModel;
import base.core.mudules.anotation.OverrideTable;

@OverrideTable(name = constant.TABLE_ERROR_REASON_TABLE_NAME)
@Entity(tableName = constant.TABLE_ERROR_REASON_TABLE_NAME)
public class ErrorReasonModel extends BaseModel {

    @ColumnInfo
    private int reason_id;
    @ColumnInfo
    private String reason_name;
    @ColumnInfo
    private int reason_group_id;
    @ColumnInfo
    private int status;

    public int getReason_id() {
        return reason_id;
    }

    public void setReason_id(int reason_id) {
        this.reason_id = reason_id;
    }

    public String getReason_name() {
        return reason_name;
    }

    public void setReason_name(String reason_name) {
        this.reason_name = reason_name;
    }

    public int getReason_group_id() {
        return reason_group_id;
    }

    public void setReason_group_id(int reason_group_id) {
        this.reason_group_id = reason_group_id;
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
