package xoanaraujo.recu_uf1.db;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import java.util.ArrayList;

import xoanaraujo.recu_uf1.model.Habitacion;
import xoanaraujo.recu_uf1.model.Hotel;

public class CRUD {
    public static Hotel[] selectHoteles(SQLiteDatabase rdb){
        ArrayList<Hotel> hoteles = new ArrayList<>();

        String selectHoteles = "SELECT * FROM HOTELES";
        Cursor cursor = rdb.rawQuery(selectHoteles, null);
        while (cursor.moveToNext()){
            int indexId = cursor.getColumnIndex("ID");
            int indexNombre = cursor.getColumnIndex("NOMBRE");
            int indexEstrellas = cursor.getColumnIndex("ESTRELLAS");
            hoteles.add(new Hotel(cursor.getInt(indexId), cursor.getString(indexNombre), cursor.getInt(indexEstrellas)));
        }
        return hoteles.toArray(hoteles.toArray(new Hotel[0]));
    }

    public static Hotel selectHotelByNumHabitacion (SQLiteDatabase rdb, Integer numero){
        Hotel hotel = null;
        String selectHoteles = "SELECT * FROM HOTELES WHERE ID = (SELECT IDHOTEL FROM HABITACIONES WHERE NUMERO = ?)";
        Cursor cursor = rdb.rawQuery(selectHoteles, new String[]{String.valueOf(numero)});
        while (cursor.moveToNext()){
            int indexId = cursor.getColumnIndex("ID");
            int indexNombre = cursor.getColumnIndex("NOMBRE");
            int indexEstrellas = cursor.getColumnIndex("ESTRELLAS");
            hotel = new Hotel(cursor.getInt(indexId), cursor.getString(indexNombre), cursor.getInt(indexEstrellas));
        }
        return hotel;
    }

    public static void deleteHotelById(SQLiteDatabase wdb, int id) {
        String delete = "DELETE FROM HOTELES WHERE ID = ?";
        SQLiteStatement statement = wdb.compileStatement(delete);
        statement.bindLong(1, id);
        if (statement.executeUpdateDelete() != 1){
            throw new RuntimeException("CRUD.deleteHotelById elimino un numero de hoteles != de 1");
        }
    }

    public static Habitacion[] selectHabitacionesByHotel(SQLiteDatabase db, Integer idHotel){
        ArrayList<Habitacion> habitaciones = new ArrayList<>();

        String selectHabitacionesByIdHotel = "SELECT * FROM HABITACIONES WHERE IDHOTEL = ?";
        Cursor cursor = db.rawQuery(selectHabitacionesByIdHotel, new String[]{String.valueOf(idHotel)});
        while (cursor.moveToNext()){
            int indexIdHotel = cursor.getColumnIndex("IDHOTEL");
            int indexNumero = cursor.getColumnIndex("NUMERO");
            int indexPrecio = cursor.getColumnIndex("PRECIO");
            habitaciones.add(new Habitacion(cursor.getInt(indexIdHotel), cursor.getInt(indexNumero), cursor.getInt(indexPrecio)));
        }
        return habitaciones.toArray(habitaciones.toArray(new Habitacion[0]));
    }
}
