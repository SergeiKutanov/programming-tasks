package com.sergeik.design;

import java.util.PriorityQueue;
import java.util.Queue;

/**
 * Design a system that manages the reservation state of n seats that are numbered from 1 to n.
 *
 * Implement the SeatManager class:
 *
 * SeatManager(int n) Initializes a SeatManager object that will manage n seats numbered from 1 to n.
 * All seats are initially available.
 * int reserve() Fetches the smallest-numbered unreserved seat, reserves it, and returns its number.
 * void unreserve(int seatNumber) Unreserves the seat with the given seatNumber.
 */
public class SeatReservationManager {

    public static void main(String[] args) {
        SeatManager seatManager = new SeatManager(5); // Initializes a SeatManager with 5 seats.
        assert 1 == seatManager.reserve();    // All seats are available, so return the lowest numbered seat, which is 1.
        assert 2 == seatManager.reserve();    // The available seats are [2,3,4,5], so return the lowest of them, which is 2.
        seatManager.unreserve(2); // Unreserve seat 2, so now the available seats are [2,3,4,5].
        assert 2 == seatManager.reserve();    // The available seats are [2,3,4,5], so return the lowest of them, which is 2.
        assert 3 == seatManager.reserve();    // The available seats are [3,4,5], so return the lowest of them, which is 3.
        assert 4 == seatManager.reserve();    // The available seats are [4,5], so return the lowest of them, which is 4.
        assert 5 == seatManager.reserve();    // The only available seat is seat 5, so return 5.
        seatManager.unreserve(5); // Unreserve seat 5, so now the available seats are [5].
    }

    static class SeatManager {

        private Queue<Integer> pQueue;
        private boolean[] seats;

        public SeatManager(int n) {
            pQueue = new PriorityQueue<>();
            seats = new boolean[n + 1];
            seats[0] = true;
            for (int i = 1; i < seats.length; i++)
                pQueue.offer(i);
        }

        public int reserve() {
            int seatNumber = -1;
            if (!pQueue.isEmpty()) {
                seatNumber = pQueue.poll();
                seats[seatNumber] = true;
            }
            return seatNumber;
        }

        public void unreserve(int seatNumber) {
            if (seatNumber >= 0 && seatNumber < seats.length && seats[seatNumber]) {
                seats[seatNumber] = false;
                pQueue.offer(seatNumber);
            }
        }
    }


}
