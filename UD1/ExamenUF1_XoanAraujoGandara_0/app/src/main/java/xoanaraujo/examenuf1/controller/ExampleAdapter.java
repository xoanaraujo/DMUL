package xoanaraujo.examenuf1.controller;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

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
        holder.tvName.setText(item.getName());
        holder.tvIncId.setText(item.getIncId());
    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}
