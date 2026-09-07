package com.example.smartpantrymanager.model;

public class PantryItem {
    private int id;
    private String name;
    private double quantity;
    private String unit;
    private String expiryDate;


    //constructor
     public PantryItem(String name, double quantity, String unit, String expiryDate){
         this(0, name, quantity, unit, expiryDate);
//        this.name = name;
//        this.quantity = quantity;
//        this.unit = unit;
//        this.expiryDate = expiryDate;
    }

    public PantryItem(int id,String name, double quantity, String unit, String expiryDate){
         this.id = id;
        this.name = (name  == null) ? "" : name;
        this.quantity = quantity;
        this.unit = (unit == null) ? "" : unit;
        this.expiryDate = (expiryDate == null) ? "" : expiryDate;

    }

    public String getName(){
        return name;
    }

    public String getUnit(){
         return unit;
    }

    public double getQuantity(){
        return quantity;
    }

    public String getExpiryDate(){
        return expiryDate;
    }

    public int getId(){
        return id;
    }

    public void setName(String name){
         this.name =  name;

    }
    public void setQuantity(double quantity){
        this.quantity =  quantity;

    }
    public void setExpiry(String expiryDate){
        this.expiryDate =  expiryDate;

    }
    public void setUnit(String unit){
        this.unit =  unit;

    }
    public void setId(int id){
        this.id =  id;

    }



}
