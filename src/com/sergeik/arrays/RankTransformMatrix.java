package com.sergeik.arrays;

import javafx.util.Pair;

import java.util.*;

/**
 * Given an m x n matrix, return a new matrix answer where answer[row][col] is the rank of matrix[row][col].
 *
 * The rank is an integer that represents how large an element is compared to other elements. It is calculated
 * using the following rules:
 *
 * The rank is an integer starting from 1.
 * If two elements p and q are in the same row or column, then:
 * If p < q then rank(p) < rank(q)
 * If p == q then rank(p) == rank(q)
 * If p > q then rank(p) > rank(q)
 * The rank should be as small as possible.
 * It is guaranteed that answer is unique under the given rules.
 */
public class RankTransformMatrix {

    public static void main(String[] args) {
        int[][] exp, res;
        
//        res = solution(new int[][] {
//                {-23,   20, -49,    -30,    -39,    -28,-5, -14},
//                {-19,   4,  -33,    2,      -47,    28, 43, -6},
//                {-47,   36, -49,    6,      17,     -8, -21,-30},
//                {-27,   44, 27,     10,     21,     -8, 3,  14},
//                {-19,   12, -25,    34,     -27,    -48,-37,14},
//                {-47,   40, 23,     46,     -39,    48, -41,18},
//                {-27,   -4, 7,      -10,    9,      36, 43, 2},
//                {37,    44, 43,     -38,    29,     -44,19, 38}
//        });
//        exp = new int[][] {
//                {7,13,1,5,4,6,9,8},
//                {8,11,2,10,1,12,14,9},
//                {2,14,1,11,13,7,5,3},
//                {3,19,16,12,14,7,10,13},
//                {8,12,6,14,5,1,4,13},
//                {2,16,15,17,4,18,3,14},
//                {3,7,11,6,12,13,14,10},
//                {16,19,18,3,15,2,11,17}
//        };
//        for (int i = 0; i < exp.length; i++)
//            for (int j = 0; j < exp[i].length; j++)
//                assert exp[i][j] == res[i][j];
//
//        res = solution(new int[][] {
//                {-2,    -35,-32,    -5,     -30,    33,     -12},
//                { 7,    2,  -43,    4,      -49,    14,     17},
//                { 4,    23, -6,     -15,    -24,    -17,    6},
//                {-47,   20, 39,     -26,    9,      -44,    39},
//                {-50,   -47,44,     43,     -22,    33,     -36},
//                {-13,   34, 49,     24,     23,     -2,     -35},
//                {-40,   43,-22,     -19,    -4,     23,     -18}
//        });
//        exp = new int[][] {
//                {10,3,4,9,5,15,8},
//                {12,4,2,10,1,13,14},
//                {11,13,9,8,6,7,12},
//                {2,10,15,4,9,3,15},
//                {1,2,17,16,7,15,3},
//                {5,14,18,11,10,8,4},
//                {3,15,5,6,8,14,7}
//        };
//        for (int i = 0; i < exp.length; i++)
//            for (int j = 0; j < exp[i].length; j++)
//                assert exp[i][j] == res[i][j];

        exp = new int[][] {
                {4,2,3},
                {1,3,4},
                {5,1,6},
                {1,3,4}
        };
        res = solution(new int[][] {
                {20,-21,14},
                {-19,4,19},
                {22,-47,24},
                {-19,4,19}
        });
        for (int i = 0; i < exp.length; i++)
            for (int j = 0; j < exp[i].length; j++)
                assert exp[i][j] == res[i][j];
    }

    private static int[][] solution(int[][] matrix) {
        int n = matrix.length;
        int m = matrix[0].length;
        int[] rank = new int[n + m];
        //contains map from val to list of coordinates with the same value
        Map<Integer, List<Pair<Integer, Integer>>> invMap = new TreeMap<>();
        for (int i = 0; i < n; i++)
            for (int j = 0; j < m; j++)
                invMap.computeIfAbsent(matrix[i][j], l -> new ArrayList<>()).add(new Pair<>(i, j));

        for (int key : invMap.keySet()) {
            UF uf = new UF(n + m);
            int[] rank2 = rank.clone();
            for (Pair<Integer, Integer> coord : invMap.get(key)) {
                Pair<Integer, Integer> res = uf.union(coord.getKey(), coord.getValue() + n);
                rank2[res.getValue()] = Math.max(rank2[res.getValue()], rank2[res.getKey()]);
            }
            for (Pair<Integer, Integer> coord : invMap.get(key))
                rank[coord.getKey()] = rank[coord.getValue() + n]
                        = matrix[coord.getKey()][coord.getValue()]
                        = rank2[uf.find(coord.getKey())] + 1;
        }
        return matrix;
    }

    static class UF {
        int[] parent;
        public UF(int n) {
            parent = new int[n];
            for (int i = 0; i < n; i++) parent[i] = i;
        }
        public int find(int x) {
            return parent[x] = parent[x] == x ? x : find(parent[x]);
        }
        public Pair<Integer, Integer> union(int x, int y) {
            int px = find(x);
            int py = find(y);
            parent[px] = py;
            return new Pair<>(px, py);
        }
    }
}
