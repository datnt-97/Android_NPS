package base.core.model.result;

import android.os.Build;

import androidx.annotation.RequiresApi;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;

import base.core.lib.constant;
import base.core.lib.util;
import base.core.model.base.BaseModel;

import org.json.JSONObject;

import java.io.IOException;

public class ResultModel extends BaseModel {
    private int errorCode = constant.ERROR_CODE_GENARAL;
    private Object data;
    private String message;
    private int recordsTotal = 0;

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    //    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public String getMessage() {
        return util.mapMessageByCode(this.errorCode) + " \n Detail : " + message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getRecordsTotal() {
        return recordsTotal;
    }

    public void setRecordsTotal(int recordsTotal) {
        this.recordsTotal = recordsTotal;
    }

    public ResultModel() {
    }

    @Override
    public String getString() {
        return null;
    }

    public ResultModel Parse(JSONObject dataObject) throws IOException {
        ResultModel resultModel = new ResultModel();
        ResultBase<ResultModel> resultBase = new ResultBase<ResultModel>(this);
        try {
            resultModel = resultBase.Parse(dataObject.toString());
        } catch (IOException e) {
            throw e;
        }
        return resultModel;
    }

    public ResultModel Parse(JSONObject dataObject, boolean isCamel) throws IOException {
        ResultModel resultModel = new ResultModel();
        ResultBase<ResultModel> resultBase = new ResultBase<ResultModel>(this);
        try {
            if (isCamel) {
                resultModel = resultBase.Parse(dataObject.toString(), PropertyNamingStrategy.LOWER_CAMEL_CASE);
            } else {
                resultModel = resultBase.Parse(dataObject.toString());

            }
        } catch (IOException e) {
            throw e;
        }
        return resultModel;
    }

}
