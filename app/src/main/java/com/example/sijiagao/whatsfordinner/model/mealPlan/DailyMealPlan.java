package com.example.sijiagao.whatsfordinner.model.mealPlan;

import java.util.List;

public class DailyMealPlan {
    private int weekday;
    private List<Meal> meals;

    public DailyMealPlan(int weekday, List<Meal> meals) {
        this.weekday = weekday;
        this.meals = meals;
    }

    public int getWeekday() {
        return weekday;
    }

    public void setWeekday(int weekday) {
        this.weekday = weekday;
    }

    public List<Meal> getMeals() {
        return meals;
    }

    public void setMeals(List<Meal> meals) {
        this.meals = meals;
    }

    @Override
    public String toString() {
        return "DailyMealPlan{" +
                "weekday=" + weekday +
                ", meals=" + meals +
                '}';
    }
}
