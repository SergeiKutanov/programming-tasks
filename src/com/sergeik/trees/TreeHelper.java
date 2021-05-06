package com.sergeik.trees;

import java.util.ArrayList;
import java.util.List;

public class TreeHelper {

    public static void toArray(TreeNode root, int level, Object[] res) {
        if (root != null) {
            res[level] = root.val;
            toArray(root.left, 2 * level + 1, res);
            toArray(root.right, 2 * level + 2, res);
        }
    }

}
