package base.core.model;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;

import base.core.lib.constant;
import base.core.model.base.BaseModel;
import base.core.mudules.anotation.OverrideTable;

@OverrideTable(name = constant.TABLE_SERVER_TABLE_NAME)
@Entity(tableName = constant.TABLE_SERVER_TABLE_NAME)
public class ServerModel extends BaseModel {
    @ColumnInfo(name = "servername")
    String servername;
    @ColumnInfo(name = "port")
    private int port;
    @Ignore
    private UserModel userModel;

    public void setUrl(String url) {
        this.url = url;
    }

    @ColumnInfo(name = "url")
    private String url;

    public boolean isSSl() {
        return isSSl;
    }


    public void setSSl(boolean SSl) {
        isSSl = SSl;
    }

    @ColumnInfo(name = "ssl")
    private boolean isSSl;

    @ColumnInfo(name = "remember")
    private boolean isRemember;

    public boolean isRemember() {
        return isRemember;
    }

    public void setRemember(boolean remember) {
        isRemember = remember;
    }

    public String getUrl() {
        String url = servername + (port != constant.PORT_DEFAUT ? ":" + port : "");
        return isSSl ? "https://" + url : "http://" + url;
    }

    public UserModel getUserModel() {
        return userModel;
    }

    public void setUserModel(UserModel userModel) {
        this.userModel = userModel;
    }


    public String getServername() {
        return servername;
    }

    public void setServername(String servername) {
        this.servername = servername;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }


    public ServerModel(int id, String servername, int port, String url, boolean isSSl, boolean isRemember) {
        super(id);
        this.servername = servername;
        this.port = port;
        this.url = url;
        this.isSSl = isSSl;
        this.isRemember = isRemember;
    }

    @Override
    public String getString() {
        return null;
    }
}
