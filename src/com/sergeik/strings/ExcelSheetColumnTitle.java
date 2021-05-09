package com.sergeik.strings;

/**
 * Given an integer columnNumber, return its corresponding column title as it appears in an Excel sheet.
 */
public class ExcelSheetColumnTitle {

    public static void main(String[] args) {
        assert "A".equals(solution(1));
        assert "AB".equals(solution(28));
        assert "ZY".equals(solution(701));
        assert "FXSHRXW".equals(solution(2147483647));
    }

    private static String solution(int columnNumber) {
        StringBuilder sb = new StringBuilder();
        while(columnNumber > 0) {
            columnNumber--; //to handle 26 case
            sb.insert(0, (char)('A' + columnNumber % 26));
            columnNumber /= 26;
        }
        return sb.toString();
    }

}
