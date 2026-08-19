package com.arete.nexoatomico;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;

import androidx.core.app.NotificationCompat;

public class AlarmReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        String channelId = "nexo_alarm_channel";
        NotificationManager manager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            Uri sound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM);
            NotificationChannel channel = new NotificationChannel(channelId, "Alarmas del Nexo", NotificationManager.IMPORTANCE_HIGH);
            channel.setSound(sound, null);
            channel.enableVibration(true);
            manager.createNotificationChannel(channel);
        }
        Intent openIntent = context.getPackageManager().getLaunchIntentForPackage(context.getPackageName());
        PendingIntent openApp = null;
        if (openIntent != null) {
            openApp = PendingIntent.getActivity(context, 0, openIntent, PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_IMMUTABLE);
        }
        NotificationCompat.Builder notification = new NotificationCompat.Builder(context, channelId)
                .setSmallIcon(android.R.drawable.ic_lock_idle_alarm)
                .setContentTitle("⏰ ¡Hora de tu rutina!")
                .setContentText("Es momento de realizar tu rutina.")
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setAutoCancel(true)
                .setDefaults(NotificationCompat.DEFAULT_SOUND | NotificationCompat.DEFAULT_VIBRATE);
        if (openApp != null) notification.setContentIntent(openApp);
        manager.notify((int) (System.currentTimeMillis() % Integer.MAX_VALUE), notification.build());
    }
}
