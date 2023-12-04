package io.github.xoanaraujo.mareasgalicia.viewmodel.activity.mainactivity;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import io.github.xoanaraujo.mareasgalicia.R;
import io.github.xoanaraujo.mareasgalicia.model.Port;

public class PortSpinnerAdapter extends ArrayAdapter<Port> {

    public PortSpinnerAdapter(@NonNull Context context, int resource, @NonNull Port[] objects) {
        super(context, resource, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return createView(position, convertView, parent);
    }


    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return super.getDropDownView(position, convertView, parent);
    }
    private View createView(int position, View convertView, ViewGroup parent) {
        if (convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_port_spinner, parent, false);
        }

        TextView tvPort = convertView.findViewById(R.id.tvPort);
        Port port = getItem(position);
        if (port != null)
            tvPort.setText(port.getName());
    }

}

