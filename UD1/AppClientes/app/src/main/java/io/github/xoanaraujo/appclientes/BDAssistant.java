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

    public boolean insertClient(Client client){
        boolean isClientInserted = false;
        SQLiteDatabase clientDB  =  getWritableDatabase();
        values.put("NIF",client.getNIF());
        values.put("name",client.getName());
        values.put("surname",client.getSurname());
        values.put("province",client.getProvince());
        values.put("VIP",client.isVip());
        values.put("latitude",client.getLatitude());
        values.put("longitude",client.getLongitude());

        if(clientDB.insert("CLIENTS",null, values) != -1){
            isClientInserted = true;
        }
        clientDB.close();
        return  isClientInserted;
    }
    @SuppressLint("Range")
    public List<ClientBase> listClients(){
        List<ClientBase> listClients = new ArrayList<>();
        SQLiteDatabase dbCLients = getWritableDatabase();
        String query = "SELECT * FROM CLIENTS";
        Cursor cursor = dbCLients.rawQuery(query,null);
        if(cursor != null){
            String name, surname, NIF;
            try {
                if(cursor.moveToFirst()) {
                    int indexName = cursor.getColumnIndex("Nombre");
                    int indexSurname = cursor.getColumnIndex("Apellido1");
                    int indexNIF = cursor.getColumnIndex("NIF");
                    do {
                        name = cursor.getString(cursor.getColumnIndex("Nombre"));
                        surname = cursor.getString(cursor.getColumnIndex("Apellido1"));
                        NIF = cursor.getString(cursor.getColumnIndex("NIF"));
                        listClients.add(new ClientBase(name, surname, NIF));

                    } while (cursor.moveToNext());
                }
            }finally {
                cursor.close();
            }
        }
        dbCLients.close();
        return listClients;
    }

}
