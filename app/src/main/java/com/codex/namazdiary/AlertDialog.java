package com.codex.namazdiary;

import android.app.AlarmManager;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import com.example.unchallenged.mynamaz.R;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import static com.codex.namazdiary.Reminder.MY_PREFS_NAME;

public class AlertDialog extends AppCompatActivity {
    public TextView kazabtn;
    public CheckBox Fajar, Zohar, Asar, Magrib, Esha;
    public Button submit;
    public static final String MY_PREFS = "MyPrefsKaza";
    private AdView adview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alert_dialog);
        kazabtn = findViewById(R.id.textView3);
        submit = findViewById(R.id.button2);
        Fajar = findViewById(R.id.checkBox);
        Zohar = findViewById(R.id.checkBox2);
        Asar = findViewById(R.id.checkBox3);
        Magrib = findViewById(R.id.checkBox4);
        Esha = findViewById(R.id.checkBox5);

        MobileAds.initialize(this, getString(R.string.admob_app_id));

        adview = (AdView) findViewById(R.id.adview1);
        AdRequest adRequest = new AdRequest.Builder().build();
        adview.loadAd(adRequest);
        adview.setAdListener(new AdListener(){
            @Override
            public void onAdLoaded(){
                super.onAdLoaded();
                adview.setVisibility(View.VISIBLE);
            }
        });


        kazabtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), KazaNAMAZ.class);
                startActivity(intent);
            }
        });
        final SharedPreferences.Editor editor = getSharedPreferences(MY_PREFS, MODE_PRIVATE).edit();
        final SharedPreferences prefs = getSharedPreferences(MY_PREFS, MODE_PRIVATE);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!Fajar.isChecked()) {

                    editor.putLong("fajar", prefs.getLong("fajar", 0) + 1);
                    editor.apply();

                }
                if (!Zohar.isChecked()) {
                    editor.putLong("zohar", prefs.getLong("zohar", 0) + 1);
                    editor.commit();

                }
                if (!Asar.isChecked()) {
                    editor.putLong("asar", prefs.getLong("asar", 0) + 1);
                    editor.commit();
                }
                if (!Magrib.isChecked()) {
                    editor.putLong("magrib", prefs.getLong("magrib", 0) + 1);
                    editor.commit();
                }
                if (!Esha.isChecked()) {

                    editor.putLong("esha", prefs.getLong("esha", 0) + 1);
                    editor.commit();
                }

                if (!Esha.isChecked() || !Magrib.isChecked() || !Asar.isChecked() || !Zohar.isChecked() || !Fajar.isChecked()) {
                    DateFormat currentTime = new SimpleDateFormat("dd-MMM-yy  hh:mm:ss aa");
                    String d = currentTime.format(new Date());
                    editor.putString("Date", d);
                    editor.commit();


                }
                Toast.makeText(AlertDialog.this, "Submitted", Toast.LENGTH_SHORT).show();
                NotificationManager manager = (NotificationManager) AlertDialog.this.getSystemService(Context.NOTIFICATION_SERVICE);
                manager.cancel(100);

                repeatingfornext();
                finish();
            }
        });
    }

    private void repeatingfornext() {

        SharedPreferences prefs = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
        int h=prefs.getInt("hoursD", -1);
        int m=prefs.getInt("minuteD", -1);

        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.HOUR_OF_DAY, h);
        cal.set(Calendar.MINUTE, m);
        cal.set(Calendar.SECOND, 0);

        if (cal.getTimeInMillis() < System.currentTimeMillis()){
            cal.add(Calendar.DAY_OF_MONTH, 1);
        }
        setAlarmDaily(cal);

    }

    private void setAlarmDaily(Calendar targetCal) {


        //   if (targetCal.getTimeInMillis() > System.currentTimeMillis()) {
        Intent intent = new Intent(AlertDialog.this, AlarmReceiverDaily.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(AlertDialog.this, 100, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        AlarmManager alarmManager = (AlarmManager) AlertDialog.this.getSystemService(ALARM_SERVICE);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
            alarmManager.setAlarmClock(new AlarmManager.AlarmClockInfo(targetCal.getTimeInMillis(),pendingIntent),pendingIntent);
        else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
            alarmManager.setExact(AlarmManager.RTC, targetCal.getTimeInMillis(), pendingIntent);
        else
            alarmManager.set(AlarmManager.RTC, targetCal.getTimeInMillis(), pendingIntent);

        //   alarmManager.setInexactRepeating(AlarmManager.RTC_WAKEUP, targetCal.getTimeInMillis(), 24 * 60 * 60 * 1000, pendingIntent);

        //    }
    }


    @Override
    public void onBackPressed() {
        Intent intent = new Intent(getApplicationContext(), AlertDialog.class);
        startActivity(intent);
        Toast.makeText(this, "You must submit info about today's NAMAZ ", Toast.LENGTH_SHORT).show();
        super.onBackPressed();
    }
}
