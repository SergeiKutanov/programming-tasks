package com.sergeik.trees;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class ConstructTreeFromPreAndInOrderTrav {

    static int preOrderIndex = 0;

    public static void main(String[] args) {
        int[] preorder = new int[]{1,2};
        int[] inorder = new int[]{2,1};
        TreeNode res = solution(preorder, inorder);

        List<Integer> preorderRes = new LinkedList<>();
        preOrder(res, preorderRes);
        List<Integer> inorderRes = new LinkedList<>();
        inOrder(res, inorderRes);
        assert Arrays.equals(preorder, listToArr(preorderRes));
        assert Arrays.equals(inorder, listToArr(inorderRes));


        preorder = new int[]{3,9,20,15,7};
        inorder = new int[]{9,3,15,20,7};
        res = solution(preorder, inorder);

        preorderRes = new LinkedList<>();
        preOrder(res, preorderRes);
        inorderRes = new LinkedList<>();
        inOrder(res, inorderRes);
        assert Arrays.equals(preorder, listToArr(preorderRes));
        assert Arrays.equals(inorder, listToArr(inorderRes));

    }

    private static int[] listToArr(List<Integer> list) {
        int[] arr = new int[list.size()];
        for (int i = 0; i < list.size(); i++)
            arr[i] = list.get(i);
        return arr;
    }

    /**
     * In a Preorder sequence, the leftmost element is the root of the tree. So we know ‘A’ is the root
     * for given sequences. By searching ‘A’ in the Inorder sequence, we can find out all elements on the left
     * side of ‘A’ is in the left subtree, and elements on right in the right subtree. So we know the below
     * structure now.
     *
     * Algorithm: buildTree() 
     * 1) Pick an element from Preorder. Increment a Preorder Index Variable (preIndex in below code) to pick the next element in the next recursive call. 
     * 2) Create a new tree node tNode with the data as the picked element. 
     * 3) Find the picked element’s index in Inorder. Let the index be inIndex. 
     * 4) Call buildTree for elements before inIndex and make the built tree as a left subtree of tNode. 
     * 5) Call buildTree for elements after inIndex and make the built tree as a right subtree of tNode. 
     * 6) return tNode.
     *
     * @param preorder
     * @param inorder
     * @return
     */
    private static TreeNode solution(int[] preorder, int[] inorder) {
        preOrderIndex = 0;
        return buildTree(preorder, inorder, 0, inorder.length - 1);
    }

    private static TreeNode buildTree(int[] preorder, int[] inorder, int inOrderStart, int inOrderEnd) {
        if (inOrderStart > inOrderEnd)
            return null;
        TreeNode node = new TreeNode(preorder[preOrderIndex++]);
        int inOrderIndex = inOrderStart;
        while (inOrderIndex <= inOrderEnd) {
            if (inorder[inOrderIndex] == node.val)
                break;
            inOrderIndex++;
        }

        node.left = buildTree(preorder, inorder, inOrderStart, inOrderIndex - 1);
        node.right = buildTree(preorder, inorder, inOrderIndex + 1, inOrderEnd);
        return node;
    }

    private static void inOrder(TreeNode node, List<Integer> result) {
        if (node == null)
            return;
        inOrder(node.left, result);
        result.add(node.val);
        inOrder(node.right, result);
    }

    private static void preOrder(TreeNode node, List<Integer> result) {
        if (node == null)
            return;
        result.add(node.val);
        preOrder(node.left, result);
        preOrder(node.right, result);
    }

}
