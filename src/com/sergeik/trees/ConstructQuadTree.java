package com.sergeik.trees;

/**
 * Given a n * n matrix grid of 0's and 1's only. We want to represent the grid with a Quad-Tree.
 *
 * Return the root of the Quad-Tree representing the grid.
 *
 * Notice that you can assign the value of a node to True or False when isLeaf is False, and both are accepted in the answer.
 *
 * A Quad-Tree is a tree data structure in which each internal node has exactly four children. Besides, each
 * node has two attributes:
 *
 * val: True if the node represents a grid of 1's or False if the node represents a grid of 0's.
 * isLeaf: True if the node is leaf node on the tree or False if the node has the four children.
 * class Node {
 *     public boolean val;
 *     public boolean isLeaf;
 *     public Node topLeft;
 *     public Node topRight;
 *     public Node bottomLeft;
 *     public Node bottomRight;
 * }
 * We can construct a Quad-Tree from a two-dimensional area using the following steps:
 *
 * If the current grid has the same value (i.e all 1's or all 0's) set isLeaf True and set val to the value of the
 * grid and set the four children to Null and stop.
 * If the current grid has different values, set isLeaf to False and set val to any value and divide the current grid
 * into four sub-grids as shown in the photo.
 * Recurse for each of the children with the proper sub-grid.
 */
public class ConstructQuadTree {

    public static void main(String[] args) {
        Node root;
        root = solution(new int[][] {
                {0,1},
                {1,0}
        });
        root = solution(new int[][] {
                {1,1,1,1,0,0,0,0},
                {1,1,1,1,0,0,0,0},
                {1,1,1,1,1,1,1,1},
                {1,1,1,1,1,1,1,1},
                {1,1,1,1,0,0,0,0},
                {1,1,1,1,0,0,0,0},
                {1,1,1,1,0,0,0,0},
                {1,1,1,1,0,0,0,0}
        });
    }

    private static Node solution(int[][] grid) {
        Node res = buildTree(grid, 0, 0, grid.length, grid[0].length);
        return res;
    }

    private static Node buildTree(int[][] grid, int leftR, int leftC, int rightR, int rightC) {
        boolean sameVal = isSame(grid, leftR, leftC, rightR, rightC);
        int val = grid[leftR][leftC];
        Node node = new Node(val == 1, sameVal);
        if (!sameVal) {
            node.topLeft = buildTree(grid, leftR, leftC, (leftR + rightR) / 2, (leftC + rightC) / 2);
            node.bottomLeft = buildTree(grid, (leftR + rightR) / 2, leftC, rightR, (leftC + rightC) / 2);
            node.topRight = buildTree(grid, leftR, (leftC + rightC) / 2, (leftR + rightR) / 2, rightC);
            node.bottomRight = buildTree(grid, (leftR + rightR) / 2, (leftC + rightC) / 2, rightR, rightC);
        }
        return node;
    }

    private static boolean isSame(int[][] grid, int leftR, int leftC, int rightR, int rightC) {
        int val = grid[leftR][leftC];
        for (int r = leftR; r < rightR; r++) {
            for (int c = leftC; c < rightC; c++) {
                if (val != grid[r][c]) {
                    return false;
                }
            }
        }
        return true;
    }

    static class Node {
        public boolean val;
        public boolean isLeaf;
        public Node topLeft;
        public Node topRight;
        public Node bottomLeft;
        public Node bottomRight;


        public Node() {
            this.val = false;
            this.isLeaf = false;
            this.topLeft = null;
            this.topRight = null;
            this.bottomLeft = null;
            this.bottomRight = null;
        }

        public Node(boolean val, boolean isLeaf) {
            this.val = val;
            this.isLeaf = isLeaf;
            this.topLeft = null;
            this.topRight = null;
            this.bottomLeft = null;
            this.bottomRight = null;
        }

        public Node(boolean val, boolean isLeaf, Node topLeft, Node topRight, Node bottomLeft, Node bottomRight) {
            this.val = val;
            this.isLeaf = isLeaf;
            this.topLeft = topLeft;
            this.topRight = topRight;
            this.bottomLeft = bottomLeft;
            this.bottomRight = bottomRight;
        }
    }

}
