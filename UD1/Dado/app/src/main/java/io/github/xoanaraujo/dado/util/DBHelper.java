package io.github.xoanaraujo.dado.util;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {

    private static final String NAME = "DB_DADO";
    private static final int VERSION = 1;
    public DBHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}

class DataBaseContract {
    public static final int DB_VERSION = 1;
    public static final String DB_NAME = "DB_DADO";
    public static final String T_THROWS = "THROWS";
    public static final String C_VALUE = "THROWS";
}

