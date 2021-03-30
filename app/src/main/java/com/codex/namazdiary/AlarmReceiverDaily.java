package com.codex.namazdiary;


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

import static android.app.PendingIntent.FLAG_UPDATE_CURRENT;
import static android.content.Context.NOTIFICATION_SERVICE;


/**
 * Created by UnChallengeD on 1/6/2017.
 */

public class AlarmReceiverDaily extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {


            NotificationManager notificationManager = (NotificationManager) context.getSystemService(NOTIFICATION_SERVICE);
            String CHANNEL_ID = "Channel_id_1";// The id of the channel.



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

            intent = new Intent(context, AlertDialog.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
            intent.addFlags(Intent.FLAG_RECEIVER_FOREGROUND); // added new line
            intent.putExtra("STARTED_BY_RECEIVER", true);

            PendingIntent pendingIntent = PendingIntent.getActivity(context, 100, intent, FLAG_UPDATE_CURRENT);
               NotificationCompat.Builder builder = new NotificationCompat.Builder(context, CHANNEL_ID)
                    .setSmallIcon(R.drawable.ic_notification_praying)
                 //   .setContentIntent(pendingIntent)
                    .setPriority(NotificationCompat.PRIORITY_MAX)
                    .setContentText("Submit Today's NAMAZ status")
                    .setContentTitle("NAMAZ ALERT")
                    .addAction(R.drawable.ic_action, "Submit", pendingIntent)
                    .setAutoCancel(true)
                    .setOngoing(true);
            Uri alarmSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
            builder.setSound(alarmSound);
            notificationManager.notify(100, builder.build());

    }

}
