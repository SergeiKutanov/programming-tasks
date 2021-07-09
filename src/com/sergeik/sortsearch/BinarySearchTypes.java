package com.sergeik.sortsearch;

public class BinarySearchTypes {

    /**
     * With every iteration we reduce the search space by half.
     * At every step we compare mid element with the target, if mid element is not the target then we proceed to
     * the half where target if present should be at and discard the another half.
     *
     * After all the iterations when low == high, if target is present in the array then it should be present
     * at low == high == mid , if not then we can surely say that target is not present in the array.
     *
     * Note this, for the very first time when low > high in that case
     * ceil(target) = arr[low] and floor(target) = arr[high].
     * @param nums
     * @param target
     * @return
     */
    private int regular(int[] nums, int target) {
        int n = nums.length;
        int low = 0;
        int high = n - 1;

        while (low <= high) {
            int mid = (low + high) / 2; // this may cause integer overflow
            if (nums[mid] == target)
                return mid;
            else if (nums[mid] < target)
                low = mid + 1;
            else
                high = mid - 1;
        }

        return -1;
    }

    /**
     * Round up and Round down yields different result when size of search space is even, because mid in case of
     * even number of elements is not clearly defined, so round down makes left element as mid and round up makes
     * right element as mid.
     * @param nums
     * @param target
     * @return
     */
    private int roundDown(int[] nums, int target) {
        int n = nums.length;
        int low = 0;
        int high = n - 1;

        while (low <= high) {
            int mid = low + (high - low) / 2; // round down (eliminates integer overflow)
            if (nums[mid] == target)
                return mid;
            else if (nums[mid] < target)
                low = mid + 1;
            else
                high = mid - 1;
        }

        return -1;
    }

    private int roundUp(int[] nums, int target) {
        int n = nums.length;
        int low = 0;
        int high = n - 1;

        while (low <= high) {
            int mid = low + (high - low + 1) / 2; // round up (eliminates integer overflow)
            if (nums[mid] == target)
                return mid;
            else if (nums[mid] < target)
                low = mid + 1;
            else
                high = mid - 1;
        }

        return -1;
    }

    private int roundDownNoComparison(int[] nums, int target) {
        int n = nums.length;
        int low = 0;
        int high = n - 1;

        while (low < high) { // notice we do not compare element at mid with our target
            int mid = low + (high - low) / 2;
            if (nums[mid] >= target)
                high = mid;
            else
                low = mid + 1;
        }

		 /* at this point our search space has shrinked to
		only one element if current element is the target element
		then return its index else we can safely assume that element was not found*/

        return nums[low] == target ? low : -1; // low == high
    }

    private int roundUpNoComparison(int[] nums, int target) {
        int n = nums.length;
        int low = 0;
        int high = n - 1;

        while (low < high) { // notice we do not compare element at mid with our target
            int mid = low + (high - low + 1) / 2;
            if (nums[mid] <= target)
                low = mid;
            else
                high = mid - 1;
        }

        /* at this point our search space has shrinked to
		only one element if current element is the target element
		then return its index else we can safely assume that element was not found*/

        return nums[low] == target ? low : -1;  // low == high
    }

}
