package com.example.resourcemanager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import Model.Item;

public class MainActivity2 extends AppCompatActivity {

    private ArrayList<Item> itemList = new ArrayList<>();
    private ItemRecViewAdapder itemAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main2);

        // Set up edge-to-edge display
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main2), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Get data from the intent
        Intent intent = getIntent();
        String collectionName = intent.getStringExtra("Collection Name");
        itemList = intent.getParcelableArrayListExtra("Items");

        // Set collection name in TextView
        TextView collectionNameTextView = findViewById(R.id.CollectionName);
        collectionNameTextView.setText("Collection Name: " + collectionName);

        // Set up RecyclerView
        RecyclerView itemView = findViewById(R.id.Items);
        itemAdapter = new ItemRecViewAdapder(this);
        itemAdapter.setItemList(itemList);
        itemView.setAdapter(itemAdapter);
        itemView.setLayoutManager(new GridLayoutManager(this, 2));

        // Find EditText fields
        EditText etItemName = findViewById(R.id.etItemName);
        EditText etItemPrice = findViewById(R.id.etItemPrice);
        EditText etItemUsage = findViewById(R.id.etItemUsage);

        // Find buttons
        Button btnAddItem = findViewById(R.id.btnAddItem);
        Button btnSubmitItem = findViewById(R.id.btnSubmitItem);

        // Initially, hide the EditText views and the Submit button
        etItemName.setVisibility(View.GONE);
        etItemPrice.setVisibility(View.GONE);
        etItemUsage.setVisibility(View.GONE);
        btnSubmitItem.setVisibility(View.GONE);

        // Show EditText views and Submit button when "Add new item" is clicked
        btnAddItem.setOnClickListener(v -> {
            etItemName.setVisibility(View.VISIBLE);
            etItemPrice.setVisibility(View.VISIBLE);
            etItemUsage.setVisibility(View.VISIBLE);
            btnSubmitItem.setVisibility(View.VISIBLE);
        });

        // Add the new item when "Confirm" is clicked
        btnSubmitItem.setOnClickListener(v -> {
            String name = etItemName.getText().toString();
            String priceStr = etItemPrice.getText().toString();
            String usageStr = etItemUsage.getText().toString();

            // Validate input
            if (!name.isEmpty() && !priceStr.isEmpty() && !usageStr.isEmpty()) {
                try {
                    double price = Double.parseDouble(priceStr);
                    int usage = Integer.parseInt(usageStr);

                    // Create a new item and add it to the list
                    Item newItem = new Item(name, price, usage);
                    itemList.add(newItem);

                    // Update the RecyclerView
                    itemAdapter.setItemList(itemList);
                    itemAdapter.notifyItemInserted(itemList.size() - 1); // Notify new item inserted

                    // Clear the EditText fields
                    etItemName.setText("");
                    etItemPrice.setText("");
                    etItemUsage.setText("");

                    // Hide the EditText fields and Submit button after adding the item
                    etItemName.setVisibility(View.GONE);
                    etItemPrice.setVisibility(View.GONE);
                    etItemUsage.setVisibility(View.GONE);
                    btnSubmitItem.setVisibility(View.GONE);

                    // Optionally, show a confirmation message
                    Toast.makeText(MainActivity2.this, "Item added successfully", Toast.LENGTH_SHORT).show();

                } catch (NumberFormatException e) {
                    // Handle invalid input (non-numeric data for price or usage)
                    Toast.makeText(MainActivity2.this, "Invalid input! Please enter valid numbers.", Toast.LENGTH_SHORT).show();
                }
            } else {
                // Show message if fields are empty
                Toast.makeText(MainActivity2.this, "Please fill out all fields.", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
