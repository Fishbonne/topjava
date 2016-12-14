package ru.javawebinar.topjava.dao;

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.util.MealsUtil;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Created by Semen on 10.12.2016.
 */
public class MealDAOImpl implements MealDAO {
    private static List<Meal> database;

    static {
        database = new CopyOnWriteArrayList<>(MealsUtil.getMealList());
    }

    @Override
    public void addMeal(Meal meal) {
        database.add(meal);
    }

    @Override
    public void removeMeal(int id) {
        database.removeIf(meal -> meal.getId() == id);
    }

    @Override
    public void updateMeal(Meal meal) {
        for (int i = 0; i < database.size(); i++) {
            if (database.get(i).getId() == meal.getId()) database.set(i, meal);
        }
    }

    @Override
    public Meal getMealById(int id) {
        Meal meal = null;
        for (Meal m : database) {
            if (m.getId() == id) meal = m;
        }
        return meal;
    }

    @Override
    public List<Meal> getMealList() {
        return database;
    }
}
