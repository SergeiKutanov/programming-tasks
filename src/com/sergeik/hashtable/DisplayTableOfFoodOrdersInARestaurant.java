package com.sergeik.hashtable;

import java.util.*;

/**
 * Given the array orders, which represents the orders that customers have done in a restaurant. More specifically
 * orders[i]=[customerNamei,tableNumberi,foodItemi] where customerNamei is the name of the customer, tableNumberi
 * is the table customer sit at, and foodItemi is the item customer orders.
 *
 * Return the restaurant's “display table”. The “display table” is a table whose row entries denote how many of
 * each food item each table ordered. The first column is the table number and the remaining columns correspond
 * to each food item in alphabetical order. The first row should be a header whose first column is “Table”,
 * followed by the names of the food items. Note that the customer names are not part of the table. Additionally,
 * the rows should be sorted in numerically increasing order.
 */
public class DisplayTableOfFoodOrdersInARestaurant {

    public static void main(String[] args) {
        List<List<String>> orders = new LinkedList<>();
        orders.add(Arrays.asList("David","3","Ceviche"));
        orders.add(Arrays.asList("Corina","10","Beef Burrito"));
        orders.add(Arrays.asList("David","3","Fried Chicken"));
        orders.add(Arrays.asList("Carla","5","Water"));
        orders.add(Arrays.asList("Carla","5","Ceviche"));
        orders.add(Arrays.asList("Rous","3","Ceviche"));

        List<List<String>> res = displayTable(orders);
        List<List<String>> exp = new LinkedList<>();
        exp.add(Arrays.asList("Table","Beef Burrito","Ceviche","Fried Chicken","Water"));
        exp.add(Arrays.asList("3","0","2","1","0"));
        exp.add(Arrays.asList("5","0","1","0","1"));
        exp.add(Arrays.asList("10","1","0","0","0"));

        for (int i = 0; i < exp.size(); i++)
            for (int j = 0; j < exp.get(i).size(); j++)
                assert exp.get(i).get(j).equals(res.get(i).get(j));
    }

    private static List<List<String>> displayTable(List<List<String>> orders) {
        List<List<String>> res = new LinkedList<>();
        TreeSet<String> items = new TreeSet<>();
        TreeMap<Integer, Map<String, Integer>> map = new TreeMap<>();

        for (List<String> order: orders) {
            int table = Integer.valueOf(order.get(1));
            String dish = order.get(2);
            items.add(dish);
            if (!map.containsKey(table))
                map.put(table, new HashMap<>());
            Map<String, Integer> record = map.get(table);
            record.put(dish, record.getOrDefault(dish, 0) + 1);
        }

        List<String> header = new LinkedList<>();
        header.add("Table");
        for (String item: items) {
            header.add(item);
        }
        res.add(header);

        for (int row: map.keySet()) {
            Map<String, Integer> record = map.get(row);
            List<String> recordRow = new LinkedList<>();
            recordRow.add(String.valueOf(row));
            for (String item: items) {
                recordRow.add(String.valueOf(record.getOrDefault(item, 0)));
            }
            res.add(recordRow);
        }

        return res;

    }

}
