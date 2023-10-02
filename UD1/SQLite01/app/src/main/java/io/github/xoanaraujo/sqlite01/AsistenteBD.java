package io.github.xoanaraujo.sqlite01;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class AsistenteBD extends SQLiteOpenHelper {
    public AsistenteBD(@Nullable Context context) {
        super(context, null, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sqlTablaProvincias = "CREATE TABLE provincias (codProvincia INTEGER PRIMARY KEY AUTOINCREMENT, nombre TEXT NOT NULL)";
        String sqlTablaClientes = "CREATE TABLE clientes (codCliente INTEGER PRIMARY KEY AUTOINCREMENT, nombre TEXT NOT NULL, apellidos TEXT," +
                "NIF TEXT NOT NULL, codProvincia INTEGER, VIP INTEGER, latitud REAL, longitud REAL)";
        db.execSQL(sqlTablaClientes);
        db.execSQL(sqlTablaProvincias);
        ContentValues cv = new ContentValues();
        cv.put("nombre", "A Coruña");
        db.insert("provincias", null, cv);

        cv.put("nombre", "Lugo");
        db.insert("provincias", null, cv);

        cv.put("nombre", "Ourense");
        db.insert("provincias", null, cv);

        cv.put("nombre", "Pontevedra");
        db.insert("provincias", null, cv);


    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

}
