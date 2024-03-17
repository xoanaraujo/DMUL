package io.github.xoanaraujo.recu_uf1.controller.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import io.github.xoanaraujo.recu_uf1.R;
import io.github.xoanaraujo.recu_uf1.controller.fragment.RefreshFragment;
import io.github.xoanaraujo.recu_uf1.db.CRUD;
import io.github.xoanaraujo.recu_uf1.db.MySQLiteHelper;
import io.github.xoanaraujo.recu_uf1.model.Grupo;
import io.github.xoanaraujo.recu_uf1.util.AccionType;
import io.github.xoanaraujo.recu_uf1.util.Utils;

public class MainActivity extends AppCompatActivity implements RefreshFragment.RefreshListener {

    private TextView tvTitle, tvAccion, tvIdAlumno, tvDniAlumno, tvNombreAlumno, tvIdGrupoAlumno;
    private RefreshFragment refreshFragment;
    private Button btnManagement;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnManagement = findViewById(R.id.btnManagement);
        tvTitle = findViewById(R.id.tvActMainTitle);
        tvAccion = findViewById(R.id.tvActMainAccion);
        tvIdAlumno = findViewById(R.id.tvActMainIdAlumno);
        tvDniAlumno = findViewById(R.id.tvActMainDniAlumno);
        tvNombreAlumno = findViewById(R.id.tvActMainNombreAlumno);
        tvIdGrupoAlumno = findViewById(R.id.tvActMainIdGrupoAlumno);

        refreshFragment = new RefreshFragment(this);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainerView, refreshFragment).commit();

        tvTitle.setText("");
        tvAccion.setText("");
        tvIdAlumno.setText("");
        tvDniAlumno.setText("");
        tvNombreAlumno.setText("");
        tvIdGrupoAlumno.setText("");

        btnManagement.setOnClickListener(e -> loadActivity(ManagementActivity.class));
    }

    private void loadActivity(Class<? extends AppCompatActivity> activityClass) {
        Intent intent = new Intent(this, activityClass);
        startActivity(intent);
    }

    @Override
    public void refresh() {
        SharedPreferences preferences = getSharedPreferences("prefs", MODE_PRIVATE);
        int id = preferences.getInt("id", -1);
        if (id != -1){
            String dni = preferences.getString("dni", "");
            String nombre = preferences.getString("nombre", "");
            int idGrupo = preferences.getInt("idGrupo", -1);
            AccionType accionType = AccionType.valueOf(preferences.getString("accionType", "NONE"));
            tvTitle.setText("Ultima accion");
            switch (accionType){
                case NONE: {
                    tvAccion.setText("???");
                }break;
                case INSERT: {
                    tvAccion.setText("Insercion");
                    tvAccion.setTextColor(Color.GREEN);
                } break;
                case UPDATE: {
                    tvAccion.setText("Actualizacion");
                    tvAccion.setTextColor(Color.BLUE);
                } break;
                case DELETE: {
                    tvAccion.setText("Borrado ");
                    tvAccion.setTextColor(Color.RED);
                } break;
            }
            tvIdAlumno.setText("[" + id + "]");
            tvDniAlumno.setText(dni);
            tvNombreAlumno.setText(nombre);

            MySQLiteHelper sqLiteHelper = new MySQLiteHelper(this);
            Grupo grupo = CRUD.selectGrupoById(sqLiteHelper.getReadableDatabase(), idGrupo);
            tvIdGrupoAlumno.setText(grupo == null ? "" : grupo.getNombre());
        } else {
            Utils.launchToast(this, "No hay acciones registradas");
        }
    }
}