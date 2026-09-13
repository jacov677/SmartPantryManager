package com.example.smartpantrymanager;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.smartpantrymanager.data.DatabaseHelper;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        DatabaseHelper dbHelper = new DatabaseHelper(this);
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
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}