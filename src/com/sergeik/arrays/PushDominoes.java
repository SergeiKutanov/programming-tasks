package com.sergeik.arrays;

/**
 * There are n dominoes in a line, and we place each domino vertically upright. In the beginning, we simultaneously
 * push some of the dominoes either to the left or to the right.
 *
 * After each second, each domino that is falling to the left pushes the adjacent domino on the left. Similarly,
 * the dominoes falling to the right push their adjacent dominoes standing on the right.
 *
 * When a vertical domino has dominoes falling on it from both sides, it stays still due to the balance of the forces.
 *
 * For the purposes of this question, we will consider that a falling domino expends no additional force to a falling
 * or already fallen domino.
 *
 * You are given a string dominoes representing the initial state where:
 *
 * dominoes[i] = 'L', if the ith domino has been pushed to the left,
 * dominoes[i] = 'R', if the ith domino has been pushed to the right, and
 * dominoes[i] = '.', if the ith domino has not been pushed.
 * Return a string representing the final state.
 */
public class PushDominoes {

    public static void main(String[] args) {
        assert "LL.RR.LLRRLL..".equals(forcesSolution(".L.R...LR..L.."));
        assert "LL.RR.LLRRLL..".equals(adjacentSymbolsSolution(".L.R...LR..L.."));
    }

    private static String adjacentSymbolsSolution(String dominoes) {
        int n = dominoes.length();
        int[] indexes = new int[n + 2];
        char[] symbols = new char[n + 2];
        int len = 1;
        indexes[0] = -1;
        symbols[0] = 'L';

        for (int i = 0; i < n; i++) {
            if (dominoes.charAt(i) != '.') {
                indexes[len] = i;
                symbols[len++] = dominoes.charAt(i);
            }
        }

        indexes[len] = n;
        symbols[len++] = 'R';

        char[] ans = dominoes.toCharArray();
        for (int idx = 0; idx < len - 1; idx++) {
            int i = indexes[idx], j = indexes[idx + 1];
            char x = symbols[idx], y = symbols[idx + 1];

            if (x == y) {
                for (int k = i + 1; k < j; k++)
                    ans[k] = x;
            } else if (x > y) { //RL
                for (int k = i + 1; k < j; k++)
                    ans[k] = k - i == j - k ? '.' : k - i < j - k ? 'R' : 'L';
            }
        }
        return String.valueOf(ans);
    }

    private static String forcesSolution(String dominoes) {
        char[] a = dominoes.toCharArray();
        int n = a.length;
        int[] forces = new int[n];

        //forces left to right
        int force = 0;
        for (int i = 0; i < n; i++) {
            if (a[i] == 'R')
                force = n;
            else if (a[i] == 'L')
                force = 0;
            else
                force = Math.max(force - 1, 0);
            forces[i] += force;
        }

        //forces right to left
        force = 0;
        for (int i = n - 1; i >= 0; i--) {
            if (a[i] == 'L')
                force = n;
            else if (a[i] == 'R')
                force = 0;
            else
                force = Math.max(force - 1, 0);
            forces[i] -= force;
        }

        StringBuilder res = new StringBuilder();
        for (int f: forces)
            res.append(f > 0 ? 'R' : f < 0 ? 'L' : '.');
        return res.toString();
    }

}
