package xoanaraujo.examenuf1;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;

import androidx.annotation.Nullable;

import java.util.ArrayList;

import xoanaraujo.examenuf1.model.Room;

public class DBHelper extends SQLiteOpenHelper {

    private static final int VERSION = 1;
    private static final String NAME = "DBExamen";

    private static final String[] rooms = {"Ascensor", "Camino"};
    private static final String[] paths = {"wd", "waada"};

    public DBHelper(@Nullable Context context) {
        super(context, NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String tablaRooms = "CREATE TABLE ROOM(" +
                "ID INTEGER PRIMARY KEY AUTOINCREMENT," +
                "NAME TEXT," +
                "PATH TEXT)";
        db.execSQL(tablaRooms);

        SQLiteStatement insertRooms = db.compileStatement("INSERT INTO ROOM (NAME, PATH) VALUES(?, ?)");
        for (int i = 0; i < rooms.length; i++) {
            insertRooms.bindString(1, rooms[i]);
            insertRooms.bindString(2, paths[i]);
            insertRooms.executeInsert();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public ArrayList<Room> getRooms(){
        SQLiteDatabase db = getReadableDatabase();
        String query = "SELECT ID, NAME, PATH FROM ROOM";
        Cursor cursor =db.rawQuery(query, null);


        if (cursor.moveToFirst()){
            ArrayList<Room> rooms = new ArrayList<>();
            int idIndex = cursor.getColumnIndex("ID");
            int nameIndex = cursor.getColumnIndex("NAME");
            int pathIndex = cursor.getColumnIndex("PATH");
            int id = 0;
            String name, path;
            boolean next = true;
            while (next){
                id = cursor.getInt(idIndex);
                name = cursor.getString(nameIndex);
                path = cursor.getString(pathIndex);
                rooms.add(new Room(id, name, path));
                if (!cursor.moveToNext())
                    next = false;
            }
            return rooms;
        }
        return null;
    }

    public Room getRoomById(int id){
        SQLiteDatabase db = getReadableDatabase();
        String query = "SELECT NAME, PATH FROM ROOM WHERE ID = ?";
        Cursor cursor = db.rawQuery(query, new String[]{String.valueOf(id)});
        if (cursor.moveToFirst()){
            int nameIndex = cursor.getColumnIndex("NAME");
            int pathIndex = cursor.getColumnIndex("PATH");
            String name = cursor.getString(nameIndex);
            String path = cursor.getString(pathIndex);
            return new Room(id, name, path);
        }
        return null;
    }
}
