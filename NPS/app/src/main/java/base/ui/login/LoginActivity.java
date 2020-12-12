package base.ui.login;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.location.Location;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Switch;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;

import base.MainActivity;

import com.example.nps.R;

import base.core.api.AppController;
import base.core.api.SocketController;
import base.core.lib.constant;
import base.core.lib.util;
import base.core.mudules.database.CookieDB;
import microsoft.aspnet.signalr.client.Platform;
import microsoft.aspnet.signalr.client.http.android.AndroidPlatformComponent;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;

import java.util.List;

public class LoginActivity extends Activity {

    private TextView txtUserName;
    private TextView txtPassword;
    private TextView txtServer;
    private TextView txtPort;
    private Button btnLogin;
    private Button btnForgetPassword;
    private Switch isSsl;
    private CheckBox cbRemember;
    private AwesomeValidation awesomeValidation;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        awesomeValidation = new AwesomeValidation(ValidationStyle.BASIC);
        btnLogin = (Button) findViewById(R.id.btnLogin);
        btnForgetPassword = (Button) findViewById(R.id.btnLinkToRegisterScreen);
        txtUserName = (TextView) findViewById(R.id.userName);
        txtPassword = (TextView) findViewById(R.id.password);
        txtServer = (TextView) findViewById(R.id.server);
        txtPort = (TextView) findViewById(R.id.port);
        isSsl = (Switch) findViewById(R.id.isSSL);
        cbRemember = (CheckBox) findViewById(R.id.cbRemember);
        if (MainActivity.appServerModel != null) {
            txtServer.setText(MainActivity.appServerModel.getServername());
            txtPort.setText(String.valueOf(MainActivity.appServerModel.getPort()));
            isSsl.setChecked(MainActivity.appServerModel.isSSl());
            cbRemember.setChecked(MainActivity.appServerModel.isRemember());
            if (MainActivity.appServerModel.isRemember()) {
                txtUserName.setText(MainActivity.appUserModel.getUserName());
                txtPassword.setText(MainActivity.appUserModel.getPassword());
            }
        }


        //adding validation to edittexts
        awesomeValidation.addValidation(this, R.id.userName, "^(?=\\s*\\S).*$", R.string.valid_user_name);
        awesomeValidation.addValidation(this, R.id.password, "^(?=\\s*\\S).*$", R.string.valid_password);
        awesomeValidation.addValidation(this, R.id.server, "^(?=\\s*\\S).*$", R.string.valid_server_name);
//        awesomeValidation.addValidation(this, R.id.port, "^(?=\\s*\\S).*$", R.string.valid_port);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    if (awesomeValidation.validate()) {
                        btnLogin.setClickable(false);
                        if (!btnLogin.isClickable()) {
                            MainActivity.appController = new AppController(
                                    txtServer.getText().toString().trim(),
                                    Integer.parseInt(txtPort.getText().toString() != "" ?
                                            txtPort.getText().toString() :
                                            String.valueOf(constant.PORT_DEFAUT)),
                                    isSsl.isChecked(), cbRemember.isChecked(), LoginActivity.this);
                            MainActivity.appController.login(txtUserName.getText().toString(), txtPassword.getText().toString());

                        }
                        btnLogin.setClickable(true);
                    }
//                    }
                } catch (Exception e) {
                    btnLogin.setClickable(true);
                    util.createAlert(LoginActivity.this, util.ALERT_STATUS.ALERT_STATUS_DANGER, util.ALERT_TYPE.ALERT_TYPE_INFO,
                            e.getMessage(), null);
                }
            }
        });
        btnForgetPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                MainActivity.appController.setContext(LoginActivity.this);

//                https://npss.vn/MobiFunc/DownloadAttachment?Name=BSMS_110620103328.apk
                //Create an Intent with action as ACTION_PICK
//                FileBase.pickImage(LoginActivity.this, true);

                try {
                    SocketController.getInstance().connectHubChat().invoke(constant.SOCKET_METHOD_SEND, "Client", "Hello world!").get();
                    SocketController.getInstance().disconnect();
                } catch (Exception e) {
                    util.createAlert(LoginActivity.this, util.ALERT_STATUS.ALERT_STATUS_INFOR, util.ALERT_TYPE.ALERT_TYPE_INFO, e.getMessage(), null);

                }
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case constant.TASK_REQUEST_CODE:
                if (resultCode == RESULT_OK && data != null) {
                    Bitmap selectedImage = (Bitmap) data.getExtras().get("data");
                }

                break;
            case constant.GALLERY_REQUEST_CODE:
                if (resultCode == RESULT_OK && data != null) {
                    Uri selectedImage = data.getData();
                    String[] filePathColumn = {MediaStore.Images.Media.DATA};
                    if (selectedImage != null) {
                        Cursor cursor = getContentResolver().query(selectedImage,
                                filePathColumn, null, null, null);
                        if (cursor != null) {
                            cursor.moveToFirst();

                            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                            String picturePath = cursor.getString(columnIndex);
                            cursor.close();
                        }
                    }

                }
                break;
        }
    }

    private boolean checkAutoLogin() {
        return false;
    }
}

