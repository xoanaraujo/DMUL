package xoanaraujo.examenuf1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;

import xoanaraujo.examenuf1.controller.ExampleAdapter;
import xoanaraujo.examenuf1.model.ExampleItem;

public class MainActivity extends AppCompatActivity {

    private RecyclerView rv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rv = findViewById(R.id.rv);

        ArrayList<ExampleItem> items = new ArrayList<>();
        items.add(new ExampleItem("Mesa"));
        items.add(new ExampleItem("Murcia"));
        items.add(new ExampleItem("Raul"));


        rv.setAdapter(new ExampleAdapter(this, items));
        rv.setLayoutManager(new LinearLayoutManager(this));
    }
}