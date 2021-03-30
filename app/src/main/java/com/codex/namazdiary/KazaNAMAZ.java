package com.codex.namazdiary;

import android.content.SharedPreferences;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.unchallenged.mynamaz.R;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import static com.codex.namazdiary.AlertDialog.MY_PREFS;

public class KazaNAMAZ extends AppCompatActivity {
    public Button btncancel,btnsubmit;
    public TextView incr1,incr2,incr3,incr4,incr5;
    public Button btnIncr1,btnIncr2,btnIncr3,btnIncr4,btnIncr5,btnDecr1,btnDecr2,btnDecr3,btnDecr4,btnDecr5;
    private int count1 = 0, decCount1, count2 = 0, count3 = 0, count4 = 0, count5 = 0,decCount2,decCount3,decCount4,decCount5;
    private AdView adview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kaza_namaz);

        MobileAds.initialize(this, getString(R.string.admob_app_id));

        adview = (AdView) findViewById(R.id.adviewkaza);
        AdRequest adRequest = new AdRequest.Builder().build();
        adview.loadAd(adRequest);


        btnsubmit = (Button) findViewById(R.id.btnsbmt);
        btncancel = (Button) findViewById(R.id.btncncl);

        incr1 = (TextView) findViewById(R.id.fajrcount);
        incr2 = (TextView) findViewById(R.id.zoharcount);
        incr3 = (TextView) findViewById(R.id.asarcount);
        incr4 = (TextView) findViewById(R.id.maghribcount);
        incr5 = (TextView) findViewById(R.id.eshacount);

        btnIncr1 = (Button) findViewById(R.id.FajrIncr);
        btnIncr2 = (Button) findViewById(R.id.ZoharIncr);
        btnIncr3 = (Button) findViewById(R.id.AsarIncr);
        btnIncr4 = (Button) findViewById(R.id.MaghribInc);
        btnIncr5 = (Button) findViewById(R.id.EshaInc);

        btnDecr1 = (Button) findViewById(R.id.FajrDec);
        btnDecr2 = (Button) findViewById(R.id.ZoharDec);
        btnDecr3 = (Button) findViewById(R.id.Asardec);
        btnDecr4 = (Button) findViewById(R.id.MaghribDec);
        btnDecr5 = (Button) findViewById(R.id.EshaDec);

        if (count1 ==0){
            btnDecr1.setEnabled(false);
        }
        if (count2 ==0){
            btnDecr2.setEnabled(false);
        }
        if (count3 ==0){
            btnDecr3.setEnabled(false);
        }
        if (count4 ==0){
            btnDecr4.setEnabled(false);
        }
        if (count5 ==0){
            btnDecr5.setEnabled(false);
        }
        btnIncr1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                incr1.setText("");
                count1 = count1 +1;
                String res = "";
                res = String.valueOf(count1);
                incr1.append(res);
                if(count1 >0){
                    btnDecr1.setEnabled(true);
                }

            }
        });
        btnIncr2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                incr2.setText("");
                count2 = count2 +1;
                String res = "";
                res = String.valueOf(count2);
                incr2.append(res);
                if(count2 >0){
                    btnDecr2.setEnabled(true);
                }

            }
        });
        btnIncr3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                incr3.setText("");
                count3 = count3 +1;
                String res = "";
                res = String.valueOf(count3);
                incr3.append(res);
                if(count3 >0){
                    btnDecr3.setEnabled(true);
                }

            }
        });
        btnIncr4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                incr4.setText("");
                count4 = count4 +1;
                String res = "";
                res = String.valueOf(count4);
                incr4.append(res);
                if(count4 >0){
                    btnDecr4.setEnabled(true);
                }

            }
        });
        btnIncr5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                incr5.setText("");
                count5 = count5 +1;
                String res = "";
                res = String.valueOf(count5);
                incr5.append(res);
                if(count5 >0){
                    btnDecr5.setEnabled(true);
                }
            }
        });

        btnDecr1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                incr1.setText("");
                decCount1 = count1;
                decCount1 = decCount1 -1;
                String res = String.valueOf(decCount1);
                incr1.append(res);
                count1 = decCount1;
                if (decCount1 ==0){
                    btnDecr1.setEnabled(false);
                }



            }
        });
        btnDecr2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                incr2.setText("");
                decCount2 = count2;
                decCount2 = decCount2 -1;
                String res = String.valueOf(decCount2);
                incr2.append(res);
                count2 = decCount2;
                if (decCount2 ==0){
                    btnDecr2.setEnabled(false);
                }
            }
        });
        btnDecr3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                incr3.setText("");
                decCount3 = count3;
                decCount3 = decCount3 -1;
                String res = String.valueOf(decCount3);
                incr3.append(res);
                count3 = decCount3;
                if (decCount3 ==0){
                    btnDecr3.setEnabled(false);
                }

            }
        });
        btnDecr4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                incr4.setText("");
                decCount4 = count4;
                decCount4 = decCount4 -1;
                String res = String.valueOf(decCount4);
                incr4.append(res);
                count4 = decCount4;
                if (decCount4 ==0){
                    btnDecr4.setEnabled(false);
                }

            }
        });
        btnDecr5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                incr5.setText("");
                decCount5 = count5;
                decCount5 = decCount5 -1;
                String res = String.valueOf(decCount5);
                incr5.append(res);
                count5 = decCount5;
                if (decCount5 == 0){
                    btnDecr5.setEnabled(false);
                }

            }
        });


        btncancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        btnsubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SharedPreferences prefs = getSharedPreferences(MY_PREFS, MODE_PRIVATE);

                final SharedPreferences.Editor editor = getSharedPreferences(MY_PREFS, MODE_PRIVATE).edit();
                editor.putLong("fajar",prefs.getLong("fajar",0)-count1);
                editor.putLong("zohar",prefs.getLong("zohar",0)-count2);
                editor.putLong("asar",prefs.getLong("asar",0)-count3);
                editor.putLong("magrib",prefs.getLong("magrib",0)-count4);
                editor.putLong("esha",prefs.getLong("esha",0)-count5);


                DateFormat currentTime= new SimpleDateFormat("dd-MMM-yy  hh:mm:ss aa");
                String d=currentTime.format(new Date());
                editor.putString("Date",d);
                editor.apply();

                Toast.makeText(KazaNAMAZ.this, "Recored Updated", Toast.LENGTH_SHORT).show();
                /*Intent intent = new Intent(getApplicationContext(),MyStat.class);
                startActivity(intent);*/
                finish();

            }
        });


    }
}
