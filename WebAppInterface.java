package com.arete.nexoatomico;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.webkit.JavascriptInterface;

public class WebAppInterface {
    private final Context context;

    public WebAppInterface(Context context) {
        this.context = context.getApplicationContext();
    }

    @JavascriptInterface
    public void scheduleExactAlarm(long triggerMillis) {
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        if (alarmManager == null) return;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S && !alarmManager.canScheduleExactAlarms()) return;

        Intent intent = new Intent(context, AlarmReceiver.class);
        int requestCode = (int) (triggerMillis % Integer.MAX_VALUE);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(
                context, requestCode, intent,
                PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_IMMUTABLE
        );
        alarmManager.setExactAndAllowWhileIdle(
                AlarmManager.RTC_WAKEUP, triggerMillis, pendingIntent
        );
    }
}
