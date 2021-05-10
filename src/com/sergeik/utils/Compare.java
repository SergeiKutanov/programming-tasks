package com.sergeik.utils;

import java.util.Arrays;
import java.util.List;

public class Compare {

    public static boolean compareObjArr(Object[] l1, Object[] l2) {
        if (l1.length != l2.length)
            return false;
        for (int i = 0; i < l1.length; i++) {
            Object el = l1[i];
            if (el != null && !el.equals(l2[i]))
                return false;
        }
        return true;
    }

    public static boolean compareMatrix(int[][] m1, int[][] m2) {
        if (m1.length != m2.length)
            return false;
        for (int row = 0; row < m1.length; row++) {
            if (!Arrays.equals(m1[row], m2[row]))
                return false;
        }
        return true;
    }

}
