package base.core.mudules.services;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import base.MainActivity;
import base.core.mudules.map.MapBase;

public class MainService extends Service {

    private static MapBase mapBase;

    public static MapBase getMapBase() {
        return mapBase;
    }

    public static void setMapBase(MapBase mapBase) {
        MainService.mapBase = mapBase;
    }

    public MainService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mapBase = new MapBase(getApplicationContext());
        // Create MediaPlayer object, to play your song.
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        // Play song.
        mapBase.startLocationUpdates();
        return START_STICKY;
    }

    // Destroy
    @Override
    public void onDestroy() {
        // Release the resources
        mapBase = null;
        super.onDestroy();
    }
}
