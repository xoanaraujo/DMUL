package io.github.xoanaraujo.appclientes;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ClientActivity extends AppCompatActivity implements View.OnClickListener {

    int codClient;
    private boolean isNewClient;
    private List<ClientBase> listClients;
    private EditText etNIF, etName, etSurname, etLat, etLon;
    private Button btnSave;
    private CheckBox cbVIP;
    private Spinner provinceList;
    private SQLiteDatabase db = new BDAssistant(this).getReadableDatabase();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        codClient = getIntent().getIntExtra("codCliente", -1);
        isNewClient = (codClient == -1);
        setResult(RESULT_CANCELED);

        setContentView(R.layout.activity_client);


        etNIF = findViewById(R.id.etNIF);
        etNIF.addTextChangedListener(new TextWatcher() {
            @Override public void beforeTextChanged(CharSequence s,int start,int count,int after) {}
            @Override public void onTextChanged(CharSequence s, int start, int before, int count) {}
            @Override public void afterTextChanged(Editable s) {
                etNIF.setTextColor(!checkNIF(s.toString()) ? Color.RED : Color.BLACK);
            }
        });

        etName = findViewById(R.id.etName);
        etSurname = findViewById(R.id.etSurname);
        etLat = findViewById(R.id.etLat);
        etLon = findViewById(R.id.etLog);
        cbVIP = findViewById(R.id.cbVIP);
        btnSave = findViewById(R.id.btnSave);
        provinceList = (Spinner)findViewById(R.id.spProv);

        btnSave.setOnClickListener(this);


        //BDAssistant BDAssistant = new BDAssistant(this);
        //listClients = BDAssistant.listClients();
    }

    public static boolean checkNIF(String nif) {
        String letras = "TRWAGMYFPDXBNJZSQVHLCKE";
        Pattern pattern = Pattern.compile(
                "(\\d{8})([" + letras + "])",Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(nif);
        if (matcher.matches()) {
            int numero = Integer.parseInt(matcher.group(1));
            String letra = matcher.group(2);
            char letraCorrecta = letras.charAt(numero % 23);
            if (letra.toUpperCase().charAt(0) == letraCorrecta) return true;
        }
        return false;
    }


    private void launchToast(String ms){
        Toast.makeText(this,ms,Toast.LENGTH_LONG).show();
    }

    @Override
    public void onClick(View view) {
        String nombre= etName.getText().toString().trim();
        String apellidos=etSurname.getText().toString().trim();
        String NIF=etNIF.getText().toString().trim();
        int codProvincia=((Provincia) provinceList.getSelectedItem()).getCodProvincia();
        String latitud=etLat.getText().toString().replace(",",".");
        String longitud=etLon.getText().toString().replace(",",".");
        Object objCodProvincia=(codProvincia==-1)?null:codProvincia;
        //TODO: if(hayErroresEnDatos) { setError - return }
        if(isNewClient) {
            String sql="INSERT INTO clientes (nombre,apellidos,NIF,codProvincia,VIP,latitud,longitud) VALUES (?,?,?,?,?,?,?)";
            Object[] valores = {nombre,apellidos,NIF,objCodProvincia,
                    cbVIP.isChecked()?1:0,latitud,longitud};
            db.execSQL(sql,valores);
        }
        else {
            String sql="UPDATE Clientes SET nombre=?,apellidos=?,NIF=?,codProvincia=?,VIP=?, latitud=?,longitud=? WHERE codCliente=?";
            Object[] valores={nombre,apellidos,NIF,objCodProvincia,
                    cbVIP.isChecked()?1:0,latitud,longitud,codClient};
            db.execSQL(sql,valores);
        }
        setResult(RESULT_OK);
        finish();
    }
}