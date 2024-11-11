package com.example.resourcemanager;

import static android.widget.Toast.*;

import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import Model.Collection;

public class CollectionRecViewAdapter extends RecyclerView.Adapter<CollectionRecViewAdapter.ViewHolder>{
    private List<Collection> collectionList = new ArrayList<>();

    private Context context;
    public CollectionRecViewAdapter(Context context){
        this.context = context;
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
        Collection currentCollection = collectionList.get(position);
        holder.collectionName.setText(collectionList.get(position).getName());
        holder.NumItems.setText("Number of Items:" + collectionList.get(position).getListSize());
        holder.parent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "cliked", LENGTH_SHORT).show();
                Intent intent = new Intent(context, MainActivity2.class);
                intent.putExtra("Collection Name", currentCollection.getName());
                intent.putParcelableArrayListExtra("Items", (ArrayList<? extends Parcelable>) currentCollection.getItems());
                context.startActivity(intent);
            }

        });

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

        private CardView parent;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            collectionName = itemView.findViewById(R.id.CollectionName);
            NumItems = itemView.findViewById(R.id.NumItems);
            parent = itemView.findViewById(R.id.parentCollection);
        }
    }
}
