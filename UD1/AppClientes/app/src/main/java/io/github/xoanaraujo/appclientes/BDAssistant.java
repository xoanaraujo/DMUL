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

    private final static String NAME ="Clientes_DB";
    private final static int VERSION = 1;

    private static ContentValues values = new ContentValues();

    public BDAssistant(@Nullable Context context) {
        super(context, NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sqlTablaProvincias = "" +
                "CREATE TABLE provincias(" +
                "codProvincia INTEGER PRIMARY KEY AUTOINCREMENT," +
                "nombre TEXT NOT NULL)";
        String sqlTablaClientes = "CREATE TABLE clientes (" +
                "codCliente INTEGER PRIMARY KEY AUTOINCREMENT," +
                "NIF TEXT NOT NULL," +
                "nombre TEXT NOT NULL," +
                "apellidos TEXT," +
                "codProvincia INTEGER," +
                "VIP INTEGER," +
                "latitud REAL," +
                "longitud REAL)";
        db.execSQL(sqlTablaClientes);
        db.execSQL(sqlTablaProvincias);
        values.put("nombre", "A Coruña");
        db.insert("provincias", null, values);

        values.put("nombre", "Lugo");
        db.insert("provincias", null, values);

        values.put("nombre", "Ourense");
        db.insert("provincias", null, values);

        values.put("nombre", "Pontevedra");
        db.insert("provincias", null, values);


    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public boolean insertClient(Client client){
        boolean isClientInserted = false;
        SQLiteDatabase clientDB  =  getWritableDatabase();
        values.put("NIF",client.getNIF());
        values.put("nombre",client.getName());
        values.put("apellidos",client.getApellido1() + " " + client.getGetApellido2());
        values.put("provincia",client.getProvincia());
        values.put("VIP",client.isVip());
        values.put("latitud",client.getLatitud());
        values.put("longitud",client.getAltitud());

        if(clientDB.insert("CLIENTE",null, values) != -1)
            isClientInserted = true;
        clientDB.close();
        return  isClientInserted;
    }
    @SuppressLint("Range")
    public List<ClientBase> listClients(){
        List<ClientBase> listClients = new ArrayList<>();
        SQLiteDatabase dbCLientes = getWritableDatabase();
        String query = "SELECT * FROM CLIENTE";
        Cursor cursor = dbCLientes.rawQuery(query,null);
        if(cursor != null){
            String nombre, apellido1, apellido2,NIF;
            try {
                if(cursor.moveToFirst()) {
                    int indxNombre = cursor.getColumnIndex("Nombre");
                    int indxApellido1 = cursor.getColumnIndex("Apellido1");
                    int indxApellido2 = cursor.getColumnIndex("Apellido2");
                    int indxNIF = cursor.getColumnIndex("NIF");
                    do {
                        nombre = cursor.getString(cursor.getColumnIndex("Nombre"));
                        apellido1 = cursor.getString(cursor.getColumnIndex("Apellido1"));
                        apellido2 = cursor.getString(cursor.getColumnIndex("Apellido2"));
                        NIF = cursor.getString(cursor.getColumnIndex("NIF"));
                        listClients.add(new ClientBase(nombre, apellido1, apellido2, NIF));

                    } while (cursor.moveToNext());
                }
            }finally {
                cursor.close();
            }
        }
        dbCLientes.close();
        return listClients;
    }

}
