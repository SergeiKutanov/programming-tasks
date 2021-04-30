package com.sergeik.strings;

import java.util.ArrayList;
import java.util.List;

/**
 * The string "PAYPALISHIRING" is written in a zigzag pattern on a given number of rows like this:
 * (you may want to display this pattern in a fixed font for better legibility)
 *
 * P   A   H   N
 * A P L S I I G
 * Y   I   R
 *
 *
 * Input: s = "PAYPALISHIRING", numRows = 4
 * Output: "PINALSIGYAHRPI"
 * Explanation:
 * P     I    N
 * A   L S  I G
 * Y A   H R
 * P     I
 */
public class ZigZagConversion {

    public static void main(String[] args) {
        assert "ABCED".equals(solution("ABCDE", 4));
        assert "Aiosrhem,tseoihartaaeeriwgrlasasnoctaoieplnrmiaodprs,ubroohreunefnttacneedhsmwynihrieto,iheeaalwnefrdutettpntainnwrdvdr.adew,anereqcustbaeeitdcntnlocojmsuuoddis"
                .equals(solution(
                        "Apalindromeisaword,phrase,number,orothersequenceofunitsthatcanbereadthesamewayineitherdirection,withgeneralallowancesforadjustmentstopunctuationandworddividers.",
                        3
                ));
        assert "AB".equals(solution("AB", 1));
        assert "PINALSIGYAHRPI".equals(solution("PAYPALISHIRING", 4));
        assert "PAHNAPLSIIGYIR".equals(solution("PAYPALISHIRING", 3));
        assert "A".equals(solution("A", 1));


        assert "ABCED".equals(sortByRowSolution("ABCDE", 4));
        assert "Aiosrhem,tseoihartaaeeriwgrlasasnoctaoieplnrmiaodprs,ubroohreunefnttacneedhsmwynihrieto,iheeaalwnefrdutettpntainnwrdvdr.adew,anereqcustbaeeitdcntnlocojmsuuoddis"
                .equals(sortByRowSolution(
                        "Apalindromeisaword,phrase,number,orothersequenceofunitsthatcanbereadthesamewayineitherdirection,withgeneralallowancesforadjustmentstopunctuationandworddividers.",
                        3
                ));
        assert "AB".equals(sortByRowSolution("AB", 1));
        assert "PINALSIGYAHRPI".equals(sortByRowSolution("PAYPALISHIRING", 4));
        assert "PAHNAPLSIIGYIR".equals(sortByRowSolution("PAYPALISHIRING", 3));
        assert "A".equals(sortByRowSolution("A", 1));

        assert "ABCED".equals(visitByRowSolution("ABCDE", 4));
        assert "Aiosrhem,tseoihartaaeeriwgrlasasnoctaoieplnrmiaodprs,ubroohreunefnttacneedhsmwynihrieto,iheeaalwnefrdutettpntainnwrdvdr.adew,anereqcustbaeeitdcntnlocojmsuuoddis"
                .equals(visitByRowSolution(
                        "Apalindromeisaword,phrase,number,orothersequenceofunitsthatcanbereadthesamewayineitherdirection,withgeneralallowancesforadjustmentstopunctuationandworddividers.",
                        3
                ));
        assert "AB".equals(visitByRowSolution("AB", 1));
        assert "PINALSIGYAHRPI".equals(visitByRowSolution("PAYPALISHIRING", 4));
        assert "PAHNAPLSIIGYIR".equals(visitByRowSolution("PAYPALISHIRING", 3));
        assert "A".equals(visitByRowSolution("A", 1));
    }

    private static String solution(String s, int numRows) {

        if (numRows == 1)
            return s;

        int chunk = 2 * numRows - 2;
        int chunks = s.length() / numRows;
        char[][] matrix = new char[numRows][chunks + (chunk - numRows) * chunks + 1];

        int row;
        int col = 0;

        int charIndex = 0;
        while (charIndex < s.length()) {
            //go down
            row = -1;
            for (int i = 0; i < numRows; i++) {
                row++;
                if (charIndex < s.length()) {
                    matrix[row][col] = s.charAt(charIndex++);
                }
            }
            //go up in diagonal
            for (int i = 1; i < numRows - 1; i++) {
                row -= 1;
                col = col + 1;
                if (charIndex < s.length()) {
                    matrix[row][col] = s.charAt(charIndex++);
                }
            }
            col++;
        }

        //build the word
        StringBuilder stringBuilder = new StringBuilder();
        for (int rowI = 0; rowI < matrix.length; rowI++) {
            for (int colJ = 0; colJ < matrix[rowI].length; colJ++) {
                if (matrix[rowI][colJ] > 0) {
                    stringBuilder.append(matrix[rowI][colJ]);
                }
            }
        }

        return stringBuilder.toString();
    }

    private static String sortByRowSolution(String s, int numRows) {
        if (numRows == 1)
            return s;

        List<StringBuilder> rows = new ArrayList<>();
        for (int i = 0; i < Math.min(numRows, s.length()); i++) {
            rows.add(new StringBuilder());
        }

        int currentRow = 0;
        boolean goingDown = false;

        for (char ch: s.toCharArray()) {
            rows.get(currentRow).append(ch);
            if (currentRow == 0 || currentRow == (numRows - 1))
                goingDown = !goingDown;
            currentRow += goingDown ? 1 : -1;
        }

        StringBuilder result = new StringBuilder();
        for (StringBuilder row: rows) {
            result.append(row);
        }
        return result.toString();
    }

    private static String visitByRowSolution(String s, int numRows) {
        if (numRows == 1)
            return s;

        StringBuilder result = new StringBuilder();
        int cycleLength = 2 * numRows - 2;
        int length = s.length();

        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j + i < length; j += cycleLength) {
                result.append(s.charAt(j + i));
                if (i != 0 && i != numRows - 1 && j + cycleLength - i < length) {
                    result.append(s.charAt(j + cycleLength - i));
                }
            }
        }
        return result.toString();
    }


}
