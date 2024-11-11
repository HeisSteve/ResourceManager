package com.example.resourcemanager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import Model.Item;

public class ItemRecViewAdapder extends RecyclerView.Adapter<ItemRecViewAdapder.ItemViewHolder> {

    private List<Item> itemList = new ArrayList<>();

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_view, parent, false);
        ItemRecViewAdapder.ItemViewHolder holder = new ItemRecViewAdapder.ItemViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
        holder.itemName.setText(itemList.get(position).getName());
        holder.price.setText("Price:" + itemList.get(position).getPrice());
        holder.Usage.setProgress(itemList.get(position).getUsage());
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder{
        private TextView itemName;
        private TextView price;

        private ProgressBar Usage;

        private RelativeLayout parent;
        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);
            itemName = itemView.findViewById(R.id.Name);
            price = itemView.findViewById(R.id.Price);
            Usage = itemView.findViewById(R.id.Usage);
            parent= itemView.findViewById(R.id.main2);

        }
    }
}
