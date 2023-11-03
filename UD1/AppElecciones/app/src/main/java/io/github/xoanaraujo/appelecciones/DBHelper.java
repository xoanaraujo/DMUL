package io.github.xoanaraujo.appelecciones;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

import io.github.xoanaraujo.appelecciones.model.Candidate;
import io.github.xoanaraujo.appelecciones.model.Util;
import io.github.xoanaraujo.appelecciones.model.Voter;

public class DBHelper extends SQLiteOpenHelper {
    public static final int VERSION = 1;
    public static final String DB_ELECCIONES = "DB_ELECCIONES";


    public DBHelper(@Nullable Context context) {
        super(context, DB_ELECCIONES, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE VOTERS (NIF TEXT PRIMARY KEY, PASSWORD TEXT, VOTED INTEGER)");
        db.execSQL("CREATE TABLE PARTIES (codPartido INTEGER PRIMARY KEY AUTOINCREMENT, NAME TEXT, COLOR INTEGER)");
        db.execSQL("CREATE TABLE CANDIDATES (codCandidato INTEGER PRIMARY KEY AUTOINCREMENT, CODPARTY INTEGER, NAMEPARTY TEXT, votos INTEGER DEFAULT 0)");

        SQLiteStatement insertParties = db.compileStatement("INSERT INTO PARTIES ()");

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    private void insertParty(SQLiteStatement insertParty, String partyName, int color, String[] candidates) {
        insertParty.bindString();
    }

    public void addVoter(Voter voter) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues cv = new ContentValues();
        String insertVoter = "INSERT INTO VOTERS ( nif, password) VALUES (?, ?)";
        db.execSQL(insertVoter, new String[]{voter.getNif(), Util.generateHash(voter.getPassword())});

    }

    /*public void addVote(String nif, String candidateName) {
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

    public ArrayList<Candidate> getCandidates() {
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
        if (cursor.moveToFirst()) {
            while (cursor.moveToNext()) {
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

    public Voter getVoter(String voterNif, String voterPassword) {
        Voter voter = null;

        String selectNif = "SELECT " + COL_COD_VOTER + ", " + COL_NIF + ", " + COL_PASSWORD + " " +
                " FROM " + TABLE_VOTERS +
                " WHERE " + COL_NIF + " = ? AND " + COL_PASSWORD + " = ?";

        Cursor cursor = getReadableDatabase().rawQuery(selectNif, new String[]{voterNif, voterPassword});
        int colCod = cursor.getColumnIndex(COL_COD_VOTER);
        int colNif = cursor.getColumnIndex(COL_NIF);
        int colPassword = cursor.getColumnIndex(COL_PASSWORD);
        if (cursor.moveToFirst()) {
            int cod = cursor.getInt(colCod);
            String nif = cursor.getString(colNif);
            String pass = cursor.getString(colPassword);
            voter = new Voter(nif, pass);
        }
        return voter;
    }*/
}
