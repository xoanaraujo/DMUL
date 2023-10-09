package io.github.xoanaraujo.appclients;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import java.util.ArrayList;
public class MainActivity extends AppCompatActivity
        implements AdapterView.OnItemClickListener {
    ActivityResultLauncher<Intent> resultLauncher;
    ListView listaClientes;
    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //region Interface
        setContentView(R.layout.activity_main);
        listaClientes = (ListView)findViewById(R.id.listView);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fabAdd);
        //endregion
        resultLauncher =registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override public void onActivityResult(ActivityResult result) {
                        ClienteActivityReturn(result);
                    }
                });
        poblarLista();
        listaClientes.setOnItemClickListener(this);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View view) { nuevoCliente(); }
        });
    }
    public void ClienteActivityReturn(ActivityResult result) {
        if(result.getResultCode()==RESULT_OK) {
            poblarLista();
            Toast.makeText(this,getString(R.string.OperacionRealizada),Toast.LENGTH_LONG).show();
        }
    }

    public void poblarLista() {
        listaClientes.setAdapter(
                new ArrayAdapter<MiniCliente>(this,
                        android.R.layout.simple_list_item_1,getDatosClientes()));
    }
    public ArrayList<MiniCliente> getDatosClientes() {
        ArrayList<MiniCliente> datosClientes = new ArrayList<>();
        SQLiteDatabase bd = new AsistenteBD(this).getReadableDatabase();
        Cursor filas=bd.rawQuery("SELECT * FROM clientes ORDER BY apellidos,nombre", null);
        int colIndexCodCliente = filas.getColumnIndex("codCliente");
        int colIndexNombre = filas.getColumnIndex("nombre");
        int colIndexApellidos = filas.getColumnIndex("apellidos");
        int colIndexVIP = filas.getColumnIndex("VIP");
        while(filas.moveToNext()) {
            int codCliente = filas.getInt(colIndexCodCliente);
            String nombre = filas.getString(colIndexNombre);
            String apellidos = filas.getString(colIndexApellidos);
            boolean VIP = filas.getInt(colIndexVIP)==1;
            datosClientes.add(new MiniCliente(codCliente,nombre,apellidos,VIP));
        }
        bd.close();
        return datosClientes;
    }
    @Override public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        MiniCliente cliente = (MiniCliente)parent.getItemAtPosition(position);
        abrirCliente(cliente.getCodCliente());
    }
    private void nuevoCliente() {
        Intent intent = new Intent(this,ClienteActivity.class);
        resultLauncher.launch(intent);
    }
    private void abrirCliente(int codCliente) {
        Intent intent = new Intent(this,ClienteActivity.class);
        intent.putExtra("codCliente", codCliente);
        resultLauncher.launch(intent);
    }
}