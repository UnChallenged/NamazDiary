package com.codex.namazdiary.counterapp;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class CounterDatabase extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "CounterDB.db";

    public CounterDatabase(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_Counter_Table);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersio, int newVersion) {

        db.execSQL(Delete_Counter_Table);
        onCreate(db);

    }

    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }

    private static final String CREATE_Counter_Table = "CREATE TABLE " + CounterDatabaseContract.CounterDatabase.TABLE_NAME +
            "( " + CounterDatabaseContract.CounterDatabase._ID + " INTEGER PRIMARY KEY," +
            CounterDatabaseContract.CounterDatabase.COLUMN_NAME_COL1 + " text," +
            CounterDatabaseContract.CounterDatabase.COLUMN_NAME_COL2 + " text)";
    private static final String Delete_Counter_Table = "DROP TABLE IF EXISTS " + CounterDatabaseContract.CounterDatabase.TABLE_NAME;
}
