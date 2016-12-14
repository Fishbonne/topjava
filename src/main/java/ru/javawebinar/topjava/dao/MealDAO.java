package ru.javawebinar.topjava.dao;

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.MealWithExceed;

import java.util.List;

/**
 * Created by Semen on 10.12.2016.
 */
public interface MealDAO {
    void addMeal(Meal meal);

    void removeMeal(int id);

    void updateMeal(Meal meal);

    Meal getMealById(int id);

    List<Meal> getMealList();
}
