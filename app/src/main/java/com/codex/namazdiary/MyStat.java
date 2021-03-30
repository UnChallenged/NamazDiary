package com.codex.namazdiary;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.unchallenged.mynamaz.R;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

public class MyStat extends AppCompatActivity {
    private TextView fajar, zohar, asar, magrib, esha,updateOn;
    private Button btnadd,btnremove;
    private AdView adview;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_stat);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        MobileAds.initialize(this, getString(R.string.admob_app_id));

        adview = findViewById(R.id.adviewstatic);
        AdRequest adRequest = new AdRequest.Builder().build();
        adview.loadAd(adRequest);

        fajar = findViewById(R.id.fajrtotal);
        zohar = findViewById(R.id.zohartotal);
        asar = findViewById(R.id.asartotal);
        magrib = findViewById(R.id.maghribtotal);
        esha = findViewById(R.id.eshatotal);
        btnadd = findViewById(R.id.button5);
        btnremove = findViewById(R.id.button6);
        updateOn = findViewById(R.id.updateDate);

        btnremove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),KazaNAMAZ.class);
                startActivity(intent);
                finish();
            }
        });


        btnadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),ImportKaza.class);
                startActivity(intent);
                finish();
            }
        });

        SharedPreferences prefs = getSharedPreferences(AlertDialog.MY_PREFS, MODE_PRIVATE);
        fajar.setText(String.valueOf(prefs.getLong("fajar", 0)));
        zohar.setText(String.valueOf(prefs.getLong("zohar", 0)));
        asar.setText(String.valueOf(prefs.getLong("asar", 0)));
        magrib.setText(String.valueOf(prefs.getLong("magrib", 0)));
        esha.setText(String.valueOf(prefs.getLong("esha", 0)));

        updateOn.setText("Last updated on "+prefs.getString("Date",""));
    }

}
