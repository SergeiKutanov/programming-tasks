package com.sergeik.arrays;

/**
 * Given n non-negative integers a1, a2, ..., an , where each represents a point at coordinate (i, ai).
 * n vertical lines are drawn such that the two endpoints of the line i is at (i, ai) and (i, 0).
 * Find two lines, which, together with the x-axis forms a container, such that the container contains the most water.
 */
public class ContainerWithMostWater {

    public static void main(String[] args) {
        assert 17 == solution(new int[]{2,3,4,5,18,17,6});
        assert 17 == bruteSolution(new int[]{2,3,4,5,18,17,6});
        assert 36 == solution(new int[]{2,3,10,5,7,8,9});
        assert 49 == solution(new int[]{1,8,6,2,5,4,8,3,7});
    }

    private static int solution(int[] height) {
        int leftW = 0;
        int rightW = height.length - 1;

        int maxArea = Math.min(height[leftW], height[rightW]) * (rightW - leftW);

        while (leftW < rightW) {
            if (height[leftW] < height[rightW]) {
                leftW++;
            } else {
                rightW--;
            }
            int area = Math.min(height[leftW], height[rightW]) * (rightW - leftW);
            if (maxArea < area) {
                maxArea = area;
            }
        }
        return maxArea;
    }

    private static int bruteSolution(int[] height) {
        int maxArea = 0;
        for (int i = 0; i < height.length - 1; i++) {
            for (int j = i; j < height.length; j++) {
                int curArea = Math.min(height[i], height[j]) * (j - i);
                if (curArea > maxArea)
                    maxArea = curArea;
            }
        }
        return maxArea;
    }

}
