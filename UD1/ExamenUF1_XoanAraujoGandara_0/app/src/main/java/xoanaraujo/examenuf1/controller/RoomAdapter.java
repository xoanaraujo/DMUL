package xoanaraujo.examenuf1.controller;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import xoanaraujo.examenuf1.R;
import xoanaraujo.examenuf1.model.Room;

public class RoomAdapter extends RecyclerView.Adapter<RoomHolder> {
    private Context context;
    private ArrayList<Room> rooms;

    public RoomAdapter(Context context, ArrayList<Room> rooms) {
        this.context = context;
        this.rooms = rooms;
    }

    @NonNull
    @Override
    public RoomHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new RoomHolder(LayoutInflater.from(context).inflate(R.layout.item_rv_rooms, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull RoomHolder holder, int position) {
        Room room = rooms.get(position);
        if (room != null){
            holder.tvIdRoom.setText(room.getName());
            holder.tvNameRoom.setText(String.valueOf(room.getId())); // TODO IMPORTANTE PARSEAR CADA OBJETO A STRING!!!!
        }
    }

    @Override
    public int getItemCount() {
        return rooms.size();
    }

}

class RoomHolder extends RecyclerView.ViewHolder{
    TextView tvIdRoom, tvNameRoom;

    public RoomHolder(@NonNull View itemView) {
        super(itemView);
        tvNameRoom = itemView.findViewById(R.id.tvNameRoom);
        tvIdRoom = itemView.findViewById(R.id.tvIdRoom);
    }
}