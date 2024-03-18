package xoanaraujo.recu_uf1.controller.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;

import xoanaraujo.recu_uf1.R;
import xoanaraujo.recu_uf1.controller.adapter.HotelAdapter;
import xoanaraujo.recu_uf1.db.CRUD;
import xoanaraujo.recu_uf1.db.MySQLiteHelper;
import xoanaraujo.recu_uf1.model.Habitacion;
import xoanaraujo.recu_uf1.model.Hotel;
import xoanaraujo.recu_uf1.util.Utils;

public class MainActivity extends AppCompatActivity {

    private MySQLiteHelper sqLiteHelper;
    private ListView lvHoteles;
    private Hotel[] hoteles;
    private ImageButton iBtnExport;
    private HotelAdapter hotelAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sqLiteHelper = new MySQLiteHelper(this);
        SQLiteDatabase rdb = sqLiteHelper.getReadableDatabase();

        lvHoteles = findViewById(R.id.lvActMainHoteles);

        hoteles = CRUD.selectHoteles(rdb);
        hotelAdapter = new HotelAdapter(this, hoteles);
        lvHoteles.setAdapter(hotelAdapter);
        lvHoteles.setOnItemClickListener((parent, view, position, id) -> {
            Utils.launchToast(this, "AQUI");
            Intent intent = new Intent(this, HotelActivity.class);
            intent.putExtra("id", hoteles[position].getId());
            startActivity(intent);
        });

        iBtnExport = findViewById(R.id.iBtnActMainExport);
        iBtnExport.setOnClickListener(e -> Utils.launchToast(this, "No implementado"));
    }

}