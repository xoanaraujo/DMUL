package com.example.elecciones.DATABASE;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.elecciones.DATA.Candidato;
import com.example.elecciones.DATA.Ciudadano;
import com.example.elecciones.DATA.Partido;
import com.example.elecciones.RECURSOS.Recursos;

import java.util.ArrayList;

public class MySqlite extends SQLiteOpenHelper {

    final private static String NAME = "DB_ELECCIONES1.sqlite";
    final private static int VERSION = 1;

    private ContentValues values = new ContentValues();


    public MySqlite(@Nullable Context context) {
        super(context, NAME, null, VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        String tableCiudadano =
                "ID INTEGER PRIMARY KEY AUTOINCREMENT," +
                "NIF TEXT UNIQUE," +
                "PASSWORD TEXT," +
                "HA_VOTADO INTEGER)";
        String tableCandidato = "CREATE TABLE CANDIDATO(" +
                "ID INTEGER PRIMARY KEY AUTOINCREMENT," +
                "NAME TEXT," +
                "NUM_VOTES INTEGER," +
                "ACRONYM_PARTIDO INTEGER," +
                "FOREIGN KEY(ACRONYM_PARTIDO) REFERENCES PARTIDO(ACRONYM)) ";
        //LAS CLAVES FORANEAS NO SE ACTIVAN POR DEFECTO EN SQLITE3 --> OVERRIDE onConfigure

        String tablePartido = "CREATE TABLE PARTIDO(" +
                "ID INTEGER PRIMARY KEY AUTOINCREMENT," +
                "ACRONYM TEXT UNIQUE," +
                "COLOR INTEGER)";


        db.execSQL(tableCiudadano);
        db.execSQL(tableCandidato);
        db.execSQL(tablePartido);
        values.clear();
        SQLiteStatement sql = db.compileStatement("INSERT INTO PARTIDO (ACRONYM,COLOR) VALUES  (?,?)");
        for (Partido p : Recursos.listPartidos) {
           sql.bindString(1, p.getAcronym());
            sql.bindLong(2,p.getCorporativeColor());
          /*  values.put("ACRONYM", p.getAcronym());
            values.put("COLOR", p.getCorporativeColor());
            db.insert("PARTIDO", null, values);*/
        }

        sql = db.compileStatement("INSERT INTO CANDIDATO(NAME,ACRONYM_PARTIDO) VALUES (?,?)");
        for (Candidato c : Recursos.listCandidatos) {
            sql.bindString(1,c.getName());
            sql.bindString(2,c.getP().getAcronym());
            sql.executeInsert();
          /* values.put("NAME", c.getName());
            values.put("ACRONYM_PARTIDO", c.getP().getAcronym());
            db.insert("CANDIDATO", null, values);*/
        }

        sql = db.compileStatement("INSERT INTO CIUDADANO(PASSWORD,NIF) VALUES (?,?)");

        for (Ciudadano c : Recursos.ciudadanos) {
            sql.bindString(1,c.getPassword());
            sql.bindString(2,c.getNIF());
            sql.executeInsert();
         /*   values.put("PASSWORD", c.getPassword());
            values.put("NIF", c.getNIF());
            db.insert("CIUDADANO", null, values);*/
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    @Override
    public void onConfigure(SQLiteDatabase db) {
        super.onConfigure(db);
        db.setForeignKeyConstraintsEnabled(true);
    }


    //INSERTAR CIUDADANO
    public boolean insertCiudadano(Ciudadano c) {
        /*GENERAR CODIGO*/
        SQLiteDatabase db = getWritableDatabase();
        values.clear();
        updateContentValuesCiudadano(c);
        if (db.insert("CIUDADANO", null, values) == -1) {
            db.close();
            return false;
        }

        db.close();
        return true;
    }

    private void updateContentValuesCiudadano(@NonNull Ciudadano c) {
        values.put("NIF", c.getNIF());
        values.put("PASSWORD", c.getPassword());
        values.put("HA_VOTADO", c.HaVotado() ? 1 : 0);
    }

    private void updateContentValuesCandidatoVotos(Candidato c) {
        values.clear();
        values.put("NUM_VOTES", c.getNumVotes() + 1);
    }

    //CONSULTAR CIUDADANO MEDIANTE DNI Y CONTRASEÑA
    public Ciudadano queryCiudadano(String dni, String contraseña) {
        SQLiteDatabase db = getWritableDatabase();
        String query = "SELECT * FROM CIUDADANO WHERE NIF = ? AND PASSWORD = ?";
        Cursor cursor = db.rawQuery(query, new String[]{dni, contraseña});
        Ciudadano c = null;
        if (cursor.moveToFirst()) {
            int index_HA_VOTADO = cursor.getColumnIndex("HA_VOTADO");
            c = new Ciudadano(dni, contraseña);
            c.votado(cursor.getInt(index_HA_VOTADO) == 1 ? true : false); // asegurarse de que al votar  haya 3 votos
        }
        cursor.close();
        db.close();
        return c;
    }

    //LISTAR CANDIDATOS
    public ArrayList<Candidato> listarCandidatos(boolean primerCandidatoBlanco) {
        ArrayList<Candidato> l = new ArrayList<>();
        if(primerCandidatoBlanco){
            l.add(new Candidato());
        }
        SQLiteDatabase db = getWritableDatabase();
        String query = "SELECT NAME,COLOR,ACRONYM_PARTIDO, NUM_VOTES FROM CANDIDATO INNER JOIN PARTIDO ON ACRONYM_PARTIDO = ACRONYM  ";
        Cursor cursor = db.rawQuery(query, null);
        String name = "", acronymPartido = "";
        int color = 0;


        boolean next = true;

        if (cursor.moveToFirst()) {
            int nameIndx = cursor.getColumnIndex("NAME");
            int acronymIndx = cursor.getColumnIndex("ACRONYM_PARTIDO");
            int colorIndx = cursor.getColumnIndex("COLOR");
            int votesIndx = cursor.getColumnIndex("NUM_VOTES");
            Candidato c = null;
            while (next) {
                name = cursor.getString(nameIndx);
                acronymPartido = cursor.getString(acronymIndx);
                color = cursor.getInt(colorIndx);
                c = new Candidato(name, new Partido(acronymPartido, color));
                c.setNumVotes(cursor.getInt(votesIndx));
                l.add(c);
                if (!cursor.moveToNext())
                    next = false;
            }
        }

        return l;
    }

    //TRANSACCION
    public boolean updateVotos(Ciudadano c) {
        SQLiteDatabase db = getWritableDatabase();
        String[] args = null;
        int numRowAfectadas = 0;
        try {
            db.beginTransaction();
            for (Candidato cand : c.getCandidatos()) {
                updateContentValuesCandidatoVotos(cand);
                args = new String[]{cand.getName(), cand.getP().getAcronym()};
               if( db.update("CANDIDATO", values, "NAME = ? AND ACRONYM_PARTIDO = ? ", args)!=1){
                   db.endTransaction();
                   return false;
               }
            }
            values.clear();
            updateContentValuesCiudadano(c);
            args = new String[]{c.getNIF()};
            if(db.update("CIUDADANO", values, "NIF = ? ", args)!=1){
                db.endTransaction();
                return false;
            }
            db.setTransactionSuccessful();
        } finally {
            db.endTransaction();
        }
        db.close();
        return true;
    }


}
