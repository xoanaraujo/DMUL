package io.github.xoanaraujo.recu_uf1.controller.activity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import io.github.xoanaraujo.recu_uf1.R;
import io.github.xoanaraujo.recu_uf1.controller.adapter.ArrayAdapterAlumnos;
import io.github.xoanaraujo.recu_uf1.controller.adapter.ArrayAdapterGrupos;
import io.github.xoanaraujo.recu_uf1.db.CRUD;
import io.github.xoanaraujo.recu_uf1.db.MySQLiteHelper;
import io.github.xoanaraujo.recu_uf1.model.Alumno;
import io.github.xoanaraujo.recu_uf1.model.Grupo;

public class ManagementActivity extends AppCompatActivity {

    private Spinner spGrupos;
    private ListView lvAlumnos;
    private ImageButton iBtnAddAlumno, iBtnAddGrupo, iBtnEditGrupo;
    private ImageButton iBtnAtras;
    private Grupo[] grupos;
    private Alumno[] alumnos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_management);

        spGrupos = findViewById(R.id.spGrupos);
        lvAlumnos = findViewById(R.id.lvAlumnos);
        iBtnAddAlumno = findViewById(R.id.iBtnActManagementAddAlumno);
        iBtnAddGrupo = findViewById(R.id.iBtnActManagementAddGrupo);
        iBtnEditGrupo = findViewById(R.id.iBtnActManagementEditGrupo);
        iBtnAtras = findViewById(R.id.iBtnActManagementAtras);
        MySQLiteHelper sqLiteHelper = new MySQLiteHelper(this);
        SQLiteDatabase db = sqLiteHelper.getReadableDatabase();

        updateGrupos(db);
        Intent intent = getIntent();
        if(intent.hasExtra("idGrupo")){

            int idGrupo = intent.getIntExtra("idGrupo", 1);
            int pos = getPosicionGrupoById(idGrupo);
            spGrupos.setSelection(pos);
            updateAlumnos(db, idGrupo);
        }

        spGrupos.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                updateAlumnos(db, grupos[position].getId());
            }
            @Override public void onNothingSelected(AdapterView<?> parent) {}
        });

        lvAlumnos.setOnItemClickListener((parent, view, position, id) -> launchAlumnoActivity(alumnos[position]));

        iBtnAddAlumno.setOnClickListener(e -> launchAlumnoActivity());
        iBtnAddGrupo.setOnClickListener(e -> launchGrupoActivity());
        iBtnEditGrupo.setOnClickListener(e -> launchGrupoActivity(grupos[spGrupos.getSelectedItemPosition()]));
        iBtnAtras.setOnClickListener(e -> launchMainActivity());
    }

    private int getPosicionGrupoById(int idGrupo) {
        int pos = -1;
        for (int i = 0; pos == -1 && i < grupos.length; i++) {
            if (grupos[i].getId() == idGrupo)
                pos = i;
        }
        return pos == -1 ? 0 : pos;
    }

    private void launchGrupoActivity() {
        Intent intent = new Intent(this, GrupoActivity.class);
        startActivity(intent);
    }
    private void launchGrupoActivity(Grupo grupo) {
        Intent intent = new Intent(this, GrupoActivity.class);
        intent.putExtra("grupo", grupo);
        startActivity(intent);
    }

    private void launchMainActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    private void launchAlumnoActivity() {
        Intent intent = new Intent(this, AlumnoActivity.class);
        intent.putExtra("idGrupo", spGrupos.getSelectedItemPosition() + 1);
        startActivity(intent);
    }

    private void launchAlumnoActivity(Alumno alumno) {
        Intent intent = new Intent(this, AlumnoActivity.class);
        intent.putExtra("alumno", alumno);
        startActivity(intent);
    }

    private void updateGrupos(SQLiteDatabase db) {
        grupos = CRUD.selectGrupos(db);
        spGrupos.setAdapter(new ArrayAdapterGrupos(this, grupos));
    }

    private void updateAlumnos(SQLiteDatabase db, int grupoId) {
        alumnos = CRUD.selectAlumnosByIdGrupo(db, grupoId);

        lvAlumnos.setAdapter(new ArrayAdapterAlumnos(this, alumnos));
    }
}