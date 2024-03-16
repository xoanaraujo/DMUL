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
import io.github.xoanaraujo.recu_uf1.model.Grupo;

public class ArrayAdapterGrupos extends ArrayAdapter<Grupo> {

    private static class ViewHolder{
        TextView tvIdGrupo;
        TextView tvNombreGrupo;
    }

    public ArrayAdapterGrupos(@NonNull Context context, @NonNull Grupo[] objects) {
        super(context, 0, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Grupo grupo = getItem(position);
        ViewHolder viewHolder;

        if (convertView == null){ // Es decir, no se ha inflado.
            viewHolder = new ViewHolder();
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_grupo, parent, false);
            viewHolder.tvIdGrupo = convertView.findViewById(R.id.tvItemIdGrupo);
            viewHolder.tvNombreGrupo = convertView.findViewById(R.id.tvItemNombreGrupo);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.tvIdGrupo = convertView.findViewById(R.id.tvItemIdGrupo);
        viewHolder.tvNombreGrupo = convertView.findViewById(R.id.tvItemNombreGrupo);

        if (grupo == null){
            viewHolder.tvIdGrupo.setText("NULL REFERENCE");
            viewHolder.tvNombreGrupo.setText("NULL REFERENCE");
        } else {
            viewHolder.tvIdGrupo.setText(String.valueOf(grupo.getId()));
            viewHolder.tvNombreGrupo.setText(grupo.getNombre());
        }

        return convertView;
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return getView(position, convertView, parent);
    }
}
