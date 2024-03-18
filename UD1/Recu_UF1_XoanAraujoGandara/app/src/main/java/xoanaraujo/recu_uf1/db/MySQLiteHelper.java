package xoanaraujo.recu_uf1.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;

import androidx.annotation.Nullable;

import xoanaraujo.recu_uf1.model.Habitacion;
import xoanaraujo.recu_uf1.model.Hotel;

public class MySQLiteHelper extends SQLiteOpenHelper {
    private Hotel[] hoteles = new Hotel[]{
      new Hotel("Rias Baixas", 4),
      new Hotel("Os Escudos", 5),
    };
    private Habitacion[] habitaciones = new Habitacion[]{
            new Habitacion(1, 100, 20),
            new Habitacion(1, 101,50),
            new Habitacion(2, 200,70),
            new Habitacion(2, 201,40),
    };
    public static final String DB_NAME = "dbRecu";
    public static final int VERSION = 1;

    public MySQLiteHelper(@Nullable Context context) {
        super(context, DB_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        String createHoteleslTable = "CREATE TABLE HOTELES (" +
                "ID INTEGER PRIMARY KEY AUTOINCREMENT," +
                "NOMBRE TEXT UNIQUE NOT NULL," +
                "ESTRELLAS INTEGER NOT NULL)";
        SQLiteStatement stmCreateHotelesTable = sqLiteDatabase.compileStatement(createHoteleslTable);
        stmCreateHotelesTable.execute();

        String insertHoteles = "INSERT INTO HOTELES(NOMBRE, ESTRELLAS) VALUES(?, ?)";
        SQLiteStatement stmInsertHoteles = sqLiteDatabase.compileStatement(insertHoteles);
        for (Hotel hotel : hoteles) {
            stmInsertHoteles.bindString(1, hotel.getNombre());
            stmInsertHoteles.bindLong(2, hotel.getEstrellas());
            stmInsertHoteles.executeInsert();
        }

        String createHabitacionesTable = "CREATE TABLE HABITACIONES(" +
                "IDHOTEL INTEGER," +
                "NUMERO INTEGER," +
                "PRECIO INTEGER," +
                "PRIMARY KEY(IDHOTEL, NUMERO)," +
                "FOREIGN KEY(IDHOTEL) REFERENCES HOTELES(ID))";
        SQLiteStatement stmCreateHabitacionesTable = sqLiteDatabase.compileStatement(createHabitacionesTable);
        stmCreateHabitacionesTable.execute();

        String insertHabitaciones = "INSERT INTO HABITACIONES(IDHOTEL, NUMERO, PRECIO) VALUES (?, ?, ?)";
        SQLiteStatement stmInsertHabitaciones = sqLiteDatabase.compileStatement(insertHabitaciones);

        for (int i = 0; i < habitaciones.length; i++) {
            stmInsertHabitaciones.bindLong(1, habitaciones[i].getIdHotel());
            stmInsertHabitaciones.bindLong(2, habitaciones[i].getNumero());
            stmInsertHabitaciones.bindLong(3, habitaciones[i].getPrecio());
            stmInsertHabitaciones.executeInsert();
        }

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
