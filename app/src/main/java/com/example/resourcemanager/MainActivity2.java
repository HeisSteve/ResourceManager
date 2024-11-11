package com.example.resourcemanager;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import Model.Collection;
import Model.HomeResources;
import Model.Item;

public class MainActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main2);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main2), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Intent intent = getIntent();
        String collection_name = intent.getStringExtra("Collection Name");
        ArrayList<Item> itemList = intent.getParcelableArrayListExtra("Items");
        TextView collectionName = findViewById(R.id.CollectionName);
        collectionName.setText("Collection Name: " + collection_name);

        RecyclerView itemView;
        itemView = findViewById(R.id.Items);

        ItemRecViewAdapder itemAdapter = new ItemRecViewAdapder(this);
        itemAdapter.setItemList(itemList);
        itemView.setAdapter(itemAdapter);

        itemView.setLayoutManager(new GridLayoutManager(this, 1));


    }


}