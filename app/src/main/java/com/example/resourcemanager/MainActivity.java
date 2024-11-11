package com.example.resourcemanager;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import Model.Collection;
import Model.HomeResources;
import Model.Item;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);  // Only call this once

        RecyclerView collectionViews;
        HomeResources homeResources;

        // Initialize views
        EditText etCollectionName = findViewById(R.id.etCollectionName); // EditText for input
        Button btnAddCollection = findViewById(R.id.btnAddCollection);
        Button btnSubmitCollection = findViewById(R.id.btnSubmitCollection);

        // Setup edge-to-edge
        EdgeToEdge.enable(this);
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

        // Initialize HomeResources and populate with test data
        Collection food = new Collection("Food");
        food.addItem(new Item("Item 1", 100, 10));
        food.addItem(new Item("Item 2", 100,10));
        food.addItem(new Item("Item 3", 100,10));

        Collection cleaningProduct = new Collection("Clean product");
        cleaningProduct.addItem(new Item("Item 1", 100, 10));
        cleaningProduct.addItem(new Item("Item 2", 100,10));
        cleaningProduct.addItem(new Item("Item 3", 100,10));

        homeResources = new HomeResources();
        homeResources.addCollection(cleaningProduct);
        homeResources.addCollection(food);

        CollectionRecViewAdapter collectionAdapter = new CollectionRecViewAdapter(this);
        collectionAdapter.setCollectionList(homeResources.getAllResources());
        collectionViews.setAdapter(collectionAdapter);

        collectionViews.setLayoutManager(new GridLayoutManager(this, 2));

        // Add New Collection button click handler
        btnAddCollection.setOnClickListener(v -> {
            // Show the EditText and Submit button
            etCollectionName.setVisibility(View.VISIBLE);
            btnSubmitCollection.setVisibility(View.VISIBLE);
            btnAddCollection.setVisibility(View.GONE); // Hide the Add button
        });

        // Submit new collection after entering a name
        btnSubmitCollection.setOnClickListener(v -> {
            String collectionName = etCollectionName.getText().toString().trim(); // Get text from EditText

            // Validate input
            if (!collectionName.isEmpty()) {
                Collection newCollection = new Collection(collectionName);
                homeResources.addCollection(newCollection);
                collectionAdapter.setCollectionList(homeResources.getAllResources());
                collectionAdapter.notifyDataSetChanged(); // Update the RecyclerView
                etCollectionName.setText(""); // Clear the input field after adding
                etCollectionName.setVisibility(View.GONE); // Hide EditText
                btnSubmitCollection.setVisibility(View.GONE); // Hide Submit button
                btnAddCollection.setVisibility(View.VISIBLE); // Show Add button again
            } else {
                Toast.makeText(MainActivity.this, "Please enter a collection name.", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
