package com.sergeik.design;

import java.util.PriorityQueue;

/**
 * The median is the middle value in an ordered integer list. If the size of the list is even,
 * there is no middle value and the median is the mean of the two middle values.
 *
 * For example, for arr = [2,3,4], the median is 3.
 * For example, for arr = [2,3], the median is (2 + 3) / 2 = 2.5.
 * Implement the MedianFinder class:
 *
 * MedianFinder() initializes the MedianFinder object.
 * void addNum(int num) adds the integer num from the data stream to the data structure.
 * double findMedian() returns the median of all elements so far. Answers within 10-5 of the actual
 * answer will be accepted.
 */
public class FindMedianFromDataStream {

    public static void main(String[] args) {
        MedianFinder medianFinder;

        medianFinder = new MedianFinder();
        medianFinder.addNum(-1);
        assert -1 == medianFinder.findMedian();
        medianFinder.addNum(-2);
        assert -1.5 == medianFinder.findMedian();
        medianFinder.addNum(-3);
        assert -2 == medianFinder.findMedian();
        medianFinder.addNum(-4);
        assert -2.5 == medianFinder.findMedian();
        medianFinder.addNum(-5);
        assert -3 == medianFinder.findMedian();


        medianFinder = new MedianFinder();
        medianFinder.addNum(1);    // arr = [1]
        medianFinder.addNum(2);    // arr = [1, 2]
        assert 1.5 == medianFinder.findMedian(); // return 1.5 (i.e., (1 + 2) / 2)
        medianFinder.addNum(3);    // arr[1, 2, 3]
        assert 2 == medianFinder.findMedian(); // return 2.0
    }


    static class MedianFinder {

        PriorityQueue<Integer> leftHeap, rightHeap;

        /** initialize your data structure here. */
        public MedianFinder() {
            rightHeap = new PriorityQueue<>();
            leftHeap = new PriorityQueue<>((a,b) -> b - a);
        }

        public void addNum(int num) {
            if (leftHeap.size() == 0) {
                leftHeap.add(num);
                return;
            }
            if (leftHeap.peek() > num)
                leftHeap.offer(num);
            else
                rightHeap.offer(num);

            if (leftHeap.size() - rightHeap.size() == 2)
                rightHeap.offer(leftHeap.poll());
            //balance heaps
            if (rightHeap.size() > leftHeap.size())
                leftHeap.offer(rightHeap.poll());

        }

        public double findMedian() {
            double res = leftHeap.peek();
            if (leftHeap.size() == rightHeap.size()) {
                res += rightHeap.peek();
                res /= 2.0;
            }
            return res;
        }
    }


}
