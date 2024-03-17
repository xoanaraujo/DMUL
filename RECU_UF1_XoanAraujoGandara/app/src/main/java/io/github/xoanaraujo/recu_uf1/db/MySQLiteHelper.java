package io.github.xoanaraujo.recu_uf1.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.HashMap;

import io.github.xoanaraujo.recu_uf1.model.Grupo;

public class MySQLiteHelper extends SQLiteOpenHelper {
    private static final Grupo[] grupos = new Grupo[] {
            new Grupo("DAM"),
            new Grupo("DAW"),
            new Grupo("ASIR"),
            new Grupo("SMR"),
            new Grupo("1 ESO")
    };
    public static final String DB_NAME = "dbRecu";
    public static final int VERSION = 1;
    private final ContentValues cv;

    public MySQLiteHelper(@Nullable Context context) {
        super(context, DB_NAME, null, VERSION);
        cv = new ContentValues();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String tableGrupo = "CREATE TABLE GRUPOS (" +
                "ID INTEGER PRIMARY KEY AUTOINCREMENT," +
                "NOMBRE TEXT)";
        db.execSQL(tableGrupo);
        String tableAlumno = "CREATE TABLE ALUMNOS (" +
                "ID INTEGER PRIMARY KEY AUTOINCREMENT," +
                "DNI TEXT UNIQUE," +
                "NOMBRE TEXT," +
                "IDGRUPO INTEGER," +
                "FOREIGN KEY(IDGRUPO) REFERENCES GRUPOS(ID))";
        db.execSQL(tableAlumno);

        String insertGrupos = "INSERT INTO GRUPOS (NOMBRE) VALUES (?)";
        SQLiteStatement stmInsertGrupos = db.compileStatement(insertGrupos);
        for (int i = 0; i < grupos.length; i++) {
            stmInsertGrupos.bindString(1, grupos[i].getNombre());
            stmInsertGrupos.executeInsert();
        }
        /*String insertAlumnos = "INSERT INTO ALUMNOS (DNI, NOMBRE, IDGRUPO) VALUES (?, ?, ?)";
        SQLiteStatement stmInsertAlumnos = db.compileStatement(insertAlumnos);
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < (i +1); j++) {
                stmInsertAlumnos.bindString(1, "DNI " + (j + 1) + " " + (i + 1));
                stmInsertAlumnos.bindString(2, "Alumno " + (j + 1) + " " + (i + 1));
                stmInsertAlumnos.bindLong(3, i + 1);
                stmInsertAlumnos.executeInsert();
            }
        }
         */
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
