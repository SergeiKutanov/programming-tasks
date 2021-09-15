package com.sergeik.strings;

public class ReverseOnlyLetters {

    public static void main(String[] args) {
        assert "Qedo1ct-eeLg=ntse-T!".equals(sbSolution("Test1ng-Leet=code-Q!"));
        assert "j-Ih-gfE-dCba".equals(sbSolution("a-bC-dEf-ghIj"));
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
