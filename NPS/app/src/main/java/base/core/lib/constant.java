package base.core.lib;

import android.app.Application;
import android.os.Environment;

import java.util.HashMap;
import java.util.Map;

public class constant {
    public static final String FORMAT_TIME = "dd/MM/yyyy HH:mm:ss";
    public static final String FORMAT_DATE = "dd/MM/yyyy";
    public static final String TIME_ZONE_HCM = "Asia/Ho_Chi_Minh";
    public static final long DAY_PER_MILIS = 86400000;

    public static final int ERROR_CODE_SUCCESS = 0;
    public static final int ERROR_CODE_GENARAL = -1000;
    public static final int ERROR_CODE_AUTHENTICATE = -999;
    public static final int ERROR_CODE_NOT_FOUND = -998;
    public static final int ERROR_CODE_MISSING_PARAM = -997;
    public static final int ERROR_CODE_ERROR_TYPE = -996;
    public static final int ERROR_CODE_ERROR_LIMIT = -995;
    public static final int ERROR_CODE_ERROR_NULL = -994;
    public static final int ERROR_CODE_ERROR_SERVER = -993;
    public static final int ERROR_CODE_ERROR_SEND_EMAIL_FAIL = -992;
    public static final int ERROR_CODE_ERROR_INVALID = -991;
    public static final int ERROR_CODE_ERROR_OVER_TIME = -990;
    public static final int ERROR_CODE_ACCEPT_DENIED = -989;
    public static final int ERROR_CODE_SQL = -988;
    public static final int ERROR_CODE_STACK_OVER_FLOW = -987;
    public static final int ERROR_CODE_OUT_OF_MEMORY = -986;
    public static final int ERROR_CODE_ARGRUMENT_ERROR = -984;
    public static final int ERROR_CODE_SYSTEM_ERROR = -984;

    public static final int ERROR_CODE_ERROR_CLIENT = -13;
    public static final String[] MIME_TYPE_IMAGE = {"image/jpeg", "image/png"};
    public static final int GALLERY_REQUEST_CODE = 1;
    public static final int TASK_REQUEST_CODE = 0;

    public static final int PORT_DEFAUT = 80;


    //    URL constant
    public static final String URL_CHECK_LOGIN = "/api/User/CheckLogin";
    public static final String URL_LOGIN = "/api/User/Login";
    public static final String URL_LOGOUT = "/api/User/Logout";
    public static final String URL_TICKET = "/api/incident/incidents";
    public static final String URL_TICKET_DETAIL = "/api/User/Logout";
    public static final String URL_ATM = "/api/ATM";
    public static final String URL_META_DATA = "/api/BaseAPI/Metadata";
    public static final String URL_ATM_DETAIL = "/api/User/Logout";
    public static final String URL_USER_IMAGE = "/ckeditor/FileUpload/HoSoNhanVien/";
    public static final String URL_SOCKET = "/signalr";

    // Files
    public static final long LIMIT_RECORD_LOAD = 10;
    public static final long LIMIT_FILES_UPLOAD = 5;
    public static final long LIMIT_FILES_SIZE_UPLOAD = 100;//10 mb
    public static final long IO_BUFFER_SIZE = 4 * 1024;//10 mb


    //Db
    public static final String TABLE_COOKIE_TABLE_NAME = "Cookie";
    public static final String TABLE_ATM_TABLE_NAME = "ATM";
    public static final String TABLE_USER_TABLE_NAME = "User";
    public static final String TABLE_SERVER_TABLE_NAME = "Server";
    public static final String TABLE_ERROR_TABLE_NAME = "Error";
    public static final String TABLE_ERROR_GROUP_TABLE_NAME = "Error_Group";
    public static final String TABLE_ERROR_HANDLE_TABLE_NAME = "Error_Handle";
    public static final String TABLE_ERROR_REASON_TABLE_NAME = "Error_Reason";
    public static final String TABLE_ERROR_REASON_DETAIL_TABLE_NAME = "Error_Reason_Detail";
    public static final String TABLE_ERROR_REASON_GROUP_TABLE_NAME = "Error_Reason_Group";
    public static final String TABLE_PROVICE_TABLE_NAME = "Provice";
    public static final String TABLE_STOCK_TABLE_NAME = "Stock";
    public static final String TABLE_INCIDENT_TABLE_NAME = "Incident";
    public static final String TABLE_BRANCH_TABLE_NAME = "Branch";
    public static final String TABLE_CUSTOMER_TABLE_NAME = "Customer";
    public static final String TABLE_EMPLOYEE_TABLE_NAME = "Employee";
    public static final String TABLE_REQUEST_TYPE_TABLE_NAME = "RequestTypeModel";
    public static final String TABLE_CONTRACT_TABLE_NAME = "Contract";
    public static final String TABLE_CHANEL_TABLE_NAME = "Chanel";
    public static final String TABLE_REQUEST_STATUS_TABLE_NAME = "RequesStatus";
    public static final String TABLE_PROCESS_STATUS_TABLE_NAME = "ProcessStatus";


    public static final int DB_FIRST_ID = 1;
    public static final int DB_DEFAULT_ID = -1;


    //

    public static final String COOKIE_KEY_SET = "Set-Cookie";
    public static final String COOKIE_KEY = "AuthorizeToken";

    //TIME
    public static final long MILISECOND_TO_HOUR = 3600000;
    public static final double COOKIE_TIME = 0.02; //1 hour
    public static final long MILISECOND_TIME_OUT = 1000 * 30;
    public static final long MILISECOND_TIME_TICK = 1000;

    public static final String HTML_PATTERN = "<(\"[^\"]*\"|'[^']*'|[^'\">])*>";

    //GPS
    public static final long GPS_UPDATE_INTERVAL = 10 * 1000;  /* 10 secs */
    public static final long GPS_FASTEST_INTERVAL = 2000; /* 2 sec */
    public static final long GPS_DISTANCE = 5; /* 5 meter */
    public static final long GPS_RADIUS_EARTH = 6371; /*  kilometer */
    public static final double GPS_CONVERT_KILOMETER = 1.609344; /*  kilometer */
    public static final double GPS_CONVERT_MILE = 0.8684; /*  mile */
    public static final double GPS_CONVERT_METER = GPS_CONVERT_KILOMETER * 1609.344; /*  meter */


    //SOCKET
    public static final String SOCKET_HUB_CHAT = "ChatHub";
    public static final String SOCKET_METHOD_SEND = "Send";
}
