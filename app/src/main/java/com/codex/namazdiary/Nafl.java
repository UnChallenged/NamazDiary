package com.codex.namazdiary;

import android.content.SharedPreferences;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.unchallenged.mynamaz.R;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

public class Nafl extends AppCompatActivity {
    private String MyNafil = "Nafil";
    private Button btnadd, btnremove;
    private EditText addtxt, removetxt;
    private TextView total;
    private AdView adview;
    private long tempadd = 0, tempremove = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nafl);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        MobileAds.initialize(this, getString(R.string.admob_app_id));

        adview = (AdView) findViewById(R.id.adviewnafl);
        AdRequest adRequest = new AdRequest.Builder().build();
        adview.loadAd(adRequest);
        //     adviewlandscape.loadAd(adRequest);

        btnadd = (Button) findViewById(R.id.buttonAdd);
        btnremove = (Button) findViewById(R.id.ButtonRemove);

        addtxt = (EditText) findViewById(R.id.editTextAdd);
        removetxt = (EditText) findViewById(R.id.editTextRemove);

        total = (TextView) findViewById(R.id.viewTotal);

        SharedPreferences prefs = getSharedPreferences(MyNafil, MODE_PRIVATE);
        String t = String.valueOf(prefs.getLong("TotalN", 0));
        total.append(t);

        btnadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (addtxt.getText().toString().equals("")) {
                    tempadd = 0;
                    add();
                } else {
                    tempadd = Long.parseLong(addtxt.getText().toString());
                    add();
                }


            }
        });

        btnremove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (removetxt.getText().toString().equals("")) {
                    tempremove = 0;
                    remove();
                } else {
                    tempremove = Long.parseLong(removetxt.getText().toString());
                    remove();
                }


            }
        });

        addtxt.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    removetxt.setText("");
                }
            }
        });
        removetxt.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    addtxt.setText("");
                }
            }
        });
    }

    private void add() {

        long temptotal = Long.parseLong((total.getText().toString()));


        long result = temptotal + tempadd;
        final SharedPreferences.Editor editor = getSharedPreferences(MyNafil, MODE_PRIVATE).edit();
        editor.putLong("TotalN", result);
        editor.apply();
        SharedPreferences prefs = getSharedPreferences(MyNafil, MODE_PRIVATE);

        String t = String.valueOf(prefs.getLong("TotalN", 0));
        total.setText(t);

    }

    private void remove() {
        long temptotal = Long.parseLong(total.getText().toString());
        long result = temptotal - tempremove;
        final SharedPreferences.Editor editor = getSharedPreferences(MyNafil, MODE_PRIVATE).edit();
        editor.putLong("TotalN", result);
        editor.apply();
        SharedPreferences prefs = getSharedPreferences(MyNafil, MODE_PRIVATE);
        String t = String.valueOf(prefs.getLong("TotalN", 0));
        total.setText(t);
    }
}
