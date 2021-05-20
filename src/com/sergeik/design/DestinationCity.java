package com.sergeik.design;

import java.util.*;

/**
 * You are given the array paths, where paths[i] = [cityAi, cityBi] means there exists a direct path going
 * from cityAi to cityBi. Return the destination city, that is, the city without any path outgoing to another city.
 *
 * It is guaranteed that the graph of paths forms a line without any loop, therefore, there will be exactly
 * one destination city.
 */
public class DestinationCity {

    public static void main(String[] args) {
        Solution solution = new Solution();
        List<List<String>> paths = new LinkedList<>();
        paths.add(Arrays.asList("London", "New York"));
        paths.add(Arrays.asList("New York", "Lima"));
        paths.add(Arrays.asList("Lima", "Sao Paolo"));
        assert "Sao Paolo".equals(solution.destCity(paths));
    }

    static class Solution {
        public String destCity(List<List<String>> paths) {
            if (paths == null || paths.size() == 0)
                return null;
            Map<String, String> map = new HashMap<>();
            for (List<String> path: paths)
                map.put(path.get(0), path.get(1));
            String startCity = paths.get(0).get(0);
            while (map.containsKey(startCity)) {
                startCity = map.get(startCity);
            }
            return startCity;
        }
    }

}
