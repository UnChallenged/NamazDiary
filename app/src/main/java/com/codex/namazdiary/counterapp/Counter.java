package com.codex.namazdiary.counterapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.unchallenged.mynamaz.R;

import java.util.ArrayList;
import java.util.List;

import static com.codex.namazdiary.Reminder.MY_PREFS_NAME;

public class Counter extends AppCompatActivity {

    private Button btncounter;
    private CounterDatabase dbHelper;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter userAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private Button btnsave, btnsavewithnotes;
    private List<CounterDetails> counterDetailsList;
    private SQLiteDatabase db;
    private EditText getnotes;
    private ImageView btnblack,btngrey;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_counter);

        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);

        btncounter = findViewById(R.id.button);
        btnsave = findViewById(R.id.button9);
        btnsavewithnotes = findViewById(R.id.button7);
        dbHelper = new CounterDatabase(this);
        db = dbHelper.getReadableDatabase();
        recyclerView = findViewById(R.id.rv_counter);
        getnotes = findViewById(R.id.editText6);

        btnblack=findViewById(R.id.imgblack);
        btngrey=findViewById(R.id.imggrey);

        final Vibrator vib = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);



        //----------------------------------------------

        btngrey.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnblack.setVisibility(View.VISIBLE);
                btngrey.setVisibility(View.GONE);
                SharedPreferences.Editor editor = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE).edit();
                editor.putBoolean("vibratorOn", true);
                editor.apply();

            }
        });
        btnblack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btngrey.setVisibility(View.VISIBLE);
                btnblack.setVisibility(View.GONE);
                SharedPreferences.Editor editor = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE).edit();
                editor.remove("vibratorOn");
                editor.apply();

            }
        });

        btnsavewithnotes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (getnotes.getText().toString().isEmpty()) {
                    getnotes.setError("This field can't be empty!");
                } else {

                    db = dbHelper.getReadableDatabase();
                    String note = getnotes.getText().toString();
                    String count = btncounter.getText().toString();

                    ContentValues values = new ContentValues();
                    values.put(CounterDatabaseContract.CounterDatabase.COLUMN_NAME_COL1, note);
                    values.put(CounterDatabaseContract.CounterDatabase.COLUMN_NAME_COL2, count);

                    long rowID = db.insert(CounterDatabaseContract.CounterDatabase.TABLE_NAME, null, values);
                    if (rowID != -1) {
                        SharedPreferences.Editor editor = getSharedPreferences("CounterbtnSaved", MODE_PRIVATE).edit();
                        editor.putLong("Tcounts", 0);
                        editor.apply();
                        btncounter.setText("0");
                      //  db.close();
                        settingrcyclerview();
                        Toast.makeText(Counter.this, "Saved", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(Counter.this, "Something Wrong", Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });

        btnsave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db = dbHelper.getReadableDatabase();
                String note = btncounter.getText().toString();
                String count = "";
                getnotes.setError(null);
                ContentValues values = new ContentValues();
                values.put(CounterDatabaseContract.CounterDatabase.COLUMN_NAME_COL1, note);
                values.put(CounterDatabaseContract.CounterDatabase.COLUMN_NAME_COL2, count);

                long rowID = db.insert(CounterDatabaseContract.CounterDatabase.TABLE_NAME, null, values);
                if (rowID != -1) {
                    SharedPreferences.Editor editor = getSharedPreferences("CounterbtnSaved", MODE_PRIVATE).edit();
                    editor.putLong("Tcounts", 0);
                    editor.apply();
                    btncounter.setText("0");
                  //  db.close();
                    settingrcyclerview();
                    Toast.makeText(Counter.this, "Saved", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(Counter.this, "Something Wrong", Toast.LENGTH_SHORT).show();
                }
            }
        });


        //used to show saved counts on button
        final SharedPreferences prefs = getSharedPreferences("CounterbtnSaved", MODE_PRIVATE);
        final SharedPreferences prefsV = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
        boolean btngb = prefsV.getBoolean("vibratorOn",false);
        String t = String.valueOf(prefs.getLong("Tcounts", 0));
        btncounter.setText(t);

        if(btngb)
        {
            btngrey.setVisibility(View.GONE);
            btnblack.setVisibility(View.VISIBLE);
        }
        if(!btngb)
        {
            btnblack.setVisibility(View.GONE);
            btngrey.setVisibility(View.VISIBLE);
        }

        btncounter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Long x = Long.parseLong(btncounter.getText().toString());
                String total = String.valueOf(x + 1);
                btncounter.setText(total);

                //saving button text for later use
                final SharedPreferences.Editor editor = getSharedPreferences("CounterbtnSaved", MODE_PRIVATE).edit();
                editor.putLong("Tcounts", Long.parseLong(total));
                editor.apply();


                if(prefsV.getBoolean("vibratorOn",false))
                {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                        vib.vibrate(VibrationEffect.createOneShot(50, VibrationEffect.DEFAULT_AMPLITUDE));
                    } else {
                        //deprecated in API 26
                        vib.vibrate(50);
                    }
                }

            }
        });


        settingrcyclerview();
    }

    @Override
    protected void onDestroy() {
        db.close();
        super.onDestroy();
    }

    private void settingrcyclerview()
    {
        counterDetailsList = new ArrayList<CounterDetails>();
        counterDetailsList.clear();
        Cursor c1 = db.query(CounterDatabaseContract.CounterDatabase.TABLE_NAME, null, null, null, null, null, null);
        if (c1 != null && c1.getCount() != 0) {
            counterDetailsList.clear();
            while (c1.moveToNext()) {
                CounterDetails counterDetailsItem = new CounterDetails();
                counterDetailsItem.setUserId(c1.getInt(c1.getColumnIndex(CounterDatabaseContract.CounterDatabase._ID)));
                counterDetailsItem.setNote(c1.getString(c1.getColumnIndex(CounterDatabaseContract.CounterDatabase.COLUMN_NAME_COL1)));
                counterDetailsItem.setCounts(c1.getString(c1.getColumnIndex(CounterDatabaseContract.CounterDatabase.COLUMN_NAME_COL2)));
                counterDetailsList.add(counterDetailsItem);
            }
        }
        c1.close();

        layoutManager = new LinearLayoutManager(this);
        userAdapter = new CounterDetailsAdapter(counterDetailsList);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(userAdapter);
        int position=recyclerView.getAdapter().getItemCount()-1;
        if(!(position==-1))
        {
        recyclerView.smoothScrollToPosition(position);
    }
    }
}

