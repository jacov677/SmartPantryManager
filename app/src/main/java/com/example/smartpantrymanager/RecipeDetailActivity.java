package com.example.smartpantrymanager;

//import static android.content.Intent.getIntent;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.smartpantrymanager.data.DatabaseHelper;
import com.example.smartpantrymanager.model.RecipeIngredient;

import java.util.List;

public class RecipeDetailActivity extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle saveInstanceState){
        super.onCreate(saveInstanceState);
        setContentView(R.layout.activity_recipe_detail);

        int recipeId = getIntent().getIntExtra("RECIPE_ID", -1);
        String recipeName = getIntent().getStringExtra("RECIPE_NAME");
        String recipeSteps = getIntent().getStringExtra("RECIPE_STEPS");

        setTitle("The Recipe");

        TextView textDetailName = findViewById(R.id.textDetailName);
        TextView textDetailIngredients = findViewById(R.id.textDetailIngredients);
        TextView textDetailSteps = findViewById(R.id.textDetailSteps);

        textDetailName.setText(recipeName);
        textDetailSteps.setText(recipeSteps);

        DatabaseHelper db = new DatabaseHelper(this);
        List<RecipeIngredient> ingredients = db.getIngredientsForRecipe(recipeId);

        StringBuilder builder = new StringBuilder();
        for (RecipeIngredient ingredient : ingredients){
            builder.append("* ")
                    .append(ingredient.getQuantity())
                    .append(" ")
                    .append(ingredient.getUnit())
                    .append(" ")
                    .append(ingredient.getIngredientName())
                    .append("\n");
        }
        textDetailIngredients.setText(builder.toString());
    }



}
