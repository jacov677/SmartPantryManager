package com.example.smartpantrymanager.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.smartpantrymanager.R;
import com.example.smartpantrymanager.model.PantryItem;

import java.util.List;

public class PantryAdapter extends
        RecyclerView.Adapter<PantryAdapter.PantryViewHolder> {

    private final List<PantryItem> items;


    //Contructor
    public PantryAdapter(List<PantryItem> items) {
        this.items = items;
    }

    public static class PantryViewHolder extends RecyclerView.ViewHolder {
        TextView textName;
        TextView textQuantity;

        public PantryViewHolder(@NonNull View itemView) {

            super(itemView);
            textName = itemView.findViewById(R.id.textName);

            textQuantity = itemView.findViewById(R.id.textQuantity);

        }
    }

    @NonNull
    @Override
    public PantryAdapter.PantryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_pantry, parent, false);
        return new PantryAdapter.PantryViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull PantryAdapter.PantryViewHolder holder, int position) {
        PantryItem item = items.get(position);

        holder.textName.setText(item.getName());

        holder.textQuantity.setText(item.getQuantity() + " " + item.getUnit());

    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}
