package com.sergeik.design;

import java.util.HashMap;
import java.util.Map;

/**
 * An underground railway system is keeping track of customer travel times between different stations. They are using
 * this data to calculate the average time it takes to travel from one station to another.
 *
 * Implement the UndergroundSystem class:
 *
 * void checkIn(int id, string stationName, int t)
 * A customer with a card ID equal to id, checks in at the station stationName at time t.
 * A customer can only be checked into one place at a time.
 * void checkOut(int id, string stationName, int t)
 * A customer with a card ID equal to id, checks out from the station stationName at time t.
 * double getAverageTime(string startStation, string endStation)
 * Returns the average time it takes to travel from startStation to endStation.
 * The average time is computed from all the previous traveling times from startStation to endStation that happened
 * directly, meaning a check in at startStation followed by a check out from endStation.
 * The time it takes to travel from startStation to endStation may be different from the time it takes to travel
 * from endStation to startStation.
 * There will be at least one customer that has traveled from startStation to endStation before getAverageTime
 * is called.
 * You may assume all calls to the checkIn and checkOut methods are consistent. If a customer checks in at time t1
 * then checks out at time t2, then t1 < t2. All events happen in chronological order.
 */
public class DesignUndergroundSystem {

    public static void main(String[] args) {
        UndergroundSystem undergroundSystem = new UndergroundSystem();
        undergroundSystem.checkIn(10, "Leyton", 3);
        undergroundSystem.checkOut(10, "Paradise", 8); // Customer 10 "Leyton" -> "Paradise" in 8-3 = 5
        assert 5 == undergroundSystem.getAverageTime("Leyton", "Paradise"); // return 5.00000, (5) / 1 = 5
        undergroundSystem.checkIn(5, "Leyton", 10);
        undergroundSystem.checkOut(5, "Paradise", 16); // Customer 5 "Leyton" -> "Paradise" in 16-10 = 6
        assert 5.5 == undergroundSystem.getAverageTime("Leyton", "Paradise"); // return 5.50000, (5 + 6) / 2 = 5.5
        undergroundSystem.checkIn(2, "Leyton", 21);
        undergroundSystem.checkOut(2, "Paradise", 30); // Customer 2 "Leyton" -> "Paradise" in 30-21 = 9
        assert Math.abs(6.66667 - undergroundSystem.getAverageTime("Leyton", "Paradise")) < 0.0001; // return 6.66667, (5 + 6 + 9) / 3 = 6.66667
    }

    static class UndergroundSystem {

        Map<Integer, Record> inTravel;
        Map<String, double[]> average;

        public UndergroundSystem() {
            inTravel = new HashMap<>();
            average = new HashMap<>();
        }

        private class Record {
            int time;
            String station;

            Record(int t, String s) {
                time = t;
                station = s;
            }

        }

        public void checkIn(int id, String stationName, int t) {
            Record record = new Record(t, stationName);
            inTravel.put(id, record);
        }

        public void checkOut(int id, String stationName, int t) {

            Record record = inTravel.get(id);
            int travelTime = t - record.time;
            String key = getKey(record.station, stationName);
            inTravel.remove(id);
            if (!average.containsKey(key)) {
                average.put(key, new double[2]);
            }
            double[] data = average.get(key);
            double newAve = (data[0] * data[1] + travelTime) / ++data[1];
            data[0] = newAve;
        }

        public double getAverageTime(String startStation, String endStation) {
            String key = getKey(startStation, endStation);
            return average.get(key)[0];
        }

        private String getKey(String startStation, String endStation) {
            return startStation + "_" + endStation;
        }
    }


}
