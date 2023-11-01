package com.example.elecciones.FRAGMENTS;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.elecciones.INTERFACES.ComunicationFragments;
import com.example.elecciones.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link BtnLimitadoFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BtnLimitadoFragment extends Fragment {

    private Button btn ;

    private View view;

    private Activity activity;

    private String txtBtn;

    private int maxClick;

    private int contador;

    private View.OnClickListener listenerClick;
    private ComunicationFragments zcomunicationInterface;

    public BtnLimitadoFragment() {
        // Required empty public constructor
    }

    public BtnLimitadoFragment(String txtBtn, int maxClick) {
        this.txtBtn = txtBtn;
        this.maxClick = maxClick;
    }


    public static BtnLimitadoFragment newInstance(String param1, String param2) {
        BtnLimitadoFragment fragment = new BtnLimitadoFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }


    //METODO DONDE INFLAMOS
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_btn_limitado, container, false);
        btn = view.findViewById(R.id.btnLimitado);
        btn.setText(txtBtn);
        btn.setOnClickListener(clickListenerBtn);
        return view;
    }

    //LOGICA DEL BOTÓN
    private View.OnClickListener clickListenerBtn = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            contador++;
            if(contador <= maxClick){
                comunicationInterface.clickButton();
                if(contador ==maxClick){
                    btn.setActivated(false);
                    comunicationInterface.finalClick();
                }
            }


        }
    };

    //ASIGNAMOS EL activity
    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if(context instanceof  Activity){
            activity =(Activity) context;
            comunicationInterface = (ComunicationFragments) activity;
        }
    }

    public void deletedLatestClick() {
        contador--;
    }
}