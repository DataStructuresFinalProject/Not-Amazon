package application;

import java.util.Random;

public class Item {
    private final String Name;
    private double Price;
    private int Quantity;
    private final int ItemID;

    /**
     * Default constructor for creating an item
     * Initializes the item with no quantity and a randomly generated ID
     * @param name Name of item
     * @param price Price of individual item
     */
    public Item(String name, double price){
        Random rand = new Random();
        Name = name;
        Price = price;
        Quantity = 0;
        ItemID = rand.nextInt(4);
    }

    /**
     * Alternate constructor for creating an item
     * Manually defines initial quantity and ItemID
     * @param name Name of item
     * @param price Price of individual item
     * @param quantity Initially stocked quantity of item
     * @param itemID Internal Item Identifier
     */
    public Item(String name, double price, int quantity, int itemID){
        Name = name;
        Price = price;
        Quantity = quantity;
        ItemID = itemID;
    }

    
    /** 
     * Method to check if the item is in stock
     * @return boolean True if the item quanitity is greater than 0
     */
    public boolean inStock() {
        return Quantity>0;
    }

    
    /** 
     * Adds items into stock
     * @param amount Amount of the item being restocked
     */
    public void restock(int amount) {
        Quantity += amount;
    }

    
    /** 
     * Purchases the item being called
     * When item is purchased, the price of the item is returned for invoicing and the available quantity is reduced.
     * @return double The price of the item being purchased.
     */
    public double purchase() {
        Quantity -= 1;
        return Price;
    }

    public String getName() {
        return Name;
    }

    public double getPrice() {
        return Price;
    }

    public int getAmount() {
        return Quantity;    
    }

    /** 
     * Returns formatted string of item information
     * @return String Item information
     */
    public String toString() {
        return Name + " has " + Quantity + " in stock and costs $" + Price;
    }
}
