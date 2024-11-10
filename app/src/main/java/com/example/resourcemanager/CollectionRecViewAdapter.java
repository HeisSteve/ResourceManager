package com.example.resourcemanager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import Model.Collection;

public class CollectionRecViewAdapter extends RecyclerView.Adapter<CollectionRecViewAdapter.ViewHolder>{
    private List<Collection> collectionList = new ArrayList<>();

    public CollectionRecViewAdapter(){
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.collection_list_view, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.collectionName.setText(collectionList.get(position).getName());
        holder.NumItems.setText(collectionList.get(position).getListSize());
    }

    @Override
    public int getItemCount() {
        return collectionList.size();
    }

    public void setCollectionList(List<Collection> collectionList){
        this.collectionList = collectionList;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView collectionName;
        private TextView NumItems;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            collectionName = itemView.findViewById(R.id.CollectionName);
            NumItems = itemView.findViewById(R.id.NumItems);
        }
    }
}
