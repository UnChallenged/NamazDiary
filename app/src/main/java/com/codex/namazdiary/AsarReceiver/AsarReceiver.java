package com.codex.namazdiary.AsarReceiver;


import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;

import androidx.core.app.NotificationCompat;

import com.example.unchallenged.mynamaz.R;

import static android.content.Context.NOTIFICATION_SERVICE;


/**
 * Created by UnChallengeD on 1/6/2017.
 */

public class AsarReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {

        NotificationManager notificationManager = (NotificationManager) context.getSystemService(NOTIFICATION_SERVICE);
        Intent buttonIntent = new Intent(context, OkButtonAsar.class);
        buttonIntent.putExtra("notificationId", 103);

        String CHANNEL_ID = "Channel_id_3";

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

            CharSequence name = "Channel NAMAZ";
            int importance = NotificationManager.IMPORTANCE_HIGH;
            NotificationChannel notificationChannel = new NotificationChannel(CHANNEL_ID, name, importance);

            notificationChannel.setDescription("NAMAZ Channel");
            notificationChannel.enableLights(true);
            notificationChannel.setLightColor(Color.RED);
            notificationChannel.setVibrationPattern(new long[]{0, 1000, 500, 1000});
            notificationChannel.enableVibration(true);
            notificationManager.createNotificationChannel(notificationChannel);
        }

        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 0, buttonIntent,0);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_notification_praying)
                // .setContentIntent(pendingIntent)
                .setPriority(NotificationCompat.PRIORITY_MAX)
                .setContentText("It's Asar Time!")
                .addAction(R.drawable.ic_action, "OK", pendingIntent)
                .setContentTitle("Namaz Alert!")
                .setAutoCancel(true)
                .setOngoing(true);

        Uri alarmSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        builder.setSound(alarmSound);
        notificationManager.notify(103, builder.build());

    }

}
