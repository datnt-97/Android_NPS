package base.core.model;

import org.json.JSONException;

import java.io.IOException;

import base.core.model.base.BaseModel;
import base.core.model.result.ResultBase;

public class ResultLoginModel extends BaseModel {
    private String token;
    private String url;
    private String name;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ResultLoginModel() {
    }

    @Override
    public String getString() {
        return null;
    }

    public static ResultLoginModel Parse(Object dataObject) throws IOException {
        ResultBase<ResultLoginModel> resultBase = new ResultBase<ResultLoginModel>(new ResultLoginModel());
        try {
            return resultBase.Parse(dataObject);
        } catch (IOException e) {
            throw e;
        }
    }
}
