package com.example.sijiagao.whatsfordinner.model.mealPlan;

import com.example.sijiagao.whatsfordinner.model.recipe.Recipe;

import java.util.List;

enum MealTime {
    BREAKFAST, LUNCH, DINNER;
}

public class Meal {
    private List<Recipe> mealRecipes;
    private MealTime time;

    public Meal(List<Recipe> mealReciepes, MealTime time) {
        this.mealRecipes = mealReciepes;
        this.time = time;
    }

    public List<Recipe> getMealReciepes() {
        return mealRecipes;
    }

    public void setMealReciepes(List<Recipe> mealReciepes) {
        this.mealRecipes = mealReciepes;
    }

    public MealTime getTime() {
        return time;
    }

    public void setTime(MealTime time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "Meal{" +
                "mealRecipes=" + mealRecipes +
                ", time=" + time +
                '}';
    }
}
