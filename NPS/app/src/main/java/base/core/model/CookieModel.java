package base.core.model;

import base.core.model.base.BaseModel;

import java.util.Date;

public class CookieModel extends BaseModel {
    ServerModel server_model;
    private Date date;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public ServerModel getServer_model() {
        return server_model;
    }

    public void setServer_model(ServerModel server_model) {
        this.server_model = server_model;
    }

    @Override
    public String getString() {
        return null;
    }
}
