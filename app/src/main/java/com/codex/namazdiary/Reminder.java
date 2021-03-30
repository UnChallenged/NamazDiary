package com.codex.namazdiary;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.codex.namazdiary.AsarReceiver.AsarReceiver;
import com.codex.namazdiary.EshaReceiver.EshaReceiver;
import com.codex.namazdiary.FajarReceiver.FajarReceiver;
import com.codex.namazdiary.MagribReceiver.MagribReceiver;
import com.codex.namazdiary.ZoharReceiver.ZoharReceiver;
import com.example.unchallenged.mynamaz.R;


import java.util.Calendar;


public class Reminder extends AppCompatActivity {
    public static final String MY_PREFS_NAME = "MyPrefsFile";
    private ImageView daily_n_g, daily_n_b, getDaily_n_setting;
    private ImageView fajar_n_g, fajar_n_b, fajar_n_setting, zohar_n_g, zohar_n_b, zohar_n_setting, asar_n_g, asar_n_b, asar_n_setting, magrib_n_g, magrib_n_b, magrib_n_setting, esha_n_g, esha_n_b, esha_n_setting;
    private TextView Tfajar, Tzohar, Tasar, Tmagrib, Tesha;
    private TextView Tdaily;
    private int btDtexthour, btDtextminute,
            btftexthour, btftextminute,
            btztexthour, btztextminute,
            btatexthour, btatextminute,
            btmtexthour, btmtextminute,
            btetexthour, btetextminute;
    private String format = "";
    private int def = -1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reminder);

        Tfajar = findViewById(R.id.timefajar);
        Tzohar = findViewById(R.id.zohartime);
        Tasar = findViewById(R.id.asartime);
        Tmagrib = findViewById(R.id.magribtime);
        Tesha = findViewById(R.id.eshatime);

        Tdaily = findViewById(R.id.dailytime);


        daily_n_g = findViewById(R.id.dailyG);
        daily_n_b = findViewById(R.id.dailyB);
        getDaily_n_setting = findViewById(R.id.dailyiconsetting);

        fajar_n_g = findViewById(R.id.fajarG);
        fajar_n_b = findViewById(R.id.fajarB);
        fajar_n_setting = findViewById(R.id.fajarseticon);

        zohar_n_g = findViewById(R.id.zoharG);
        zohar_n_b = findViewById(R.id.zoharB);
        zohar_n_setting = findViewById(R.id.zoharseticon);


        asar_n_g = findViewById(R.id.asarG);
        asar_n_b = findViewById(R.id.asarB);
        asar_n_setting = findViewById(R.id.asarseticon);


        magrib_n_g = findViewById(R.id.magribG);
        magrib_n_b = findViewById(R.id.magribB);
        magrib_n_setting = findViewById(R.id.magribseticon);


        esha_n_g = findViewById(R.id.eshaG);
        esha_n_b = findViewById(R.id.eshaB);
        esha_n_setting = findViewById(R.id.eshaseticon);


//--------------------------------------------------------------------------------------/


        getDaily_n_setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                timepickerdaily();
            }
        });
        fajar_n_setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                timepickerfajar();

            }
        });
        zohar_n_setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                timepickerzohar();
            }
        });
        asar_n_setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                timepickerasar();
            }
        });
        magrib_n_setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                timepickermagrib();

            }
        });
        esha_n_setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                timepickeresha();

            }
        });

        //========================================================//

        daily_n_b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if (Tdaily.getText().equals("")) {
                    Toast.makeText(Reminder.this, "Set Time First! ", Toast.LENGTH_SHORT).show();
                } else {
                    SharedPreferences.Editor editor = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE).edit();
                    editor.putBoolean("OnKeydaily", true);
                    editor.apply();

                    setCalenderDaily(btDtexthour, btDtextminute);
                    daily_n_g.setVisibility(View.VISIBLE);
                    daily_n_b.setVisibility(View.GONE);
                    Toast.makeText(Reminder.this, "Enabled", Toast.LENGTH_SHORT).show();
                }

            }
        });
        daily_n_g.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                SharedPreferences.Editor editor = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE).edit();
                editor.remove("OnKeydaily");
                editor.apply();

                cancelAlarmDaily();
                daily_n_b.setVisibility(View.VISIBLE);
                daily_n_g.setVisibility(View.GONE);
                Toast.makeText(Reminder.this, "Disabled", Toast.LENGTH_SHORT).show();
            }
        });

        fajar_n_b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (Tfajar.getText().equals("")) {
                    Toast.makeText(Reminder.this, "Set Time First! ", Toast.LENGTH_SHORT).show();
                } else {
                    SharedPreferences.Editor editor = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE).edit();
                    editor.putBoolean("OnKeyfajar", true);
                    editor.apply();
                    setCalenderFajar(btftexthour, btftextminute);
                    fajar_n_g.setVisibility(View.VISIBLE);
                    fajar_n_b.setVisibility(View.GONE);
                    Toast.makeText(Reminder.this, "Enabled", Toast.LENGTH_SHORT).show();
                }


            }
        });

        fajar_n_g.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                cancelAlarmFajar();
                SharedPreferences.Editor editor = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE).edit();
                editor.remove("OnKeyfajar");
                editor.apply();
                fajar_n_b.setVisibility(View.VISIBLE);
                fajar_n_g.setVisibility(View.GONE);
                Toast.makeText(Reminder.this, "Disabled", Toast.LENGTH_SHORT).show();
            }
        });


        zohar_n_b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (Tzohar.getText().equals("")) {
                    Toast.makeText(Reminder.this, "Set Time First! ", Toast.LENGTH_SHORT).show();
                } else {
                    SharedPreferences.Editor editor = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE).edit();
                    editor.putBoolean("OnKeyzohar", true);
                    editor.apply();

                    setCalenderZohar(btztexthour, btztextminute);
                    zohar_n_g.setVisibility(View.VISIBLE);
                    zohar_n_b.setVisibility(View.GONE);
                    Toast.makeText(Reminder.this, "Enabled", Toast.LENGTH_SHORT).show();
                }

            }
        });
        zohar_n_g.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cancelAlarmZohar();
                SharedPreferences.Editor editor = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE).edit();
                editor.remove("OnKeyzohar");
                editor.apply();
                zohar_n_b.setVisibility(View.VISIBLE);
                zohar_n_g.setVisibility(View.GONE);
                Toast.makeText(Reminder.this, "Disabled", Toast.LENGTH_SHORT).show();
            }
        });
        asar_n_b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (Tasar.getText().equals("")) {
                    Toast.makeText(Reminder.this, "Set Time First! ", Toast.LENGTH_SHORT).show();
                } else {
                    SharedPreferences.Editor editor = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE).edit();
                    editor.putBoolean("OnKeyasar", true);
                    editor.apply();
                    setCalenderAsar(btatexthour, btatextminute);
                    asar_n_g.setVisibility(View.VISIBLE);
                    asar_n_b.setVisibility(View.GONE);
                    Toast.makeText(Reminder.this, "Enabled", Toast.LENGTH_SHORT).show();
                }


            }
        });
        asar_n_g.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cancelAlarmAsar();
                SharedPreferences.Editor editor = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE).edit();
                editor.remove("OnKeyasar");
                editor.apply();
                asar_n_b.setVisibility(View.VISIBLE);
                asar_n_g.setVisibility(View.GONE);
                Toast.makeText(Reminder.this, "Disabled", Toast.LENGTH_SHORT).show();
            }
        });
        magrib_n_b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (Tmagrib.getText().equals("")) {
                    Toast.makeText(Reminder.this, "Set Time First! ", Toast.LENGTH_SHORT).show();
                } else {
                    SharedPreferences.Editor editor = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE).edit();
                    editor.putBoolean("OnKeymagrib", true);
                    editor.apply();
                    setCalenderMagrib(btmtexthour, btmtextminute);
                    magrib_n_g.setVisibility(View.VISIBLE);
                    magrib_n_b.setVisibility(View.GONE);
                    Toast.makeText(Reminder.this, "Enabled", Toast.LENGTH_SHORT).show();
                }

            }
        });
        magrib_n_g.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cancelAlarmMagrib();
                SharedPreferences.Editor editor = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE).edit();
                editor.remove("OnKeymagrib");
                editor.apply();
                magrib_n_b.setVisibility(View.VISIBLE);
                magrib_n_g.setVisibility(View.GONE);
                Toast.makeText(Reminder.this, "Disabled", Toast.LENGTH_SHORT).show();
            }
        });

        esha_n_b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Tesha.getText().equals("")) {
                    Toast.makeText(Reminder.this, "Set Time First! ", Toast.LENGTH_SHORT).show();
                } else {
                    SharedPreferences.Editor editor = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE).edit();
                    editor.putBoolean("OnKeyesha", true);
                    editor.apply();
                    setCalenderEsha(btetexthour, btetextminute);
                    esha_n_g.setVisibility(View.VISIBLE);
                    esha_n_b.setVisibility(View.GONE);
                    Toast.makeText(Reminder.this, "Enabled", Toast.LENGTH_SHORT).show();
                }

            }
        });
        esha_n_g.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cancelAlarmEsha();
                SharedPreferences.Editor editor = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE).edit();
                editor.remove("OnKeyesha");
                editor.apply();
                esha_n_b.setVisibility(View.VISIBLE);
                esha_n_g.setVisibility(View.GONE);
                Toast.makeText(Reminder.this, "Disabled", Toast.LENGTH_SHORT).show();
            }
        });

//-----------------------------------------------------------------------------//


//----------------------save for later activity-----------------

        SharedPreferences prefs = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
        boolean daily = prefs.getBoolean("OnKeydaily", false);
        boolean fajar = prefs.getBoolean("OnKeyfajar", false);
        boolean zohar = prefs.getBoolean("OnKeyzohar", false);
        boolean asar = prefs.getBoolean("OnKeyasar", false);
        boolean magrib = prefs.getBoolean("OnKeymagrib", false);
        boolean esha = prefs.getBoolean("OnKeyesha", false);

        if (daily) {

            daily_n_g.setVisibility(View.VISIBLE);
            daily_n_b.setVisibility(View.GONE);
        }
        if (!daily) {
            daily_n_g.setVisibility(View.GONE);
            daily_n_b.setVisibility(View.VISIBLE);
        }

        if (fajar) {
            fajar_n_g.setVisibility(View.VISIBLE);
            fajar_n_b.setVisibility(View.GONE);

        }
        if (!fajar) {
            fajar_n_g.setVisibility(View.GONE);
            fajar_n_b.setVisibility(View.VISIBLE);
        }
        if (zohar) {
            zohar_n_g.setVisibility(View.VISIBLE);
            zohar_n_b.setVisibility(View.GONE);

        }
        if (!zohar) {
            zohar_n_g.setVisibility(View.GONE);
            zohar_n_b.setVisibility(View.VISIBLE);
        }
        if (asar) {
            asar_n_g.setVisibility(View.VISIBLE);
            asar_n_b.setVisibility(View.GONE);

        }
        if (!asar) {
            asar_n_g.setVisibility(View.GONE);
            asar_n_b.setVisibility(View.VISIBLE);
        }
        if (magrib) {
            magrib_n_g.setVisibility(View.VISIBLE);
            magrib_n_b.setVisibility(View.GONE);

        }
        if (!magrib) {
            magrib_n_g.setVisibility(View.GONE);
            magrib_n_b.setVisibility(View.VISIBLE);
        }
        if (esha) {
            esha_n_g.setVisibility(View.VISIBLE);
            esha_n_b.setVisibility(View.GONE);

        }
        if (!esha) {
            esha_n_g.setVisibility(View.GONE);
            esha_n_b.setVisibility(View.VISIBLE);
        }


        if (!(prefs.getInt("hoursD", def) == def) && !(prefs.getInt("minuteD", def) == def)) {
            Tdaily.setText(showTime(prefs.getInt("hoursD", def), prefs.getInt("minuteD", def)));
        }

        if (!(prefs.getInt("hoursF", def) == def) && !(prefs.getInt("minuteF", def) == def)) {
            Tfajar.setText(showTime(prefs.getInt("hoursF", def), prefs.getInt("minuteF", def)));
        }

        if (!(prefs.getInt("hoursZ", def) == def) && !(prefs.getInt("minuteZ", def) == def)) {
            Tzohar.setText(showTime(prefs.getInt("hoursZ", def), prefs.getInt("minuteZ", def)));
        }

        if (!(prefs.getInt("hoursA", def) == def) && !(prefs.getInt("minuteA", def) == def)) {
            Tasar.setText(showTime(prefs.getInt("hoursA", def), prefs.getInt("minuteA", def)));
        }

        if (!(prefs.getInt("hoursM", def) == def) && !(prefs.getInt("minuteM", def) == def)) {
            Tmagrib.setText(showTime(prefs.getInt("hoursM", def), prefs.getInt("minuteM", def)));
        }

        if (!(prefs.getInt("hoursE", def) == def) && !(prefs.getInt("minuteE", def) == def)) {
            Tesha.setText(showTime(prefs.getInt("hoursE", def), prefs.getInt("minuteE", def)));
        }


    }


//==============================================================================================/

    private void timepickerdaily() {

        Calendar mcurrentTime = Calendar.getInstance();
        int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
        int minute = mcurrentTime.get(Calendar.MINUTE);
        TimePickerDialog mTimePicker;
        mTimePicker = new TimePickerDialog(Reminder.this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {

                btDtexthour = selectedHour;
                btDtextminute = selectedMinute;

                SharedPreferences.Editor editor = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE).edit();
                editor.putInt("hoursD", selectedHour);
                editor.putInt("minuteD", selectedMinute);
                editor.remove("OnKeydaily");
                editor.apply();

                daily_n_g.setVisibility(View.GONE);
                daily_n_b.setVisibility(View.VISIBLE);
                cancelAlarmDaily();


                Tdaily.setText(showTime(selectedHour, selectedMinute));
                Toast.makeText(Reminder.this, "Now Turn Notification Icon On", Toast.LENGTH_SHORT).show();


            }

        }, hour, minute, false);//Yes 24 hour time
        mTimePicker.setTitle("Select Time");
        mTimePicker.show();


    }

    private void timepickerfajar() {

        Calendar mcurrentTime = Calendar.getInstance();
        int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
        int minute = mcurrentTime.get(Calendar.MINUTE);
        TimePickerDialog mTimePicker;
        mTimePicker = new TimePickerDialog(Reminder.this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {

                btftexthour = selectedHour;
                btftextminute = selectedMinute;
                SharedPreferences.Editor editor = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE).edit();
                editor.putInt("hoursF", selectedHour);
                editor.putInt("minuteF", selectedMinute);
                editor.remove("OnKeyfajar");
                editor.apply();

                fajar_n_g.setVisibility(View.GONE);
                fajar_n_b.setVisibility(View.VISIBLE);
                cancelAlarmFajar();

                Tfajar.setText(showTime(selectedHour, selectedMinute));
                Toast.makeText(Reminder.this, "Now Turn Notification Icon On", Toast.LENGTH_SHORT).show();

            }

        }, hour, minute, false);//Yes 24 hour time
        mTimePicker.setTitle("Select Time");
        mTimePicker.show();


    }

    private void timepickerzohar() {

        Calendar mcurrentTime = Calendar.getInstance();
        int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
        int minute = mcurrentTime.get(Calendar.MINUTE);
        TimePickerDialog mTimePicker;
        mTimePicker = new TimePickerDialog(Reminder.this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {

                btztexthour = selectedHour;
                btztextminute = selectedMinute;

                SharedPreferences.Editor editor = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE).edit();
                editor.putInt("hoursZ", selectedHour);
                editor.putInt("minuteZ", selectedMinute);
                editor.remove("OnKeyzohar");
                editor.apply();

                zohar_n_g.setVisibility(View.GONE);
                zohar_n_b.setVisibility(View.VISIBLE);
                cancelAlarmZohar();

                Tzohar.setText(showTime(selectedHour, selectedMinute));
                Toast.makeText(Reminder.this, "Now Turn Notification Icon On", Toast.LENGTH_SHORT).show();

            }

        }, hour, minute, false);//Yes 24 hour time
        mTimePicker.setTitle("Select Time");
        mTimePicker.show();


    }

    private void timepickerasar() {

        Calendar mcurrentTime = Calendar.getInstance();
        int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
        int minute = mcurrentTime.get(Calendar.MINUTE);
        TimePickerDialog mTimePicker;
        mTimePicker = new TimePickerDialog(Reminder.this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {

                btatexthour = selectedHour;
                btatextminute = selectedMinute;
                SharedPreferences.Editor editor = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE).edit();
                editor.putInt("hoursA", selectedHour);
                editor.putInt("minuteA", selectedMinute);
                editor.remove("OnKeyasar");
                editor.apply();

                asar_n_g.setVisibility(View.GONE);
                asar_n_b.setVisibility(View.VISIBLE);
                cancelAlarmAsar();

                Tasar.setText(showTime(selectedHour, selectedMinute));
                Toast.makeText(Reminder.this, "Now Turn Notification Icon On", Toast.LENGTH_SHORT).show();


            }

        }, hour, minute, false);//Yes 24 hour time
        mTimePicker.setTitle("Select Time");
        mTimePicker.show();

    }

    private void timepickermagrib() {

        Calendar mcurrentTime = Calendar.getInstance();
        int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
        int minute = mcurrentTime.get(Calendar.MINUTE);
        TimePickerDialog mTimePicker;
        mTimePicker = new TimePickerDialog(Reminder.this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {

                btmtexthour = selectedHour;
                btmtextminute = selectedMinute;
                SharedPreferences.Editor editor = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE).edit();
                editor.putInt("hoursM", selectedHour);
                editor.putInt("minuteM", selectedMinute);
                editor.remove("OnKeymagrib");
                editor.apply();

                magrib_n_g.setVisibility(View.GONE);
                magrib_n_b.setVisibility(View.VISIBLE);
                cancelAlarmMagrib();

                Tmagrib.setText(showTime(selectedHour, selectedMinute));
                Toast.makeText(Reminder.this, "Now Turn Notification Icon On", Toast.LENGTH_SHORT).show();


            }

        }, hour, minute, false);//Yes 24 hour time
        mTimePicker.setTitle("Select Time");
        mTimePicker.show();

    }

    private void timepickeresha() {

        Calendar mcurrentTime = Calendar.getInstance();
        int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
        int minute = mcurrentTime.get(Calendar.MINUTE);
        TimePickerDialog mTimePicker;
        mTimePicker = new TimePickerDialog(Reminder.this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {

                btetexthour = selectedHour;
                btetextminute = selectedMinute;
                SharedPreferences.Editor editor = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE).edit();
                editor.putInt("hoursE", selectedHour);
                editor.putInt("minuteE", selectedMinute);
                editor.remove("OnKeyesha");
                editor.apply();

                esha_n_g.setVisibility(View.GONE);
                esha_n_b.setVisibility(View.VISIBLE);
                cancelAlarmEsha();

                Tesha.setText(showTime(selectedHour, selectedMinute));
                Toast.makeText(Reminder.this, "Now Turn Notification Icon On", Toast.LENGTH_SHORT).show();

            }

        }, hour, minute, false);//Yes 24 hour time
        mTimePicker.setTitle("Select Time");
        mTimePicker.show();


    }


    private String showTime(int hour, int min) {
        if (hour == 0) {
            hour += 12;
            format = "AM";
        } else if (hour == 12) {
            format = "PM";
        } else if (hour > 12) {
            hour -= 12;
            format = "PM";
        } else {
            format = "AM";
        }

        return hour + ":" + min + " " + format;
    }

    public void setCalenderDaily(int hour, int minute) {

        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.HOUR_OF_DAY, hour);
        cal.set(Calendar.MINUTE, minute);
        cal.set(Calendar.SECOND, 0);

        if (cal.getTimeInMillis() < System.currentTimeMillis()){
            cal.add(Calendar.DAY_OF_MONTH, 1);
        }
        setAlarmDaily(cal);

    }

    private void setAlarmDaily(Calendar targetCal) {


     //   if (targetCal.getTimeInMillis() > System.currentTimeMillis()) {
            Intent intent = new Intent(Reminder.this, AlarmReceiverDaily.class);
            PendingIntent pendingIntent = PendingIntent.getBroadcast(Reminder.this, 100, intent, PendingIntent.FLAG_UPDATE_CURRENT);
            AlarmManager alarmManager = (AlarmManager) Reminder.this.getSystemService(ALARM_SERVICE);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
            alarmManager.setAlarmClock(new AlarmManager.AlarmClockInfo(targetCal.getTimeInMillis(),pendingIntent),pendingIntent);
        else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
            alarmManager.setExact(AlarmManager.RTC, targetCal.getTimeInMillis(), pendingIntent);
        else
            alarmManager.set(AlarmManager.RTC, targetCal.getTimeInMillis(), pendingIntent);

         //   alarmManager.setInexactRepeating(AlarmManager.RTC_WAKEUP_WAKEUP, targetCal.getTimeInMillis(), 24 * 60 * 60 * 1000, pendingIntent);

    //    }
    }
    public void setCalenderFajar(int hour, int minute) {

        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.HOUR_OF_DAY, hour);
        cal.set(Calendar.MINUTE, minute);
        cal.set(Calendar.SECOND, 0);

        if (cal.getTimeInMillis() < System.currentTimeMillis()){
            cal.add(Calendar.DAY_OF_MONTH, 1);
        }

        setAlarmFajar(cal);

    }

    private void setAlarmFajar(Calendar targetCal) {

          Intent intent = new Intent(Reminder.this, FajarReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(Reminder.this, 101, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        AlarmManager alarmManager = (AlarmManager) Reminder.this.getSystemService(ALARM_SERVICE);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
            alarmManager.setAlarmClock(new AlarmManager.AlarmClockInfo(targetCal.getTimeInMillis(),pendingIntent),pendingIntent);
        else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
            alarmManager.setExact(AlarmManager.RTC_WAKEUP, targetCal.getTimeInMillis(), pendingIntent);
        else
            alarmManager.set(AlarmManager.RTC_WAKEUP, targetCal.getTimeInMillis(), pendingIntent);


    }
    public void setCalenderZohar(int hour, int minute) {

        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.HOUR_OF_DAY, hour);
        cal.set(Calendar.MINUTE, minute);
        cal.set(Calendar.SECOND, 0);
        if (cal.getTimeInMillis() < System.currentTimeMillis()){
            cal.add(Calendar.DAY_OF_MONTH, 1);
        }
        setAlarmZohar(cal);

    }

    private void setAlarmZohar(Calendar targetCal) {



         Intent intent = new Intent(Reminder.this, ZoharReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(Reminder.this, 102, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        AlarmManager alarmManager = (AlarmManager) Reminder.this.getSystemService(ALARM_SERVICE);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
            alarmManager.setAlarmClock(new AlarmManager.AlarmClockInfo(targetCal.getTimeInMillis(),pendingIntent),pendingIntent);
        else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
            alarmManager.setExact(AlarmManager.RTC_WAKEUP, targetCal.getTimeInMillis(), pendingIntent);
        else
            alarmManager.set(AlarmManager.RTC_WAKEUP, targetCal.getTimeInMillis(), pendingIntent);

    }
    public void setCalenderAsar(int hour, int minute) {

        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.HOUR_OF_DAY, hour);
        cal.set(Calendar.MINUTE, minute);
        cal.set(Calendar.SECOND, 0);
        if (cal.getTimeInMillis() < System.currentTimeMillis()){
            cal.add(Calendar.DAY_OF_MONTH, 1);
        }
        setAlarmAsar(cal);

    }

    private void setAlarmAsar(Calendar targetCal) {



        //   if (targetCal.getTimeInMillis() > System.currentTimeMillis()) {
        Intent intent = new Intent(Reminder.this, AsarReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(Reminder.this, 103, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        AlarmManager alarmManager = (AlarmManager) Reminder.this.getSystemService(ALARM_SERVICE);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
            alarmManager.setAlarmClock(new AlarmManager.AlarmClockInfo(targetCal.getTimeInMillis(),pendingIntent),pendingIntent);
        else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
            alarmManager.setExact(AlarmManager.RTC_WAKEUP, targetCal.getTimeInMillis(), pendingIntent);
        else
            alarmManager.set(AlarmManager.RTC_WAKEUP, targetCal.getTimeInMillis(), pendingIntent);

        //   alarmManager.setInexactRepeating(AlarmManager.RTC_WAKEUP_WAKEUP, targetCal.getTimeInMillis(), 24 * 60 * 60 * 1000, pendingIntent);

        //    }
    }
    public void setCalenderMagrib(int hour, int minute) {

        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.HOUR_OF_DAY, hour);
        cal.set(Calendar.MINUTE, minute);
        cal.set(Calendar.SECOND, 0);
        if (cal.getTimeInMillis() < System.currentTimeMillis()){
            cal.add(Calendar.DAY_OF_MONTH, 1);
        }
        setAlarmMagrib(cal);

    }

    private void setAlarmMagrib(Calendar targetCal) {


        //   if (targetCal.getTimeInMillis() > System.currentTimeMillis()) {
        Intent intent = new Intent(Reminder.this, MagribReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(Reminder.this, 104, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        AlarmManager alarmManager = (AlarmManager) Reminder.this.getSystemService(ALARM_SERVICE);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
            alarmManager.setAlarmClock(new AlarmManager.AlarmClockInfo(targetCal.getTimeInMillis(),pendingIntent),pendingIntent);
        else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
            alarmManager.setExact(AlarmManager.RTC_WAKEUP, targetCal.getTimeInMillis(), pendingIntent);
        else
            alarmManager.set(AlarmManager.RTC_WAKEUP, targetCal.getTimeInMillis(), pendingIntent);

        //   alarmManager.setInexactRepeating(AlarmManager.RTC_WAKEUP_WAKEUP, targetCal.getTimeInMillis(), 24 * 60 * 60 * 1000, pendingIntent);

        //    }
    }
    public void setCalenderEsha(int hour, int minute) {

        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.HOUR_OF_DAY, hour);
        cal.set(Calendar.MINUTE, minute);
        cal.set(Calendar.SECOND, 0);
        if (cal.getTimeInMillis() < System.currentTimeMillis()){
            cal.add(Calendar.DAY_OF_MONTH, 1);
        }
        setAlarmEsha(cal);

    }

    private void setAlarmEsha(Calendar targetCal) {



        //   if (targetCal.getTimeInMillis() > System.currentTimeMillis()) {
        Intent intent = new Intent(Reminder.this, EshaReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(Reminder.this, 105, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        AlarmManager alarmManager = (AlarmManager) Reminder.this.getSystemService(ALARM_SERVICE);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
            alarmManager.setAlarmClock(new AlarmManager.AlarmClockInfo(targetCal.getTimeInMillis(),pendingIntent),pendingIntent);
        else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
            alarmManager.setExact(AlarmManager.RTC_WAKEUP, targetCal.getTimeInMillis(), pendingIntent);
        else
            alarmManager.set(AlarmManager.RTC_WAKEUP, targetCal.getTimeInMillis(), pendingIntent);

        //   alarmManager.setInexactRepeating(AlarmManager.RTC_WAKEUP_WAKEUP, targetCal.getTimeInMillis(), 24 * 60 * 60 * 1000, pendingIntent);

        //    }
    }

    private void cancelAlarmDaily() {

        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(getBaseContext(), AlarmReceiverDaily.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(getBaseContext(), 100, intent, 0);
        alarmManager.cancel(pendingIntent);

    }
    private void cancelAlarmFajar() {

        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(getBaseContext(), FajarReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(getBaseContext(), 101, intent, 0);
         alarmManager.cancel(pendingIntent);

    }
    private void cancelAlarmZohar() {

        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(getBaseContext(), ZoharReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(getBaseContext(), 102, intent, 0);
        alarmManager.cancel(pendingIntent);

    }
    private void cancelAlarmAsar() {

        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(getBaseContext(), AsarReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(getBaseContext(), 103, intent, 0);
        alarmManager.cancel(pendingIntent);

    }
    private void cancelAlarmMagrib() {
        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(getBaseContext(), MagribReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(getBaseContext(), 104, intent, 0);
        alarmManager.cancel(pendingIntent);

    }
    private void cancelAlarmEsha() {

        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(getBaseContext(), EshaReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(getBaseContext(), 105, intent, 0);
        alarmManager.cancel(pendingIntent);

    }
}
