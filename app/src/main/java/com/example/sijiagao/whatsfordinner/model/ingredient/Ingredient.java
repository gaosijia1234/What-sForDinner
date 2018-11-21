package com.example.sijiagao.whatsfordinner.model.ingredient;

public class Ingredient {
    private String ingredientName;
    private IngredientUnit unit;

    public Ingredient(String ingredientName, IngredientUnit unit) {
        this.ingredientName = ingredientName;
        this.unit = unit;
    }

    public String getIngredientName() {
        return ingredientName;
    }

    public void setIngredientName(String ingredientName) {
        this.ingredientName = ingredientName;
    }

    public IngredientUnit getUnit() {
        return unit;
    }

    public void setUnit(IngredientUnit unit) {
        this.unit = unit;
    }

    @Override
    public String toString() {
        return "Ingredient{" +
                "ingredientName='" + ingredientName + '\'' +
                ", unit=" + unit +
                '}';
    }
}
