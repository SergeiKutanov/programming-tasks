package com.sergeik.strings;

/**
 * Convert a non-negative integer num to its English words representation.
 */
public class IntegerToEnglishWords {

    public static void main(String[] args) {
        assert "One Hundred".equals(solution(100));
        assert "One Hundred Thousand".equals(solution(100000));
        assert "Fifty Thousand Eight Hundred Sixty Eight".equals(solution(50868));
        assert "One Thousand".equals(solution(1000));
        assert "Twenty".equals(solution(20));
        assert "Zero".equals(solution(0));
        assert "One Billion Two Hundred Thirty Four Million Five Hundred Sixty Seven Thousand Eight Hundred Ninety One"
                .equals(solution(1234567891));
        assert "One Million Two Hundred Thirty Four Thousand Five Hundred Sixty Seven".equals(solution(1234567));
        assert "One Hundred Twenty Three".equals(solution(123));
        assert "Twelve Thousand Three Hundred Forty Five".equals(solution(12345));
    }

    private static String solution(int num) {
        if (num == 0)
            return "Zero";
        String[] suffixes = new String[] {"", " Thousand ", " Million ", " Billion "};
        int n, m;
        int suffix = 0;
        StringBuilder res = new StringBuilder();
        while (num > 0) {
            n = 0; m = 1;
            for (int i = 0; i < 3; i++) {
                n += num % 10 * m;
                m *= 10;
                num /= 10;
            }
            res.insert(0, getText(n, suffixes[suffix++]));
        }
        return res.toString().trim();
    }

    private static String getText(int n, String suffix) {
        if (n == 0)
            return "";
        String[] words = new String[] {
                "Zero", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine", "Ten",
                "Eleven", "Twelve", "Thirteen", "Fourteen", "Fifteen", "Sixteen", "Seventeen", "Eighteen", "Nineteen",
                "Twenty", "Thirty", "Forty", "Fifty", "Sixty", "Seventy", "Eighty", "Ninety"
        };
        StringBuilder res = new StringBuilder(suffix);

        int lower = n % 10;
        n /= 10;
        int mid = n % 10;
        n /= 10;
        int high = n;
        if (lower > 0 && mid > 1) {
            res.insert(0, words[lower]);
        } else if (lower >= 0 && mid == 1) {
            res.insert(0, words[mid + lower + 9]);
        } else if (lower > 0) {
            res.insert(0, words[lower]);
        }
        if (mid > 1) {
            if (lower > 0)
                res.insert(0, " ");
            res.insert(0, words[mid + 18]);
        }
        if (high > 0) {
            if (res.length() > 0 && res.charAt(0) != ' ')
                res.insert(0, ' ');
            res.insert(0, " Hundred");
            res.insert(0, words[high]);
        }

        return res.toString();
    }

}
