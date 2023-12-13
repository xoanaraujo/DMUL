package xoanaraujo.examenuf1.controller;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import xoanaraujo.examenuf1.R;
import xoanaraujo.examenuf1.model.ExampleItem;

public class ExampleAdapter extends RecyclerView.Adapter<ExampleItemHolder> {
    private Context context;
    private ArrayList<ExampleItem> items;

    public ExampleAdapter(Context context, ArrayList<ExampleItem> items) {
        this.context = context;
        this.items = items;
    }

    @NonNull
    @Override
    public ExampleItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ExampleItemHolder(LayoutInflater.from(context).inflate(R.layout.item_recycler_view, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ExampleItemHolder holder, int position) {
        ExampleItem item = items.get(position);
        if (item != null){
            holder.tvName.setText(item.getName());
            holder.tvIncId.setText(String.valueOf(item.getIncId())); // TODO IMPORTANTE PARSEAR CADA OBJETO A STRING!!!!
        }
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

}
class ExampleItemHolder extends RecyclerView.ViewHolder{
    TextView tvName, tvIncId;

    public ExampleItemHolder(@NonNull View itemView) {
        super(itemView);
        tvIncId = itemView.findViewById(R.id.tvIncId);
        tvName = itemView.findViewById(R.id.tvName);
    }
}
