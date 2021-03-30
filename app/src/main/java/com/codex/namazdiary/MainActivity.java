package com.codex.namazdiary;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import android.os.PowerManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.codex.namazdiary.counterapp.Counter;
import com.example.unchallenged.mynamaz.R;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;


public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button viewStat = (Button) findViewById(R.id.button15);
        Button naflStatus = (Button) findViewById(R.id.button4);
        Button btncounter = (Button) findViewById(R.id.buttoncn);
        Button btnotification = (Button) findViewById(R.id.button8);


        MobileAds.initialize(this, getString(R.string.admob_app_id));
        AdView adview = (AdView) findViewById(R.id.adviewmain);
        AdRequest adRequest = new AdRequest.Builder().build();
        adview.loadAd(adRequest);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {

           String msPackageName = getPackageName();
            PowerManager  moPowerManager = (PowerManager) getSystemService(Context.POWER_SERVICE);

            if (!moPowerManager.isIgnoringBatteryOptimizations(msPackageName)) {
                Intent loIntent = new Intent();
                loIntent.setAction(android.provider.Settings.ACTION_REQUEST_IGNORE_BATTERY_OPTIMIZATIONS);
                loIntent.setData(Uri.parse("package:" + msPackageName));
                startActivity(loIntent);

            }
        }

        btnotification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Reminder.class);
                startActivity(intent);
            }
        });
        btncounter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Counter.class);
                startActivity(intent);
            }
        });
        viewStat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MyStat.class);
                startActivity(intent);

            }
        });
        naflStatus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Nafl.class);
                startActivity(intent);
            }
        });

    }


}


