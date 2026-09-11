package com.example.smartpantrymanager.data;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

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
                    COL_PANTRY_ID          + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COL_PANTRY_NAME        + " TEXT NOT NULL, " +
                    COL_PANTRY_QUANTITY    + " REAL NOT NULL, " +
                    COL_PANTRY_UNIT        + " TEXT, " +
                    COL_PANTRY_EXPIRY      + " TEXT" +
            ")";
    private static final String CREATE_TABLE_RECIPES =
            "CREATE TABLE " + TABLE_RECIPES + " (" +
                    COL_RECIPE_ID          + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COL_RECIPE_NAME        + " TEXT NOT NULL, " +
                    COL_RECIPE_STEPS    + " TEXT NOT NULL" +

                    ")";

    private static final String CREATE_TABLE_RECIPE_INGREDIENTS =
            "CREATE TABLE " + TABLE_RECIPE_INGREDIENTS + " (" +
                    COL_INGREDIENT_RECIPE_ID          + " INTEGER NOT NULL, " +
                    COL_INGREDIENT_ID        + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COL_INGREDIENT_NAME    + " TEXT NOT NULL, " +
                    COL_INGREDIENT_QUANTITY        + " REAL, " +
                    COL_INGREDIENT_UNIT      + " TEXT " +
                    ")";


    public DatabaseHelper(Context context){
        super(context, DATABASE_NAME,null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db){
        db.execSQL(CREATE_TABLE_PANTRY);
        db.execSQL(CREATE_TABLE_RECIPES);
        db.execSQL(CREATE_TABLE_RECIPE_INGREDIENTS);







    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVer, int newVer){
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PANTRY);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_RECIPES);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_RECIPE_INGREDIENTS);





    }



}
