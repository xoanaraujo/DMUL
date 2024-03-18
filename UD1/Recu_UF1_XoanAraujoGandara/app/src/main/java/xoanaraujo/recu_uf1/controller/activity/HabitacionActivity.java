package xoanaraujo.recu_uf1.controller.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;

import xoanaraujo.recu_uf1.R;
import xoanaraujo.recu_uf1.controller.adapter.HotelBasicAdapter;
import xoanaraujo.recu_uf1.db.CRUD;
import xoanaraujo.recu_uf1.db.MySQLiteHelper;
import xoanaraujo.recu_uf1.model.Habitacion;
import xoanaraujo.recu_uf1.model.Hotel;

public class HabitacionActivity extends AppCompatActivity {
    private MySQLiteHelper sqLiteHelper;
    private Habitacion habitacion;
    private Spinner spHoteles;
    private EditText etNumero, etPrecio;
    private ImageButton iBtnSave, iBtnDelete;
    private Hotel[] hoteles;
    private HotelBasicAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_habitacion);

        spHoteles = findViewById(R.id.spActHabitacionHotel);
        etNumero = findViewById(R.id.etActHabitacionNumero);
        etPrecio = findViewById(R.id.etActHabitacionPrecio);
        iBtnDelete = findViewById(R.id.iBtnActHabitacionDelete);
        iBtnSave = findViewById(R.id.iBtnActHabitacionSave);

        sqLiteHelper = new MySQLiteHelper(this);

        hoteles = CRUD.selectHoteles(sqLiteHelper.getWritableDatabase());
        adapter = new HotelBasicAdapter(this, hoteles);
        spHoteles.setAdapter(adapter);

        Intent intent = getIntent();
        if (intent.hasExtra("habitacion")){
            habitacion = (Habitacion) intent.getExtras().get("habitacion");
            etNumero.setText(String.valueOf(habitacion.getNumero()));
            etPrecio.setText(String.valueOf(habitacion.getPrecio()));
        } else {
            habitacion = new Habitacion();
        }
        spHoteles.setSelection(findHotelPosById(CRUD.selectHotelByNumHabitacion(sqLiteHelper.getReadableDatabase(), habitacion.getNumero()).getId()));
    }

    private int findHotelPosById(int id) {
        int pos = -1;
        for (int i = 0; pos == -1 && i < hoteles.length; i++) {
            if (hoteles[i].getId() == id)
                pos = i;
        }
        return pos == -1 ? 0 : pos;
    }
}