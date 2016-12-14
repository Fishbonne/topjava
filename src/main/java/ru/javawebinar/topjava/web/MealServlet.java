package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import ru.javawebinar.topjava.dao.MealDAO;
import ru.javawebinar.topjava.dao.MealDAOImpl;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.util.MealsUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.LocalTime;

import static org.slf4j.LoggerFactory.*;

/**
 * Created by Semen on 09.12.2016.
 */
public class MealServlet extends HttpServlet {
    private MealDAO dao = new MealDAOImpl();
    private static final Logger LOG = getLogger(MealServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LOG.debug("generating list and redirecting to meals.jsp");
        req.setCharacterEncoding("UTF-8");
        req.setAttribute("list", MealsUtil.getFilteredWithExceededByCycle(dao.getMealList(), LocalTime.MIN, LocalTime.MAX, 2000));
        req.getRequestDispatcher("meals.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LOG.debug("process POST request");
        req.setCharacterEncoding("UTF-8");
        String operation = req.getParameter("operation");
        int id = 0;
        int calories = 0;
        LocalDateTime date = null;
        if (!req.getParameter("id").equals("")) id = Integer.parseInt(req.getParameter("id"));
        if (!req.getParameter("calories").equals("")) calories = Integer.parseInt(req.getParameter("calories"));
        if (!req.getParameter("date").equals("")) date = LocalDateTime.parse(req.getParameter("date"));
        String description = req.getParameter("description");
        Meal meal;
        switch (operation) {
            case "create":
                LOG.debug("creating new meal");
                meal = new Meal(date, description, calories);
                dao.addMeal(meal);
                break;
            case "update":
                LOG.debug("updating new meal");
                meal = new Meal(id, date, description, calories);
                dao.updateMeal(meal);
                break;
            case "delete":
                LOG.debug("deleting meal");
                dao.removeMeal(id);
                break;
        }
        doGet(req, resp);
    }
}
