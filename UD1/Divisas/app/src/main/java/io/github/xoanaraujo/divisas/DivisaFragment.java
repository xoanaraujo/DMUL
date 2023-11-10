package io.github.xoanaraujo.divisas;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class DivisaFragment extends Fragment {
    EditText etEuro;
    EditText etDolar;
    private TextWatcher textWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }
        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            if (etDolar.hasFocus()){
                if (!etDolar.getText().toString().equals("")){
                    Double valorDolar = Double.parseDouble(etDolar.getText().toString()) * 0.94;
                    etEuro.setEnabled(false);
                    etEuro.setText(String.valueOf(valorDolar));
                    etEuro.setEnabled(true);
                }
            } else if (etEuro.hasFocus()) {
                if (!etEuro.getText().toString().equals("")){
                    Double valorDolar = Double.parseDouble(etEuro.getText().toString()) * 1.07;
                    etDolar.setEnabled(false);
                    etDolar.setText(String.valueOf(valorDolar));
                    etDolar.setEnabled(true);
                }
            }
        }
        @Override
        public void afterTextChanged(Editable s) {

        }
    };
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_divisa, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        etEuro = view.findViewById(R.id.etEuro);
        etDolar = view.findViewById(R.id.etDolar);

        etEuro.addTextChangedListener(textWatcher);
        etDolar.addTextChangedListener(textWatcher);
    }
}
