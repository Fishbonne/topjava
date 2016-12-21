package ru.javawebinar.topjava.web.meal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import ru.javawebinar.topjava.AuthorizedUser;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.service.MealService;

import java.util.Collection;

/**
 * GKislin
 * 06.03.2015.
 */
@Controller
public class MealRestController {
    private final Logger LOG = LoggerFactory.getLogger(getClass());

    @Autowired
    private MealService service;

    public Meal add(Meal meal) {
        meal.setId(null);
        LOG.info("add meal " + meal);
        return service.save(meal, AuthorizedUser.id());
    }

    public void update(Meal meal, int id) {
        meal.setId(id);
        LOG.info("update meal " + meal);
        service.update(meal, AuthorizedUser.id());
    }

    public void delete(int id) {
        LOG.info("delete meal with id " + id);
        service.delete(id, AuthorizedUser.id());
    }

    public Meal get(int id) {
        LOG.info("get meal with id " + id);
        return service.get(id, AuthorizedUser.id());
    }

    public Collection<Meal> getAll() {
        LOG.info("getAll meals");
        return service.getAll(AuthorizedUser.id());
    }
}
