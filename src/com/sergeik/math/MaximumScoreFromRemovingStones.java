package com.sergeik.math;

import java.util.*;

/**
 * You are playing a solitaire game with three piles of stones of sizes a​​​​​​, b,​​​​​​ and c​​​​​​ respectively.
 * Each turn you choose two different non-empty piles, take one stone from each, and add 1 point to your score.
 * The game stops when there are fewer than two non-empty piles (meaning there are no more available moves).
 *
 * Given three integers a​​​​​, b,​​​​​ and c​​​​​, return the maximum score you can get.
 */
public class MaximumScoreFromRemovingStones {

    public static void main(String[] args) {
        assert 8 == solution(1, 8,8);
        assert 7 == solution(4,4,6);
        assert 6 == solution(2,4,6);

        assert 3 == mathSolution(6,2, 1);
        assert 8 == mathSolution(1, 8,8);
        assert 7 == mathSolution(4,4,6);
        assert 6 == mathSolution(2,4,6);

        assert 3 == recSolution(6,2, 1);
        assert 8 == recSolution(1, 8,8);
        assert 7 == recSolution(4,4,6);
        assert 6 == recSolution(2,4,6);
    }

    private static int recSolution(int a, int b, int c) {
        if (a < b) {
            return recSolution(b, a, c);
        }
        if (b < c)
            return recSolution(a, c, b);
        return b == 0 ? 0 : 1 + recSolution(a - 1, b - 1, c);
    }

    private static int mathSolution(int a, int b, int c) {
        int[] nums = new int[]{a,b,c};
        Arrays.sort(nums);

        int sum1 = nums[0] + nums[1];
        int sum2 = nums[2];

        int res = 0;
        if (sum1 >= sum2) {
            res = sum2 + (sum1 - sum2) / 2;
        } else {
            res = sum1;
        }
        return res;
    }

    private static int solution(int a, int b, int c) {
        Queue<Integer> heap = new PriorityQueue<>((x,y) -> y - x);
        heap.offer(a);
        heap.offer(b);
        heap.offer(c);
        int maxScore = takeStones(heap);
        return maxScore;
    }

    private static int takeStones(Queue<Integer> heap) {
        int max = heap.poll();
        if (heap.peek() == 0)
            return 0;
        int next = heap.poll();
        heap.offer(--max);
        heap.offer(--next);
        return 1 + takeStones(heap);
    }

}
