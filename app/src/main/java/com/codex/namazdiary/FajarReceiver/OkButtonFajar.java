package com.codex.namazdiary.FajarReceiver;

import android.app.AlarmManager;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;

import com.codex.namazdiary.AlarmReceiverDaily;
import com.codex.namazdiary.AlertDialog;

import java.util.Calendar;

import static android.content.Context.ALARM_SERVICE;
import static android.content.Context.MODE_PRIVATE;
import static com.codex.namazdiary.Reminder.MY_PREFS_NAME;

public class OkButtonFajar extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        int notificationId = intent.getIntExtra("notificationId", 0);
        SharedPreferences prefs = context.getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
        int h = prefs.getInt("hoursF", -1);
        int m = prefs.getInt("minuteF", -1);
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.HOUR_OF_DAY, h);
        cal.set(Calendar.MINUTE, m);
        cal.set(Calendar.SECOND, 0);

        if (cal.getTimeInMillis() < System.currentTimeMillis()){
            cal.add(Calendar.DAY_OF_MONTH, 1);
        }
        //   if (targetCal.getTimeInMillis() > System.currentTimeMillis()) {
         intent = new Intent(context, FajarReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 101, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(ALARM_SERVICE);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
            alarmManager.setAlarmClock(new AlarmManager.AlarmClockInfo(cal.getTimeInMillis(),pendingIntent),pendingIntent);
        else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
            alarmManager.setExact(AlarmManager.RTC, cal.getTimeInMillis(), pendingIntent);
        else
            alarmManager.set(AlarmManager.RTC, cal.getTimeInMillis(), pendingIntent);

         //  alarmManager.setInexactRepeating(AlarmManager.RTC_WAKEUP, cal.getTimeInMillis(), 24 * 60 * 60 * 1000, pendingIntent);

        //    }

        NotificationManager manager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        manager.cancel(notificationId);


    }




}
