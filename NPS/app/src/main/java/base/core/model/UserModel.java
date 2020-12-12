package base.core.model;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;

import base.core.lib.constant;
import base.core.model.base.BaseModel;
import base.core.mudules.anotation.OverrideTable;

@OverrideTable(name = constant.TABLE_USER_TABLE_NAME)
@Entity(tableName = constant.TABLE_USER_TABLE_NAME)
public class UserModel extends BaseModel {
    @ColumnInfo(name = "userName")
    String userName;
    @ColumnInfo(name = "password")
    String password;

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    @ColumnInfo(name = "imageUrl")
    String imageUrl;

    @ColumnInfo(name = "userFullName")
    String userFullName;

    public String getUserFullName() {
        return userFullName;
    }

    public void setUserFullName(String userFullName) {
        this.userFullName = userFullName;
    }


    public UserModel(int id, String userName, String password, String imageUrl, String userFullName) {
        super(id);
        this.userName = userName;
        this.password = password;
        this.imageUrl = imageUrl;
        this.userFullName = userFullName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String getString() {
        return null;
    }
}
