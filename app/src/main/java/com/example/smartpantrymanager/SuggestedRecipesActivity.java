package com.example.smartpantrymanager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.smartpantrymanager.adapter.RecipeAdapter;
import com.example.smartpantrymanager.data.DatabaseHelper;
import com.example.smartpantrymanager.logic.RecipeMatcher;
import com.example.smartpantrymanager.model.Recipe;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.List;

public class SuggestedRecipesActivity extends AppCompatActivity {

    private DatabaseHelper dbHelper;
    private RecyclerView recyclerSuggested;
    private TextView textNoMatches;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_suggested);
        setTitle("The Suggested Recipe's");

        dbHelper = new DatabaseHelper(this);
        recyclerSuggested = findViewById(R.id.recyclerSuggested);
        textNoMatches = findViewById(R.id.textNoMatches);
        recyclerSuggested.setLayoutManager(new LinearLayoutManager(this));

        BottomNavigationView bottomNav = findViewById(R.id.bottomNav);
        bottomNav.setSelectedItemId(R.id.nav_suggested);
        bottomNav.setOnItemSelectedListener(menuItem -> {
            if (menuItem.getItemId() == R.id.nav_pantry) {
                startActivity(new Intent(SuggestedRecipesActivity.this, MainActivity.class));
                finish();
            }
            return true;
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

        List<Recipe> suggested = RecipeMatcher.getSuggestedRecipes(dbHelper);
        recyclerSuggested.setAdapter(new RecipeAdapter(suggested));

        if (suggested.isEmpty()) {
            recyclerSuggested.setVisibility(View.GONE);
            textNoMatches.setVisibility(View.VISIBLE);
        } else {
            recyclerSuggested.setVisibility(View.VISIBLE);
            textNoMatches.setVisibility(View.GONE);
        }
    }
}
