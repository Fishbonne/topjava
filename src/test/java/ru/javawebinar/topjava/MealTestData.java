package ru.javawebinar.topjava;

import ru.javawebinar.topjava.matcher.ModelMatcher;
import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import static ru.javawebinar.topjava.model.BaseEntity.START_SEQ;

/**
 * GKislin
 * 13.03.2015.
 */
public class MealTestData {


    public static final ModelMatcher<Meal> MATCHER = new ModelMatcher<>((expected, actual) -> expected == actual || Objects.equals(expected.toString(), actual.toString()));

    public static final Map<Integer, Map<Integer, Meal>> MEALS = new HashMap<>();

    public static void init() {
        Map<Integer, Meal> userMeal = new HashMap<>();
        userMeal.put(START_SEQ + 2, new Meal(START_SEQ + 2, LocalDateTime.of(2015, Month.MAY, 30, 10, 0), "Завтрак", 500));
        userMeal.put(START_SEQ + 3, new Meal(START_SEQ + 3, LocalDateTime.of(2015, Month.MAY, 30, 13, 0), "Обед", 1000));
        userMeal.put(START_SEQ + 4, new Meal(START_SEQ + 4, LocalDateTime.of(2015, Month.MAY, 30, 20, 0), "Ужин", 500));
        Map<Integer, Meal> adminMeal = new HashMap<>();
        adminMeal.put(START_SEQ + 5, new Meal(START_SEQ + 5, LocalDateTime.of(2015, Month.MAY, 31, 10, 0), "Завтрак", 1000));
        adminMeal.put(START_SEQ + 6, new Meal(START_SEQ + 6, LocalDateTime.of(2015, Month.MAY, 31, 13, 0), "Обед", 500));
        adminMeal.put(START_SEQ + 7, new Meal(START_SEQ + 7, LocalDateTime.of(2015, Month.MAY, 31, 20, 0), "Ужин", 510));
        MEALS.put(UserTestData.USER_ID, userMeal);
        MEALS.put(UserTestData.ADMIN_ID, adminMeal);
    }
}
