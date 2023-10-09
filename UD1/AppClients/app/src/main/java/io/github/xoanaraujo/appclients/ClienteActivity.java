package io.github.xoanaraujo.appclients;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;

import java.util.ArrayList;

public class ClienteActivity extends AppCompatActivity implements View.OnClickListener {
    boolean esNuevoCliente;
    int codCliente;
    SQLiteDatabase bd;
    EditText etNombre, etApellidos, etNIF, etLatitud, etLongitud;
    Spinner listaProvincias;
    CheckBox chkVIP;
    ImageButton bMapa;
    Button bGuardar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bd = new AsistenteBD(this).getReadableDatabase();
        codCliente = getIntent().getIntExtra("codCliente", -1);
        esNuevoCliente = (codCliente == -1);
        setResult(RESULT_CANCELED);
        //region View's
        setContentView(R.layout.activity_cliente);
        etNombre = (EditText) findViewById(R.id.etName);
        etApellidos = (EditText) findViewById(R.id.etSurname);
        etNIF = (EditText) findViewById(R.id.etNIF);
        listaProvincias = (Spinner) findViewById(R.id.spProv);
        chkVIP = (CheckBox) findViewById(R.id.cbVIP);
        etLatitud = findViewById(R.id.etLat);
        etLongitud = findViewById(R.id.etLog);
        bMapa = findViewById(R.id.bMapa);
        bGuardar = (Button) findViewById(R.id.btnSave);
        //endregion
        etNIF.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                etNIF.setTextColor(!Utiles.esNIF(s.toString()) ? Color.RED : Color.BLACK);
            }
        });
        bMapa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                VerMapa();
            }
        });
        bGuardar.setOnClickListener(this);
        setTitle(getString(esNuevoCliente ? R.string.nuevoCliente : R.string.editarCliente));
        bGuardar.setText(getString(esNuevoCliente ? R.string.insertar : R.string.guardar));
        int codProvincia = -1;
//codProvincia virtual correspondiente al 1º elemento vacío de la lista de provincias
        if (!esNuevoCliente) {
            Cursor filas = bd.rawQuery("SELECT * FROM Clientes WHERE codCliente=?",
                    new String[]{codCliente + ""});
            int colIndexNombre = filas.getColumnIndex("nombre");
            int colIndexApellidos = filas.getColumnIndex("apellidos");
            int colIndexNIF = filas.getColumnIndex("NIF");
            int colIndexCodProvincia = filas.getColumnIndex("codProvincia");
            int colIndexVIP = filas.getColumnIndex("VIP");
            int colIndexLatitud = filas.getColumnIndex("latitud");
            int colIndexLongitud = filas.getColumnIndex("longitud");
            if (filas.moveToFirst()) {
                String nombre = filas.getString(colIndexNombre);
                String apellidos = filas.getString(colIndexApellidos);
                String NIF = filas.getString(colIndexNIF);
                codProvincia = filas.getInt(colIndexCodProvincia);
                boolean VIP = filas.getInt(colIndexVIP) == 1;
                float latitud = filas.getFloat(colIndexLatitud);
                float longitud = filas.getFloat(colIndexLongitud);
                etNombre.setText(nombre);
                etApellidos.setText(apellidos);
                etNIF.setText(NIF);
                //la prov. seleccionada se establece después de poblar la lista para aprovechar el bucle
                chkVIP.setChecked(VIP);
                etLatitud.setText(latitud + "");
                etLongitud.setText(longitud + "");
            }
            filas.close();
        }
        //region Lista de Provincias
        int posProvinciaSeleccionada = 0, posActual = 0;
        ArrayList<Provincia> alProvincias = new ArrayList<>();
        alProvincias.add(new Provincia(-1, ""));
// primer elemento en blanco. Comprobar su código.
        Cursor filas = bd.rawQuery("SELECT * FROM Provincias ORDER BY nombre", null);
        int colIndexCodProvincia = filas.getColumnIndex("codProvincia");
        int colIndexNombre = filas.getColumnIndex("nombre");
        while (filas.moveToNext()) {
            posActual++;
            int codProvinciaBD = filas.getInt(colIndexCodProvincia);
            if (codProvinciaBD == codProvincia) posProvinciaSeleccionada = posActual;
            String nombreBD = filas.getString(colIndexNombre);
            alProvincias.add(new Provincia(codProvinciaBD, nombreBD));
        }
        filas.close();
        listaProvincias.setAdapter(new ArrayAdapter<Provincia>(this,
                android.R.layout.simple_list_item_1, alProvincias));
        listaProvincias.setSelection(posProvinciaSeleccionada);
        //endregion
    }

    public void VerMapa() {
        String latitud = etLatitud.getText().toString();
        String longitud = etLongitud.getText().toString();
        String URI = "geo:" + latitud + "," + longitud + "?z=17";
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(URI));
        startActivity(intent);
    }

    @Override
    public void onClick(View v) { // Guardar
        String nombre = etNombre.getText().toString().trim();
        String apellidos = etApellidos.getText().toString().trim();
        String NIF = etNIF.getText().toString().trim();
        int codProvincia = ((Provincia) listaProvincias.getSelectedItem()).getCodProvincia();
        String latitud = etLatitud.getText().toString().replace(",", ".");
        String longitud = etLongitud.getText().toString().replace(",", ".");
        Object objCodProvincia = (codProvincia == -1) ? null : codProvincia;
        //TODO: if(hayErroresEnDatos) { setError - return }
        if (esNuevoCliente) {
            String sql = "INSERT INTO clientes
            (nombre, apellidos, NIF, codProvincia, VIP, latitud, longitud)VALUES( ?,?,?,?,?,?,?)";
            Object[] valores = {nombre, apellidos, NIF, objCodProvincia,
                    chkVIP.isChecked() ? 1 : 0, latitud, longitud};
            bd.execSQL(sql, valores);
        } else {
            String sql = "UPDATE Clientes SET nombre=?,apellidos=?,NIF=?,
            codProvincia =?,VIP =?,latitud =?,longitud =?WHERE codCliente =?";
            Object[] valores = {nombre, apellidos, NIF, objCodProvincia,
                    chkVIP.isChecked() ? 1 : 0, latitud, longitud, codCliente};
            bd.execSQL(sql, valores);
        }
        setResult(RESULT_OK);
        finish();
    }
}