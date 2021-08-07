package com.sergeik.design;

import java.util.*;

/**
 * A k-booking happens when k events have some non-empty intersection (i.e., there is some time that is common
 * to all k events.)
 *
 * You are given some events [start, end), after each given event, return an integer k representing the maximum
 * k-booking between all the previous events.
 *
 * Implement the MyCalendarThree class:
 *
 * MyCalendarThree() Initializes the object.
 * int book(int start, int end) Returns an integer k representing the largest integer such that there exists a
 * k-booking in the calendar.
 */
public class MyCalendarIII {

    public static void main(String[] args) {
        MyCalendarThree myCalendarThree;

        myCalendarThree = new MyCalendarThree();
        assert 1 == myCalendarThree.book(24,40);
        assert 1 == myCalendarThree.book(43,50);
        assert 2 == myCalendarThree.book(27,43);
        assert 2 == myCalendarThree.book(5,21);
        assert 3 == myCalendarThree.book(30,40);
        assert 3 == myCalendarThree.book(14,29);
        assert 3 == myCalendarThree.book(3,19);
        assert 3 == myCalendarThree.book(3, 14);
        assert 4 == myCalendarThree.book(25,39);
        assert 4 == myCalendarThree.book(6,19);


        myCalendarThree = new MyCalendarThree();
        assert 1 == myCalendarThree.book(10, 20); // return 1, The first event can be booked and is disjoint, so the maximum k-booking is a 1-booking.
        assert 1 == myCalendarThree.book(50, 60); // return 1, The second event can be booked and is disjoint, so the maximum k-booking is a 1-booking.
        assert 2 == myCalendarThree.book(10, 40); // return 2, The third event [10, 40) intersects the first event, and the maximum k-booking is a 2-booking.
        assert 3 == myCalendarThree.book(5, 15); // return 3, The remaining events cause the maximum K-booking to be only a 3-booking.
        assert 3 == myCalendarThree.book(5, 10); // return 3
        assert 3 == myCalendarThree.book(25, 55); // return 3
    }

    static class MyCalendarThree {

        TreeMap<Integer, Integer> map;
        int max;

        public MyCalendarThree() {
            map = new TreeMap<>();
            map.put(-1, 0);
            max = 0;
        }

        public int book(int start, int end) {
            map.put(start, map.floorEntry(start).getValue());
            map.put(end, map.floorEntry(end).getValue());
            Map<Integer, Integer> submap = map.subMap(start, end);
            for (Map.Entry<Integer, Integer> entry: submap.entrySet()) {
                int val = entry.getValue() + 1;
                entry.setValue(val);
                max = Math.max(val, max);
            }
            return max;
        }

    }


}
