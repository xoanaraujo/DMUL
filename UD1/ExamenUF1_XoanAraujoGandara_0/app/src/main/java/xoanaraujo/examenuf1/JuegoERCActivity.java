package xoanaraujo.examenuf1;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import xoanaraujo.examenuf1.controller.ArrayAdapterRoom;
import xoanaraujo.examenuf1.model.Room;

public class JuegoERCActivity extends AppCompatActivity {

    ActivityResultLauncher<Intent> resultLauncher;
    private static String WIN_COMBO = "";
    private StringBuilder buffer;
    private TextView tvErrores;
    private Spinner spRooms;
    private ControlesFragment controles;

    private int errores, maxErrores;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_juego_ercactivity);

        // RESULT LAUNCHER
        resultLauncher = registerForActivityResult( new ActivityResultContracts.StartActivityForResult(), null);

        // DB Y SHARED PREFS
        DBHelper dbHelper = new DBHelper(this);

        tvErrores = findViewById(R.id.tvErrores);
        maxErrores = errores = getIntent().getIntExtra("errores", -1);

        SharedPreferences sharedPreferences = getSharedPreferences("shared_pref", Context.MODE_PRIVATE);

        int valorInt = sharedPreferences.getInt(String.valueOf(errores), 0);

        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(String.valueOf(errores), ++valorInt);
        editor.apply();

        // LAYOUT Y FUNCIONAMIENTO

        tvErrores.setText(String.valueOf(errores));
        spRooms = findViewById(R.id.spRooms);
        ArrayList<Room> rooms = dbHelper.getRooms();
        spRooms.setAdapter(new ArrayAdapterRoom(this, rooms));
        spRooms.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                TextView tv = view.findViewById(R.id.tvIdRoom);
                Room room = dbHelper.getRoomById(Integer.parseInt(tv.getText().toString()));
                WIN_COMBO = room.getPath();
                Log.i("Xoan Araujo", " " + room.getPath()+ " ");
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        controles = (ControlesFragment) getSupportFragmentManager().findFragmentById(R.id.fragmentContainerView);
        buffer = new StringBuilder();
        controles.setListener(ch -> {
            if (!WIN_COMBO.equals("")) {
                switch (ch) {
                    case 'e': {
                        boolean windCondition = buffer.toString().equals(WIN_COMBO);
                        String msg = windCondition ? "GANASTE" : "PERDISTE";
                        buffer = new StringBuilder();
                        if (!windCondition){
                            if (errores > 0){
                                errores--;
                                tvErrores.setText(String.valueOf(errores));
                                Toast.makeText(this, " ERRORES RESTANTES: "+ errores, Toast.LENGTH_SHORT).show();
                            } else {
                                finPartida(msg);
                            }
                        } else {
                            finPartida(msg);
                        }
                    }
                    break;
                    case 'w':
                    case 'a':
                    case 's':
                    case 'd': {
                        buffer.append(ch);
                        Toast.makeText(this, buffer.toString(), Toast.LENGTH_SHORT).show();
                    }
                    break;
                }
            } else {
                Toast.makeText(this, "SELECCIONA JUEGO", Toast.LENGTH_SHORT).show();
            }
        }, true);
    }

    private void finPartida(String msg){
        Toast.makeText(this, msg + ". FIN DE PARTIDA", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, MainActivity.class);
        resultLauncher.launch(intent);
    }
}