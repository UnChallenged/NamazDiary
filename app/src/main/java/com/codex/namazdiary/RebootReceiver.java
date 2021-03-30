package com.codex.namazdiary;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;

import com.codex.namazdiary.AsarReceiver.AsarReceiver;
import com.codex.namazdiary.EshaReceiver.EshaReceiver;
import com.codex.namazdiary.FajarReceiver.FajarReceiver;
import com.codex.namazdiary.MagribReceiver.MagribReceiver;
import com.codex.namazdiary.ZoharReceiver.ZoharReceiver;

import java.util.Calendar;

import static android.content.Context.ALARM_SERVICE;
import static android.content.Context.MODE_PRIVATE;
import static com.codex.namazdiary.Reminder.MY_PREFS_NAME;

public class RebootReceiver extends BroadcastReceiver {

    Context alarmContext;

    @Override
    public void onReceive(Context context, Intent intent) {

        alarmContext = context;

/*       Intent i = new Intent(context, MyStat.class);
        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(i);*/


        SharedPreferences prefs = context.getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
        boolean daily = prefs.getBoolean("OnKeydaily", false);
        boolean fajar = prefs.getBoolean("OnKeyfajar", false);
        boolean zohar = prefs.getBoolean("OnKeyzohar", false);
        boolean asar = prefs.getBoolean("OnKeyasar", false);
        boolean magrib = prefs.getBoolean("OnKeymagrib", false);
        boolean esha = prefs.getBoolean("OnKeyesha", false);

        if (daily) {
            int h = prefs.getInt("hoursD", -1);
            int m = prefs.getInt("minuteD", -1);
            Calendar cal = Calendar.getInstance();
            cal.set(Calendar.HOUR_OF_DAY, h);
            cal.set(Calendar.MINUTE, m);
            cal.set(Calendar.SECOND, 0);

            if (cal.getTimeInMillis() < System.currentTimeMillis()) {
                cal.add(Calendar.DAY_OF_MONTH, 1);
            }
            //   if (targetCal.getTimeInMillis() > System.currentTimeMillis()) {
            intent = new Intent(context, AlarmReceiverDaily.class);
            PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 100, intent, PendingIntent.FLAG_UPDATE_CURRENT);
            AlarmManager alarmManager = (AlarmManager) context.getSystemService(ALARM_SERVICE);

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
                alarmManager.setAlarmClock(new AlarmManager.AlarmClockInfo(cal.getTimeInMillis(), pendingIntent), pendingIntent);
            else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
                alarmManager.setExact(AlarmManager.RTC_WAKEUP, cal.getTimeInMillis(), pendingIntent);
            else
                alarmManager.set(AlarmManager.RTC_WAKEUP, cal.getTimeInMillis(), pendingIntent);

            //  alarmManager.setInexactRepeating(AlarmManager.RTC_WAKEUP_WAKEUP, cal.getTimeInMillis(), 24 * 60 * 60 * 1000, pendingIntent);

            //    }

        }
        if (fajar) {
            int h = prefs.getInt("hoursF", -1);
            int m = prefs.getInt("minuteF", -1);
            Calendar cal = Calendar.getInstance();
            cal.set(Calendar.HOUR_OF_DAY, h);
            cal.set(Calendar.MINUTE, m);
            cal.set(Calendar.SECOND, 0);

            if (cal.getTimeInMillis() < System.currentTimeMillis()) {
                cal.add(Calendar.DAY_OF_MONTH, 1);
            }
            //   if (targetCal.getTimeInMillis() > System.currentTimeMillis()) {
            intent = new Intent(context, FajarReceiver.class);
            PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 101, intent, PendingIntent.FLAG_UPDATE_CURRENT);
            AlarmManager alarmManager = (AlarmManager) context.getSystemService(ALARM_SERVICE);

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
                alarmManager.setAlarmClock(new AlarmManager.AlarmClockInfo(cal.getTimeInMillis(), pendingIntent), pendingIntent);
            else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
                alarmManager.setExact(AlarmManager.RTC_WAKEUP, cal.getTimeInMillis(), pendingIntent);
            else
                alarmManager.set(AlarmManager.RTC_WAKEUP, cal.getTimeInMillis(), pendingIntent);

            //  alarmManager.setInexactRepeating(AlarmManager.RTC_WAKEUP_WAKEUP, cal.getTimeInMillis(), 24 * 60 * 60 * 1000, pendingIntent);

            //    }

        }
        if (zohar) {
            int h = prefs.getInt("hoursZ", -1);
            int m = prefs.getInt("minuteZ", -1);
            Calendar cal = Calendar.getInstance();
            cal.set(Calendar.HOUR_OF_DAY, h);
            cal.set(Calendar.MINUTE, m);
            cal.set(Calendar.SECOND, 0);

            if (cal.getTimeInMillis() < System.currentTimeMillis()) {
                cal.add(Calendar.DAY_OF_MONTH, 1);
            }
            //   if (targetCal.getTimeInMillis() > System.currentTimeMillis()) {
            intent = new Intent(context, ZoharReceiver.class);
            PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 102, intent, PendingIntent.FLAG_UPDATE_CURRENT);
            AlarmManager alarmManager = (AlarmManager) context.getSystemService(ALARM_SERVICE);

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
                alarmManager.setAlarmClock(new AlarmManager.AlarmClockInfo(cal.getTimeInMillis(), pendingIntent), pendingIntent);
            else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
                alarmManager.setExact(AlarmManager.RTC_WAKEUP, cal.getTimeInMillis(), pendingIntent);
            else
                alarmManager.set(AlarmManager.RTC_WAKEUP, cal.getTimeInMillis(), pendingIntent);

            //  alarmManager.setInexactRepeating(AlarmManager.RTC_WAKEUP_WAKEUP, cal.getTimeInMillis(), 24 * 60 * 60 * 1000, pendingIntent);

            //    }

        }
        if (asar) {
            int h = prefs.getInt("hoursA", -1);
            int m = prefs.getInt("minuteA", -1);
            Calendar cal = Calendar.getInstance();
            cal.set(Calendar.HOUR_OF_DAY, h);
            cal.set(Calendar.MINUTE, m);
            cal.set(Calendar.SECOND, 0);

            if (cal.getTimeInMillis() < System.currentTimeMillis()) {
                cal.add(Calendar.DAY_OF_MONTH, 1);
            }
            //   if (targetCal.getTimeInMillis() > System.currentTimeMillis()) {
            intent = new Intent(context, AsarReceiver.class);
            PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 103, intent, PendingIntent.FLAG_UPDATE_CURRENT);
            AlarmManager alarmManager = (AlarmManager) context.getSystemService(ALARM_SERVICE);

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
                alarmManager.setAlarmClock(new AlarmManager.AlarmClockInfo(cal.getTimeInMillis(), pendingIntent), pendingIntent);
            else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
                alarmManager.setExact(AlarmManager.RTC_WAKEUP, cal.getTimeInMillis(), pendingIntent);
            else
                alarmManager.set(AlarmManager.RTC_WAKEUP, cal.getTimeInMillis(), pendingIntent);

            //  alarmManager.setInexactRepeating(AlarmManager.RTC_WAKEUP_WAKEUP, cal.getTimeInMillis(), 24 * 60 * 60 * 1000, pendingIntent);

            //    }

        }
        if (magrib) {
            int h = prefs.getInt("hoursM", -1);
            int m = prefs.getInt("minuteM", -1);
            Calendar cal = Calendar.getInstance();
            cal.set(Calendar.HOUR_OF_DAY, h);
            cal.set(Calendar.MINUTE, m);
            cal.set(Calendar.SECOND, 0);

            if (cal.getTimeInMillis() < System.currentTimeMillis()) {
                cal.add(Calendar.DAY_OF_MONTH, 1);
            }
            //   if (targetCal.getTimeInMillis() > System.currentTimeMillis()) {
            intent = new Intent(context, MagribReceiver.class);
            PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 104, intent, PendingIntent.FLAG_UPDATE_CURRENT);
            AlarmManager alarmManager = (AlarmManager) context.getSystemService(ALARM_SERVICE);

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
                alarmManager.setAlarmClock(new AlarmManager.AlarmClockInfo(cal.getTimeInMillis(), pendingIntent), pendingIntent);
            else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
                alarmManager.setExact(AlarmManager.RTC_WAKEUP, cal.getTimeInMillis(), pendingIntent);
            else
                alarmManager.set(AlarmManager.RTC_WAKEUP, cal.getTimeInMillis(), pendingIntent);

            //  alarmManager.setInexactRepeating(AlarmManager.RTC_WAKEUP_WAKEUP, cal.getTimeInMillis(), 24 * 60 * 60 * 1000, pendingIntent);

            //    }

        }
        if (esha) {
            int h = prefs.getInt("hoursE", -1);
            int m = prefs.getInt("minuteE", -1);
            Calendar cal = Calendar.getInstance();
            cal.set(Calendar.HOUR_OF_DAY, h);
            cal.set(Calendar.MINUTE, m);
            cal.set(Calendar.SECOND, 0);

            if (cal.getTimeInMillis() < System.currentTimeMillis()) {
                cal.add(Calendar.DAY_OF_MONTH, 1);
            }
            //   if (targetCal.getTimeInMillis() > System.currentTimeMillis()) {
            intent = new Intent(context, EshaReceiver.class);
            PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 105, intent, PendingIntent.FLAG_UPDATE_CURRENT);
            AlarmManager alarmManager = (AlarmManager) context.getSystemService(ALARM_SERVICE);

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
                alarmManager.setAlarmClock(new AlarmManager.AlarmClockInfo(cal.getTimeInMillis(), pendingIntent), pendingIntent);
            else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
                alarmManager.setExact(AlarmManager.RTC_WAKEUP, cal.getTimeInMillis(), pendingIntent);
            else
                alarmManager.set(AlarmManager.RTC_WAKEUP, cal.getTimeInMillis(), pendingIntent);

            //  alarmManager.setInexactRepeating(AlarmManager.RTC_WAKEUP_WAKEUP, cal.getTimeInMillis(), 24 * 60 * 60 * 1000, pendingIntent);

            //    }

        }

    }


}

