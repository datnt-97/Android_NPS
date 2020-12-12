package base.core.model.metadata;

import androidx.room.ColumnInfo;
import androidx.room.Entity;

import base.core.lib.constant;
import base.core.model.base.BaseModel;
import base.core.mudules.anotation.OverrideTable;

@OverrideTable(name = constant.TABLE_ERROR_TABLE_NAME)
@Entity(tableName = constant.TABLE_ERROR_TABLE_NAME)
public class ErrorModel extends BaseModel {
    @ColumnInfo
    private String error_code;
    @ColumnInfo
    private String error_name;
    @ColumnInfo
    private Byte status;
    @ColumnInfo
    private int error_group_id;
    @ColumnInfo
    private int process_time;

    public String getError_code() {
        return error_code;
    }

    public void setError_code(String error_code) {
        this.error_code = error_code;
    }

    public String getError_name() {
        return error_name;
    }

    public void setError_name(String error_name) {
        this.error_name = error_name;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public int getError_group_id() {
        return error_group_id;
    }

    public void setError_group_id(int error_group_id) {
        this.error_group_id = error_group_id;
    }

    public int getProcess_time() {
        return process_time;
    }

    public void setProcess_time(int process_time) {
        this.process_time = process_time;
    }

    @Override
    public String getString() {
        return null;
    }
}
