package com.common;

public class MeasureTime {

    public static void measure(Action action, int times){
        long a = System.currentTimeMillis();
        for (int i = 0; i < times; i++) {
            action.actionToPerform();
        }
        System.out.println((System.currentTimeMillis() - a) + "ms");
    }

    public interface Action {
        void actionToPerform();
    }
}
