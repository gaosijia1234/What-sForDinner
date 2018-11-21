package com.example.sijiagao.whatsfordinner.model.mealPlan;

import com.example.sijiagao.whatsfordinner.model.mealPlan.DailyMealPlan;

import java.util.List;

public class WeeklyMealPlan {
    private List<DailyMealPlan> weeklyMealPlan;

    public WeeklyMealPlan(List<DailyMealPlan> weeklyMealPlan) {
        this.weeklyMealPlan = weeklyMealPlan;
    }

    public List<DailyMealPlan> getWeeklyMealPlan() {
        return weeklyMealPlan;
    }

    public void setWeeklyMealPlan(List<DailyMealPlan> weeklyMealPlan) {
        this.weeklyMealPlan = weeklyMealPlan;
    }

    @Override
    public String toString() {
        return "WeeklyMealPlan{" +
                "weeklyMealPlan=" + weeklyMealPlan +
                '}';
    }
}
