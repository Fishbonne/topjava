package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.javawebinar.topjava.model.MealWithExceed;
import ru.javawebinar.topjava.util.MealsUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalTime;
import java.util.List;

import static org.slf4j.LoggerFactory.*;

/**
 * Created by Semen on 09.12.2016.
 */
public class MealServlet extends HttpServlet {
    private static final Logger LOG = getLogger(MealServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LOG.debug("generating list and redirecting to meals.jsp");
        List<MealWithExceed> list = MealsUtil.getFilteredWithExceededByCycle(MealsUtil.getMealList(), LocalTime.of(0, 0), LocalTime.of(23, 59), 2000);
        req.setCharacterEncoding("UTF-8");
        req.setAttribute("list", list);
        req.getRequestDispatcher("meals.jsp").forward(req,resp);
    }
}
