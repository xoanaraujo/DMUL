package io.github.xoanaraujo.appclientes;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

public class ClientActivity extends AppCompatActivity {

    List<ClientBase> listClients;
    private BDAssistant db = new BDAssistant(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client);

        EditText etNIF = findViewById(R.id.etNIF);
        EditText etName = findViewById(R.id.etName);
        EditText etSurname = findViewById(R.id.etSurname);
        EditText etLat = findViewById(R.id.etLat);
        EditText etLon = findViewById(R.id.etLog);
        CheckBox cbVIP = findViewById(R.id.cbVIP);
        Button btnSave = findViewById(R.id.btnSave);

        btnSave.setOnClickListener(view -> {
            if(etNIF.getText().toString().equals("") || etName.getText().toString().equals("")){
                launchToast("FALTAN DATOS");
            } else {
                String NIF = etNIF.getText().toString();
                if(checkNIF(NIF)) {
                    String name = etName.getText().toString();
                    String surname = etSurname.getText().toString();

                    double lat = Double.parseDouble(etLat.getText().toString());
                    double lon = Double.parseDouble(etLon.getText().toString());
                    //Provincia provincia = provincias.get(spnProvincia.getSelectedItemPosition());
                    boolean vip = cbVIP.isChecked();
                    Client c = new Client(name, surname, NIF, null, vip, lat, lon);
                    insertClient(c);
                }
            }

        });

        BDAssistant BDAssistant = new BDAssistant(this);
        listClients = BDAssistant.listClients();
    }

    private void insertClient(Client c) {

        if(db.insertClient(c)){
            launchToast("CLIENTE AÑADIDO");
            Intent returnIntent= new Intent(this, MainActivity.class);
            setResult(RESULT_OK,returnIntent);
            finish();
        }
    }

    private boolean checkNIF(String NIF) {

        if(NIF.matches("[0-9]{8}[a-zA-Z]")){
            List listNIFClients =db.listClients();
            if(listNIFClients.contains(NIF)){
                launchToast("Cliente ya registrado");
                return false;
            }
        }
        else{
            launchToast("NIF con formato incorrecto");
            return false;
        }

        return true;
    }

    private void launchToast(String ms){
        Toast.makeText(this,ms,Toast.LENGTH_LONG).show();
    }
}