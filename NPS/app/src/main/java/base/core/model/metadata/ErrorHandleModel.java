package base.core.model.metadata;

import androidx.room.ColumnInfo;
import androidx.room.Entity;

import base.core.lib.constant;
import base.core.model.base.BaseModel;
import base.core.mudules.anotation.OverrideTable;

@OverrideTable(name = constant.TABLE_ERROR_HANDLE_TABLE_NAME)
@Entity(tableName = constant.TABLE_ERROR_HANDLE_TABLE_NAME)
public class ErrorHandleModel extends BaseModel {
    @ColumnInfo
    private int error_handle_id;
    @ColumnInfo
    private int error_id;
    @ColumnInfo
    private int reason_detail_id;
    @ColumnInfo
    private String solution;
    @ColumnInfo
    private int process_time;
    @ColumnInfo
    private int status;
    @ColumnInfo
    private int replace_device;

    public int getError_handle_id() {
        return error_handle_id;
    }

    public void setError_handle_id(int error_handle_id) {
        this.error_handle_id = error_handle_id;
    }

    public int getError_id() {
        return error_id;
    }

    public void setError_id(int error_id) {
        this.error_id = error_id;
    }

    public int getReason_detail_id() {
        return reason_detail_id;
    }

    public void setReason_detail_id(int reason_detail_id) {
        this.reason_detail_id = reason_detail_id;
    }

    public String getSolution() {
        return solution;
    }

    public void setSolution(String solution) {
        this.solution = solution;
    }

    public int getProcess_time() {
        return process_time;
    }

    public void setProcess_time(int process_time) {
        this.process_time = process_time;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getReplace_device() {
        return replace_device;
    }

    public void setReplace_device(int replace_device) {
        this.replace_device = replace_device;
    }

    @Override
    public String getString() {
        return null;
    }
}
