package com.example.sijiagao.whatsfordinner.model.grocery;

import com.example.sijiagao.whatsfordinner.model.ingredient.IngredientUnit;

public class GroceryItem {
    private String itemName;
    private double itemQuantity;
    private IngredientUnit itemUnit;

    public GroceryItem(String itemName, double itemQuantity, IngredientUnit itemUnit) {
        this.itemName = itemName;
        this.itemQuantity = itemQuantity;
        this.itemUnit = itemUnit;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public double getItemQuantity() {
        return itemQuantity;
    }

    public void setItemQuantity(double itemQuantity) {
        this.itemQuantity = itemQuantity;
    }

    public IngredientUnit getItemUnit() {
        return itemUnit;
    }

    public void setItemUnit(IngredientUnit itemUnit) {
        this.itemUnit = itemUnit;
    }

    @Override
    public String toString() {
        return "GroceryItem{" +
                "itemName='" + itemName + '\'' +
                ", itemQuantity=" + itemQuantity +
                ", itemUnit=" + itemUnit +
                '}';
    }
}
