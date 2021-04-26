package com.sergeik.utils;

import java.util.concurrent.Callable;

public class Timer {

    private static long startTime;

    public static void start() {
        startTime = System.currentTimeMillis();
    }

    public static long end() {
        return System.currentTimeMillis() - startTime;
    }

    public static <V extends Object> V runWithTimer(String label, Callable<V> callable) {
        start();
        V result = null;
        try {
            result = callable.call();
        } catch (Exception e) {
            e.printStackTrace();
        }
        long timeTook = end();
        System.out.println(label + " running time: " + timeTook);
        return result;
    }

}
