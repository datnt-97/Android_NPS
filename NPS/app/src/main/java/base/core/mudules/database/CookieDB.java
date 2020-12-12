package base.core.mudules.database;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;

import base.core.lib.constant;
import base.core.model.base.BaseModel;
import base.core.mudules.anotation.OverrideTable;

@OverrideTable(name = constant.TABLE_COOKIE_TABLE_NAME)
@Entity(tableName = constant.TABLE_COOKIE_TABLE_NAME)
public class CookieDB extends BaseModel {

    @ColumnInfo(name = "token")
    private String token;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }


    public Long getDateCreate() {
        return dateCreate;
    }

    public void setDateCreate(Long dateCreate) {
        this.dateCreate = dateCreate;
    }

    @ColumnInfo(name = "date_create")
    private Long dateCreate;

    public CookieDB(int id, String token, Long dateCreate) {
        super(id);
        this.token = token;
        this.dateCreate = dateCreate;
    }


    @Override
    public String getString() {
        return null;
    }
}
