package com.sergeik.strings;

/**
 * Given two version numbers, version1 and version2, compare them.
 *
 * Version numbers consist of one or more revisions joined by a dot '.'. Each revision consists of digits
 * and may contain leading zeros. Every revision contains at least one character. Revisions are 0-indexed from
 * left to right, with the leftmost revision being revision 0, the next revision being revision 1, and so on.
 * For example 2.5.33 and 0.1 are valid version numbers.
 *
 * To compare version numbers, compare their revisions in left-to-right order. Revisions are compared using
 * their integer value ignoring any leading zeros. This means that revisions 1 and 001 are considered equal.
 * If a version number does not specify a revision at an index, then treat the revision as 0. For example,
 * version 1.0 is less than version 1.1 because their revision 0s are the same, but their revision
 * 1s are 0 and 1 respectively, and 0 < 1.
 *
 * Return the following:
 *
 * If version1 < version2, return -1.
 * If version1 > version2, return 1.
 * Otherwise, return 0.
 */
public class CompareVersionNumbers {

    public static void main(String[] args) {
        assert -1 == solution("1", "1.1");
        assert 0 == solution("1.01", "1.001");
        assert 0 == solution("1.0", "1.0.0");
        assert -1 == solution("0.1", "1.1");
        assert 1 == solution("1.0.1", "1");
        assert -1 == solution("7.5.2.4", "7.5.3");
    }

    private static int solution(String version1, String version2) {
        String[] rev1 = version1.split("\\.");
        String[] rev2 = version2.split("\\.");

        int idx1 = 0, idx2 = 0, res = 0;
        while (idx1 < rev1.length && idx2 < rev2.length && res == 0) {
            res = compare(rev1[idx1++], rev2[idx2++]);
        }

        while (idx1 < rev1.length && res == 0)
            res = compare(rev1[idx1++], "0");
        while (idx2 < rev2.length && res == 0)
            res = compare("0", rev2[idx2++]);

        return res;
    }

    private static int compare(String rev1, String rev2) {
        int rev1Int = Integer.valueOf(rev1);
        int rev2Int = Integer.valueOf(rev2);
        if (rev1Int > rev2Int)
            return 1;
        if (rev1Int < rev2Int)
            return -1;
        return 0;
    }

}
