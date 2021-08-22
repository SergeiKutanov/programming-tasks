package com.sergeik.math;

/**
 * Given two numbers, hour and minutes. Return the smaller angle (in degrees) formed between the hour and the
 * minute hand.
 */
public class AngleBetweenHandsOfClock {

    public static void main(String[] args) {
        assert 76.5 == solution(1, 57);
        assert 7.5 == solution(3,15);
        assert 75 == solution(3, 30);
        assert 165 == solution(12,30);
    }

    private static double solution(int hour, int minutes) {
        double minuteStart = 360.0 / 60 * minutes;
        double minuteOffset = 30.0 / 60 * minutes;
        double hourStart = 360.0 / 12 * (hour == 12 ? 0 : hour) + minuteOffset;
        double ang1 = Math.min(360 - minuteStart + hourStart, 360 - hourStart + minuteStart);
        double ang2 = Math.max(hourStart, minuteStart) - Math.min(hourStart, minuteStart);
        return Math.min(ang1, ang2);
    }

}
