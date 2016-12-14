package ru.javawebinar.topjava.dao;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by Semen on 12.12.2016.
 */
public class Counter {
    private static AtomicInteger inc;

    static {
        inc = new AtomicInteger(0);
    }

    public static int incrementAndGet() {
        return inc.incrementAndGet();
    }
}
