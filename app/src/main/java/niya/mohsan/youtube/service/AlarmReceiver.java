package niya.mohsan.youtube.service;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * Created by mohsan on 28/07/16.
 */
public class AlarmReceiver extends BroadcastReceiver {

    public static final int REQUEST_CODE = 12345;
    public static final String ACTION = "niya.mohsan.activities.alarm";

    @Override
    public void onReceive(Context context, Intent intent) {
        Intent intent1 = new Intent(context, ServiceDatabase.class);
        context.startService(intent1);

    }
}
