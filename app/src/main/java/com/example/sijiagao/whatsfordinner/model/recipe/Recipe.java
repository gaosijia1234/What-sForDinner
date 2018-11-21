package com.example.sijiagao.whatsfordinner.model.recipe;

import com.example.sijiagao.whatsfordinner.model.ingredient.Ingredient;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Recipe implements Serializable{
    private static final long serialVersionUID = -1213949467658913456L;

    private String recipeName;
    private List<Ingredient> ingredients;
    private String cookingDirections;
    private String imagePath;

    public Recipe() {};

    public Recipe(String theName, List<Ingredient> theIngredients, String theDirections, String thePath){
        this.recipeName = theName;
        this.ingredients = theIngredients;
        this.cookingDirections = theDirections;
        this.imagePath = thePath;
    }

    public String getRecipeName() {
        return recipeName;
    }

    public void setRecipeName(String recipeName) {
        this.recipeName = recipeName;
    }

    public List<Ingredient> getIngredients() {
        return ingredients;
    }


    public void setIngredients(List<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    public String getCookingDirections() {
        return cookingDirections;
    }

    public void setCookingDirections(String cookingDirections) {
        this.cookingDirections = cookingDirections;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    @Override
    public String toString() {
        return "Recipe{" +
                "recipeName='" + recipeName + '\'' +
                ", ingredients=" + ingredients +
                ", cookingDirections='" + cookingDirections + '\'' +
                ", imagePath='" + imagePath + '\'' +
                '}';
    }
}
