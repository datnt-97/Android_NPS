package base.core.lib;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.location.Location;
import android.os.CountDownTimer;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.core.text.HtmlCompat;

import com.example.nps.R;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;

import base.MainActivity;
import base.core.model.ServerModel;
import base.core.mudules.database.CookieDB;
import base.ui.login.LoginActivity;

import static base.MainActivity.appDatabase;
import static base.MainActivity.appServerModel;

import java.lang.reflect.Method;
import java.util.regex.*;

public class util {
    public enum ALERT_STATUS {
        ALERT_STATUS_SUCCESS,
        ALERT_STATUS_WARNING,
        ALERT_STATUS_DANGER,
        ALERT_STATUS_INFOR
    }

    public enum ALERT_TYPE {
        ALERT_TYPE_COMFIRM,
        ALERT_TYPE_INFO,
        ALERT_TYPE_ORTHER
    }

    public enum CONDITION {
        MORE_THAN,
        MORE_THAN_AS,
        LESS_THEN,
        LESS_THEN_AS,
        AS
    }

    public static boolean checkDate(Date date, Date date2, CONDITION condition) {
        switch (condition) {
            case AS:
                return date.compareTo(date2) == 0;
            case LESS_THEN:
                return date.compareTo(date2) == -1;
            case MORE_THAN:
                return date.compareTo(date2) == 1;
            case LESS_THEN_AS:
                return date.compareTo(date2) <= -1;
            case MORE_THAN_AS:
                return date.compareTo(date2) >= 1;
        }
        return true;
    }


    public static Date stringToDate(String dates) throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(constant.FORMAT_DATE);
        Date date;
        try {
            date = simpleDateFormat.parse(dates);
        } catch (ParseException e) {
            throw e;
        }
        return date;
    }

    public static Date stringToDatetime(String dates) throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(constant.FORMAT_TIME);
        Date date;
        try {
            date = simpleDateFormat.parse(dates);
        } catch (ParseException e) {
            throw e;
        }
        return date;
    }

    public static String dateToString(String date) {
        Date dates = new Date(date);
        DateFormat dateFormat = new SimpleDateFormat(constant.FORMAT_DATE);
        return dateFormat.format(dates);
    }

    public static String dateTimeToString(Date date) {
        DateFormat dateFormat = new SimpleDateFormat(constant.FORMAT_TIME);
        return dateFormat.format(date);
    }

    public static boolean hasHTMLTags(String text) {
        Pattern pattern = Pattern.compile(constant.HTML_PATTERN);
        Matcher matcher = pattern.matcher(text);
        return matcher.find();
    }

    public static class Alert {
        public static void alertError(Context context, String message, final util.FunctionCallback3<AlertDialog.Builder, View, Boolean> functionCallback) {
            util.createAlert(context, util.ALERT_STATUS.ALERT_STATUS_DANGER, util.ALERT_TYPE.ALERT_TYPE_INFO,
                    message, functionCallback);
        }

        public static void alertSuccess(Context context, String message, final util.FunctionCallback3<AlertDialog.Builder, View, Boolean> functionCallback) {
            util.createAlert(context, ALERT_STATUS.ALERT_STATUS_SUCCESS, ALERT_TYPE.ALERT_TYPE_INFO,
                    message, functionCallback);
        }

    }

    public static void createAlert(Context context, ALERT_STATUS alert_status, ALERT_TYPE alert_type, String message,
                                   final util.FunctionCallback3<AlertDialog.Builder, View, Boolean> functionCallback) {
        Activity activity = (Activity) context;
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        LayoutInflater inflater = activity.getLayoutInflater();
//        layout_mess
        View views = inflater.inflate(R.layout.layout_alert, null);
        ImageView icon = views.findViewById(R.id.alert_icon_title);
        TextView title = views.findViewById(R.id.alert_title);
        TextView mess = views.findViewById(R.id.alert_message);
        Button btnClose = views.findViewById(R.id.btn_close);
        Button btnOk = views.findViewById(R.id.btn_ok);
        LinearLayout linearLayoutMess = views.findViewById(R.id.layout_mess);
        builder.setCancelable(false);
        if (hasHTMLTags(message)) {
            WebView webView = new WebView(context);
            webView.getSettings().setJavaScriptEnabled(true);
            webView.loadDataWithBaseURL("", message, "text/html", "UTF-8", "");
            linearLayoutMess.removeAllViews();
            linearLayoutMess.addView(webView);
//            mess.setText(HtmlCompat.fromHtml(message, 0));
        } else {
            mess.setText(message);
        }

        switch (alert_status) {
            case ALERT_STATUS_INFOR:
                title.setText("Info");
                icon.setBackgroundResource(R.drawable.ic_info_black_24dp);
                break;
            case ALERT_STATUS_DANGER:
                title.setText("Danger");
                icon.setBackgroundResource(R.drawable.ic_cancel_black_24dp);
                break;
            case ALERT_STATUS_SUCCESS:
                title.setText("Success");
                icon.setBackgroundResource(R.drawable.ic_check_circle_black_24dp);

                break;
            case ALERT_STATUS_WARNING:
                title.setText("Warning");
                icon.setBackgroundResource(R.drawable.ic_warning_black_24dp);
                break;
        }
        switch (alert_type) {
            case ALERT_TYPE_INFO:

                btnOk.setVisibility(View.INVISIBLE);
                builder.setView(views);
                final AlertDialog dialog1 = builder.show();

                btnClose.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (functionCallback != null) {
                            functionCallback.apply(builder, views);
                        }
                        dialog1.dismiss();
                    }
                });
                break;
            case ALERT_TYPE_COMFIRM:
                title.setText("Confirm");
                builder.setView(views);
                final AlertDialog dialog = builder.show();
                btnOk.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (functionCallback != null) {
                            functionCallback.apply(builder, views);
                        }
                        dialog.dismiss();
                    }
                });
                btnClose.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
                break;
            case ALERT_TYPE_ORTHER:
                btnOk.setVisibility(View.INVISIBLE);
                if (functionCallback != null) {
                    functionCallback.apply(builder, views);
                }
                break;
        }


    }

    public static <T> HashMap<String, Object> parseParams(T model) {
        ObjectMapper mapObject = new ObjectMapper();
        HashMap<String, Object> params = mapObject.convertValue(model, HashMap.class);
        return params;
    }

    public static <T> HashMap<String, String> parseParamsString(T model) {
        ObjectMapper mapObject = new ObjectMapper();
        HashMap<String, String> params = mapObject.convertValue(model, HashMap.class);
        return params;
    }

    public static class DialogProgress {
        Activity acitvity;
        AlertDialog dialog;
        CountDownTimer downTimer;

        public CountDownTimer getDownTimer() {
            return downTimer;
        }

        public void setDownTimer(CountDownTimer downTimer) {
            this.downTimer = downTimer;
        }

        public DialogProgress(Activity myAcitvity) {
            acitvity = myAcitvity;
        }

        public void start() {
            AlertDialog.Builder builder = new AlertDialog.Builder(acitvity);
            LayoutInflater inflater = acitvity.getLayoutInflater();
            builder.setView(inflater.inflate(R.layout.progress_bar, null));
            builder.setCancelable(false);
            dialog = builder.create();
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

            dialog.show();
        }

        public void dismiss() {
            dialog.dismiss();
        }
    }

    public interface FunctionCallback3<In0, In2, Out> {
        public Out apply(In0 in0, In2 in2);
    }

    public interface FunctionCallback<Out> {
        public Out apply();
    }

    public interface FunctionCallback2<In0, Out> {
        public Out apply(In0 in0);
    }

    //Map Util
    public static final String KEY_REQUESTING_LOCATION_UPDATES = "requesting_locaction_updates";

    /**
     * Returns true if requesting location updates, otherwise returns false.
     *
     * @param context The {@link Context}.
     */
    public static boolean requestingLocationUpdates(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context)
                .getBoolean(KEY_REQUESTING_LOCATION_UPDATES, false);
    }

    /**
     * Stores the location updates state in SharedPreferences.
     *
     * @param requestingLocationUpdates The location updates state.
     */
    public static void setRequestingLocationUpdates(Context context, boolean requestingLocationUpdates) {
        PreferenceManager.getDefaultSharedPreferences(context)
                .edit()
                .putBoolean(KEY_REQUESTING_LOCATION_UPDATES, requestingLocationUpdates)
                .apply();
    }

    /**
     * Returns the {@code location} object as a human readable string.
     *
     * @param location The {@link Location}.
     */
    public static String getLocationText(Location location) {
        return location == null ? "Unknown location" :
                "(" + location.getLatitude() + ", " + location.getLongitude() + ")";
    }

    public static void createViewLogin(Context context) {
        appDatabase.cookieDAO().deleteAll(appDatabase.cookieDAO().findAll());
        if (appServerModel == null || !appServerModel.isRemember()) {
            appDatabase.userDAO().deleteAll(appDatabase.userDAO().findAll());
        }
        Intent intent = new Intent(context, LoginActivity.class);
        ((Activity) context).startActivity(intent);
        ((Activity) context).finish();
    }

    public static String mapMessageByCode(int code) {
        switch (code) {
            default:
                return "Successfully";
            case constant.ERROR_CODE_GENARAL:
                return "Error genaral";
            case constant.ERROR_CODE_AUTHENTICATE:
                return "Error authenticate failed";
            case constant.ERROR_CODE_NOT_FOUND:
                return "Error not found";
            case constant.ERROR_CODE_MISSING_PARAM:
                return "Error missing param";
            case constant.ERROR_CODE_ERROR_TYPE:
                return "Error passing type";
            case constant.ERROR_CODE_ERROR_LIMIT:
                return "Error limit";
            case constant.ERROR_CODE_ERROR_NULL:
                return "Error empty";
            case constant.ERROR_CODE_ERROR_SERVER:
                return "Error from server";
            case constant.ERROR_CODE_ERROR_SEND_EMAIL_FAIL:
                return "Error send mail failed";
            case constant.ERROR_CODE_ERROR_INVALID:
                return "Error invalid";
            case constant.ERROR_CODE_ERROR_OVER_TIME:
                return "Error over time";
            case constant.ERROR_CODE_ACCEPT_DENIED:
                return "Access denied";
        }
    }
    //    Load bitmap

    public static class Network {
        public static HashMap<String, String> getHeader() {
            CookieDB cookieDB = MainActivity.appDatabase.cookieDAO().findById(constant.DB_FIRST_ID);
            HashMap<String, String> headers = new HashMap<String, String>();
            headers.put("Accept", "application/json");
            if (cookieDB != null) {
                headers.put(constant.COOKIE_KEY_SET, constant.COOKIE_KEY + "=" + cookieDB.getToken());
            }
            return headers;
        }

        public static String getURL() {
            ServerModel serverModel = appDatabase.serverDAO().findById(constant.DB_DEFAULT_ID);
            if (serverModel != null) {
                return serverModel.getUrl();
            }
            return "";
        }
    }

    public static class ProcessClass {

        //    Properties
        public static <T> T getProperty(Object obj, String property, T defaultValue) {

            T returnValue = (T) getProperty(obj, property);
            if (returnValue == null) {
                returnValue = defaultValue;
            }
            return returnValue;

        }

        public static Object getProperty(Object obj, String property) {
            Object returnValue = null;

            try {
                String methodName = "get" + property.substring(0, 1).toUpperCase() + property.substring(1, property.length());
                Class clazz = obj.getClass();
                Method method = clazz.getMethod(methodName, null);
                returnValue = method.invoke(obj, null);
            } catch (Exception e) {
                // Do nothing, we'll return the default value
            }

            return returnValue;
        }

        public static <T> ArrayList<T> listOf(Class<T> clazz) {
            return new ArrayList<T>();
        }
    }


    public static PackageInfo getPackageIngfo(Context context) throws PackageManager.NameNotFoundException {
        PackageManager manager = context.getPackageManager();
        PackageInfo info = manager.getPackageInfo(context.getPackageName(), PackageManager.GET_ACTIVITIES);
        return info;
    }
}
