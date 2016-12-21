package ru.javawebinar.topjava;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.javawebinar.topjava.web.meal.MealRestController;

import java.util.Arrays;

/**
 * User: gkislin
 * Date: 05.08.2015
 *
 * @link http://caloriesmng.herokuapp.com/
 * @link https://github.com/JavaOPs/topjava
 */
public class Main {
    public static void main(String[] args) {
        System.out.format("Hello Topjava Enterprise!");
        ConfigurableApplicationContext context = new ClassPathXmlApplicationContext("spring/spring-app.xml");
//        System.out.println(Arrays.toString(context.getBeanDefinitionNames()));
        MealRestController controller = context.getBean(MealRestController.class);
        controller.delete(1);
        context.close();
    }
}
