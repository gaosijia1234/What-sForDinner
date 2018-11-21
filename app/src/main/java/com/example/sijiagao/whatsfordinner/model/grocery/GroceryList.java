package com.example.sijiagao.whatsfordinner.model.grocery;

import com.example.sijiagao.whatsfordinner.model.grocery.GroceryItem;

import java.util.List;

public class GroceryList {
    private List<GroceryItem> groceryItemList;

    public GroceryList(List<GroceryItem> groceryItemList) {
        this.groceryItemList = groceryItemList;
    }

    public List<GroceryItem> getGroceryItemList() {
        return groceryItemList;
    }

    public void setGroceryItemList(List<GroceryItem> groceryItemList) {
        this.groceryItemList = groceryItemList;
    }

    @Override
    public String toString() {
        return "GroceryList{" +
                "groceryItemList=" + groceryItemList +
                '}';
    }
}
