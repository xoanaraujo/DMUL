package xoanaraujo.examenuf1;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class ControlesFragment extends Fragment {
    private boolean enterEnable = true;

    interface OnControlCLickedListener{
        void controlClicked(Character ch);
    }
    private Button btnArriba, btnAbajo, btnEnter, btnDer, btnIzq;
    private OnControlCLickedListener listener;

    public void setListener(OnControlCLickedListener listener, boolean enterEnable) {
        this.listener = listener;
        //btnEnter.setVisibility(enterEnable ? View.VISIBLE : View.INVISIBLE);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_controles, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        btnArriba = view.findViewById(R.id.btnArriba);
        btnArriba.setOnClickListener( e -> listener.controlClicked('w'));
        btnAbajo = view.findViewById(R.id.btnAbajo);
        btnAbajo.setOnClickListener( e -> listener.controlClicked('s'));
        btnDer = view.findViewById(R.id.btnDer);
        btnDer.setOnClickListener( e -> listener.controlClicked('d'));
        btnIzq = view.findViewById(R.id.btnIzq);
        btnIzq.setOnClickListener( e -> listener.controlClicked('a'));
        btnEnter = view.findViewById(R.id.btnEnter);
        btnEnter.setOnClickListener( e -> listener.controlClicked('e'));
    }

}
