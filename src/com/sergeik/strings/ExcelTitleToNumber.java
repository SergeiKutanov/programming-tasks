package com.sergeik.strings;

public class ExcelTitleToNumber {

    /**
     * Given a string columnTitle that represents the column title as appear in an Excel sheet,
     * return its corresponding column number.
     *
     * @param args
     */
    public static void main(String[] args) {
        assert 1 == solution("A");
        assert 28 == solution("AB");
        assert 701 == solution("ZY");
        assert 2147483647 == solution("FXSHRXW");
    }

    private static int solution(String columnTitle) {
        int num = 0;
        for (char ch: columnTitle.toCharArray()) {
            num *= 26;
            num += (ch - 'A' + 1);
        }
        return num;
    }

}
