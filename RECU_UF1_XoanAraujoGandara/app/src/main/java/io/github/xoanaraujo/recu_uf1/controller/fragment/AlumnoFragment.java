package io.github.xoanaraujo.recu_uf1.controller.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import io.github.xoanaraujo.recu_uf1.R;
import io.github.xoanaraujo.recu_uf1.model.Alumno;

public class AlumnoFragment extends Fragment {

    private Alumno alumno;

    public AlumnoFragment(Alumno alumno) {
        this.alumno = alumno;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_alumno, container, false);
        TextView tvIdAlumno = view.findViewById(R.id.tvIdAlumno);
        TextView tvDniAlumno = view.findViewById(R.id.tvNombreAlumno);
        TextView tvIdGrupoAlumno = view.findViewById(R.id.tvIdGrupoAlumno);

        tvIdAlumno.setText(alumno.getId());
        tvDniAlumno.setText(alumno.getDni());
        tvIdGrupoAlumno.setText(alumno.getIdGrupo());
        return view;
    }
}