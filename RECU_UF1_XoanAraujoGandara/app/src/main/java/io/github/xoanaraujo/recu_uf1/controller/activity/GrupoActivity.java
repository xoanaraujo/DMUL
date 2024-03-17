package io.github.xoanaraujo.recu_uf1.controller.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import io.github.xoanaraujo.recu_uf1.R;
import io.github.xoanaraujo.recu_uf1.db.CRUD;
import io.github.xoanaraujo.recu_uf1.db.MySQLiteHelper;
import io.github.xoanaraujo.recu_uf1.model.Grupo;
import io.github.xoanaraujo.recu_uf1.util.AccionType;
import io.github.xoanaraujo.recu_uf1.util.Utils;

public class GrupoActivity extends AppCompatActivity {

    private MySQLiteHelper sqLiteHelper;
    private Grupo grupo;
    private TextView tvId;
    private EditText etNombre;
    private ImageButton iBtnSave, iBtnDelete, iBtnAtras;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grupo);

        sqLiteHelper = new MySQLiteHelper(this);

        tvId = findViewById(R.id.tvActGrupoIdGrupo);
        etNombre = findViewById(R.id.etActGrupoNombre);
        iBtnDelete = findViewById(R.id.iBtnActGrupoDelete);
        iBtnSave = findViewById(R.id.iBtnActGrupoSave);
        iBtnAtras = findViewById(R.id.iBtnActGrupoAtras);


        Intent intent = getIntent();
        if (intent.hasExtra("grupo")) {
            grupo = (Grupo) intent.getSerializableExtra("grupo");
            tvId.setText(String.valueOf(grupo.getId()));
            etNombre.setText(grupo.getNombre());
            iBtnAtras.setOnClickListener(e -> {
                launchManagementActivity(grupo.getId());
            });
        } else {
            grupo = new Grupo();
            tvId.setText("Nuevo Grupo");
            iBtnDelete.setVisibility(View.INVISIBLE);
            iBtnDelete.setActivated(false);
            iBtnAtras.setOnClickListener(e -> launchManagementActivity());
        }

        iBtnSave.setOnClickListener(e -> guardar());
        iBtnDelete.setOnClickListener(e -> borrar());
    }

    private void launchManagementActivity(int id) {
        Intent intent = new Intent(this, ManagementActivity.class);
        intent.putExtra("idGrupo", id);
        startActivity(intent);
    }

    private void launchManagementActivity() {
        Intent intent = new Intent(this, ManagementActivity.class);
        startActivity(intent);
    }

    private void guardar() {
        String nombre = etNombre.getText().toString();
        grupo.setNombre(nombre);
        SQLiteDatabase db = sqLiteHelper.getWritableDatabase();
        if (CRUD.selectGrupoById(db, grupo.getId()) != null) {
            // ACTUALIZANDO
            CRUD.updateGrupo(db, grupo);
            Utils.launchToast(this, "Grupo actualizado");
            savePrefs(AccionType.UPDATE);
            launchManagementActivity();
        } else {
            if (CRUD.selectGrupoByNombre(db, nombre) != null) {
                // INSERTANDO
                Utils.launchToast(this, "Grupo existente");
            } else {
                CRUD.insertGrupo(db, grupo);
                grupo = CRUD.selectGrupoByNombre(db, nombre);
                Utils.launchToast(this, "Grupo insertado");
                savePrefs(AccionType.INSERT);
                launchManagementActivity(grupo.getId());
            }
        }
    }

    private void borrar() {
        SQLiteDatabase db = sqLiteHelper.getWritableDatabase();
        if (CRUD.selectGrupos(db).length == 1) {
            Utils.launchToast(this,
                    "  El ultimo grupo\n" +
                            "no se puede borrar");
        }
        if (CRUD.selectAlumnosByIdGrupo(db, grupo.getId()).length != 0) {
            Utils.launchToast(this, "Grupo con alumnos");
        } else {
            CRUD.deleteGrupoById(db, grupo.getId());
            savePrefs(AccionType.DELETE);
            launchManagementActivity();
        }
    }

    private void savePrefs(AccionType accionType) {
        SharedPreferences preferences = getSharedPreferences("prefs", MODE_PRIVATE);
        preferences.edit().clear()
                .putInt("idG", grupo.getId())
                .putString("nombreG", grupo.getNombre())
                .putString("accionType", accionType.toString())
                .apply();
    }

}