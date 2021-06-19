package com.sergeik;

import java.util.LinkedList;
import java.util.List;

public class Play {


    public static void main(String[] args) {
        NumArray numArray;

        numArray = new NumArray(new int[] {1,2,3,4,5,6,7,8});
        assert 10 == numArray.sumRange(0, 3);
        assert 36 == numArray.sumRange(0, 7);
        assert 22 == numArray.sumRange(3, 6);
        numArray.update(3, 10);
        assert 16 == numArray.sumRange(0,3);
        assert 42 == numArray.sumRange(0,7);
        assert 18 == numArray.sumRange(2, 4);
        assert 31 == numArray.sumRange(2, 6);

        numArray = new NumArray(new int[] {1, 3, 5});
        numArray.sumRange(0, 2); // return 1 + 3 + 5 = 9
        numArray.update(1, 2);   // nums = [1, 2, 5]
        numArray.sumRange(0, 2); // return 1 + 2 + 5 = 8
    }

    static class NumArray {

        int[] bit;

        public NumArray(int[] nums) {
            bit = new int[nums.length + 1];
            for (int i = 0; i < nums.length; i++)
                update(i, nums[i]);
        }

        public void update(int index, int val) {
            int cur = query(index) - query(index - 1);
            int diff = val - cur;
            index++;
            for (; index < bit.length; index += index & -index)
                bit[index] += diff;
        }

        public int sumRange(int left, int right) {
            return query(right) - query(left - 1);
        }

        private int query(int index) {
            int sum = 0;
            index++;
            for (; index > 0; index -= index & -index)
                sum += bit[index];
            return sum;
        }
    }

}
