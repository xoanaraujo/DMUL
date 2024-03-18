package xoanaraujo.recu_uf1.controller.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Spinner;

import xoanaraujo.recu_uf1.R;
import xoanaraujo.recu_uf1.controller.adapter.HabitacionAdapter;
import xoanaraujo.recu_uf1.controller.adapter.HotelBasicAdapter;
import xoanaraujo.recu_uf1.db.CRUD;
import xoanaraujo.recu_uf1.db.MySQLiteHelper;
import xoanaraujo.recu_uf1.model.Habitacion;
import xoanaraujo.recu_uf1.model.Hotel;

public class HotelActivity extends AppCompatActivity {
    private Spinner spHoteles;
    private ListView lvHabitaciones;
    private ImageButton iBtnEdit, iBtnAdd;
    private MySQLiteHelper sqLiteHelper;
    private HotelBasicAdapter adapter;
    private Hotel[] hoteles;
    private Habitacion[] habitaciones;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hotel);

        spHoteles = findViewById(R.id.spActHotelHoteles);
        lvHabitaciones = findViewById(R.id.lvActHotelHabitaciones);
        iBtnAdd = findViewById(R.id.iBtnActHotelAddHabitacion);
        iBtnEdit = findViewById(R.id.iBtnActHotelEdit);

        sqLiteHelper = new MySQLiteHelper(this);

        hoteles = CRUD.selectHoteles(sqLiteHelper.getReadableDatabase());
        adapter = new HotelBasicAdapter(this, hoteles);
        spHoteles.setAdapter(adapter);

        Intent intent = getIntent();
        int hotelPos = findHotelPosById(intent.getIntExtra("id", 0));

        spHoteles.setSelection(hotelPos);
        updateHabitaciones(hoteles[hotelPos].getId());

        spHoteles.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                updateHabitaciones(hoteles[position].getId());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });

        lvHabitaciones.setOnItemClickListener((parent, view, position, id) -> {
            Intent intent1 = new Intent(this, HabitacionActivity.class);
            intent1.putExtra("habitacion", habitaciones[position]);
            startActivity(intent1);
        });
        iBtnAdd.setOnClickListener(e -> addHabitacion());
        iBtnEdit.setOnClickListener(e -> editHotel());
    }

    private void updateHabitaciones(Integer id) {
        habitaciones = CRUD.selectHabitacionesByHotel(sqLiteHelper.getReadableDatabase(), id);
        lvHabitaciones.setAdapter(new HabitacionAdapter(this, habitaciones));
    }

    private int findHotelPosById(int id) {
        int pos = -1;
        for (int i = 0; pos == -1 && i < hoteles.length; i++) {
            if (hoteles[i].getId() == id)
                pos = i;
        }
        return pos == -1 ? 0 : pos;
    }

    private void editHotel() {
    }

    private void addHabitacion() {
        
    }
}