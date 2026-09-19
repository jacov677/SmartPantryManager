package com.example.smartpantrymanager;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.smartpantrymanager.data.DatabaseHelper;
import com.example.smartpantrymanager.model.PantryItem;

public class AddEditActivity extends AppCompatActivity {

    private EditText editName, editQuantity, editUnit, editExpiry;
    private DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_add_edit);
        dbHelper = new DatabaseHelper(this);

        editName = findViewById(R.id.editName);
        editQuantity = findViewById(R.id.editQuantity);
        editUnit = findViewById(R.id.editUnit);
        editExpiry = findViewById(R.id.editExpiry);

        Button buttonSave = findViewById(R.id.buttonSave);
        buttonSave.setOnClickListener(v -> saveItem());






        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

private void saveItem(){
    String name = editName.getText().toString().trim();
    String quantityText = editQuantity.getText().toString().trim();
    String unit = editUnit.getText().toString().trim();
    String expiry = editExpiry.getText().toString().trim();

    if (name.isEmpty()) {
        editName.setError("Name is required");
        return;
    }
    if (quantityText.isEmpty()) {
        editQuantity.setError("Quantity is required");
        return;
    }
    if (unit.isEmpty()) {
        editUnit.setError("Unit is required");
        return;
    }
    double quantity = Double.parseDouble(quantityText);
    dbHelper.insertPantryItem(new PantryItem(name, quantity, unit, expiry));
    finish();
}

            }