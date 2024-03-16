package com.example.elecciones.Activitys;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.elecciones.DATA.Ciudadano;
import com.example.elecciones.DATABASE.MySqlite;
import com.example.elecciones.R;
import com.example.elecciones.RECURSOS.Recursos;


public class MainActivity extends AppCompatActivity implements TextWatcher {
    private EditText etxtDNI;
    private EditText etxtPassword;
    private Button btnAcceder;
    private TextView txtRegistrer;

    private TextView txtMode;
    private Button btnRegistrer;
    private boolean modoRegistro = false;
    private final String KEY_MODO ="";
    private TextView tv;
    private StringBuilder sb;

    private static final String TAG ="ESTADO";

    private MySqlite ms;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ms = new MySqlite(this);



       /* ContentValues values = new ContentValues();

        values.put("NIF","00000000A");
        values.put("PASSWORD","XXXXXXXXXXXX");
        values.put("NUM_VOTES",0);

        wBd.insert("CIUDADANO",null,values);*/

        //ASIGNAR IDS
        etxtDNI = findViewById(R.id.etxtDNI);
        etxtPassword = findViewById(R.id.etxtPassword);
        btnAcceder = findViewById(R.id.btnAcceder);
        btnRegistrer = findViewById(R.id.btnRegistrer);
        txtRegistrer = findViewById(R.id.txtRegistrer);
        txtMode = findViewById(R.id.txtMode);


//ALTERNAR MODO REGISTRO ---> MODO LOGIN
        btnRegistrer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cambiarModo();


            }
        });


        //EVENTO PARA ACCEDER O REGISTRARSE DEPENDIENDO DEL MODO
        btnAcceder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String DNI = etxtDNI.getText().toString().trim();
                String contraseña = Recursos.hash(etxtPassword.getText().toString());
                accederRegistrarse(DNI, contraseña); //Metodo que depende del modo actual del activity


            }
        });

        //AÑADIR ESCUCHADOR TextWatcher
        etxtDNI.addTextChangedListener(this);


        //VER ESTADO
        sb = new StringBuilder("");
       addLinea("OnCreate");
    }

    //--> DEPENDIENDO DEL MODO --> REGISTRO O ACCESO A VOTACIÓN
    private void accederRegistrarse(String dni, String contraseña) {

        if (modoRegistro) { //REGISTRAR --> MODO REGISTRO
            Ciudadano c = new Ciudadano(dni, contraseña);
            if (!ms.insertCiudadano(c))
                launchToast("ERROR EN EL REGISTRO!");
            else {
                launchToast("CIUDADANO REGISTRADO");
                cambiarModo();

            }
        } else if (!modoRegistro) { //ACCEDER --> MODO LOGGIN

            Ciudadano c = ms.queryCiudadano(dni, contraseña);
            if (c != null) {
                if (c != null && !c.HaVotado())
                    lanzarActivity(VotacionActivity.class, c);
                else if (c.HaVotado())
                    launchToast("PROCESO FINALIZADO PARA EL CIUDADANO");
            } else
                launchToast("ERROR EN LAS CREDENCIALES");

        } else {
            launchToast("ERROR");
        }

    }


    //METODO PARA MOSTRAR TOAST POR PANTALLA
    private void launchToast(String s) {
        Toast.makeText(this, s, Toast.LENGTH_LONG).show();
    }

    //ALTERNAR ENTRE EL MODO REGISTRO Y LOGIN
    private void cambiarModo() {
        if (modoRegistro == false) {
            cargarModoRegistro();

        } else {
            cargarModoLogin();
        }
        etxtDNI.setText("");
        etxtPassword.setText("");
        etxtDNI.setHint("DNI");
        etxtPassword.setHint("CONTRASEÑA");
    }

    private void cargarModoLogin() {
        modoRegistro = false;
        txtMode.setText("LOGIN");
        txtRegistrer.setText("REGISTRARSE");
        btnAcceder.setText("ACCEDER");
    }

    private void cargarModoRegistro() {
        modoRegistro = true;
        txtMode.setText("REGISTRO");
        txtRegistrer.setText("LOGIN");
        btnAcceder.setText("VALIDAR");
    }

    //METODO PARA LANZAR ACTIVITY
    private void lanzarActivity(Class<VotacionActivity> ActivityClass, Ciudadano c) {
        Intent i = new Intent(this, ActivityClass);
        i.putExtra("Ciudadano", c); //Para pasar un objeto propio este tiene que ser serializable
        startActivity(i);
    }

    //TEXTWATCHER
    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    //METODO DE TextWatcher
    @Override
    public void afterTextChanged(Editable editable) {
        String DNI = etxtDNI.getText().toString();
        if (DNI.length() == 9 && modoRegistro) {
            if (Recursos.esNIF(DNI)) {
                etxtDNI.setTextColor(Color.GREEN);
            } else {
                etxtDNI.setTextColor(Color.RED);
            }
        } else if (DNI.length() < 9) {
            etxtDNI.setTextColor(Color.BLACK);
        }

    }
    //METODO PARA MOSTRAR EN EL LOGCAT EL ESTADO ACTUAL DEL ACTIVITY
    private void addLinea(String linea) {
        sb.append(linea);
        sb.append("\n");
        Log.i(TAG,linea);
    }
    //OVERRIDE DE TODOS LOS METODOS
    @Override protected void onStart() { super.onStart(); addLinea("onStart"); }
    @Override protected void onRestart() { super.onRestart(); addLinea("onRestart"); }
    @Override protected void onResume() { super.onResume(); addLinea("onResume"); }
    @Override protected void onPause() { super.onPause(); addLinea("onPause"); }
    @Override protected void onStop() { super.onStop(); addLinea("onStop"); }
    @Override protected void onDestroy() { super.onDestroy(); addLinea("onDestroy"); }



    //METODO PARA RECARGAR DATOS CUANDO PASA POR  onPause()→onStop()→onDestroy()
    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        modoRegistro = savedInstanceState.getBoolean(KEY_MODO);
        if(modoRegistro == true)
            cargarModoRegistro();
        else
            cargarModoLogin();
        super.onRestoreInstanceState(savedInstanceState);

    }



    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        outState.putBoolean(KEY_MODO, modoRegistro);
        super.onSaveInstanceState(outState);
    }
}