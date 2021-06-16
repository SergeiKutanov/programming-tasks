package com.sergeik.dynamic;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * Given an array of meeting time intervals intervals where intervals[i] = [starti, endi],
 * return the minimum number of conference rooms required.
 */
public class MeetingRoomsII {

    public static void main(String[] args) {
        assert 4 == solution(new int[][]{{1293,2986},{848,3846},{4284,5907},{4466,4781},{518,2918},{300,5870}});
        assert 1 == solution(new int[][]{{13,15},{1,13}});
        assert 3 == solution(new int[][] {{6,15},{13,20},{6,17}});
        assert 1 == solution(new int[][] {{7,10}, {2,4}});
        assert 2 == solution(new int[][] {{0,30}, {5,10},{15,20}});
    }

    private static int solution(int[][] intervals) {
        Arrays.sort(intervals, (a, b) -> a[0] - b[0]);
        PriorityQueue<int[]> roomQueue = new PriorityQueue<>((a, b) -> a[1] - b[1]);
        int res = 0, available = 0;
        for (int i = 0; i < intervals.length; i++) {
            while (!roomQueue.isEmpty() && roomQueue.peek()[1] <= intervals[i][0]) {
                available++;
                roomQueue.poll();
            }
            if (available == 0)
                res++;
            else
                available--;
            roomQueue.offer(intervals[i]);
        }
        return res;
    }

}
