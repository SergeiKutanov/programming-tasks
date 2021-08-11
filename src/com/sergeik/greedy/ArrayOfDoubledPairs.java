package com.sergeik.greedy;

import java.util.*;

public class ArrayOfDoubledPairs {

    public static void main(String[] args) {
        assert !solution(new int[] {0,0,0,1});
        assert !solution(new int[] {-5,-2});
        assert solution(new int[] {4,-2,2,-4});
        assert !solution(new int[] {1,2,4,16,8,4});
    }

    private static boolean solution(int[] arr) {
        Arrays.sort(arr);
        Map<Integer, Integer> map = new HashMap<>();
        for (int n: arr)
            map.put(n, map.getOrDefault(n, 0) + 1);

        for (int i = 0; i < arr.length; i++) {
            if (!map.containsKey(arr[i]))
                continue;
            int product;
            if (arr[i] < 0) {
                if (arr[i] % 2 != 0)
                    return false;
                product = arr[i] / 2;
            }
            else
                product = arr[i] * 2;

            if (map.get(arr[i]) == 1)
                map.remove(arr[i]);
            else
                map.put(arr[i], map.get(arr[i]) - 1);
            if (!map.containsKey(product))
                return false;
            if (map.get(product) == 1)
                map.remove(product);
            else
                map.put(product, map.get(product) - 1);
        }
        return true;
    }

}
