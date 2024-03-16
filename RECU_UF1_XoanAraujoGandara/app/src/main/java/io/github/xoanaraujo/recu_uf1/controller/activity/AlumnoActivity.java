package io.github.xoanaraujo.recu_uf1.controller.activity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import io.github.xoanaraujo.recu_uf1.R;
import io.github.xoanaraujo.recu_uf1.controller.adapter.ArrayAdapterGrupos;
import io.github.xoanaraujo.recu_uf1.db.CRUD;
import io.github.xoanaraujo.recu_uf1.db.MySQLiteHelper;
import io.github.xoanaraujo.recu_uf1.model.Alumno;
import io.github.xoanaraujo.recu_uf1.util.Utils;

public class AlumnoActivity extends AppCompatActivity {

    private MySQLiteHelper sqLiteHelper;
    private Alumno alumno;
    private TextView tvId;
    private EditText etDni, etNombre;
    private Spinner spGrupos;
    private Button btnGuardar, btnBorrar;
    private ImageButton iBtnAtras;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alumno);

        sqLiteHelper = new MySQLiteHelper(this);

        tvId = findViewById(R.id.tvAcIdAlumno);
        etDni = findViewById(R.id.etDniAlumno);
        etNombre = findViewById(R.id.etNombreAlumno);
        spGrupos = findViewById(R.id.spGruposAlumno);
        btnGuardar = findViewById(R.id.btnActAlumnoGuardar);
        btnBorrar = findViewById(R.id.btnActAlumnoBorrar);
        iBtnAtras = findViewById(R.id.iBtnActAlumnoAtras);

        spGrupos.setAdapter(new ArrayAdapterGrupos(this, CRUD.selectGrupos(sqLiteHelper.getReadableDatabase())));
        Intent intent = getIntent();
        if (intent.hasExtra("alumno")){
            alumno = (Alumno) intent.getSerializableExtra("alumno");
            tvId.setText(String.valueOf(alumno.getId()));
            etDni.setText(alumno.getDni());
            etNombre.setText(alumno.getNombre());
            spGrupos.setSelection(alumno.getIdGrupo() - 1);
            btnBorrar.setOnClickListener(e -> borrar());
        } else if(intent.hasExtra("idGrupo")) {
            alumno = new Alumno();
            tvId.setText(String.valueOf(CRUD.countAlumnos(sqLiteHelper.getReadableDatabase()) + 1));
            spGrupos.setSelection(intent.getIntExtra("idGrupo", 1) - 1);
            btnBorrar.setVisibility(View.INVISIBLE);
            btnBorrar.setActivated(false);
        }

        btnGuardar.setOnClickListener(e -> guardar());
        iBtnAtras.setOnClickListener(e -> launchManagementActivity());
    }

    private void borrar() {
        SQLiteDatabase db = new MySQLiteHelper(this).getWritableDatabase();
        CRUD.deleteAlumnoById(db, alumno.getId());
        Utils.launchToast(this, alumno.toString());
        launchManagementActivity();
    }

    private void guardar() {
        String dni = etDni.getText().toString();
        if (!dni.matches("[0-9]{8}[A-Z]")){
            Utils.launchToast(this, "DNI incorrecto");
            return;
        }
        alumno.setDni(dni);
        alumno.setNombre(etNombre.getText().toString());
        alumno.setIdGrupo(spGrupos.getSelectedItemPosition() + 1);

        SQLiteDatabase db = new MySQLiteHelper(this).getWritableDatabase();
        if (CRUD.selectAlumnoById(db, alumno.getId()) == null){
            CRUD.insertAlumno(db, alumno);
            Utils.launchToast(this, alumno.getDni() + " insertado");
        } else {
            CRUD.updateAlumno(db, alumno);
            Utils.launchToast(this, alumno.getDni() + " actualizado");
        }
        launchManagementActivity();
    }

    private void launchManagementActivity() {
        Intent intent = new Intent(this, ManagementActivity.class);
        intent.putExtra("idGrupo", alumno.getIdGrupo());
        startActivity(intent);
    }
}