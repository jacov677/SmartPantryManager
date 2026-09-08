package com.example.smartpantrymanager.model;

public class RecipeIngredient {
    private int id;
    private int recipeId;
    private String ingredientName;
    private double quantity;
    private String unit;


    public RecipeIngredient(int recipeId, String ingredientName,  double quantity, String unit){
        this(0,recipeId, ingredientName, quantity, unit);


    }

    public RecipeIngredient(int id, int recipeId, String recipeName, double quantity, String unit){
        this.id = id;
        this.recipeId = recipeId;
        this.ingredientName = (ingredientName == null) ?"": ingredientName ;
        this.quantity = quantity;
        this.unit = (unit == null) ?"" : unit ;



    }

    public int getId(){
        return id;
    }

    public int getRecipeId(){
        return recipeId;
    }
    public String getIngredientName(){
        return ingredientName;
    }
    public double getQuantity(){
        return quantity;
    }
    public String getUnit(){
        return unit;
    }


    public void setId(int id){
        this.id = id;
    }
    public void setRecipeId(int recipeId){
        this.recipeId = recipeId;
    }
    public void setIngredientName(String ingredientName){
        this.ingredientName = ingredientName;
    }
    public void setQuantity(double quantity){
        this.quantity = quantity;
    }

    public void setUnit(String unit){
        this.unit = unit;
    }




}

