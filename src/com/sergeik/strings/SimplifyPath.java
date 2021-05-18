package com.sergeik.strings;

import java.util.Stack;

/**
 * Given a string path, which is an absolute path (starting with a slash '/') to a file or directory 
 * in a Unix-style file system, convert it to the simplified canonical path.
 *
 * In a Unix-style file system, a period '.' refers to the current directory, a double period '..' refers 
 * to the directory up a level, and any multiple consecutive slashes (i.e. '//') are treated as a single slash '/'.
 * For this problem, any other format of periods such as '...' are treated as file/directory names.
 *
 * The canonical path should have the following format:
 *
 * The path starts with a single slash '/'.
 * Any two directories are separated by a single slash '/'.
 * The path does not end with a trailing '/'.
 * The path only contains the directories on the path from the root directory to the target file 
 * or directory (i.e., no period '.' or double period '..')
 * Return the simplified canonical path.
 */
public class SimplifyPath {

    public static void main(String[] args) {
        assert "/".equals(solution("/../"));
        assert "/home".equals(solution("/home/"));
        assert "/home/foo".equals(solution("/home//foo/"));
        assert "/c".equals(solution("/a/./b/../../c/"));
    }

    private static String solution(String path) {
        Stack<String> stack = new Stack<>();
        int i = 0;
        while (i < path.length()) {
            StringBuilder sb = new StringBuilder();
            while (i < path.length() && path.charAt(i) != '/') {
                sb.append(path.charAt(i));
                i++;
            }
            if (sb.length() == 0) {
                i++;
                continue;
            }

            String segment = sb.toString();
            if (segment.equals(".")) {
                i++;
                continue;
            } else if (segment.equals("..")) {
                if (!stack.isEmpty())
                    stack.pop();
            } else {
                stack.push(segment);
            }
            i++;
        }

        StringBuilder simplePath = new StringBuilder();
        while (!stack.isEmpty()) {
            simplePath.insert(0, stack.pop());
            simplePath.insert(0, "/");
        }
        return simplePath.length() == 0 ? "/" : simplePath.toString();
    }

}
