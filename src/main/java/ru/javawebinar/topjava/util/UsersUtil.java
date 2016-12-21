package ru.javawebinar.topjava.util;

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.Role;
import ru.javawebinar.topjava.model.User;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Semen on 16.12.2016.
 */
public class UsersUtil {
    public static final List<User> USERS = Arrays.asList(
            new User(1, "Semen", "semen@mail.com", "12345", Role.ROLE_ADMIN, Role.ROLE_USER),
            new User(2, "Andrey", "andrey@inbox.ru", "654321", Role.ROLE_USER)
    );
}
