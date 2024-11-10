package com.example.resourcemanager;

import android.view.View;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ItemRecViewAdapder {

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
            parent= itemView.findViewById(R.id.second);

        }
    }
}
