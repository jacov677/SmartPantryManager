package com.example.smartpantrymanager.logic;

import com.example.smartpantrymanager.data.DatabaseHelper;
import com.example.smartpantrymanager.model.PantryItem;
import com.example.smartpantrymanager.model.Recipe;
import com.example.smartpantrymanager.model.RecipeIngredient;

import java.util.ArrayList;
import java.util.List;

public class RecipeMatcher {

    public static String normalise(String raw){

            if (raw == null) {
                return "";
            }
            String name = raw.toLowerCase().trim();

            if (name.endsWith("es") && name.length() > 4) {
                name = name.substring(0, name.length() - 2);
            } else if (name.endsWith("s") && name.length() > 3) {
                name = name.substring(0, name.length() - 1);
            }
            return name;
        }
    public static boolean hasEnough(RecipeIngredient needed, List<PantryItem> pantry) {
        String neededName = normalise(needed.getIngredientName());

        for (PantryItem item : pantry) {
            if (normalise(item.getName()).equals(neededName)) {
                return item.getQuantity() >= needed.getQuantity();
            }
        }
        return false;
    }
    public static List<Recipe> getSuggestedRecipes(DatabaseHelper dbHelper) {
        List<Recipe> suggested = new ArrayList<>();
        List<PantryItem> pantry = dbHelper.getAllPantryItems();

        for (Recipe recipe : dbHelper.getAllRecipes()) {
            List<RecipeIngredient> needed = dbHelper.getIngredientsForRecipe(recipe.getId());

            if (needed.isEmpty()) {
                continue;
            }

            boolean canMake = true;
            for (RecipeIngredient ingredient : needed) {
                if (!hasEnough(ingredient, pantry)) {
                    canMake = false;
                    break;
                }
            }

            if (canMake) {
                suggested.add(recipe);
            }
        }
        return suggested;
    }
}

