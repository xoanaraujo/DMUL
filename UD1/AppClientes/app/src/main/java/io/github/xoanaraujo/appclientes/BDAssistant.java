package io.github.xoanaraujo.appclientes;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class BDAssistant extends SQLiteOpenHelper {

    private final static String NAME ="CLIENTS";
    private final static int VERSION = 1;

    private static ContentValues values = new ContentValues();

    public BDAssistant(@Nullable Context context) {
        super(context, NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sqlTablaProvincias = "" +
                "CREATE TABLE PROVINCES(" +
                "codProvince INTEGER PRIMARY KEY AUTOINCREMENT," +
                "name TEXT NOT NULL)";
        String sqlTablaClientes = "CREATE TABLE CLIENTS (" +
                "codClient INTEGER PRIMARY KEY AUTOINCREMENT," +
                "NIF TEXT NOT NULL," +
                "name TEXT NOT NULL," +
                "surname TEXT," +
                "codProvince INTEGER," +
                "VIP INTEGER," +
                "latitude REAL," +
                "longitude REAL)";
        db.execSQL(sqlTablaClientes);
        db.execSQL(sqlTablaProvincias);
        values.put("name", "A Coruña");
        db.insert("PROVINCES", null, values);

        values.put("name", "Lugo");
        db.insert("PROVINCES", null, values);

        values.put("name", "Ourense");
        db.insert("PROVINCES", null, values);

        values.put("name", "Pontevedra");
        db.insert("PROVINCES", null, values);


    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
