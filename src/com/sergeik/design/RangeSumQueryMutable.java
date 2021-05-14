package com.sergeik.design;

/**
 * Given an integer array nums, handle multiple queries of the following types:
 *
 * Update the value of an element in nums.
 * Calculate the sum of the elements of nums between indices left and right inclusive where left <= right.
 * Implement the NumArray class:
 *
 * NumArray(int[] nums) Initializes the object with the integer array nums.
 * void update(int index, int val) Updates the value of nums[index] to be val.
 * int sumRange(int left, int right) Returns the sum of the elements of nums between indices left and
 * right inclusive (i.e. nums[left] + nums[left + 1] + ... + nums[right]).
 */
public class RangeSumQueryMutable {

    public static void main(String[] args) {
        NumArray numArray = new NumArray(new int[]{1,3,5});
        assert 9 == numArray.sumRange(0, 2);
        numArray.update(1,2);
        assert 8 == numArray.sumRange(0, 2);
        numArray.update(0, -5);
        assert -5 == numArray.sumRange(0, 0);
        assert -3 == numArray.sumRange(0, 1);
        assert 2 == numArray.sumRange(0, 2);
    }

    static class NumArray {

        private int[] bit;
        private int[] nums;

        public NumArray(int[] nums) {
            this.nums = nums;
            bit = new int[nums.length + 1];
            for (int i = 0; i < nums.length; i++)
                bit[i + 1] = nums[i];
            for (int i = 1; i < bit.length; i++) {
                int p = i + (i & -i); //index to parent
                if (p < bit.length)
                    bit[p] = bit[p] + bit[i];
            }
        }

        public void update(int index, int val) {
            int diff = val - nums[index];
            nums[index] = val;
            index++;
            while (index < bit.length) {
                bit[index] += diff;
                index += index & -index;
            }
        }

        public int sumRange(int left, int right) {
            int res = sum(right) - sum(left - 1);
            return res;
        }

        private int sum(int i) {
            int sum = 0;
            i++;
            while (i > 0) {
                sum += bit[i];
                i -= i & -i;
            }
            return sum;
        }
    }

}
