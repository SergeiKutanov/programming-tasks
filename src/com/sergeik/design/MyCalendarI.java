package com.sergeik.design;

import java.util.TreeMap;

/**
 * Implement a MyCalendar class to store your events. A new event can be added if adding the event
 * will not cause a double booking.
 *
 * Your class will have the method, book(int start, int end). Formally, this represents a booking on the
 * half open interval [start, end), the range of real numbers x such that start <= x < end.
 *
 * A double booking happens when two events have some non-empty intersection (ie., there is some time that
 * is common to both events.)
 *
 * For each call to the method MyCalendar.book, return true if the event can be added to the calendar
 * successfully without causing a double booking. Otherwise, return false and do not add the event to the calendar.
 *
 * Your class will be called like this: MyCalendar cal = new MyCalendar(); MyCalendar.book(start, end)
 */
public class MyCalendarI {

    public static void main(String[] args) {

        MyCalendar calendar;

        calendar = new MyCalendar();
        assert calendar.book(47, 50);
        assert calendar.book(33, 41);
        assert !calendar.book(39, 45);
        assert !calendar.book(33, 42);
        assert calendar.book(25, 32);
        assert !calendar.book(26, 35);

        calendar = new MyCalendar();
        assert calendar.book(10, 20); // returns true
        assert !calendar.book(15, 25); // returns false
        assert calendar.book(20, 30); // returns true
    }

    static class MyCalendar {

        TreeMap<Integer, Integer> events;

        public MyCalendar() {
            events = new TreeMap<>();
        }

        public boolean book(int start, int end) {
            Integer prev = events.floorKey(start);
            Integer next = events.ceilingKey(start);
            if ((prev == null || events.get(prev) <= start) && (next == null || end <= next)) {
                events.put(start, end);
                return true;
            }
            return false;
        }
    }

}
