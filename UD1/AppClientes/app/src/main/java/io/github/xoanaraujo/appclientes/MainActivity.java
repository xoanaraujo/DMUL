package io.github.xoanaraujo.appclientes;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContract;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    List<ClientBase> clientList;
    ActivityResultLauncher<Intent> resultLauncher;
    ListView listViewClients;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listViewClients = findViewById(R.id.listView);

        FloatingActionButton fab = findViewById(R.id.fabAdd);

        fab.setOnClickListener(view -> startActivity(new Intent(this, ClientActivity.class)));

        resultLauncher =registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override public void onActivityResult(ActivityResult result) {
                        ClienteActivityReturn(result);
                    }
                });
        //BDAssistant bdAssistant = new BDAssistant(this);
        //clientList = bdAssistant.listClients();
    }

    public void ClienteActivityReturn(ActivityResult result) {
        if(result.getResultCode()==RESULT_OK) {
            poblarLista();
            Toast.makeText(this,"Operacion realizada",Toast.LENGTH_LONG).show();
        }
    }

    public void poblarLista() {
        listViewClients.setAdapter(
                new ArrayAdapter<ClientBase>(this,
                        android.R.layout.simple_list_item_1,getDatosClientes()));
    }

    public ArrayList<ClientBase> getDatosClientes() {
        ArrayList<ClientBase> datosClientes = new ArrayList<>();
        SQLiteDatabase bd = new BDAssistant(this).getReadableDatabase();
        Cursor filas=bd.rawQuery("SELECT * FROM CLIENTS ORDER BY surname, name", null);
        int colIndexCodCliente = filas.getColumnIndex("codCliente");
        int colIndexNIF = filas.getColumnIndex("NIF");
        int colIndexNombre = filas.getColumnIndex("nombre");
        int colIndexApellidos = filas.getColumnIndex("apellidos");
        int colIndexVIP = filas.getColumnIndex("VIP");
        while(filas.moveToNext()) {
            int codCliente = filas.getInt(colIndexCodCliente);
            String NIF = filas.getString(colIndexNombre);
            String nombre = filas.getString(colIndexNombre);
            String apellidos = filas.getString(colIndexApellidos);
            boolean VIP = filas.getInt(colIndexVIP)==1;
            datosClientes.add(new ClientBase(codCliente,nombre,apellidos, NIF, VIP));
        }
        bd.close();
        return datosClientes;
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        ClientBase cliente = (ClientBase)adapterView.getItemAtPosition(i);
        abrirCliente(cliente.getCodClient());
    }

    private void nuevoCliente() {
        Intent intent = new Intent(this,ClientActivity.class);
        resultLauncher.launch(intent);
    }
    private void abrirCliente(int codCliente) {
        Intent intent = new Intent(this, ClientActivity.class);
        intent.putExtra("codCliente", codCliente);
        resultLauncher.launch(intent);
    }
}

