package com.sergeik;


import com.sergeik.trees.TreeNode;

import java.util.*;

public class Play {

    public static void main(String[] args) {

        long start = System.currentTimeMillis();
         int[] arr = new int[100000000];
         int x = 1;
         for (int i = 0; i < arr.length - 1; i++) {
             for (int j = i + 1; j < arr.length; j++) {
                 x += arr[i] * arr[j];
             }
         }
        System.out.println(System.currentTimeMillis() - start);

    }
}
