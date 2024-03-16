package io.github.xoanaraujo.recu_uf1.controller.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import io.github.xoanaraujo.recu_uf1.R;
import io.github.xoanaraujo.recu_uf1.model.Alumno;

public class ArrayAdapterAlumnos extends ArrayAdapter<Alumno> {

    private static class ViewHolder {
        TextView tvIdAlumno;
        TextView tvDniAlumno;
        TextView tvNombreAlumno;
        TextView tvIdGrupoAlumno;
    }

    public ArrayAdapterAlumnos(@NonNull Context context, @NonNull Alumno[] objects) {
        super(context, 0, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Alumno alumno = getItem(position);
        ViewHolder viewHolder;
        if (convertView == null){
            viewHolder = new ViewHolder();
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_alumno, parent, false);
            viewHolder.tvIdAlumno = convertView.findViewById(R.id.tvItemIdAlumno);
            viewHolder.tvDniAlumno = convertView.findViewById(R.id.tvItemDniAlumno);
            viewHolder.tvNombreAlumno = convertView.findViewById(R.id.tvItemNombreAlumno);
            viewHolder.tvIdGrupoAlumno = convertView.findViewById(R.id.tvItemIdGrupoAlumno);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.tvIdAlumno.setText(String.valueOf(alumno.getId()));
        viewHolder.tvDniAlumno.setText(alumno.getDni());
        viewHolder.tvNombreAlumno.setText(alumno.getNombre());
        viewHolder.tvIdGrupoAlumno.setText(String.valueOf(alumno.getIdGrupo()));

        return convertView;
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return getView(position, convertView, parent);
    }
}
