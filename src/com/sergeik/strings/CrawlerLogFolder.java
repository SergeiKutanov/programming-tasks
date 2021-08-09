package com.sergeik.strings;

/**
 * The Leetcode file system keeps a log each time some user performs a change folder operation.
 *
 * The operations are described below:
 *
 * "../" : Move to the parent folder of the current folder. (If you are already in the main folder, remain
 * in the same folder).
 * "./" : Remain in the same folder.
 * "x/" : Move to the child folder named x (This folder is guaranteed to always exist).
 * You are given a list of strings logs where logs[i] is the operation performed by the user at the ith step.
 *
 * The file system starts in the main folder, then the operations in logs are performed.
 *
 * Return the minimum number of operations needed to go back to the main folder after the change folder operations.
 */
public class CrawlerLogFolder {

    public static void main(String[] args) {
        assert 2 == solution(new String[] {"d1/","d2/","../","d21/","./"});
    }

    private static int solution(String[] logs) {
        int level = 0;
        for (String s: logs) {
            if (s.equals("../"))
                level = Math.max(0, level - 1);
            else if (!s.equals("./"))
                level++;
        }
        return level;
    }

}
