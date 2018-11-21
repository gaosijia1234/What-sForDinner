package com.example.sijiagao.whatsfordinner.model.ingredient;

public class IngredientUnit {
    private String unitName;
    private double quantity;

    public IngredientUnit(String unitName, double quantity) {
        this.unitName = unitName;
        this.quantity = quantity;
    }

    public String getUnitName() {
        return unitName;
    }

    public void setUnitName(String unitName) {
        this.unitName = unitName;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "IngredientUnit{" +
                "unitName='" + unitName + '\'' +
                ", quantity=" + quantity +
                '}';
    }
}
