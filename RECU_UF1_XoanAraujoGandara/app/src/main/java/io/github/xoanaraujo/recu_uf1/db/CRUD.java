package io.github.xoanaraujo.recu_uf1.db;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabaseCorruptException;
import android.database.sqlite.SQLiteStatement;

import java.util.ArrayList;

import io.github.xoanaraujo.recu_uf1.model.Alumno;
import io.github.xoanaraujo.recu_uf1.model.Grupo;
import io.github.xoanaraujo.recu_uf1.util.Utils;

public final class CRUD {
    public static Alumno selectAlumnoById (SQLiteDatabase db, int id){
        Alumno alumno = null;

        String select = "SELECT * FROM ALUMNOS WHERE ID = ?";
        Cursor cursor = db.rawQuery(select, new String[]{String.valueOf(id)});

        if (cursor.moveToFirst()){
            int indexId = cursor.getColumnIndex("ID");
            int indexDni = cursor.getColumnIndex("DNI");
            int indexNombre = cursor.getColumnIndex("NOMBRE");
            int indexIdGrupo = cursor.getColumnIndex("IDGRUPO");
            alumno = new Alumno(
                    cursor.getInt(indexId),
                    cursor.getString(indexDni),
                    cursor.getString(indexNombre),
                    cursor.getInt(indexIdGrupo));
        }
        return alumno;
    }

    public static Alumno selectAlumnoByDni(SQLiteDatabase db, String dni) {
        Alumno alumno = null;

        String select = "SELECT * FROM ALUMNOS WHERE DNI = ?";
        Cursor cursor = db.rawQuery(select, new String[]{String.valueOf(dni)});

        if (cursor.moveToFirst()){
            int indexId = cursor.getColumnIndex("ID");
            int indexDni = cursor.getColumnIndex("DNI");
            int indexNombre = cursor.getColumnIndex("NOMBRE");
            int indexIdGrupo = cursor.getColumnIndex("IDGRUPO");
            alumno = new Alumno(
                    cursor.getInt(indexId),
                    cursor.getString(indexDni),
                    cursor.getString(indexNombre),
                    cursor.getInt(indexIdGrupo));
        }
        return alumno;
    }

    public static Grupo[] selectGrupos(SQLiteDatabase db) {
        ArrayList<Grupo> grupos = new ArrayList<>();
        String select = "SELECT * FROM GRUPOS";
        Cursor cursor = db.rawQuery(select, null);
        while (cursor.moveToNext()){
            int indexId = cursor.getColumnIndex("ID");
            int indexNombre = cursor.getColumnIndex("NOMBRE");
            grupos.add(new Grupo(cursor.getInt(indexId), cursor.getString(indexNombre)));
        }
        return grupos.toArray(new Grupo[0]);
    }

    public static Alumno[] selectAlumnosByIdGrupo(SQLiteDatabase db, int id) {
        ArrayList<Alumno> alumnos = new ArrayList<>();
        String select = "SELECT ID, DNI, NOMBRE FROM ALUMNOS WHERE IDGRUPO = ?";
        Cursor cursor =  db.rawQuery(select, new String[]{String.valueOf(id)});
        while (cursor.moveToNext()){
            int indexId = cursor.getColumnIndex("ID");
            int indexDni = cursor.getColumnIndex("DNI");
            int indexNombre = cursor.getColumnIndex("NOMBRE");
            alumnos.add(new Alumno(cursor.getInt(indexId), cursor.getString(indexDni), cursor.getString(indexNombre), id));
        }
        return alumnos.toArray(new Alumno[0]);
    }

    public static void deleteAlumnoById(SQLiteDatabase db, int id) {
        String delete = "DELETE FROM ALUMNOS WHERE ID = ?";
        SQLiteStatement statement = db.compileStatement(delete);
        statement.bindLong(1, id);
        int row = statement.executeUpdateDelete();
        if (row != 1){
            throw new SQLiteDatabaseCorruptException("N ALUMNOS BORRADOS != 1");
        }
    }

    public static void updateAlumno(SQLiteDatabase db, Alumno alumno) {
        String delete = "UPDATE ALUMNOS SET DNI = ?, NOMBRE = ?, IDGRUPO = ? WHERE ID = ?";
        SQLiteStatement statement = db.compileStatement(delete);
        statement.bindString(1, alumno.getDni());
        statement.bindString(2, alumno.getNombre());
        statement.bindLong(3, alumno.getIdGrupo());
        statement.bindLong(4, alumno.getId());
        int row = statement.executeUpdateDelete();
        if (row != 1){
            throw new SQLiteDatabaseCorruptException("N ALUMNOS ACTUALIZADOS != 1");
        }
    }

    public static void insertAlumno(SQLiteDatabase db, Alumno alumno) {
        String insert = "INSERT INTO ALUMNOS(DNI, NOMBRE, IDGRUPO) VALUES(?, ?, ?)";
        SQLiteStatement statement = db.compileStatement(insert);
        statement.bindString(1, alumno.getDni());
        statement.bindString(2, alumno.getNombre());
        statement.bindLong(3, alumno.getIdGrupo());
        statement.executeInsert();
    }

    public static int countAlumnos(SQLiteDatabase db) {
        String select = "SELECT COUNT(ID) FROM ALUMNOS";
        Cursor cursor = db.rawQuery(select, null);
        if (cursor.moveToFirst()){
            return cursor.getInt(0);
        }
        return -1;
    }
}
