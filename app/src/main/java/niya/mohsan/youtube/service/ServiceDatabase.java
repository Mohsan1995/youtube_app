package niya.mohsan.youtube.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import io.realm.Realm;
import niya.mohsan.youtube.controller.RealmController;

/**
 * Created by mohsan on 28/07/16.
 */
public class ServiceDatabase extends Service {

    private RealmController realmController;
    @Override
    public void onCreate() {
        super.onCreate();
        Log.e("Test", "Service start");
        RealmController.getInstance(getApplicationContext()).clearAll();
        stopSelf();

    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.e("Service", "service destroy");
    }
}
