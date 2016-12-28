package ru.javawebinar.topjava.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.util.DbPopulator;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import java.io.FileNotFoundException;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static ru.javawebinar.topjava.MealTestData.MATCHER;
import static ru.javawebinar.topjava.MealTestData.init;
import static ru.javawebinar.topjava.MealTestData.MEALS;
import static ru.javawebinar.topjava.UserTestData.*;
import static ru.javawebinar.topjava.model.BaseEntity.*;

/**
 * Created by Semen on 28.12.2016.
 */

@ContextConfiguration({
        "classpath:spring/spring-app.xml",
        "classpath:spring/spring-db.xml"
})
@RunWith(SpringJUnit4ClassRunner.class)
public class MealServiceTest {
    @Autowired
    private MealService service;

    @Autowired
    private DbPopulator dbPopulator;


    @Before
    public void setUp() throws Exception {
        dbPopulator.execute();
        init();
    }

    @Test
    public void get() throws Exception {
        Meal meal = service.get(START_SEQ + 2, USER_ID);
        MATCHER.assertEquals(MEALS.get(USER_ID).get(START_SEQ + 2), meal);
    }

    @Test
    public void delete() throws Exception {
        service.delete(START_SEQ + 2, USER_ID);
        MEALS.get(USER_ID).remove(START_SEQ + 2);
        MATCHER.assertCollectionEquals(MEALS.get(USER_ID)
                        .values()
                        .stream()
                        .sorted((m1, m2) -> m2.getDateTime().compareTo(m1.getDateTime())).collect(Collectors.toList())
                , service.getAll(USER_ID));
    }

    @Test
    public void getBetweenDateTimes() throws Exception {
        Collection<Meal> meals = service.getBetweenDateTimes(LocalDateTime.of(2015, Month.MAY, 30, 9, 0), LocalDateTime.of(2015, Month.MAY, 30, 12, 0), USER_ID);
        MATCHER.assertEquals(MEALS.get(USER_ID).get(START_SEQ + 2), meals.stream().findFirst().orElse(null));
    }

    @Test
    public void getAll() throws Exception {
        Collection<Meal> meals = service.getAll(USER_ID);
        MATCHER.assertCollectionEquals(MEALS.get(USER_ID)
                .values()
                .stream()
                .sorted((m1, m2) -> m2.getDateTime().compareTo(m1.getDateTime())).collect(Collectors.toList()), meals);
    }

    @Test
    public void update() throws Exception {
        Meal meal = MEALS.get(USER_ID).get(START_SEQ + 2);
        meal.setDescription("Test update");
        service.update(meal, USER_ID);
        MATCHER.assertEquals(meal, service.get(meal.getId(), USER_ID));
    }

    @Test
    public void save() throws Exception {
        Meal meal = new Meal(LocalDateTime.of(2015, Month.MAY, 30, 23, 0), "Test save meal", 300);
        Map<Integer, Meal> userMeals = MEALS.get(USER_ID);
        userMeals.put(START_SEQ + 8, meal);
        service.save(meal, USER_ID);
        MATCHER.assertEquals(meal, service.get(meal.getId(), USER_ID));
    }

    @Test(expected = NotFoundException.class)
    public void testUpdateNotFound() throws Exception {
        Meal meal = MEALS.get(USER_ID).get(START_SEQ + 2);
        service.update(meal, ADMIN_ID);
    }
}