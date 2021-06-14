package com.sergeik.strings;

import java.util.Arrays;
import java.util.Comparator;

/**
 * You are given an array of logs. Each log is a space-delimited string of words, where the first
 * word is the identifier.
 *
 * There are two types of logs:
 *
 * Letter-logs: All words (except the identifier) consist of lowercase English letters.
 * Digit-logs: All words (except the identifier) consist of digits.
 * Reorder these logs so that:
 *
 * The letter-logs come before all digit-logs.
 * The letter-logs are sorted lexicographically by their contents. If their contents are the same,
 * then sort them lexicographically by their identifiers.
 * The digit-logs maintain their relative ordering.
 * Return the final order of the logs.
 */
public class ReorderDataInLogFiles {

    public static void main(String[] args) {
        assert Arrays.equals(
                new String[] {"g1 act car","a8 act zoo","ab1 off key dog","a1 9 2 3 1","zo4 4 7"},
                solution(new String[] {"a1 9 2 3 1","g1 act car","zo4 4 7","ab1 off key dog","a8 act zoo"})
        );
        assert Arrays.equals(
                new String[]{"let1 art can","let3 art zero","let2 own kit dig","dig1 8 1 5 1","dig2 3 6"},
                solution(new String[]{"dig1 8 1 5 1","let1 art can","dig2 3 6","let2 own kit dig","let3 art zero"})
        );
    }

    private static String[] solution(String[] logs) {
        Arrays.sort(logs, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                //check type
                char firstChar = o1.charAt(o1.indexOf(' ') + 1);
                boolean o1DigitType = firstChar >= '0' && firstChar <= '9';
                firstChar = o2.charAt(o2.indexOf(' ') + 1);
                boolean o2DigitType = firstChar >= '0' && firstChar <= '9';
                //first case letter logs come before digit ones
                if (!o1DigitType && o2DigitType)
                    return -1;
                if (!o2DigitType && o1DigitType)
                    return 1;
                //second case lexicograph
                if (!o1DigitType && !o2DigitType) {
                    int compareResult = o1.substring(o1.indexOf(' ') + 1).compareTo(o2.substring(o2.indexOf(' ') + 1));
                    if (compareResult != 0)
                        return compareResult;
                    return o1.compareTo(o2);
                }
                return 0;
            }
        });
        return logs;
    }

}
