package base.core.model.metadata;

import androidx.room.ColumnInfo;
import androidx.room.Entity;

import base.core.lib.constant;
import base.core.model.base.BaseModel;
import base.core.mudules.anotation.OverrideTable;

@OverrideTable(name = constant.TABLE_ERROR_REASON_GROUP_TABLE_NAME)
@Entity(tableName = constant.TABLE_ERROR_REASON_GROUP_TABLE_NAME)
public class ErrorReasonGroupModel extends BaseModel {
    @ColumnInfo
    private int reason_group_id;
    @ColumnInfo
    private String reason_group_name;
    @ColumnInfo
    private int reason_group_type;
    @ColumnInfo
    private int status;

    public int getReason_group_id() {
        return reason_group_id;
    }

    public void setReason_group_id(int reason_group_id) {
        this.reason_group_id = reason_group_id;
    }

    public String getReason_group_name() {
        return reason_group_name;
    }

    public void setReason_group_name(String reason_group_name) {
        this.reason_group_name = reason_group_name;
    }

    public int getReason_group_type() {
        return reason_group_type;
    }

    public void setReason_group_type(int reason_group_type) {
        this.reason_group_type = reason_group_type;
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
