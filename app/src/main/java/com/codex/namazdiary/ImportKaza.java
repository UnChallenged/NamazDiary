package com.codex.namazdiary;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.unchallenged.mynamaz.R;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ImportKaza extends AppCompatActivity {

    public EditText fajar, zohar, asar, magrib, esha;
    public Button btnsubmit;
    public int Gfajar = 0, Gzohar = 0, Gasar = 0, Gmagrib = 0, Gesha = 0;
    private AdView adview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_import_kaza);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        MobileAds.initialize(this, getString(R.string.admob_app_id));

        adview = (AdView) findViewById(R.id.adviewimport);
        AdRequest adRequest = new AdRequest.Builder().build();
        adview.loadAd(adRequest);
        //     adviewlandscape.loadAd(adRequest);

        fajar = findViewById(R.id.editText);
        zohar = findViewById(R.id.editText2);
        asar = findViewById(R.id.editText3);
        magrib = findViewById(R.id.editText4);
        esha = findViewById(R.id.editText5);
        btnsubmit = findViewById(R.id.button3);

        focussetting();

        btnsubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                updaterecord();
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                Intent intent = new Intent(getApplicationContext(), MyStat.class);
                startActivity(intent);
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        Intent intent = new Intent(getApplicationContext(), MyStat.class);
        startActivity(intent);
        finish();
    }

    private void updaterecord() {
        SharedPreferences prefs = getSharedPreferences(AlertDialog.MY_PREFS, MODE_PRIVATE);

        if (fajar.getText().toString().equals("")) {
            fajar.setText("0");
            Gfajar = Integer.parseInt(fajar.getText().toString());
        } else {
            Gfajar = Integer.parseInt(fajar.getText().toString());
        }
        if (zohar.getText().toString().equals("")) {
            zohar.setText("0");
            Gzohar = Integer.parseInt(zohar.getText().toString());
        } else {
            Gzohar = Integer.parseInt(zohar.getText().toString());
        }
        if (asar.getText().toString().equals("")) {
            asar.setText("0");
            Gasar = Integer.parseInt(asar.getText().toString());
        } else {
            Gasar = Integer.parseInt(asar.getText().toString());
        }
        if (magrib.getText().toString().equals("")) {
            magrib.setText("0");
            Gmagrib = Integer.parseInt(magrib.getText().toString());
        } else {
            Gmagrib = Integer.parseInt(magrib.getText().toString());
        }
        if (esha.getText().toString().equals("")) {
            esha.setText("0");
            Gesha = Integer.parseInt(esha.getText().toString());
        } else {
            Gesha = Integer.parseInt(esha.getText().toString());
        }



        DateFormat currentTime = new SimpleDateFormat("dd-MMM-yy  hh:mm:ss aa");
        String d = currentTime.format(new Date());

        final SharedPreferences.Editor editor = getSharedPreferences(AlertDialog.MY_PREFS, MODE_PRIVATE).edit();
        editor.putLong("fajar", prefs.getLong("fajar", 0) + Gfajar);
        editor.putLong("zohar", prefs.getLong("zohar", 0) + Gzohar);
        editor.putLong("asar", prefs.getLong("asar", 0) + Gasar);
        editor.putLong("magrib", prefs.getLong("magrib", 0) + Gmagrib);
        editor.putLong("esha", prefs.getLong("esha", 0) + Gesha);


        editor.putString("Date", d);
        editor.apply();
        Toast.makeText(ImportKaza.this, "Record Updated", Toast.LENGTH_SHORT).show();
    }

    private void focussetting() {
        if (fajar.hasFocus()) {
            fajar.setText("");
            zohar.setText("0");
            asar.setText("0");
            magrib.setText("0");
            esha.setText("0");
        } else if (zohar.hasFocus()) {
            zohar.setText("");
            fajar.setText("0");
            asar.setText("0");
            magrib.setText("0");
            esha.setText("0");
        } else if (asar.hasFocus()) {
            asar.setText("");
            fajar.setText("0");
            zohar.setText("0");
            magrib.setText("0");
            esha.setText("0");
        } else if (magrib.hasFocus()) {
            magrib.setText("");
            fajar.setText("0");
            zohar.setText("0");
            asar.setText("0");
            esha.setText("0");
        } else if (esha.hasFocus()) {
            esha.setText("");
            fajar.setText("0");
            zohar.setText("0");
            asar.setText("0");
            magrib.setText("0");
        }
    }
}
