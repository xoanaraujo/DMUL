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

import java.text.DecimalFormat;

public class DivisaFragment extends Fragment {
    public static final double VAL_DIV0_DIV1 = 0.94;
    EditText etDivisa0;
    EditText etDivisa1;
    private TextWatcher textWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }
        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            DecimalFormat df = new DecimalFormat("#.##");
            if (etDivisa1.hasFocus()){
                etDivisa0.removeTextChangedListener(this);
                etDivisa0.setText(!etDivisa1.getText().toString().equals("") ? df.format(Double.parseDouble(etDivisa1.getText().toString()) * VAL_DIV0_DIV1) : "");
                etDivisa0.addTextChangedListener(this);
            } else if (etDivisa0.hasFocus()) {
                etDivisa1.removeTextChangedListener(this);
                etDivisa1.setText(!etDivisa0.getText().toString().equals("") ? df.format(Double.parseDouble(etDivisa0.getText().toString()) * (1 / VAL_DIV0_DIV1)) : "");
                etDivisa1.addTextChangedListener(this);
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
        etDivisa0 = view.findViewById(R.id.etEuro);
        etDivisa1 = view.findViewById(R.id.etDolar);

        etDivisa0.addTextChangedListener(textWatcher);
        etDivisa1.addTextChangedListener(textWatcher);
    }
}
