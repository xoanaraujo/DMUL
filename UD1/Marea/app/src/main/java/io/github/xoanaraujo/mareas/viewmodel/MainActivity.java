package io.github.xoanaraujo.mareas.viewmodel;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import io.github.xoanaraujo.marea.R;
import io.github.xoanaraujo.mareas.viewmodel.fragment.MareaFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragmentContainerView, new MareaFragment())
                .commit();
    }
}