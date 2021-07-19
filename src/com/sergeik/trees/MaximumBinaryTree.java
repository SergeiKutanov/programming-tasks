package com.sergeik.trees;

import java.util.PriorityQueue;

public class MaximumBinaryTree {

    public static void main(String[] args) {
        TreeNode root = solution(new int[] {3,2,1,6,0,5});
    }

    /**
     * TODO change
     * @param nums
     * @return
     */
    private static TreeNode solution(int[] nums) {
        PriorityQueue<Integer> heap = new PriorityQueue<>((a,b) -> nums[b] - nums[a]);
        for (int i = 0; i < nums.length; i++)
            heap.offer(i);
        int prevIdx = heap.poll();
        TreeNode root = new TreeNode(nums[prevIdx]);
        TreeNode node = root;
        while (!heap.isEmpty()) {
            int idx = heap.poll();
            if (idx < prevIdx) {
                node.left = new TreeNode(nums[idx]);
            } else {
                node.right = new TreeNode(nums[idx]);
            }
        }
        return root;
    }

}
