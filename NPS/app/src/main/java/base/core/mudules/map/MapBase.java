package base.core.mudules.map;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Looper;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.SettingsClient;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;

import java.util.concurrent.ExecutionException;

import base.MainActivity;
import base.core.api.SocketController;
import base.core.lib.constant;
import base.core.lib.util;
import base.ui.login.LoginActivity;

public class MapBase {
    private static boolean isPermission = true;
    private LocationRequest mLocationRequest;

    FusedLocationProviderClient locationClient;

    public boolean isPermission() {
        return isPermission;
    }

    public void setPermission(boolean permission) {
        isPermission = permission;
    }

    public MapModel getMapModel() {
        return mapModel;
    }

    public void setMapModel(MapModel mapModel) {
        this.mapModel = mapModel;
    }

    public MapModel mapModel = new MapModel();

    private Context _context;

    public MapBase(@NonNull Context context) {
        _context = context;
    }

    public MapBase() {
    }


    private void showMessageError(String mess) {
        util.createAlert(_context, util.ALERT_STATUS.ALERT_STATUS_DANGER, util.ALERT_TYPE.ALERT_TYPE_INFO, mess, null);
    }

    //    @SuppressLint("MissingPermission")
    public void startLocationUpdates() {

        try {
            // Create the location request to start receiving updates
            mLocationRequest = new LocationRequest();
            mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
            mLocationRequest.setInterval(constant.GPS_UPDATE_INTERVAL);
            mLocationRequest.setFastestInterval(constant.GPS_FASTEST_INTERVAL);
            mLocationRequest.setSmallestDisplacement(constant.GPS_DISTANCE);
            // Create LocationSettingsRequest object using location request
            LocationSettingsRequest.Builder builder = new LocationSettingsRequest.Builder();
            builder.addLocationRequest(mLocationRequest);
            LocationSettingsRequest locationSettingsRequest = builder.build();

            SettingsClient settingsClient = LocationServices.getSettingsClient(_context);
            settingsClient.checkLocationSettings(locationSettingsRequest);

            // new Google API SDK v11 uses getFusedLocationProviderClient(this)

            if (ActivityCompat.checkSelfPermission(_context, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(_context, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                isPermission = false;
            }
            if (!isPermission) {
                showMessageError("Access denied get location");
                return;
            }
            LocationServices.getFusedLocationProviderClient(_context).requestLocationUpdates(mLocationRequest, new LocationCallback() {
                        @Override
                        public void onLocationResult(LocationResult locationResult) {
                            // do work here
                            onLocationChanged(locationResult.getLastLocation());
                        }
                    },
                    Looper.myLooper());
        } catch (Exception e) {
            util.createAlert(_context, util.ALERT_STATUS.ALERT_STATUS_INFOR, util.ALERT_TYPE.ALERT_TYPE_INFO, e.getMessage(), null);


        }

    }

    public void onLocationChanged(Location location) {
        LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());
        mapModel.setLat(latLng.latitude);
        mapModel.setLng(latLng.longitude);
        try {
//            SocketController.getInstance().connectHubByName("ChatHub");
//            SocketController.getInstance().getHub().invoke("Send", "Client", mapModel.getLat() + "---" + mapModel.getLng()).get();
//            SocketController.getInstance().disconnect();
            SocketController.getInstance().connectHubChat().invoke("Send", "Client", "Hello world!").get();

        } catch (ExecutionException e) {
            util.createAlert(_context, util.ALERT_STATUS.ALERT_STATUS_DANGER, util.ALERT_TYPE.ALERT_TYPE_INFO, e.getMessage(), null);
        } catch (InterruptedException e) {
            util.createAlert(_context, util.ALERT_STATUS.ALERT_STATUS_DANGER, util.ALERT_TYPE.ALERT_TYPE_INFO, e.getMessage(), null);
        }

    }

    @SuppressLint("MissingPermission")
    public void getLastLocation() {
        // Get last known recent location using new Google Play Services SDK (v11+)
        if (isPermission) {
            showMessageError("Access denied get location");
        }
        locationClient = LocationServices.getFusedLocationProviderClient(_context);
        locationClient.getLastLocation()
                .addOnSuccessListener(new OnSuccessListener<Location>() {
                    @Override
                    public void onSuccess(Location location) {
                        // GPS location can be null if GPS is switched off
                        if (location != null) {
                            onLocationChanged(location);
                        }
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        showMessageError(e.getMessage());
                        e.printStackTrace();
                    }
                });
    }

    public enum UNIT {
        KILOMETER,
        METTER,
        MILE
    }

    public double getDistance(Location location, Location location2, MapBase.UNIT unit) {
        if ((location.getLatitude() == location2.getLatitude()) && (location.getLongitude() == location2.getLongitude())) {
            return 0;
        } else {
            double theta = location.getLongitude() - location2.getLongitude();
            double dist = Math.sin(Math.toRadians(location.getLatitude()))
                    * Math.sin(Math.toRadians(location2.getLatitude()))
                    + Math.cos(Math.toRadians(location.getLatitude()))
                    * Math.cos(Math.toRadians(location2.getLatitude()))
                    * Math.cos(Math.toRadians(theta));
            dist = Math.acos(dist);
            dist = Math.toDegrees(dist);
            dist = dist * 60 * 1.1515;
            switch (unit) {
                case MILE:
                    return dist * constant.GPS_CONVERT_MILE;
                case KILOMETER:
                    return dist * constant.GPS_CONVERT_KILOMETER;
                default:
                    return (dist * constant.GPS_CONVERT_METER);
            }

        }
    }

    /* ::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::: */
    /* :: This function converts decimal  degrees to radians : */
    /* ::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::: */
    private static double deg2rad(double deg) {
        return (deg * Math.PI / 180.0);
    }

    /* ::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::: */
    /* :: This function converts radians to decimal degrees : */
    /* ::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::: */
    private static double rad2deg(double rad) {
        return (rad * 180.0 / Math.PI);
    }

    // System.out.println(distance(32.9697, -96.80322, 29.46786, -98.53506, 'M')
    // + " Miles\n");
    // System.out.println(distance(32.9697, -96.80322, 29.46786, -98.53506, 'K')
    // + " Kilometers\n");
    // System.out.println(distance(32.9697, -96.80322, 29.46786, -98.53506, 'N')
    // + " Nautical Miles\n");

}
