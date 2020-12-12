package base.core.api;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.RequiresApi;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.google.gson.JsonObject;

import base.MainActivity;
import base.core.lib.constant;
import base.core.lib.util;
import base.core.model.result.ResultBase;
import base.core.model.result.ResultModel;
import base.core.mudules.database.AppDatabase;
import base.core.mudules.database.CookieDB;
import base.ui.login.LoginActivity;
import retrofit2.http.Url;

import org.json.JSONObject;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


public class Controller {

    public enum METHOD {
        POST(1),
        GET(0),
        PUT(2),
        DELETE(3);
        public final int label;

        private int value;
        private static Map map = new HashMap<>();

        private METHOD(int value) {
            this.value = value;
        }

        static {
            for (METHOD pageType : METHOD.values()) {
                map.put(pageType.value, pageType);
            }
        }

        public static METHOD valueOf(int pageType) {
            return (METHOD) map.get(pageType);
        }

        public int getValue() {
            return value;
        }
    }

    protected static Context context;

    protected Controller(Context context) {
        this.context = context;
    }

    protected static void fetchDataFromApi(String url, HashMap<String, Object> params, METHOD method,
                                           final util.FunctionCallback2<ResultModel, Boolean> functionCallback) {
        // map.put();- values for request body.
        final ResultModel[] resultModel = {new ResultModel()};
        try {
            Activity activity = (Activity) context;
            util.DialogProgress progress = new util.DialogProgress(activity);
            progress.start();

            JsonObjectRequest jsonObjReq = new JsonObjectRequest(
                    method.getValue(), url, new JSONObject(params), new Response.Listener<JSONObject>() {
                @RequiresApi(api = Build.VERSION_CODES.N)
                @Override
                public void onResponse(JSONObject response) {
                    try {
                        progress.getDownTimer().cancel();
                        progress.dismiss();
                        resultModel[0] = resultModel[0].Parse(response, true);
                        if (resultModel[0].getErrorCode() != constant.ERROR_CODE_SUCCESS) {
                            if (resultModel[0].getErrorCode() == constant.ERROR_CODE_AUTHENTICATE) {
                                util.Alert.alertError(context, resultModel[0].getMessage(),
                                        new util.FunctionCallback3<AlertDialog.Builder, View, Boolean>() {
                                            @Override
                                            public Boolean apply(AlertDialog.Builder builder, View view) {
                                                util.createViewLogin(context);
                                                return true;
                                            }
                                        });
                            }
                        }
                    } catch (IOException e) {
                        resultModel[0].setErrorCode(constant.ERROR_CODE_ERROR_CLIENT);
                        resultModel[0].setMessage(e.getMessage());

                    } finally {
                        functionCallback.apply(resultModel[0]);
                    }

                }
            },
                    new Response.ErrorListener() {
                        @RequiresApi(api = Build.VERSION_CODES.N)
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            try {
                                progress.getDownTimer().cancel();
                                progress.dismiss();
                                resultModel[0].setErrorCode(constant.ERROR_CODE_ERROR_SERVER);
                                String server = util.Network.getURL();
                                String mess = "Can't connect to server ( " + server + ")";
                                if (error.networkResponse == null && error.getLocalizedMessage() != null) {
                                    mess = error.getLocalizedMessage();
                                } else if (error.networkResponse != null) {
                                    mess = error.networkResponse.statusCode
                                            + " - " + new String(error.networkResponse.data, "UTF-8");
                                }
                                resultModel[0].setMessage(mess);
                            } catch (Exception e) {
                                resultModel[0].setErrorCode(constant.ERROR_CODE_ERROR_CLIENT);
                                resultModel[0].setMessage(e.getMessage());
                            } finally {
                                functionCallback.apply(resultModel[0]);
                            }
                        }

                    }) {
                @Override
                public Map<String, String> getHeaders() throws AuthFailureError {
//                    Set-Cookie: <cookie-name>=<cookie-value>;
                    CookieDB cookieDB = AppDatabase.getInstance(context).cookieDAO().findById(constant.DB_FIRST_ID);
                    HashMap<String, String> headers = new HashMap<String, String>();
                    if (cookieDB != null) {
                        headers = util.Network.getHeader();
                    } else {
                        Intent intent = new Intent(context, LoginActivity.class);
                        context.startActivity(intent);
                    }
                    return headers;
                }
            };
            // SET TIMEOUT
            progress.setDownTimer(new CountDownTimer(constant.MILISECOND_TIME_OUT, constant.MILISECOND_TIME_TICK) {
                @Override
                public void onTick(long millisUntilFinished) {
                }

                @Override
                public void onFinish() {
                    progress.dismiss();
                    jsonObjReq.cancel();
                    util.Alert.alertError(context,
                            "Timeout load data \n Request canceled!", null);
                }
            });
            progress.getDownTimer().start();
            jsonObjReq.setRetryPolicy(new DefaultRetryPolicy(5000, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
            jsonObjReq.setShouldCache(false);
            APIFetch.getInstance(context).addToRequestQueue(jsonObjReq);
        } catch (Exception e) {
            resultModel[0].setErrorCode(constant.ERROR_CODE_ERROR_CLIENT);
            resultModel[0].setMessage(e.getMessage());
            functionCallback.apply(resultModel[0]);
        }
    }


    protected void fetchDataResultBaseFromApi(String url, HashMap<String, Object> params, int method,
                                              final util.FunctionCallback2<ResultModel, Boolean> functionCallback) {
        // map.put();- values for request body.
        try {
            Activity activity = (Activity) context;
            util.DialogProgress progress = new util.DialogProgress(activity);
            progress.start();
            ResultBaseVolleyRequest request = new ResultBaseVolleyRequest(method, url

                    , new Response.Listener<ResultModel>() {

                @Override
                public void onResponse(ResultModel resultModel) {
                    // TODO handle the response
                    progress.dismiss();
                    progress.getDownTimer().cancel();

                    try {
                        if (resultModel == null) {
                            resultModel.setMessage("Data empty!");
                            resultModel.setErrorCode(constant.ERROR_CODE_ERROR_NULL);
                        } else if (resultModel != null && resultModel.getErrorCode() != constant.ERROR_CODE_SUCCESS) {
                            if (resultModel.getErrorCode() == constant.ERROR_CODE_AUTHENTICATE) {

                                util.createAlert(context, util.ALERT_STATUS.ALERT_STATUS_DANGER, util.ALERT_TYPE.ALERT_TYPE_INFO,
                                        resultModel.getMessage(), new util.FunctionCallback3<AlertDialog.Builder, View, Boolean>() {
                                            @Override
                                            public Boolean apply(AlertDialog.Builder builder, View view) {
                                                util.createViewLogin(context);
                                                return null;
                                            }
                                        });
                            } else {
                                functionCallback.apply(resultModel);
                            }
                        } else {

                            functionCallback.apply(resultModel);
                        }
                    } catch (Exception e) {
                        // TODO Auto-generated catch block
                        resultModel = new ResultModel();
                        resultModel.setErrorCode(constant.ERROR_CODE_ERROR_CLIENT);
                        resultModel.setMessage(e.getMessage());
                        functionCallback.apply(resultModel);
                    }
                }
            }, new Response.ErrorListener() {

                @Override
                public void onErrorResponse(VolleyError error) {
                    ResultModel resultModel = new ResultModel();

                    try {
                        progress.dismiss();
                        progress.getDownTimer().cancel();

                        resultModel.setErrorCode(constant.ERROR_CODE_ERROR_SERVER);
                        String mess = "Can't connect to server : " + MainActivity.appServerModel.getUrl();
                        if (error.networkResponse == null && error.getLocalizedMessage() != null) {
                            mess = error.getLocalizedMessage();
                        } else if (error.networkResponse != null) {
                            mess = error.networkResponse.statusCode
                                    + " - " + new String(error.networkResponse.data, "UTF-8");
                        }
                        resultModel.setMessage(mess);

                        functionCallback.apply(resultModel);

                    } catch (UnsupportedEncodingException e) {
                        resultModel.setErrorCode(constant.ERROR_CODE_ERROR_CLIENT);
                        resultModel.setMessage(e.getMessage());
                        functionCallback.apply(resultModel);
                    }
                }
            }, util.parseParamsString(params)) {
                @Override
                public Map<String, String> getHeaders() throws AuthFailureError {
//                    Set - Cookie: <cookie - name >=<cookie - value >;
                    CookieDB cookieDB = AppDatabase.getInstance(context).cookieDAO().findById(constant.DB_FIRST_ID);
                    HashMap<String, String> headers = new HashMap<String, String>();
                    if (cookieDB != null) {
                        Date now = new Date();
                        Long remaining = now.getTime() - cookieDB.getDateCreate();
                        if ((remaining > constant.MILISECOND_TO_HOUR)) {
                            Intent intent = new Intent(context, LoginActivity.class);
                            context.startActivity(intent);
                        } else {
                            headers.put("Accept", "application/json");
                            if (cookieDB != null) {
                                try {
                                    String cookie = URLEncoder.encode(cookieDB.getToken(), "UTF-8");
                                    headers.put("Cookie", constant.COOKIE_KEY + "=" + cookie);
                                } catch (UnsupportedEncodingException e) {
                                    throw new AuthFailureError("Pass cookie error");
                                }
                            } else {
                                headers.put("Cookie", constant.COOKIE_KEY + "=");
                                MainActivity.isLogin = false;
                            }
                        }
                    }
                    return headers;
                }
            };

            // SET TIMEOUT
            progress.setDownTimer(new CountDownTimer(constant.MILISECOND_TIME_OUT, constant.MILISECOND_TIME_TICK) {
                @Override
                public void onTick(long millisUntilFinished) {
                }

                @Override
                public void onFinish() {
                    progress.dismiss();
                    request.cancel();
                    util.createAlert(context, util.ALERT_STATUS.ALERT_STATUS_DANGER, util.ALERT_TYPE.ALERT_TYPE_INFO,
                            "Timeout load data \n Request canceled!", new util.FunctionCallback3<AlertDialog.Builder, View, Boolean>() {
                                @Override
                                public Boolean apply(AlertDialog.Builder builder, View view) {
                                    return null;
                                }
                            });
                }
            });
            progress.getDownTimer().start();
            request.setRetryPolicy(new DefaultRetryPolicy(5000, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
            request.setShouldCache(false);
            APIFetch.getInstance(context).addToRequestQueue(request);
        } catch (Exception e) {
            ResultModel resultModel = new ResultModel();
            resultModel.setErrorCode(constant.ERROR_CODE_ERROR_CLIENT);
            resultModel.setMessage(e.getMessage());
            functionCallback.apply(resultModel);
        }
    }

    protected void fetchDataStreamFromApi(String url, HashMap<String, Object> params, int method,
                                          final util.FunctionCallback2<ResultModel, Boolean> functionCallback) {
        // map.put();- values for request body.
        try {
            Activity activity = (Activity) context;
            util.DialogProgress progress = new util.DialogProgress(activity);
            progress.start();
            InputStreamVolleyRequest request = new InputStreamVolleyRequest(method, url

                    , new Response.Listener<byte[]>() {

                @Override
                public void onResponse(byte[] response) {
                    // TODO handle the response
                    progress.dismiss();
                    ResultModel resultModel = new ResultModel();

                    try {
                        if (response != null && response.length > 0) {
                            resultModel.setMessage("Success");
                            resultModel.setErrorCode(constant.ERROR_CODE_SUCCESS);
                            resultModel.setData(response);
                            functionCallback.apply(resultModel);
                        } else {
                            resultModel.setMessage("Can't download file (length = " + response.length + ")");
                            resultModel.setErrorCode(constant.ERROR_CODE_ERROR_NULL);
                            functionCallback.apply(resultModel);
                        }
                    } catch (Exception e) {
                        // TODO Auto-generated catch block
                        resultModel.setErrorCode(constant.ERROR_CODE_ERROR_NULL);
                        resultModel.setMessage("UNABLE TO DOWNLOAD FILE");
                        functionCallback.apply(resultModel);
                    }
                }
            }, new Response.ErrorListener() {

                @Override
                public void onErrorResponse(VolleyError error) {
                    ResultModel resultModel = new ResultModel();

                    try {
                        progress.dismiss();

                        resultModel.setErrorCode(constant.ERROR_CODE_ERROR_SERVER);
                        String mess = "Can't connect to server : " + MainActivity.appServerModel.getUrl();
                        if (error.networkResponse == null && error.getLocalizedMessage() != null) {
                            mess = error.getLocalizedMessage();
                        } else if (error.networkResponse != null) {
                            mess = error.networkResponse.statusCode
                                    + " - " + new String(error.networkResponse.data, "UTF-8");
                        }
                        resultModel.setMessage(mess);

                        functionCallback.apply(resultModel);

                    } catch (UnsupportedEncodingException e) {
                        resultModel.setErrorCode(constant.ERROR_CODE_ERROR_CLIENT);
                        resultModel.setMessage(e.getMessage());
                        functionCallback.apply(resultModel);
                    }
                }
            }, util.parseParamsString(params)) {
                @Override
                public Map<String, String> getHeaders() throws AuthFailureError {
//                    Set-Cookie: <cookie-name>=<cookie-value>;
                    CookieDB cookieDB = AppDatabase.getInstance(context).cookieDAO().findById(constant.DB_FIRST_ID);
                    HashMap<String, String> headers = new HashMap<String, String>();
                    if (cookieDB != null) {
                        headers.put(constant.COOKIE_KEY_SET, constant.COOKIE_KEY + "=" + cookieDB.getToken());
                    }
                    return headers;
                }
            };

            request.setRetryPolicy(new DefaultRetryPolicy(5000, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
            request.setShouldCache(false);
            APIFetch.getInstance(context).addToRequestQueue(request);
        } catch (Exception e) {
            util.createAlert(context, util.ALERT_STATUS.ALERT_STATUS_DANGER, util.ALERT_TYPE.ALERT_TYPE_INFO, e.getMessage(), null);
        }
    }

    class InputStreamVolleyRequest extends Request<byte[]> {
        private final Response.Listener<byte[]> mListener;
        private Map<String, String> mParams;
        //create a static map for directly accessing headers
        public Map<String, String> responseHeaders;

        public InputStreamVolleyRequest(int method, String mUrl, Response.Listener<byte[]> listener,
                                        Response.ErrorListener errorListener, HashMap<String, String> params) {
            // TODO Auto-generated constructor stub
            super(method, mUrl, errorListener);
            mListener = listener;
            mParams = params;

        }

        @Override
        protected Map<String, String> getParams()
                throws com.android.volley.AuthFailureError {
            return mParams;
        }

        @Override
        protected void deliverResponse(byte[] response) {
            mListener.onResponse(response);
        }

        @Override
        protected Response<byte[]> parseNetworkResponse(NetworkResponse response) {

            try {
                //Initialise local responseHeaders map with response headers received
                responseHeaders = response.headers;
                //Pass the response data here
                return Response.success(response.data, HttpHeaderParser.parseCacheHeaders(response));
            } catch (Exception e) {
                throw e;
            }

        }
    }

    class ResultBaseVolleyRequest extends Request<ResultModel> {
        private final Response.Listener<ResultModel> mListener;
        private Map<String, String> mParams;
        //create a static map for directly accessing headers
        public Map<String, String> responseHeaders;

        public ResultBaseVolleyRequest(int method, String mUrl, Response.Listener<ResultModel> listener,
                                       Response.ErrorListener errorListener, HashMap<String, String> params) {
            // TODO Auto-generated constructor stub
            super(method, mUrl, errorListener);
            mListener = listener;
            mParams = params;

        }

        @Override
        protected Map<String, String> getParams()
                throws com.android.volley.AuthFailureError {
            return mParams;
        }

        @Override
        protected void deliverResponse(ResultModel response) {
            mListener.onResponse(response);
        }

        @Override
        protected Response<ResultModel> parseNetworkResponse(NetworkResponse response) {

            try {
                //Initialise local responseHeaders map with response headers received
                responseHeaders = response.headers;
                //Pass the response data here
                String json = new String(response.data, HttpHeaderParser.parseCharset(response.headers));
                ResultModel resultModel = new ResultModel();
                ResultBase<ResultModel> resultBase = new ResultBase<>(resultModel);
                return Response.success(resultBase.Parse(json, PropertyNamingStrategy.LOWER_CAMEL_CASE), HttpHeaderParser.parseCacheHeaders(response));
            } catch (IOException e) {
                try {
                    throw e;
                } catch (IOException ex) {
                    ex.printStackTrace();
                }

            }
            return null;

        }
    }
}