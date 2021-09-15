package com.sergeik.strings;

import java.util.Stack;

public class ReverseOnlyLetters {

    public static void main(String[] args) {
        assert "Qedo1ct-eeLg=ntse-T!".equals(reversePointerSolution("Test1ng-Leet=code-Q!"));
        assert "j-Ih-gfE-dCba".equals(reversePointerSolution("a-bC-dEf-ghIj"));
    }

    private static String reversePointerSolution(String s) {
        StringBuilder res = new StringBuilder();
        int pointer = s.length() - 1;
        for (int i = 0; i < s.length(); i++) {
            if (Character.isLetter(s.charAt(i))) {
                while (!Character.isLetter(s.charAt(pointer)))
                    pointer--;
                res.append(s.charAt(pointer--));
            } else {
                res.append(s.charAt(i));
            }
        }
        return res.toString();
    }

    private static String stackSolution(String s) {
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            if (Character.isLetter(s.charAt(i)))
                stack.push(s.charAt(i));
        }
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            if (Character.isLetter(s.charAt(i)))
                res.append(stack.pop());
            else
                res.append(s.charAt(i));
        }
        return res.toString();
    }

    private static String sbSolution(String s) {
        StringBuilder res = new StringBuilder(s);
        int left = 0, right = res.length() - 1;
        while (left < right && !Character.isLetter(res.charAt(left)))
            left++;
        while (right > left && !Character.isLetter(res.charAt(right)))
            right--;
        while (left < right) {
            char tmp = res.charAt(left);
            res.setCharAt(left++, res.charAt(right));
            res.setCharAt(right--, tmp);
            while (left < right && !Character.isLetter(res.charAt(left)))
                left++;
            while (right > left && !Character.isLetter(res.charAt(right)))
                right--;
        }
        return res.toString();
    }

    private static String solution(String s) {
        char[] res = s.toCharArray();
        int left = 0, right = s.length() - 1;
        while (left < right && !isLetter(res[left]))
            left++;
        while (right > left && !isLetter(res[right]))
            right--;
        while (left < right) {
            char tmp = res[left];
            res[left] = res[right];
            res[right] = tmp;
            left++; right--;
            while (left < right && !isLetter(res[left]))
                left++;
            while (right > left && !isLetter(res[right]))
                right--;
        }
        return new String(res);
    }

    private static boolean isLetter(char ch) {
        return (ch >= 'a' && ch <= 'z') || (ch >= 'A' && ch <= 'Z');
    }

}
