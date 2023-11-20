package io.github.xoanaraujo.dado;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.Random;

public class DadoFragment extends Fragment {

    public interface OnDadoListener {
        void onClick(int maxValue);
    }
    private OnDadoListener listener;
    private Button btnDado;
    private int maxValue;


    public void setDadoInit(int maxValue, OnDadoListener listener){
        this.listener = listener;
        this.maxValue = maxValue;
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_dado, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        btnDado = view.findViewById(R.id.button);
        btnDado.setOnClickListener( v -> {
            if (maxValue < 1)
                maxValue = 6;
            int value = new Random().nextInt(maxValue) + 1;
            listener.onClick(value);
            btnDado.setText(String.valueOf(value));
        });
    }

}
