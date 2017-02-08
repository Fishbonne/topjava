package ru.javawebinar.topjava.web.meal;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.javawebinar.topjava.AuthorizedUser;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.to.MealWithExceed;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("ajax/meals")
public class MealAjaxController extends AbstractMealController {

    @PostMapping
    public void createOrUpdateMeal(@RequestParam("id") Integer id,
                                   @RequestParam("dateTime") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dateTime,
                                   @RequestParam("description") String description,
                                   @RequestParam("calories") int calories) {
        Meal meal = new Meal(id, dateTime, description, calories);
        if (id == null) {
            super.create(meal);
        }
        else {
            super.update(meal, AuthorizedUser.id());
        }
    }

    @Override
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<MealWithExceed> getAll() {
        return super.getAll();
    }

    @DeleteMapping("/{id}")
    @Override
    public void delete(@PathVariable("id") int id) {
        super.delete(id);
    }
}
