package com.sergeik.arrays;

import java.util.PriorityQueue;
import java.util.Queue;

public class KthLargestElementInStream {

    public static void main(String[] args) {
        KthLargest kthLargest = new KthLargest(1, new int[]{});
        assert -3 == kthLargest.add(-3);
        assert -2 == kthLargest.add(-2);
        assert -2 == kthLargest.add(-4);
        assert 0 == kthLargest.add(0);
        assert 4 == kthLargest.add(4);
        
        kthLargest = new KthLargest(3, new int[]{4,5,8,2});
        assert 4 == kthLargest.add(3);
        assert 5 == kthLargest.add(5);
        assert 5 == kthLargest.add(10);
        assert 8 == kthLargest.add(9);
        assert 8 == kthLargest.add(4);
    }

    static class KthLargest {

        private Queue<Integer> pQueue;
        private int k;

        public KthLargest(int k, int[] nums) {
            this.k = k;
            pQueue = new PriorityQueue<>(k);
            for (int n: nums)
                add(n);
        }

        public int add(int val) {
            if (pQueue.size() < k)
                pQueue.offer(val);
            else if (pQueue.peek() < val) {
                pQueue.poll();
                pQueue.offer(val);
            }
            return pQueue.peek();
        }
    }


}
