package base.core.api;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.android.volley.Request;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;

import org.json.JSONException;
import org.json.JSONObject;

import base.MainActivity;
import base.core.lib.constant;
import base.core.lib.util;
import base.core.model.AtmModel;
import base.core.model.CookieModel;
import base.core.model.ResultLoginModel;
import base.core.model.ServerModel;
import base.core.model.UserModel;
import base.core.model.metadata.MetadataModel;
import base.core.model.result.ResultBase;
import base.core.model.result.ResultModel;
import base.core.mudules.database.CookieDB;
import base.core.mudules.files_management.FileBase;
import base.ui.atm.AtmFragment;
import base.ui.login.LoginActivity;
import okhttp3.internal.http.HttpMethod;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class AppController extends Controller {
    public static UserModel userModel = MainActivity.appDatabase.userDAO().findById(constant.DB_DEFAULT_ID);
    public static ServerModel serverModel = MainActivity.appDatabase.serverDAO().findById(constant.DB_DEFAULT_ID);
    public static CookieDB cookieModel = MainActivity.appDatabase.cookieDAO().findById(constant.DB_DEFAULT_ID);

    public AppController(Context context) {
        super(context);
    }

    public static boolean isLogin() {
        if (cookieModel == null || util.Network.getURL().length() == 0) {
            return false;
        }
        fetchDataFromApi(util.Network.getURL(), new HashMap<>(), METHOD.POST, new util.FunctionCallback2<ResultModel, Boolean>() {
                    @Override
                    public Boolean apply(ResultModel resultModel) {
                        return null;
                    }
                }
        );
    }

    public static void login(String username, String pass) {

    }


//    public static ServerModel serverModel = new ServerModel(constant.DB_FIRST_ID, "", constant.PORT_DEFAUT, "", false, false);
//    private static int method = Request.Method.POST;
//
//    public AppController(String url, int port, boolean isSSL, boolean isRemember, Context context) {
//        super(context);
//        serverModel.setServername(url);
//        serverModel.setPort(port);
//        serverModel.setSSl(isSSL);
//        serverModel.setRemember(isRemember);
//    }
//
//    public void setContext(Context context) {
//        this.context = context;
//    }
//
//    public AppController(Context context) {
//        super(context);
//    }
//
//    public void login(String userName, String pwd) {
//        if (serverModel.getUrl() != "") {
//            serverModel.setUserModel(new UserModel(constant.DB_FIRST_ID, userName, pwd, "", ""));
//            autoLogin();
//        } else {
//            util.createAlert(context, util.ALERT_STATUS.ALERT_STATUS_DANGER, util.ALERT_TYPE.ALERT_TYPE_INFO, "Can't found server ", null);
//        }
//    }
//
//    private void autoLogin() {
//        String url = serverModel.getUrl() + constant.URL_LOGIN;
//        HashMap<String, Object> params = util.parseParams(serverModel.getUserModel());
//        this.fetchDataFromApi(url, params, method, new util.FunctionCallback2<ResultModel, Boolean>() {
//            @Override
//            public Boolean apply(ResultModel resultModel) {
//                if (resultModel.getErrorCode() != constant.ERROR_CODE_SUCCESS) {
//                    util.createAlert(context, util.ALERT_STATUS.ALERT_STATUS_DANGER, util.ALERT_TYPE.ALERT_TYPE_INFO, resultModel.getMessage(), null);
//                    return false;
//                }
//                ResultLoginModel loginModel = null;
//                try {
//                    loginModel = ResultLoginModel.Parse(resultModel.getData());
//                    if (loginModel.getToken() == "") {
//                        util.createAlert(context, util.ALERT_STATUS.ALERT_STATUS_DANGER, util.ALERT_TYPE.ALERT_TYPE_INFO, "Can't not get token", null);
//                        return false;
//                    }
//                    CookieDB cookieDB = MainActivity.appDatabase.cookieDAO().findById(constant.DB_FIRST_ID);
//                    if (cookieDB != null) {
//                        MainActivity.appDatabase.cookieDAO().delete(cookieDB);
//                    }
//                    MainActivity.appDatabase.cookieDAO().insertAll(new CookieDB(constant.DB_FIRST_ID, loginModel.getToken().toString(), new Date().getTime()));
//
//                    ServerModel serverModeldb = MainActivity.appDatabase.serverDAO().findById(constant.DB_FIRST_ID);
//                    if (serverModeldb != null) {
//                        MainActivity.appDatabase.serverDAO().delete(serverModeldb);
//                    }
//                    MainActivity.appDatabase.serverDAO().insertAll(serverModel);
//
//                    UserModel userModels = MainActivity.appDatabase.userDAO().findById(constant.DB_FIRST_ID);
//                    if (userModels != null) {
//                        MainActivity.appDatabase.userDAO().delete(userModels);
//                    }
//                    serverModel.getUserModel().setUserFullName(loginModel.getName());
//                    serverModel.getUserModel().setImageUrl(loginModel.getUrl());
//                    MainActivity.appDatabase.userDAO().insertAll(serverModel.getUserModel());
//                    MainActivity.isLogin = true;
//                    Activity login = (Activity) context;
//                    Intent intent = new Intent(login, MainActivity.class);
//                    login.startActivity(intent);
//                    login.finish();
//                } catch (Exception e) {
//                    util.createAlert(context, util.ALERT_STATUS.ALERT_STATUS_DANGER, util.ALERT_TYPE.ALERT_TYPE_INFO, e.getMessage(), null);
//                    return false;
//                }
//
//                return true;
//            }
//        });
//    }
//
//    public void checkLogin() {
//        ServerModel serverModeldb = MainActivity.appDatabase.serverDAO().findById(constant.DB_FIRST_ID);
//        UserModel userModel = MainActivity.appDatabase.userDAO().findById(constant.DB_FIRST_ID);
//        CookieDB cookieDB = MainActivity.appDatabase.cookieDAO().findById(constant.DB_FIRST_ID);
//        if (serverModeldb != null) {
//            this.fetchDataFromApi(serverModeldb.getUrl() + constant.URL_CHECK_LOGIN, new HashMap<>(), method, new util.FunctionCallback2<ResultModel, Boolean>() {
//                @Override
//                public Boolean apply(ResultModel resultModel) {
//                    if (resultModel.getErrorCode() != constant.ERROR_CODE_SUCCESS) {
//                        util.createAlert(context, util.ALERT_STATUS.ALERT_STATUS_DANGER, util.ALERT_TYPE.ALERT_TYPE_INFO, resultModel.getMessage(), null);
//                        MainActivity.isLogin = false;
////                        MainActivity.appDatabase.serverDAO().delete(serverModeldb);
//                        MainActivity.appDatabase.userDAO().delete(userModel);
//                        MainActivity.appDatabase.cookieDAO().delete(cookieDB);
//
//                        return false;
//                    }
//                    return true;
//                }
//            });
//        } else {
//            Intent intent = new Intent(context, LoginActivity.class);
//            context.startActivity(intent);
//        }
//
//    }
//
//    public void downloadFiles(String url, int method, final util.FunctionCallback2 functionCallback) {
//        this.fetchDataStreamFromApi(url, new HashMap<>(), method, new util.FunctionCallback2<ResultModel, Boolean>() {
//            @Override
//            public Boolean apply(ResultModel resultModel) {
//                try {
//                    if (resultModel.getErrorCode() != constant.ERROR_CODE_SUCCESS) {
//                        util.createAlert(context, util.ALERT_STATUS.ALERT_STATUS_DANGER, util.ALERT_TYPE.ALERT_TYPE_INFO, resultModel.getMessage(), null);
//                    } else {
//                        functionCallback.apply((byte[]) resultModel.getData());
//                    }
//                } catch (Exception e) {
//                    util.createAlert(context, util.ALERT_STATUS.ALERT_STATUS_DANGER, util.ALERT_TYPE.ALERT_TYPE_INFO, e.getMessage(), null);
//                }
//                return true;
//            }
//        });
//    }
//
//    public void loadMetaData() throws Exception, IOException {
//        String url = MainActivity.appServerModel.getUrl() + constant.URL_META_DATA;
//
//        this.fetchDataResultBaseFromApi(url, new HashMap<>(), method, new util.FunctionCallback2<ResultModel, Boolean>() {
//            @Override
//            public Boolean apply(ResultModel resultModel) {
//                try {
//                    if (resultModel.getErrorCode() != constant.ERROR_CODE_SUCCESS) {
//                        util.createAlert(context, util.ALERT_STATUS.ALERT_STATUS_DANGER, util.ALERT_TYPE.ALERT_TYPE_INFO, resultModel.getMessage(), null);
//                    } else {
//                        resultModel.setMessage("Load metadata successfully");
//                        util.createAlert(context, util.ALERT_STATUS.ALERT_STATUS_SUCCESS, util.ALERT_TYPE.ALERT_TYPE_INFO, resultModel.getMessage(), new
//                                util.FunctionCallback3<AlertDialog.Builder, View, Boolean>() {
//                                    @Override
//                                    public Boolean apply(AlertDialog.Builder builder, View view) {
//                                        MetadataModel metadataModel = new MetadataModel();
//                                        ResultBase<MetadataModel> resultBase = new ResultBase<MetadataModel>(metadataModel);
//                                        Activity activity = (Activity) context;
//                                        util.DialogProgress progress = new util.DialogProgress(activity);
//                                        progress.start();
//                                        try {
//                                            MainActivity.metadataModel = resultBase.Parse(resultModel.getData(), PropertyNamingStrategy.LOWER_CAMEL_CASE);
//                                            MainActivity.metadataModel.localProcess();
//                                            progress.dismiss();
//                                        } catch (Exception e) {
//                                            util.createAlert(context, util.ALERT_STATUS.ALERT_STATUS_DANGER, util.ALERT_TYPE.ALERT_TYPE_INFO,
//                                                    e.getMessage() != null ? e.getMessage() : "Error unknow", null);
//                                        }
//                                        return null;
//                                    }
//                                });
//
//
//                    }
//                } catch (Exception e) {
//                    util.createAlert(context, util.ALERT_STATUS.ALERT_STATUS_DANGER, util.ALERT_TYPE.ALERT_TYPE_INFO, e.getMessage(), null);
//                }
//                return true;
//            }
//        });
//    }
//
//    public void loadAtmWithPaging(int page, View view, MainActivity mainActivity) {
//        String url = MainActivity.appServerModel.getUrl() + constant.URL_ATM + "?page=" + page;
//        HashMap<String, Object> params = new HashMap<>();
//        params.put("page", page);
//        this.fetchDataFromApi(url, params, method, new util.FunctionCallback2<ResultModel, Boolean>() {
//            @Override
//            public Boolean apply(ResultModel resultModel) {
//                try {
//                    if (resultModel.getErrorCode() != constant.ERROR_CODE_SUCCESS) {
//                        util.createAlert(context, util.ALERT_STATUS.ALERT_STATUS_DANGER, util.ALERT_TYPE.ALERT_TYPE_INFO, resultModel.getMessage(), null);
//                    } else {
//                        AtmModel atmModel = new AtmModel();
//                        ResultBase<AtmModel> resultBase = new ResultBase<AtmModel>(atmModel);
//                        try {
//                            List<AtmModel> atmModels = resultBase.ParseList(resultModel.getData());
//                        } catch (IOException e) {
//                            throw e;
//                        }
//                    }
//                } catch (Exception e) {
//                    util.createAlert(context, util.ALERT_STATUS.ALERT_STATUS_DANGER, util.ALERT_TYPE.ALERT_TYPE_INFO, e.getMessage(), null);
//                }
//                return true;
//            }
//        });
//    }
//
//    public void loadIncidentListWithPaging(int page, Context context, final util.FunctionCallback2<ResultModel, Boolean> functionCallback) {
//        String url = MainActivity.appServerModel.getUrl() + constant.URL_TICKET + "?page=" + page;
//        this.fetchDataResultBaseFromApi(url, new HashMap<>(), method, new util.FunctionCallback2<ResultModel, Boolean>() {
//            @Override
//            public Boolean apply(ResultModel resultModel) {
//                try {
//                    if (resultModel.getErrorCode() != constant.ERROR_CODE_SUCCESS) {
//                        util.createAlert(context, util.ALERT_STATUS.ALERT_STATUS_DANGER, util.ALERT_TYPE.ALERT_TYPE_INFO, resultModel.getMessage(), null);
//                    } else {
//                        functionCallback.apply(resultModel);
//                    }
//                } catch (Exception e) {
//                    util.createAlert(context, util.ALERT_STATUS.ALERT_STATUS_DANGER, util.ALERT_TYPE.ALERT_TYPE_INFO, e.getMessage(), null);
//                }
//                return true;
//            }
//        });
//    }
}
