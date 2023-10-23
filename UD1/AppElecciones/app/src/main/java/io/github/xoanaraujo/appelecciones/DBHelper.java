package io.github.xoanaraujo.appelecciones;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

import io.github.xoanaraujo.appelecciones.model.Candidate;
import io.github.xoanaraujo.appelecciones.model.Util;
import io.github.xoanaraujo.appelecciones.model.Voter;

public class DBHelper extends SQLiteOpenHelper {

    private enum Partidos{
        PP("PP"), PSOE("PSOE"), SUMAR("SUMAR"), VOX("VOX");
        Partidos(String partido) {
        }
    }

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
        String createParties = "CREATE TABLE " + TABLE_PARTIES + "(" +
                COL_COD_PARTY + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                COL_PARTY_NAME + " TEXT NOT NULL," +
                COL_COLOR + " TEXT NOT NULL)";

        String createCandidates = "CREATE TABLE " + TABLE_CANDIDATES + "(" +
                COL_COD_CANDIDATE + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                COL_NAME + " TEXT NOT NULL," +
                COL_PARTY + " INTEGER NOT NULL," +
                COL_VOTES + " TEXT NOT NULL," +
                "FOREIGN KEY(" + COL_PARTY + ") REFERENCES " + TABLE_PARTIES + "(" + COL_COD_PARTY + "))";

        String createVoters = "CREATE TABLE " + TABLE_VOTERS + "(" +
                COL_COD_VOTER + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                COL_NIF + " TEXT NOT NULL," +
                COL_PASSWORD + " TEXT NOT NULL," +
                COL_NUM_VOTES + " INTEGER NOT NULL) ";

        db.execSQL(createParties);
        db.execSQL(createCandidates);
        db.execSQL(createVoters);

        ContentValues cv = new ContentValues();

        cv.put(COL_COD_PARTY, 0);
        cv.put(COL_PARTY_NAME, Partidos.PP.name());
        cv.put(COL_COD_PARTY, 1);
        cv.put(COL_PARTY_NAME, Partidos.PSOE.name());
        cv.put(COL_COD_PARTY, 2);
        cv.put(COL_PARTY_NAME, Partidos.SUMAR.name());
        cv.put(COL_COD_PARTY, 3);
        cv.put(COL_PARTY_NAME, Partidos.VOX.name());
        cv.clear();

        cv.put(COL_NAME, "Pedro Sanchez");
        cv.put(COL_PARTY, "PSOE");
        cv.put(COL_VOTES, 0);
        db.insert(TABLE_CANDIDATES, null, cv);
        cv.put(COL_NAME, "Mariano Rajoy");
        cv.put(COL_PARTY, "PP");
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
        cv.clear();

        cv.put(COL_NIF, "");
        cv.put(COL_PASSWORD, Util.generateHash(""));
        cv.put(COL_NUM_VOTES, 0);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public void addVoter(Voter voter){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues cv = new ContentValues();
        String insertVoter = "INSERT INTO " + TABLE_VOTERS + "(" + COL_NIF + ", " + COL_PASSWORD + ") VALUES (?, ?)";
        db.execSQL(insertVoter, new String[]{voter.getNif(), Util.generateHash(voter.getPassword())});

    }

    public void addVote(String nif, String candidateName) {
        SQLiteDatabase db = getWritableDatabase();

        // Actualizar COL_NUM_VOTES en TABLE_VOTERS
        ContentValues cvVoters = new ContentValues();
        String selectNumVotes = "SELECT " + COL_NUM_VOTES +
                " FROM " + TABLE_VOTERS +
                " WHERE " + COL_NIF + " = ?";
        Cursor cursor = db.rawQuery(selectNumVotes, new String[]{nif});
        int colNumVotes = cursor.getColumnIndex(COL_NUM_VOTES);
        if (cursor.moveToFirst()) {
            int numVotes = cursor.getInt(colNumVotes);
            cvVoters.put(COL_NUM_VOTES, numVotes + 1);
            db.update(TABLE_VOTERS, cvVoters, COL_NIF + " = ?", new String[]{nif});
        }
        cursor.close();

        // Actualizar COL_VOTES esan TABLE_CANDIDATES
        ContentValues cvCandidates = new ContentValues();
        String selectCandidateVotes = "SELECT " + COL_VOTES +
                " FROM " + TABLE_CANDIDATES +
                " WHERE " + COL_NAME + " = ?";
        Cursor cursorCandidate = db.rawQuery(selectCandidateVotes, new String[]{candidateName});
        int colVotes = cursorCandidate.getColumnIndex(COL_VOTES);
        if (cursorCandidate.moveToFirst()) {
            int candidateVotes = cursorCandidate.getInt(colVotes);
            cvCandidates.put(COL_VOTES, candidateVotes + 1);
            db.update(TABLE_CANDIDATES, cvCandidates, COL_NAME + " = ?", new String[]{candidateName});
        }
        cursorCandidate.close();

        db.close();
    }

    public ArrayList<Candidate> getCandidates(){
        ArrayList<Candidate> candidates = new ArrayList<>();
        String selectCandidates =
                "SELECT C." + COL_COD_CANDIDATE + ", C." + COL_NAME + ", P." + COL_COD_PARTY + ", C." + COL_VOTES + " " +
                "FROM " + TABLE_CANDIDATES + "C INNER JOIN " + TABLE_PARTIES + " P ON C." + COL_PARTY + "= P." + COL_COD_PARTY;
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery(selectCandidates, null);
        int colCod = cursor.getColumnIndex(COL_COD_CANDIDATE);
        int colName = cursor.getColumnIndex(COL_NAME);
        int colParty = cursor.getColumnIndex(COL_NAME);
        int colVotes = cursor.getColumnIndex(COL_VOTES);
        if (cursor.moveToFirst()){
            while (cursor.moveToNext()){
                int codCandidate = cursor.getInt(colCod);
                String name = cursor.getString(colName);
                String party = cursor.getString(colParty);
                int votes = cursor.getInt(colVotes);
                candidates.add(new Candidate(codCandidate, name, party, votes));
            }
        }
        return candidates;
    }
    private boolean voterExists(String nif, String password) {
        String voterExists = "SELECT " + COL_COD_VOTER +
                " FROM " + TABLE_VOTERS +
                " WHERE " + COL_NIF + " = ? " + "AND " + COL_PASSWORD + " = ?";
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery(voterExists, new String[]{nif, Util.generateHash(password)});
        return cursor.moveToFirst();
    }

    public Voter getVoter(String voterNif, String voterPassword){
        Voter voter = null;

        String selectNif = "SELECT " + COL_COD_VOTER + ", " + COL_NIF + ", " + COL_PASSWORD + " " +
                " FROM " + TABLE_VOTERS +
                " WHERE " + COL_NIF + " = ? AND " + COL_PASSWORD + " = ?";

        Cursor cursor = getReadableDatabase().rawQuery(selectNif, new String[]{voterNif, voterPassword});
        int colCod = cursor.getColumnIndex(COL_COD_VOTER);
        int colNif = cursor.getColumnIndex(COL_NIF);
        int colPassword = cursor.getColumnIndex(COL_PASSWORD);
        if (cursor.moveToFirst()){
            int cod = cursor.getInt(colCod);
            String nif = cursor.getString(colNif);
            String pass = cursor.getString(colPassword);
            voter = new Voter(nif, pass);
        }
        return voter;
    }
}
