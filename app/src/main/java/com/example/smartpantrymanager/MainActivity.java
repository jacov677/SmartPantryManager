package com.example.smartpantrymanager;

import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.smartpantrymanager.adapter.PantryAdapter;
import com.example.smartpantrymanager.data.DatabaseHelper;
import com.example.smartpantrymanager.model.PantryItem;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        DatabaseHelper dbHelper = new DatabaseHelper(this);
        if (dbHelper.getAllPantryItems().isEmpty()) {
            dbHelper.insertPantryItem(new PantryItem("tomatoes", 3, "pieces", null));
            dbHelper.insertPantryItem(new PantryItem("garlic", 2, "cloves", null));
            dbHelper.insertPantryItem(new PantryItem("pasta", 1, "pack", null));
            dbHelper.insertPantryItem(new PantryItem("olive oil", 1, "bottle", null));
        }
        dbHelper.getWritableDatabase();
//        //dbHelper.insertPantryItem(new PantryItem("tamatoes", 3, "pieces", null));
//        List<PantryItem> items = dbHelper.getAllPantryItems();
//        for (PantryItem p : items){
//            Log.d("Pantry", p.getId() + " | " + p.getName() + " | " + p.getQuantity() + " " + p.getUnit()) ;
//        }
//        //List<PantryItem> items = dbHelper.getAllPantryItems();
//        Log.d("Pantry", "rows before = " + items.size());
//
//        if (!items.isEmpty()) {
//            PantryItem first = items.get(0);
//            first.setName("carrots");
//            first.setQuantity(99);
//            Log.d("Pantry", "rows updated = " + dbHelper.updatePantryItem(first));
//
//            PantryItem last = items.get(items.size() - 1);
//            Log.d("Pantry", "rows deleted = " + dbHelper.deletePantryItem(last.getId()));
//        }
//
//        for (PantryItem p : dbHelper.getAllPantryItems()) {
//            Log.d("Pantry", p.getId() + " | " + p.getName() + " | " + p.getQuantity() + " " + p.getUnit());
//        }
        RecyclerView recyclerPantry = findViewById(R.id.recyclerPantry);
        TextView textEmpty = findViewById(R.id.textEmpty);

        recyclerPantry.setLayoutManager(new LinearLayoutManager(this));

        List<PantryItem> items = dbHelper.getAllPantryItems();
        recyclerPantry.setAdapter(new PantryAdapter(items));

        if (items.isEmpty()) {
            recyclerPantry.setVisibility(RecyclerView.GONE);
            textEmpty.setVisibility(RecyclerView.VISIBLE);
        }else {
            recyclerPantry.setVisibility(RecyclerView.VISIBLE);
            textEmpty.setVisibility(RecyclerView.GONE);
        }



        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
//
//    private void setLayoutManager(LinearLayoutManager linearLayoutManager) {
//    }
}