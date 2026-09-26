package com.example.smartpantrymanager.data;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.smartpantrymanager.model.PantryItem;
import com.example.smartpantrymanager.model.Recipe;
import com.example.smartpantrymanager.model.RecipeIngredient;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "smartpantry.db";
    private static final int DATABASE_VERSION = 1;
    public static final String TABLE_PANTRY = "pantry_items";
    public static final String COL_PANTRY_ID = "id";
    public static final String COL_PANTRY_NAME = "name";
    public static final String COL_PANTRY_QUANTITY = "quantity";
    public static final String COL_PANTRY_UNIT = "unit";
    public static final String COL_PANTRY_EXPIRY = "expiry";


    public final static String TABLE_RECIPES = "recipes";

    public final static String COL_RECIPE_ID = "id";
    public final static String COL_RECIPE_NAME = "name";
    public final static String COL_RECIPE_STEPS = "steps";


    public final static String TABLE_RECIPE_INGREDIENTS = "recipe_ingredients";


    public final static String COL_INGREDIENT_ID = "id";


    public final static String COL_INGREDIENT_RECIPE_ID = "recipe_id";

    public final static String COL_INGREDIENT_NAME = "ingredient_name";

    public final static String COL_INGREDIENT_QUANTITY = "quantity";

    public final static String COL_INGREDIENT_UNIT = "unit";

    private static final String CREATE_TABLE_PANTRY =
            "CREATE TABLE " + TABLE_PANTRY + " (" +
                    COL_PANTRY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COL_PANTRY_NAME + " TEXT NOT NULL, " +
                    COL_PANTRY_QUANTITY + " REAL NOT NULL, " +
                    COL_PANTRY_UNIT + " TEXT, " +
                    COL_PANTRY_EXPIRY + " TEXT" +
                    ")";
    private static final String CREATE_TABLE_RECIPES =
            "CREATE TABLE " + TABLE_RECIPES + " (" +
                    COL_RECIPE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COL_RECIPE_NAME + " TEXT NOT NULL, " +
                    COL_RECIPE_STEPS + " TEXT NOT NULL" +

                    ")";

    private static final String CREATE_TABLE_RECIPE_INGREDIENTS =
            "CREATE TABLE " + TABLE_RECIPE_INGREDIENTS + " (" +
                    COL_INGREDIENT_RECIPE_ID + " INTEGER NOT NULL, " +
                    COL_INGREDIENT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COL_INGREDIENT_NAME + " TEXT NOT NULL, " +
                    COL_INGREDIENT_QUANTITY + " REAL, " +
                    COL_INGREDIENT_UNIT + " TEXT " +
                    ")";


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_PANTRY);
        db.execSQL(CREATE_TABLE_RECIPES);
        db.execSQL(CREATE_TABLE_RECIPE_INGREDIENTS);
        seedRecipes(db);


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVer, int newVer) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PANTRY);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_RECIPES);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_RECIPE_INGREDIENTS);


    }

    public long insertPantryItem(PantryItem item) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COL_PANTRY_NAME, item.getName());
        values.put(COL_PANTRY_QUANTITY, item.getQuantity());
        values.put(COL_PANTRY_UNIT, item.getUnit());
        values.put(COL_PANTRY_EXPIRY, item.getExpiryDate());
        return db.insert(TABLE_PANTRY, null, values);
    }

    public long insertRecipeIngredient(SQLiteDatabase db, int recipeId, String name, double quantity, String unit) {
        //SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COL_INGREDIENT_RECIPE_ID, recipeId);
        values.put(COL_INGREDIENT_NAME, name);
        values.put(COL_INGREDIENT_QUANTITY, quantity);
        values.put(COL_INGREDIENT_UNIT, unit);
        return db.insert(TABLE_RECIPE_INGREDIENTS, null, values);
    }


    public long insertRecipe(SQLiteDatabase db, String name, String steps) {
        //SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COL_RECIPE_NAME, name);
        values.put(COL_RECIPE_STEPS, steps);
        return db.insert(TABLE_RECIPES, null, values);
    }

    public long addRecipe(SQLiteDatabase db, String name, String steps, String[][] ingredients) {
        //SQLiteDatabase db = getWritableDatabase();
//        ContentValues values = new ContentValues();
//        values.put(COL_INGREDIENT_RECIPE_ID, recipeId);
//        values.put(COL_INGREDIENT_NAME, name);
//        values.put(COL_INGREDIENT_QUANTITY, quantity);
//        values.put(COL_INGREDIENT_UNIT, unit);
//        return db.insert(TABLE_RECIPE_INGREDIENTS,null, values);
//    }

        long recipeId = insertRecipe(db, name, steps);

        for (String[] ing : ingredients) {
            insertRecipeIngredient(db, (int) recipeId,
                    ing[0],
                    Double.parseDouble(ing[1]),
                    ing[2]);
        }
        return recipeId;
    }


    private void seedRecipes(SQLiteDatabase db) {

        addRecipe(db, "Chicken and Avo Wrap",
                "Boil the eggs and slice them. Layer the chicken, sliced egg, cheese and avo onto the wrap. Spread with mayonnaise, roll up and cut in half.",
                new String[][]{
                        {"wrap", "1", "piece"},
                        {"egg", "2", "pieces"},
                        {"cheese", "50", "g"},
                        {"mayonnaise", "2", "tbsp"},
                        {"chicken", "1", "piece"},
                        {"avo", "1", "piece"}
                });

        addRecipe(db, "Steak with Onion Gravy",
                "Fry the steak to your liking and set it aside to rest. Fry the sliced onion in the butter until soft. Stir in the cake flour, then the gravy powder and a little water, and simmer until thick. Pour over the steak.",
                new String[][]{
                        {"steak", "1", "piece"},
                        {"onion", "1", "piece"},
                        {"butter", "1", "tbsp"},
                        {"cake flour", "1", "tbsp"},
                        {"gravy powder", "2", "tbsp"}
                });

        addRecipe(db, "Tomato Pasta",
                "Boil the pasta until soft. Fry the chopped garlic in olive oil. Add chopped tomato and simmer for ten minutes. Stir through the drained pasta.",
                new String[][]{
                        {"pasta", "1", "pack"},
                        {"tomato", "3", "pieces"},
                        {"garlic", "2", "cloves"},
                        {"olive oil", "1", "tbsp"}
                });

        addRecipe(db, "Garlic Bread",
                "Mix the softened butter with crushed garlic. Spread on the bread slices. Grill for five minutes until golden.",
                new String[][]{
                        {"bread", "4", "slices"},
                        {"garlic", "2", "cloves"},
                        {"butter", "2", "tbsp"}
                });

        addRecipe(db, "Tomato Salad",
                "Slice the tomato and onion thinly. Toss together with olive oil. Season and serve cold.",
                new String[][]{
                        {"tomato", "2", "pieces"},
                        {"onion", "1", "piece"},
                        {"olive oil", "1", "tbsp"}
                });

        addRecipe(db, "Scrambled Eggs",
                "Beat the eggs with the milk. Melt butter in a pan over low heat. Pour in the eggs and stir gently until just set.",
                new String[][]{
                        {"egg", "3", "pieces"},
                        {"milk", "30", "ml"},
                        {"butter", "1", "tbsp"}
                });

        addRecipe(db, "Cheese Omelette",
                "Beat the eggs. Melt butter in a pan. Pour in the eggs, scatter the grated cheese over one half, and fold when set.",
                new String[][]{
                        {"egg", "3", "pieces"},
                        {"cheese", "50", "g"},
                        {"butter", "1", "tbsp"}
                });

        addRecipe(db, "French Toast",
                "Beat the eggs with the milk. Soak each slice of bread. Fry in butter until golden on both sides.",
                new String[][]{
                        {"bread", "2", "slices"},
                        {"egg", "2", "pieces"},
                        {"milk", "50", "ml"},
                        {"butter", "1", "tbsp"}
                });

        addRecipe(db, "Pancakes",
                "Whisk the flour, eggs and milk into a smooth batter. Rest for ten minutes. Fry spoonfuls in butter until bubbles form, then flip.",
                new String[][]{
                        {"flour", "200", "g"},
                        {"egg", "2", "pieces"},
                        {"milk", "300", "ml"},
                        {"butter", "1", "tbsp"}
                });

        addRecipe(db, "Rice and Beans",
                "Boil the rice. Fry the chopped onion and garlic until soft. Add the drained beans and warm through. Serve over the rice.",
                new String[][]{
                        {"rice", "200", "g"},
                        {"beans", "1", "tin"},
                        {"onion", "1", "piece"},
                        {"garlic", "2", "cloves"}
                });

        addRecipe(db, "Vegetable Stir Fry",
                "Boil the rice. Slice the carrot and onion. Fry quickly in hot olive oil for four minutes. Serve over the rice.",
                new String[][]{
                        {"rice", "200", "g"},
                        {"carrot", "2", "pieces"},
                        {"onion", "1", "piece"},
                        {"olive oil", "2", "tbsp"}
                });

        addRecipe(db, "Potato Soup",
                "Peel and cube the potatoes. Fry the chopped onion in butter. Add the potatoes and water, simmer until soft, then stir in the milk and blend.",
                new String[][]{
                        {"potato", "4", "pieces"},
                        {"onion", "1", "piece"},
                        {"butter", "1", "tbsp"},
                        {"milk", "200", "ml"}
                });

        addRecipe(db, "Mashed Potatoes",
                "Boil the peeled potatoes until soft. Drain well. Mash with the butter and milk until smooth.",
                new String[][]{
                        {"potato", "4", "pieces"},
                        {"butter", "2", "tbsp"},
                        {"milk", "100", "ml"}
                });

        addRecipe(db, "Chicken Stir Fry",
                "Slice the chicken, onion and carrot. Fry the chicken in olive oil until cooked through, then add the vegetables for four minutes.",
                new String[][]{
                        {"chicken", "2", "pieces"},
                        {"onion", "1", "piece"},
                        {"carrot", "1", "piece"},
                        {"olive oil", "2", "tbsp"}
                });

        addRecipe(db, "Tuna Pasta",
                "Boil the pasta. Fry the chopped onion in olive oil. Stir in the drained tuna, then fold through the pasta.",
                new String[][]{
                        {"pasta", "1", "pack"},
                        {"tuna", "1", "tin"},
                        {"onion", "1", "piece"},
                        {"olive oil", "1", "tbsp"}
                });

        addRecipe(db, "Cheese Toastie",
                "Butter the outside of both slices of bread. Fill with grated cheese. Fry in a pan until golden on both sides.",
                new String[][]{
                        {"bread", "2", "slices"},
                        {"cheese", "60", "g"},
                        {"butter", "1", "tbsp"}
                });

        addRecipe(db, "Tomato Soup",
                "Fry the chopped onion and garlic in olive oil. Add the chopped tomatoes and simmer for twenty minutes. Blend until smooth.",
                new String[][]{
                        {"tomato", "6", "pieces"},
                        {"onion", "1", "piece"},
                        {"garlic", "2", "cloves"},
                        {"olive oil", "1", "tbsp"}
                });

        addRecipe(db, "Fried Rice",
                "Cook and cool the rice. Scramble the eggs and set aside. Fry the chopped onion in olive oil, add the rice, then stir the egg back in.",
                new String[][]{
                        {"rice", "300", "g"},
                        {"egg", "2", "pieces"},
                        {"onion", "1", "piece"},
                        {"olive oil", "2", "tbsp"}
                });

        addRecipe(db, "Banana Smoothie",
                "Peel the bananas. Blend with the milk and yoghurt until smooth. Serve chilled.",
                new String[][]{
                        {"banana", "2", "pieces"},
                        {"milk", "250", "ml"},
                        {"yoghurt", "100", "g"}
                });

        addRecipe(db, "Porridge",
                "Warm the oats and milk in a pot, stirring for five minutes until thick. Top with sliced banana.",
                new String[][]{
                        {"oats", "80", "g"},
                        {"milk", "250", "ml"},
                        {"banana", "1", "piece"}
                });

        addRecipe(db, "Spaghetti Bolognese",
                "Boil the pasta. Brown the mince with the chopped onion and garlic. Add the chopped tomato and simmer for twenty minutes. Serve over the pasta.",
                new String[][]{
                        {"pasta", "1", "pack"},
                        {"mince", "500", "g"},
                        {"tomato", "4", "pieces"},
                        {"onion", "1", "piece"},
                        {"garlic", "2", "cloves"}
                });

        addRecipe(db, "Greek Salad",
                "Chop the tomato and cucumber into chunks. Crumble the feta over the top. Dress with olive oil.",
                new String[][]{
                        {"tomato", "3", "pieces"},
                        {"cucumber", "1", "piece"},
                        {"feta", "100", "g"},
                        {"olive oil", "2", "tbsp"}
                });
    }



    public List<PantryItem> getAllPantryItems() {
        List<PantryItem> items = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();

        Cursor cursor = db.query(TABLE_PANTRY, null, null, null, null, null, COL_PANTRY_NAME);
        while (cursor.moveToNext()) {
            int id = cursor.getInt(cursor.getColumnIndexOrThrow(COL_PANTRY_ID));
            String name = cursor.getString(cursor.getColumnIndexOrThrow(COL_PANTRY_NAME));
            double quantity = cursor.getDouble(cursor.getColumnIndexOrThrow(COL_PANTRY_QUANTITY));
            String unit = cursor.getString(cursor.getColumnIndexOrThrow(COL_PANTRY_UNIT));
            String expiry = cursor.getString(cursor.getColumnIndexOrThrow(COL_PANTRY_EXPIRY));


            items.add(new PantryItem(id, name, quantity, unit, expiry));
        }
        cursor.close();
        return items;

    }

    public int updatePantryItem(PantryItem item) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(COL_PANTRY_NAME, item.getName());
        values.put(COL_PANTRY_QUANTITY, item.getQuantity());
        values.put(COL_PANTRY_UNIT, item.getUnit());
        values.put(COL_PANTRY_EXPIRY, item.getExpiryDate());

        return db.update(TABLE_PANTRY, values, COL_PANTRY_ID + " = ?",
                new String[]{String.valueOf(item.getId())});

    }

    public int deletePantryItem(int id) {
        SQLiteDatabase db = getWritableDatabase();

        return db.delete(TABLE_PANTRY, COL_PANTRY_ID + " = ? ",
                new String[]{String.valueOf(id)});


    }

    public PantryItem getPantryItemById(int id) {
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.query(TABLE_PANTRY, null,
                COL_PANTRY_ID + " = ?", new String[]{ String.valueOf(id) },
                null, null, null
        );
        PantryItem item = null;
        if (cursor.moveToFirst()) {

            String name =
                    cursor.getString(cursor.getColumnIndexOrThrow(COL_PANTRY_NAME));
            double quantity =
                    cursor.getDouble(cursor.getColumnIndexOrThrow(COL_PANTRY_QUANTITY));
            String unit =
                    cursor.getString(cursor.getColumnIndexOrThrow(COL_PANTRY_UNIT));
            String expiry =
                    cursor.getString(cursor.getColumnIndexOrThrow(COL_PANTRY_EXPIRY));
            item = new PantryItem(id, name, quantity, unit, expiry);
        }
        cursor.close();
        return item;
    }
    public List<Recipe> getAllRecipes(){
        List<Recipe> recipes = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.query(TABLE_RECIPES, null, null, null, null, null, COL_RECIPE_NAME);

        while (cursor.moveToNext()){
            int id = cursor.getInt(cursor.getColumnIndexOrThrow(COL_RECIPE_ID));
            String name = cursor.getString(cursor.getColumnIndexOrThrow(COL_RECIPE_NAME));
            String steps = cursor.getString(cursor.getColumnIndexOrThrow(COL_RECIPE_STEPS));
                recipes.add(new Recipe(id, name, steps));
        }
        cursor.close();
        return recipes;
    }
    public List<RecipeIngredient> getIngredientsForRecipe(int recipeId){
        List<RecipeIngredient> ingredients = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();

        Cursor cursor = db.query(TABLE_RECIPE_INGREDIENTS, null, COL_INGREDIENT_RECIPE_ID + " = ?", new String[]{
                String.valueOf(recipeId)},null, null, null);


        while(cursor.moveToNext()){
            int id = cursor.getInt(cursor.getColumnIndexOrThrow(COL_INGREDIENT_ID));
            String name = cursor.getString(cursor.getColumnIndexOrThrow(COL_INGREDIENT_NAME));
            double quantity = cursor.getDouble(cursor.getColumnIndexOrThrow(COL_INGREDIENT_QUANTITY));
            String unit = cursor.getString(cursor.getColumnIndexOrThrow(COL_INGREDIENT_UNIT));
                ingredients.add(new RecipeIngredient(id, recipeId, name , quantity, unit));

        }
        cursor.close();
        return ingredients;
    }


}
























































