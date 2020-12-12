package base.core.model.metadata;

import androidx.room.ColumnInfo;
import androidx.room.Entity;

import base.core.lib.constant;
import base.core.model.base.BaseModel;
import base.core.mudules.anotation.OverrideTable;

@OverrideTable(name = constant.TABLE_ERROR_GROUP_TABLE_NAME)
@Entity(tableName = constant.TABLE_ERROR_GROUP_TABLE_NAME)
public class ErrorGroupModel extends BaseModel {
    @ColumnInfo
    private int group_error_id;
    @ColumnInfo
    private String group_error_name;
    @ColumnInfo
    private String group_error_code;
    @ColumnInfo
    private int status;
    @ColumnInfo
    private String note;


    public int getGroup_error_id() {
        return group_error_id;
    }

    public void setGroup_error_id(int group_error_id) {
        this.group_error_id = group_error_id;
    }

    public String getGroup_error_name() {
        return group_error_name;
    }

    public void setGroup_error_name(String group_error_name) {
        this.group_error_name = group_error_name;
    }

    public String getGroup_error_code() {
        return group_error_code;
    }

    public void setGroup_error_code(String group_error_code) {
        this.group_error_code = group_error_code;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    @Override
    public String getString() {
        return null;
    }
}
