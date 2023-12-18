package xoanaraujo.examenuf1.controller;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import xoanaraujo.examenuf1.R;
import xoanaraujo.examenuf1.model.Room;

public class ArrayAdapterRoom extends ArrayAdapter<Room> {

    public class ArrayRoomHolder{
        TextView tvId, tvName;
    }
    public ArrayAdapterRoom(@NonNull Context context, @NonNull List<Room> objects) {
        super(context, 0, objects);

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Room r =getItem(position);
        ArrayRoomHolder holder;

        if (convertView == null){
            holder = new ArrayRoomHolder();
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_rv_rooms, parent, false);
            holder.tvId =convertView.findViewById(R.id.tvIdRoom);
            holder.tvName = convertView.findViewById(R.id.tvNameRoom);
            holder.tvId.setText(String.valueOf(r.getId()));
            holder.tvName.setText(r.getName());
            convertView.setTag(holder);
        }
        return convertView;
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return getView(position, convertView, parent);
    }
}
