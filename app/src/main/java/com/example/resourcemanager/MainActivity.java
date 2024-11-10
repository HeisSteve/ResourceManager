package com.example.resourcemanager;

import android.os.Bundle;
import android.util.Log;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import Model.Collection;
import Model.HomeResources;
import Model.Item;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
         RecyclerView collectionViews;
        HomeResources homeResources;

        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        collectionViews = findViewById(R.id.Collections);
        if (collectionViews == null) {
            Log.e("RecyclerView", "RecyclerView initialization failed. Make sure the ID is correct in XML.");
        } else {
            Log.d("RecyclerView", "RecyclerView initialized successfully.");
        }

        // Test
        Collection food = new Collection("Food");
        food.addItem(new Item("Item 1", 100));
        food.addItem(new Item("Item 2", 100));
        food.addItem(new Item("Item 3", 100));
        Collection CleaningProduct = new Collection("Clean product");
        CleaningProduct.addItem(new Item("Item 1", 100));
        CleaningProduct.addItem(new Item("Item 2", 100));
        CleaningProduct.addItem(new Item("Item 3", 100));
        homeResources = new HomeResources();
        homeResources.addCollection(CleaningProduct);
        homeResources.addCollection(food);

        CollectionRecViewAdapter collectionAdapter = new CollectionRecViewAdapter(this);
        collectionAdapter.setCollectionList(homeResources.getAllResources());
        assert collectionViews != null;
        collectionViews.setAdapter(collectionAdapter);


        collectionViews.setLayoutManager(new GridLayoutManager(this, 2));

    }


}