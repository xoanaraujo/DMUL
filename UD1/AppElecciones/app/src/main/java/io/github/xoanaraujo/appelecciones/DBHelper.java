package io.github.xoanaraujo.appelecciones;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import io.github.xoanaraujo.appelecciones.model.Util;

public class DBHelper extends SQLiteOpenHelper {

    public static final int VERSION = 1;
    public static final String DB_ELECCIONES = "DB_ELECCIONES";
    public static final String TABLE_CANDIDATES = "CANDIDATES";
    public static final String COL_COD_CANDIDATE = "codCandidate";
    public static final String COL_NAME = "name";
    public static final String COL_PARTY = "party";
    public static final String COL_VOTES = "votes";
    public static final String TABLE_VOTERS = "VOTERS";
    public static final String COL_COD_VOTER = "codVoter";
    public static final String COL_NIF = "nif";
    public static final String COL_PASSWORD = "password";
    public static final String COL_NUM_VOTES = "numVotes";
    public static final String TABLE_PARTIES = "PARTIES";
    public static final String COL_COD_PARTY = "codParty";
    public static final String COL_PARTY_NAME = "partyName";
    public static final String COL_COLOR = "color";

    public DBHelper(@Nullable Context context) {
        super(context, DB_ELECCIONES, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createCandidates = "CREATE TABLE " + TABLE_CANDIDATES + "(" +
                COL_COD_CANDIDATE + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                COL_NAME + " TEXT NOT NULL," +
                COL_PARTY + " INTEGER NOT NULL," +
                COL_VOTES + " TEXT NOT NULL," +
                "FOREIGN KEY(" + COL_PARTY + ") REFERENCES " + TABLE_PARTIES + "(" + COL_COD_PARTY + "))";

        String createParties = "CREATE TABLE " + TABLE_PARTIES + "(" +
                COL_COD_PARTY + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                COL_PARTY_NAME + " TEXT NOT NULL," +
                COL_COLOR + " TEXT NOT NULL)";

        String createVoters = "CREATE TABLE " + TABLE_VOTERS + "(" +
                COL_COD_VOTER + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                COL_NIF + " TEXT NOT NULL," +
                COL_PASSWORD + " TEXT NOT NULL," +
                COL_NUM_VOTES + " INTEGER NOT NULL) ";

        db.execSQL(createCandidates);
        db.execSQL(createParties);
        db.execSQL(createVoters);

        ContentValues cv = new ContentValues();
        cv.put(COL_NAME, "Pedro Sanchez");
        cv.put(COL_PARTY, "PSOE");
        cv.put(COL_VOTES, 0);
        db.insert(TABLE_CANDIDATES, null, cv);
        cv.put(COL_NAME, "Mariano Rajoy");
        cv.put(COL_PARTY, "PSOE");
        cv.put(COL_VOTES, 0);
        db.insert(TABLE_CANDIDATES, null, cv);
        cv.put(COL_NAME, "Yolanda Diaz");
        cv.put(COL_PARTY, "SUMAR");
        cv.put(COL_VOTES, 0);
        db.insert(TABLE_CANDIDATES, null, cv);
        cv.put(COL_NAME, "Santiago Abascal");
        cv.put(COL_PARTY, "VOX");
        cv.put(COL_VOTES, 0);
        db.insert(TABLE_CANDIDATES, null, cv);

        cv.put(COL_NIF, "77550086C");
        cv.put(COL_PASSWORD, Util.generateHash("abc123."));
        cv.put(COL_NUM_VOTES, 0);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public void addVote(String nif, String password, String candidateName){
        ContentValues cv = new ContentValues();
        SQLiteDatabase db = getReadableDatabase();

        String selectNumVotes =
                "SELECT " + COL_NUM_VOTES +
                        " FROM " + TABLE_VOTERS +
                        " WHERE " + COL_NIF + " LIKE '" + nif + "' " +
                        "AND " + COL_PASSWORD + " LIKE '" + password + "'";

        db.execSQL(selectNumVotes);

    }
}
