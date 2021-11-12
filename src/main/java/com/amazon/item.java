package com.amazon;

import java.util.Random;

public class item {
    private String Name;
    private double Price;
    private int Quantity;
    private int ItemID;

    public item(String name, double price){
        Name = name;
        Price = price;
    }

    public item(String name, double price, int quantity, int itemID){
        Name = name;
        Price = price;
        Quantity = quantity;
        ItemID = itemID;
    }
}
