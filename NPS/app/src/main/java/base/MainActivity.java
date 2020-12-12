package base;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import base.core.api.AppController;
import base.core.api.SocketController;
import base.core.lib.constant;
import base.core.lib.util;
import base.core.model.ServerModel;
import base.core.model.UserModel;
import base.core.model.metadata.MetadataModel;
import base.core.mudules.database.AppDatabase;
import base.core.mudules.database.CookieDB;
import base.core.mudules.files_management.FileBase;
import base.core.mudules.map.MapBase;
import base.core.mudules.services.MainService;
import base.ui.about.AboutFragment;
import base.ui.atm.AtmFragment;
import base.ui.home.HomeViewModel;
import base.ui.incident.IncidentFragment;
import base.ui.login.LoginActivity;
import base.ui.setting.SettingFragment;
import microsoft.aspnet.signalr.client.Platform;
import microsoft.aspnet.signalr.client.SignalRFuture;
import microsoft.aspnet.signalr.client.http.android.AndroidPlatformComponent;
import microsoft.aspnet.signalr.client.hubs.HubConnection;
import microsoft.aspnet.signalr.client.hubs.HubProxy;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.example.nps.R;
import com.google.android.material.navigation.NavigationView;
import com.microsoft.signalr.HubConnectionBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    public static boolean isLogin = false;
    @SuppressLint("StaticFieldLeak")
    public static AppController appController;
    public static AppDatabase appDatabase;
    public static MetadataModel metadataModel;
    //Views
    public NavigationView viewMenu;
    public DrawerLayout viewMenuDraw;
    public ImageView btnMenu;
    public LinearLayout viewNavHeader;
    public LinearLayout viewMainContent;

    private AppBarConfiguration mAppBarConfiguration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            setContentView(R.layout.activity_main);
            appController = new AppController(MainActivity.this);
            appDatabase = AppDatabase.getInstance(getApplicationContext());
//
//            appCookie = MainActivity.appDatabase.cookieDAO().findById(constant.DB_FIRST_ID);
//            appServerModel = MainActivity.appDatabase.serverDAO().findById(constant.DB_FIRST_ID);
//            appUserModel = MainActivity.appDatabase.userDAO().findById(constant.DB_FIRST_ID);
            if (appCookie == null || appServerModel == null) {
                util.createViewLogin(MainActivity.this);
                return;
            } else {
                if (appCookie != null) {
                    Date now = new Date();
                    Long remaining = now.getTime() - appCookie.getDateCreate();
                    if ((remaining > constant.MILISECOND_TO_HOUR)) {
                        util.createViewLogin(MainActivity.this);
                    } else {
                        appController.checkLogin();
                    }
                }
            }
            viewMainContent = findViewById(R.id.llMain);
            viewMenu = this.findViewById(R.id.nav_view);
            viewMenuDraw = (DrawerLayout) this.findViewById(R.id.drawer_layout_menu);
            viewNavHeader = (LinearLayout) viewMenu.getHeaderView(0);
            ImageView imageUser = viewNavHeader.findViewById(R.id.imageUserAfter);


            if (appUserModel != null && appUserModel.getImageUrl() != "") {
                String url = appServerModel.getUrl() + constant.URL_USER_IMAGE + appUserModel.getImageUrl();
                appController.downloadFiles(url, Request.Method.GET, new util.FunctionCallback2() {
                    @Override
                    public Object apply(Object o) {
                        Bitmap bitmap = BitmapFactory.decodeByteArray((byte[]) o, 0, ((byte[]) o).length);
                        if (bitmap != null) {
                            imageUser.setImageBitmap(bitmap);
                        }
                        return null;
                    }
                });

            }
            //Load meta data
            if (metadataModel == null) {
                metadataModel = new MetadataModel();
                metadataModel.load();
                if (metadataModel.getErrorModels().size() == 0) {
                    appController.loadMetaData();
                }
            }

            Intent myIntent = new Intent(MainActivity.this, MainService.class);
            // Call startService with Intent parameter.
            this.startService(myIntent);

            TextView userName = viewNavHeader.findViewById(R.id.userNameAfter);
            userName.setText(appUserModel.getUserFullName());
            btnMenu = this.findViewById(R.id.btnMenu);
            btnMenu.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    viewMenuDraw.openDrawer(Gravity.LEFT);
                }
            });
            viewMenu.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    TextView txtTitle = (TextView) MainActivity.this.findViewById(R.id.txtNameFragment);
                    FragmentManager fragmentManager = getSupportFragmentManager();
                    viewMainContent.removeAllViews();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

                    switch (item.getItemId()) {
                        case R.id.nav_item_sign_out: {
                            util.createViewLogin(MainActivity.this);
                            break;
                        }
                        case R.id.nav_item_about: {
                            AboutFragment aboutFragment = new AboutFragment();
                            fragmentTransaction.add(viewMainContent.getId(), aboutFragment);
                            fragmentTransaction.commit();
                            break;
                        }
                        case R.id.nav_item_system_log: {

                            break;
                        }
                        case R.id.nav_item_setting: {
                            SettingFragment settingFragment = new SettingFragment();
                            fragmentTransaction.add(viewMainContent.getId(), settingFragment);
                            fragmentTransaction.commit();
                            break;
                        }
                        case R.id.nav_refernence: {

                            break;
                        }
                        case R.id.nav_contact: {

                            break;
                        }
                        case R.id.nav_mail: {

                            break;
                        }
                        case R.id.nav_contract: {

                            break;
                        }
                        case R.id.nav_atm: {
                            AtmFragment atmFragment = new AtmFragment();
                            fragmentTransaction.add(viewMainContent.getId(), atmFragment);
                            fragmentTransaction.commit();
                            appController.loadAtmWithPaging(0, viewMainContent, MainActivity.this);
                            break;
                        }
                        case R.id.nav_team_incident: {

                            break;
                        }
                        case R.id.nav_incident: {
                            IncidentFragment incidentFragment = new IncidentFragment();
                            fragmentTransaction.add(viewMainContent.getId(), incidentFragment);
                            fragmentTransaction.commit();
                            break;
                        }
                    }
                    //close navigation drawer
                    txtTitle.setText(item.getTitle());
                    viewMenuDraw.closeDrawer(Gravity.LEFT);
                    return true;
                }
            });
        } catch (Exception e) {
            util.createAlert(this, util.ALERT_STATUS.ALERT_STATUS_DANGER, util.ALERT_TYPE.ALERT_TYPE_INFO, e.getMessage(), new util.FunctionCallback3<AlertDialog.Builder, View, Boolean>() {
                @Override
                public Boolean apply(AlertDialog.Builder builder, View view) {
                    util.createViewLogin(MainActivity.this);
                    return false;
                }
            });

        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

}
