package com.sergeik.arrays;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Given an array of non-negative integers arr, you are initially positioned at start index of the array.
 * When you are at index i, you can jump to i + arr[i] or i - arr[i], check if you can reach to any index with value 0.
 *
 * Notice that you can not jump outside of the array at any time.
 */
public class JumpGameIII {

    public static void main(String[] args) {
        assert !solution(new int[] {3,0,2,1,2}, 2);
        assert solution(new int[] {4,2,3,0,3,1,2}, 0);
        assert solution(new int[] {4,2,3,0,3,1,2}, 5);
    }

    private static boolean solution(int[] arr, int start) {
        boolean[] seen = new boolean[arr.length];
        Queue<Integer> queue = new LinkedList<>();

        queue.offer(start);
        seen[start] = true;
        while (!queue.isEmpty()) {
            int idx = queue.poll();
            if (arr[idx] == 0)
                return true;
            int ff = idx + arr[idx], rr = idx - arr[idx];
            if (ff >= 0 && ff < arr.length && !seen[ff]) {
                if (arr[ff] == 0)
                    return true;
                seen[ff] = true;
                queue.offer(ff);
            }
            if (rr >= 0 && rr < arr.length && !seen[rr]) {
                if (arr[rr] == 0)
                    return true;
                seen[rr] = true;
                queue.offer(rr);
            }
        }

        return false;
    }

}
