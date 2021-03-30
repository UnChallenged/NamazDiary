package com.codex.namazdiary.counterapp;

import android.provider.BaseColumns;

public final class CounterDatabaseContract {
    private CounterDatabaseContract() {
    }
    public static class CounterDatabase implements BaseColumns {
        public static final String TABLE_NAME = "savedcounter";
        public static final String COLUMN_NAME_COL1 = "countnotes";
        public static final String COLUMN_NAME_COL2 = "counts";

    }
}
